/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TREE_1;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class DataSet {
    private String[][] data = null;
    private double entropy = 0;
    private ArrayList<Feature> features = null;

    public String[][] getData() {
        return data;
    }
    public double getEntropy() {
        return entropy;
    }
    public ArrayList<Feature> getFeatures() {
        return features;
    }
    double minusPlog2(double p)
    {
        double returnValue = 0;
        if (p!= 0) returnValue = (-1)*p*Math.log(p)/Math.log(2);
        
        return returnValue;
    }
    
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (int row = 0;row < data.length;row++)
        {
            for (int column = 0;column<data[row].length;column++)
            {
                stringBuffer.append(data[row][column]);
                IntStream.range(0, 24 - data[row][column].length()).forEach(i -> stringBuffer.append(" "));
            }
            stringBuffer.append("\n");
            if (row == 0) 
            {
                IntStream.range(0, 108).forEach(i -> stringBuffer.append("-"));
                stringBuffer.append("\n");
            }
        }
        
        return stringBuffer.toString();
        
    }

    public DataSet(String[][]data)
    {
        this.data = data;
        new Feature(data, data[0].length-1).getValues().stream().forEach(featureValue -> 
                entropy +=minusPlog2((double) featureValue.getOccurances()/ (data.length-1)));
    }
    
}
