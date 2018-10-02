package ALGORYTM_GENETYCZNY3_TSP_ANT_COLONY;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class Driver {
    static final int NUMBER_OF_ANTS = 500;
    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    static ExecutorCompletionService<Ant> executorCompletionService = new ExecutorCompletionService<>(executorService);
    private int activeAnts = 0;
    
    public static void main(String[]args)
    {
        Driver driver = new Driver();
        IntStream.range(1, NUMBER_OF_ANTS).forEach(x -> {
         executorCompletionService.submit(new Ant());
         driver.activeAnts++;
         while(driver.activeAnts>0)
         {
             try {
                 executorCompletionService.take();
                 
             }catch (Exception e)
             {
                 e.printStackTrace();
             }
             driver.activeAnts--;
         }
        });
        executorService.shutdown();
    }
}
