package K_NEAREST_NEIGHBOURS;
import java.util.ArrayList;
import org.apache.commons.math3.linear.MatrixUtils;

public class Driver {
    static String[][][] TRAINING_DATA = 
            {{{"rent (in $)", "size(in sq feets", "distance to dtwn(in miles)"}, {"sentiment(like,dislikeneutral"}},
            {{"1500.0", "0800.0" , "15.90"}, {"+"}},
            {{"0633.0", "0400.0" ,"10.50"}, {"-"}},
            {{"0700.0", "0450.0" ,"05.20"}, {"+"}},
            {{"2568.0", "1250.0", "10.48"}, {"*"}},   
            {{"0800.0", "0500.0", "06.30"}, {"+"}},   
            {{"0836.0", "0550.0", "09.25"}, {"-"}},   
            {{"0950.0", "0600.0", "02.50"}, {"+"}},   
            {{"1102.0", "0640.0", "01.50"}, {"-"}},   
            {{"1200.0", "0680.0", "20.50"}, {"*"}},   
            {{"1293.0", "0700.0", "12.50"}, {"-"}},   
            {{"2413.0", "1200.0", "02.90"}, {"-"}},   
            {{"1734.0", "0880.0", "20.50"}, {"-"}},   
            {{"2050.0", "0950.0", "13.50"}, {"-"}},   
            {{"2100.0", "1000.0", "07.89"}, {"+"}},   
            {{"2272.0", "1100.0", "05.69"}, {"-"}},   
            {{"3020.0", "1500.0", "06.70"}, {"*"}},   
            {{"2605.0", "1300.0", "03.70"}, {"-"}},  
            {{"2700.0", "1350.0", "02.80"}, {"+"}},          
};              //cena   rozmiar   odleglosc  *-neutral +like -not like 
    public static void main (String[]args)
    {
        double[][] xArray = new double[TRAINING_DATA.length-1][TRAINING_DATA[0][0].length];
        ArrayList<String> yArray = new ArrayList<String>(TRAINING_DATA.length-1);
        for (int i = 1;i<TRAINING_DATA.length;i++){
         for (int j = 0;j<TRAINING_DATA[0][0].length;j++) xArray[i-1][j] = Double.parseDouble(TRAINING_DATA[i][0][j]);
         yArray.add(TRAINING_DATA[i][1][0]);
         //yArray label 
         //xArray data 
        }
        
        handleCommandLine(new KNearestNeighbor(MatrixUtils.createRealMatrix(xArray), yArray));
    }
    
    
    static void handleCommandLine(KNearestNeighbor knn)
    {
        
    }
}