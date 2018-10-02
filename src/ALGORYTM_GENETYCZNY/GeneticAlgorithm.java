package ALGORYTM_GENETYCZNY;

public class GeneticAlgorithm {
    public static final int PUPULATION_SIZE= 8; // 8 chromosomow
    public static final int[] TARGET_CHROMOSOME = {1,1,0,1,0,0,1,1,1,0};//rozwiązanie
    public static final int  NUMB_OF_ELITE_CHROMOSOMES = 1;
    public static final int TOURNAMENT_SELECTION_SIZE = 4; // dla selekcji chromosomow krzyzowanych crossoverow 
    public static final double MUTATION_RATE = 0.25; // prawdopodoienstwo mutacji w zasiegu 0 i 1 . do 0.25 mutacja 
    private Popullation crossoverPopullation(Popullation population)
    {
        Popullation crossoverPopulation = new Popullation(population.getChromosomes().length);
        for (int x = 0;x<NUMB_OF_ELITE_CHROMOSOMES;x++)
        {
         crossoverPopulation.getChromosomes()[x] = population.getChromosomes()[x];
        }
        for (int x = NUMB_OF_ELITE_CHROMOSOMES;x < population.getChromosomes().length;x++)
        { // przejezdza po wszystkich chromosomach procz tego najbardziej pasujacego z numerem 1
            Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0]; // wybierzmemy 1 i 2 najodpowiedniejszy chromosom
            Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0]; // 
            
            crossoverPopulation.getChromosomes()[x] = crossoverChromosome(chromosome1, chromosome2);
            // pozostaje chromosome wynikly po polaczeniu 
        }
        return crossoverPopulation;
    }
    private Popullation mutatePopullation(Popullation population)
            {
                Popullation mutatePopulation = new Popullation(population.getChromosomes().length);
        for (int x = 0;x<NUMB_OF_ELITE_CHROMOSOMES;x++)
        {
         mutatePopulation.getChromosomes()[x] = population.getChromosomes()[x];
        }//zostawiaj najlepszy chromosom
        for (int x = NUMB_OF_ELITE_CHROMOSOMES;x<population.getChromosomes().length;x++)
        {
         mutatePopulation.getChromosomes()[x] = mutateChromosome(population.getChromosomes()[x]);
        }// reszta chromosomow podlega mutacji
               return  mutatePopulation; 
            }
    public  Popullation evolve(Popullation population)
    {
        return mutatePopullation(crossoverPopullation(population));
    }
    private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) // selekcja genow
    {
        Chromosome crossoverChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        // teraz selekcja genow 
        for (int x = 0;x<chromosome1.getGeny().length;x++)
        {
            if (Math.random()< 0.5) // jezeli wartosc jest mniejsza chromosome crossover pobierze geny z chromosomu 1 a jak nie to z 2
                crossoverChromosome.getGeny()[x] = chromosome1.getGeny()[x];
            else crossoverChromosome.getGeny()[x] = chromosome2.getGeny()[x];
        }
        
        return crossoverChromosome;
    }
    private Chromosome mutateChromosome(Chromosome chromosome)
    {   
        Chromosome mutateChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for (int x = 0;x < chromosome.getGeny().length;x++)
        {
            if (Math.random() < MUTATION_RATE) // Jesli wartosc jest mniejsza od 0.25 jako prawdopodbienstwa mutacji MUTUJ! 
            {
             if (Math.random() < 0.5) mutateChromosome.getGeny()[x] = 1; // przypisz w zaleznosci od losowania 0 lub 1
             else mutateChromosome.getGeny()[x] = 0;
            }
            else mutateChromosome.getGeny()[x] = chromosome.getGeny()[x]; // jesli wieksza od 0.25 przepisz wartosci
        }
                return mutateChromosome;
    }
    private Popullation selectTournamentPopulation(Popullation population)
    {//number chromosomu wybrany losowo, najlepszy z najwiekszym highest fitnest zostaje
        Popullation tournamentPopullation = new Popullation(TOURNAMENT_SELECTION_SIZE); // tworzy populację najlepszych dzeci 
        for (int x = 0;x< TOURNAMENT_SELECTION_SIZE;x++)
        {
            tournamentPopullation.getChromosomes()[x] = population.getChromosomes()
                    [(int)(Math.random()*population.getChromosomes().length)];
        }
        tournamentPopullation.sortChromosomesByFitness();
        return tournamentPopullation;
    }
}
