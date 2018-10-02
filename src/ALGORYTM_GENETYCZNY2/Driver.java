
package ALGORYTM_GENETYCZNY2;

import java.util.ArrayList;

public class Driver {
    public static final int POPULATION_SIZE = 9;  // rozmiar Populacji
    public static final double MUTATION_RATE = 0.1; // 
    public static final double CROSSOVER_RATE = 0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 3; 
    public static final int NUMB_OF_ELITE_SCHEDULES = 1; 
    private Data data;
    private int ScheduleNumb = 0;
    private int classNumb = 1;
    public static void main(String[]args)
    {
        Driver driver = new Driver();
        driver.data = new Data(); // data intialize
        int generationNumber = 0;
        driver.printAvailableData();
        System.out.println(" Generacja = "+generationNumber);
        System.out.print(" Terminarz                                                         ");
        System.out.print("Klasy [departament,klasa,pokoj,instruktor,czas-spotkania]      ");
        System.out.println("                                         |  Fitness | Konflikty ");
        System.out.print("-----------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
        Popullation population = new Popullation(Driver.POPULATION_SIZE, driver.data).sortByFitness();
        population.getSchedules().forEach(schedule -> System.out.println("        "+driver.ScheduleNumb++ +"       | "+schedule+"  |  "
        +String.format("%.5f", schedule.getFitness()) + " | "+schedule.getNumbOfConflitcs()));
        
        driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
        while(population.getSchedules().get(0).getFitness() != 1.0)
        {
            System.out.println(" Generacja = "+ ++generationNumber);
            System.out.print(" Terminarz                                                         ");
            System.out.print("Klasy [departament,klasa,pokoj,instruktor,czas-spotkania]      ");
            System.out.println("                                         |  Fitness | Konflikty ");
            System.out.print("-----------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------------------------");
            population = geneticAlgorithm.evolve(population).sortByFitness();
            driver.ScheduleNumb = 0;
            population.getSchedules().forEach(schedule -> System.out.println("        "+driver.ScheduleNumb++ +"       | "+schedule+"  |  "
            +String.format("%.5f", schedule.getFitness()) + " | "+schedule.getNumbOfConflitcs()));
            driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
            driver.classNumb = 1;
             
        }
        
    }
    private void printAvailableData()
    {
        System.out.println("Dostepne Departamenty ==>");
        data.getDepts().forEach(x -> System.out.println("Nazwa : "+x.getName()+ ", kursy "+x.getCourses()));
        System.out.println("\nDostepne Kursy ==>");
        data.getCourses().forEach(x -> System.out.println("Kurs #: "+x.getNumber()+", nazwa "+x.getName()+", maksymalna  ilosc studentow "+
                x.getMaxNumbOfStudents()+", instruktorzy "+x.getInstructors()));
        
        System.out.println("\nDostepne Pokoje ==>");
        data.getRooms().forEach(x -> System.out.println("Pokoj #: "+x.getNumber()+", maksymalnie pomiesci: "+x.getSeatingCapacity()+" osob"));
        System.out.println("\nDostepni Instructorzy ==>");
        data.getInstructors().forEach(x -> System.out.println("id: "+x.getId()+", nazwa "+x.getName()));
        System.out.println("\nDostepne godziny spotkania MEETING TIMES ==>");
        data.getMeetingTimes().forEach(x -> System.out.println("id: "+x.getId()+", Czas spotkania "+x.getTime()));
        System.out.print("--------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------");
    }
    
    
    private void printScheduleAsTable(Schedule schedule, int generation)
    {
        ArrayList<Class> classes = schedule.getClasses();
        System.out.print("\n                         ");
        System.out.println("Klasa # | Departament | Kurs (numer, maksymalna ilosc studentow) | Pokoj (pomiesci) | Instructor (Id)     |    Czas Spotkania (Id)");
        System.out.print("                        ");
        System.out.print("-------------------------------------------------------------");
         System.out.println("----------------------------------------------------------------------");
         classes.forEach(x -> {
         int majorIndex = data.getDepts().indexOf(x.getDept());
         int coursesIndex = data.getCourses().indexOf(x.getCourse());
         int roomsIndex = data.getRooms().indexOf(x.getRoom());
         int instructorIndex = data.getInstructors().indexOf(x.getInstructor());
         int meetingTimeIndex = data.getMeetingTimes().indexOf(x.getMeetingTime());
         
         System.out.print("                          ");
         System.out.print(String.format("  %1$02d  ", classNumb) + " |   ");
         System.out.print(String.format("%1$6s", data.getDepts().get(majorIndex).getName()+ "      |   "));
         System.out.print(String.format("%1$41s", data.getCourses().get(coursesIndex).getName()+ " ("+data.getCourses().get(coursesIndex).getNumber()+", "
         +x.getCourse().getMaxNumbOfStudents()+")                   | "));
         System.out.print(String.format("%1$19s", data.getRooms().get(roomsIndex).getNumber()+" ("+x.getRoom().getSeatingCapacity()+")      | "));
         System.out.print(String.format("%1$22s", data.getInstructors().get(instructorIndex).getName()+" ("+data.getInstructors().get(instructorIndex).getId()+")"+"  |   "));
         System.out.println(data.getMeetingTimes().get(meetingTimeIndex).getTime()+" ("+data.getMeetingTimes().get(meetingTimeIndex).getId()+")");
         classNumb++;
         });
         if (schedule.getFitness() == 1) System.out.println("Solution Found in "+ (generation + 1)+" generations");
         System.out.print("---------------------------------------------------------------------------------");
         System.out.println("-----------------------------------------------------------------------------------");
    }
}
