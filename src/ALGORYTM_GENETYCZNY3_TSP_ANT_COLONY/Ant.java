package ALGORYTM_GENETYCZNY3_TSP_ANT_COLONY;

import java.util.concurrent.Callable;

public class Ant implements Callable<Ant>{
    private Route route;
    public Route getRoute()
    {
        return route;
    }
    
    public Ant call() throws Exception {
        
        System.out.println("Ant.call() invoked...");
        return null;
    }
}
