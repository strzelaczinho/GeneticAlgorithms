package ALGORYTM_GENETYCZNY;


import java.util.Arrays;

public class Chromosome {
    private int[] geny;
    private int fitness;
    private boolean isFitnessChanged = true;
    public Chromosome(int length) {
        geny = new int[length];
    }
     public Chromosome intializeChromosome()
    {
        for (int x = 0;x<geny.length;x++)
        {
            if (Math.random() >= 0.5)
            {
                geny[x] = 1;
            }
            else 
            {
                geny[x] = 0;
            }
        }
        return this;
    }
    public int[] getGeny() {
        isFitnessChanged = true;
        return geny;
    }
    public int getFitness() {
        if (isFitnessChanged)
        {
        fitness = recalculateFitness();
        isFitnessChanged = false;
        }
        return fitness;
    }
    public int recalculateFitness()
    {
        int chromosomeFitness = 0;
        for (int x = 0;x < geny.length;x++)
        {
            if (geny[x] == GeneticAlgorithm.TARGET_CHROMOSOME[x])
                chromosomeFitness++;
        } // porownuje dwie tablice  po kazdym elemencie. Jesli sa rowne dostaje+;
        return chromosomeFitness;
    }
     public String toString()
     {
         return Arrays.toString(this.geny); // wypisze zawawrtosc tablicy genow 
     }
}
