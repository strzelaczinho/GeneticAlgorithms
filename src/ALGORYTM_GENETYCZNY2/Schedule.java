/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ALGORYTM_GENETYCZNY2;

import java.util.ArrayList;

/**
 *
 * @author adam
 */
public class Schedule {
   private ArrayList<Class> classes;
   private Data data;
   private int classNumb = 0;
   private int numbOfConflitcs = 0;
   private double fitness = -1;
   private boolean isFitnessChanged = true;

   public int getNumbOfConflitcs() {    return numbOfConflitcs; } 
   public Data getData() {return data;}

    public Schedule(Data data) {
        
        this.data = data;
        classes = new ArrayList<Class>(data.getNumberOfClasses()); //numer klas przekazuje jako konstruktor
    }
    public Schedule initialize()
    {
        new ArrayList<Department>(data.getDepts()).forEach(dept-> { // uzywam data aby pobrac wszystkie departamenty
            dept.getCourses().forEach(course -> { // wszystkie kursy w kazdym departamencie
                Class newClass = new Class(classNumb++,dept,course); // dla kazdego tworze nowy obiekt Klasy
                newClass.setMeetingTime(data.getMeetingTimes().get((int) (data.getMeetingTimes().size()*Math.random())));// randomowo ustawiamy
                //meeting time z mozliwych do wyciagniecia z data (tych utworzonych) obiektow jako jeden wybrany obiekt losowo z rozmiaru listy
                newClass.setRoom(data.getRooms().get((int)(data.getRooms().size()*Math.random()))); // to samo dla pokoi
                newClass.setInstructor(course.getInstructors().get((int)(course.getInstructors().size()*Math.random())));// oraz instruktorow 
                classes.add(newClass); // dodajemy klase
            });
        });
        return this; // zwracmy instacje Schedule
    }
    private double calculateFitness()
    { 
        numbOfConflitcs = 0;
        classes.forEach(x -> { // przechodzimy przez kazda klase
            if(x.getRoom().getSeatingCapacity() < x.getCourse().getMaxNumbOfStudents()) numbOfConflitcs++; // jesl w klasie mozliwosc pomieszczenia studentow jseet mniejsza od liczby studentow
            classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> { // stremujemy i sprawdzmy czy dwa obiekty nakladaja sie na siebie
                if(x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId()) { // te same czasy zajec
                    if (x.getRoom() == y.getRoom()) numbOfConflitcs++;  // te same pokoje 
                    if (x.getInstructor() == y.getInstructor()) numbOfConflitcs++; // oraz instruktorzy
                }
            });
        });
        return 1/(double)(numbOfConflitcs+1); // jezeli konfilktow jest zero zwroci Fitness = 1 . Jesli nie inna wartosc dziesietna
    }
    public double getFitness()
    {
        if (isFitnessChanged == true) // zmienia flage
        {
            fitness= calculateFitness(); // poomwnie oblicza fitness
            isFitnessChanged = false;    // 
        }
        return fitness;
    }
    public ArrayList<Class> getClasses()
    {
        isFitnessChanged = true;
        return classes;
    }
    public String toString()
    {
        String returnValue = new String();
        for (int x = 0;x < classes.size()-1;x++)
            returnValue+= classes.get(x) + " , "; // Dodaje kazdy obiekt klasy z listy classes 
        
        returnValue += classes.get(classes.size()-1);
        return returnValue;
    }
//    public static void main(String[]args)
//    {
//        Data data = new Data();
//        Schedule sch = new Schedule(data);
//        sch.initialize();
//        
//        System.out.println(sch.toString());
//    }
}
