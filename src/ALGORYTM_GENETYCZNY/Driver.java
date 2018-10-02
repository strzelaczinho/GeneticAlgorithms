package ALGORYTM_GENETYCZNY;

import java.util.Arrays;
public class Driver {
    public static void main(String[]args)
    {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        Popullation population = new Popullation(GeneticAlgorithm.PUPULATION_SIZE).initializePopullation();
        System.out.println("---------------------------------------");
        System.out.println("Generation # 0 | Fittest chromosome fitness: " +population.getChromosomes()[0].getFitness());
        //  jako ze sa posortowane pobiera najlepiej pasujacy 
        int generationNumber = 0;
        
        printPopullation(population, "Target chromosome: "+Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
        
        while(population.getChromosomes()[0].getFitness()< GeneticAlgorithm.TARGET_CHROMOSOME.length)
        {// sprawdz pomiedzy najbardziej odpowiednim chromosomem populacji a tym ktorego poszukujemy jako wzor. Dopoki nie sa rowne
            generationNumber++; // jesli nie udalo nam sie osiagnac poszukiwanej wartosci generuj nastepna populacje
            System.out.println("\n---------------------------------------");
            population = geneticAlgorithm.evolve(population); // gnerujemy nową populację 
            population.sortChromosomesByFitness();
            
            
             System.out.println("Generation # "+generationNumber+" | Fittest chromosome fitness: " +population.getChromosomes()[0].getFitness());
             printPopullation(population, "Target chromosome: "+Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
        }
    }   
    
    
    public static void printPopullation(Popullation population, String heading)
    {
     System.out.println(heading);
     System.out.println("---------------------------------------");
     for (int x = 0; x < population.getChromosomes().length;x++)
     {
         System.out.println("Chromosome # "+x+" " + " : "
                 + Arrays.toString(population.getChromosomes()[x].getGeny()) +
                 " Fitness "+ population.getChromosomes()[x].getFitness());
     }
    }
}


















