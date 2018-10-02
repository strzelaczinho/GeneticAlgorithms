package ALGORYTM_GENETYCZNY2;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 *
 * @author adam
 */
public class GeneticAlgorithm {
    private Data data;
    
    public Popullation evolve(Popullation population) {
        return mutatePopulation(crossoverPopulation(population)); // zwraca zmutowana Populacje poprzez skrzyzowane obiekty
                }
    
    public GeneticAlgorithm(Data data ) {
        this.data = data;
    }
    
    Popullation crossoverPopulation(Popullation population){ // krzyzowanie sie Populacji
        Popullation crossoverPopulation = new Popullation(population.getSchedules().size(), data); // rozmiar listy oraz data jako obiekty
        IntStream.range(0, Driver.NUMB_OF_ELITE_SCHEDULES).forEach(x -> crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x)));
        // powyzej wybieram NUMB_OF_ELITE_SCHEDULES tylko 1 obiekt ELITE wydaje sie najlepszy
        
        // dla pozostalych obiektow populacji od 1 po wszystkich pozostalych Schedules w populacji
        IntStream.range(Driver.NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(x -> {
            if (Driver.CROSSOVER_RATE > Math.random()){ // randomly wybierz z Crossover  . Jesli wartosc wylosowana jest mniejsza od CrossOverrate. KRZYZUJ! 
                Schedule schedule1 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0); // pobieramy obiekt populacji Schedule1 najelepszy po sortowaniu
                Schedule schedule2 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0); // pobieramy obiekt populacji Schedule2 najlepszy po sortowaniu
                
                crossoverPopulation.getSchedules().set(x, crossoverSchedule(schedule1, schedule2)); // ustaw na liscie obiekt skrzyzowany
                
            }
            else crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x)); // jesli wartosc wylosowana jest wieksza , przepisz pozostale obiekty
        });
        return crossoverPopulation;
    }
    
    Schedule crossoverSchedule(Schedule shedule1, Schedule schedule2){ // krzzowanie sie obiektow Schedule. return crossover Schedule
        Schedule crossoverShedule = new Schedule(data).initialize(); // tworze populacje do krzyzowania 
        IntStream.range(0, crossoverShedule.getClasses().size()).forEach(x -> {
        if (Math.random() > 0.5) crossoverShedule.getClasses().set(x, shedule1.getClasses().get(x)); // krzyzuj losowo dwa obiekty ze soba 0.5 i wiecej krzyzuj 
        else crossoverShedule.getClasses().set(x, schedule2.getClasses().get(x)); // mniej niz 0.5 przepisz 
        });
        return crossoverShedule;
    }
    Popullation mutatePopulation(Popullation population){ // mutacja
        Popullation mutatePopullation = new Popullation(population.getSchedules().size(), data);
        ArrayList<Schedule> schedules = mutatePopullation.getSchedules();
        IntStream.range(0, Driver.NUMB_OF_ELITE_SCHEDULES).forEach(x -> schedules.set(x, population.getSchedules().get(x))); // przesuwam Elite Schedule z oryginalnej Populacji do nowej
        IntStream.range(Driver.NUMB_OF_ELITE_SCHEDULES,population.getSchedules().size()).forEach(x -> { // dokonuje przypisania  pozostaych obiektow
            schedules.set(x, mutateSchedule(population.getSchedules().get(x))); // i ustawiam zmutowane na liscie korzystajac z funcji ponizej  Schedule mutateSchedule(Schedule mutateSchedule)
        });
        return mutatePopullation;
    }
    Schedule mutateSchedule(Schedule mutateSchedule)
    {
        Schedule schedule = new Schedule(data).initialize(); // tworzymy obiekt
        IntStream.range(0, mutateSchedule.getClasses().size()).forEach(x -> {
            if(Driver.MUTATION_RATE > Math.random()) mutateSchedule.getClasses().set(x, schedule.getClasses().get(x)); // sprawdza mutation rate  i dokonuje mutacji 
            // jesli zmienna wylosowana jest wieksza mutacja nie zachodzi 
        });
        
        return mutateSchedule;
    }
    Popullation selectTournamentPopulation(Popullation population){ // select 2 schedules to crossover 
        Popullation tournamentPopulation = new Popullation(Driver.TOURNAMENT_SELECTION_SIZE, data); //size 3 i przypiszemy data
        // wybieramy 3 Schedules randomowo
        IntStream.range(0, Driver.TOURNAMENT_SELECTION_SIZE).forEach(x -> { // selection size 3 czyli 3 obiekty
        tournamentPopulation.getSchedules().set(x, population.getSchedules().get((int) Math.random() * population.getSchedules().size()));//losujemy wartosci z listy obiektow aby przypisac
        
        });
        return tournamentPopulation;
    }
}
