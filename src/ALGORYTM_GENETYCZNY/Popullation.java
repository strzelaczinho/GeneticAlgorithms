package ALGORYTM_GENETYCZNY;


import java.util.Arrays;

public class Popullation {
    private Chromosome[] chromosomes;
    
    public Popullation(int length)
    {
        chromosomes = new Chromosome[length];
    }
    public Popullation initializePopullation()
    {
        for (int x = 0;x<chromosomes.length;x++)
        {
            chromosomes[x] = new Chromosome(GeneticAlgorithm.TARGET_CHROMOSOME.length).intializeChromosome();
        }
        sortChromosomesByFitness(); // sortuje przypisane 
        
        return this;
    }
   public void sortChromosomesByFitness()
   {
       Arrays.sort(chromosomes,(Chromosome chromosome1,Chromosome chromosome2) -> {
          int flag = 0;
          if (chromosome1.getFitness() > chromosome2.getFitness()) flag = -1;
          else if (chromosome1.getFitness() < chromosome2.getFitness()) flag = 1;
          return flag;
       });
   }
    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

   
}
