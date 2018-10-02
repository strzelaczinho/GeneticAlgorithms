/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ALGORYTM_GENETYCZNY2;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 *
 * @author adam
 */
public class Popullation {
    private ArrayList<Schedule> schedules;

   
    public Popullation(int size, Data data) {
        schedules = new ArrayList<Schedule>(size);
        IntStream.range(0, size).forEach(x -> schedules.add(new Schedule(data).initialize())); // Dodajemy randomowo wylosowane klasy z przypisanymi randomowo pokojem , czasem , instruktorem
     
        
    }
     public ArrayList<Schedule> getSchedules() {return this.schedules; }

    public Popullation sortByFitness ()
    {
        schedules.sort((schedule1, schedule2) -> {
            int returnValue = 0;
            if(schedule1.getFitness() > schedule2.getFitness()) returnValue = -1;
            else if (schedule1.getFitness() > schedule2.getFitness()) returnValue = 1;
            return returnValue; // komparator porownujacy dwa obiety ze soba po dopasowaniu obiektow . Sortuje
        });
        return this; // return te Populacje schedules
    }
    
//     Arrays.sort(chromosomes,(Chromosome chromosome1,Chromosome chromosome2) -> {
//          int flag = 0;
//          if (chromosome1.getFitness() > chromosome2.getFitness()) flag = -1;
//          else if (chromosome1.getFitness() < chromosome2.getFitness()) flag = 1;
//          return flag;
//       });
}
