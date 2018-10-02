package TREE_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.IntStream;

public class Driver {
    static String[][] WEATHER = 
    {{"outlook","temperature","humidity","windy","play"},
        {"sunny",   "hot",  "high",  "FALSE", "no"},
        {"sunny",   "hot",  "high",  "TRUE",  "no"},
        {"overcast","hot",  "high",  "FALSE", "yes"},
        {"rainy",   "mild", "high",  "FALSE", "yes"},
        {"rainy",   "cool", "normal","FALSE", "yes"},
        {"rainy",   "cool", "normal","TRUE",  "no"},
        {"overcast","cool", "normal","TRUE",  "yes"},
        {"sunny",   "cool", "high",  "FALSE", "no"},
        {"sunny",   "mild", "normal","FALSE", "yes"},
        {"rainy",   "cool", "normal","FALSE", "yes"},
        {"sunny",   "mild", "normal","TRUE",  "yes"},
        {"overcast","mild", "high",  "TRUE",  "yes"},
        {"overcast","hot",  "normal","FALSE", "yes"},
        {"rainy",   "mild", "high",  "TRUE",  "no"}};
  static String[][] CONTACT_LENSES = {{"age", "spectacle-prescrip","astigmatism","tear-prod_rate","prescription"},
                                         {"young",               "myope",           "no",   "reduced", "none"},
                                         {"young",               "myope",           "no",   "normal",  "soft"},
                                         {"young",               "myope",           "yes",  "reduced", "none"},
                                         {"young",               "myope",           "yes",  "normal",  "hard"},
                                         {"young",               "hypermetrope",    "no",   "reduced", "none"},
                                         {"young",               "hypermetrope",    "no",   "normal",  "soft"},
                                         {"young",               "hypermetrope",    "yes",  "reduced", "none"},
                                         {"young",               "hypermetrope",    "yes",  "normal",  "hard"},
                                         {"pre-presbyopic",               "myope",           "no",   "reduced", "none"},
                                         {"pre-presbyopic",      "myope",           "no",   "normal",  "none"},
                                         {"pre-presbyopic",               "myope",           "no",   "reduced", "none"},
                                         {"pre-presbyopic",               "myope",           "no",   "normal",  "soft"},
                                         {"pre-presbyopic",               "myope",           "yes",  "reduced", "none"},
                                         {"pre-presbyopic",               "myope",           "yes",  "normal",  "none"},
                                         {"pre-presbyopic",               "myope",           "no",   "reduced", "none"},
                                         {"pre-presbyopic",               "myope",           "no",   "normal",  "hard"},
                                         {"presbyopic",               "myope",           "yes",  "reduced", "none"},
                                         {"presbyopic",               "myope",           "yes",  "normal",  "soft"},
                                         {"presbyopic",               "myope",           "no",   "reduced", "none"},
                                         {"presbyopic",               "myope",           "no",   "normal",  "soft"},
                                         {"presbyopic",               "myope",           "yes",  "reduced", "none"},
                                         {"presbyopic",               "myope",           "yes",  "normal",  "hard"}};
        
public static void main(String[]args)
{
    Driver driver = new Driver();
    HashMap<String,String[][]> datas = new HashMap<>();
    datas.put("WEATHER",WEATHER);
    datas.put("CONTACT LENSES", CONTACT_LENSES);
    datas.keySet().forEach(data -> {
    HashMap<Feature, Double> featuresInfoGain = new HashMap<Feature,Double>(); // dla kazdego obiektu Feature w dataset 
    DataSet dataSet = new DataSet(datas.get(data)); // wtorzymy poprzez dodanie macierzy WEATHER lub CONTACT_LENSENS
    IntStream.range(0, datas.get(data)[0].length -1).forEach(column -> {
    Feature feature = new Feature(datas.get(data),column);
    ArrayList<DataSet> dataSets = new ArrayList<DataSet>();
    feature.getValues().stream().forEach(featureValue -> 
    dataSets.add(driver.createDataSet(featureValue, column, datas.get(data))));
    
    double summation = 0;
    for (int i = 0;i<dataSets.size();i++)
    {
        summation += ((double)(dataSets.get(i).getData().length-1))*dataSets.get(i).getEntropy();
    }
    featuresInfoGain.put(feature, dataSet.getEntropy()-summation);
    });
    System.out.println("<"+data+" DATASET> :\n"+dataSet);
    System.out.println(driver.generateInfoGainDisplayTable(featuresInfoGain));
    System.out.println("Best feature to split on is "+driver.determineSplitonFeature(featuresInfoGain)+"\n");
    System.out.println("\n\n");
    });
    
}
DataSet createDataSet(FeatureValue featureValue, int column,String[][]data)
{
    String[][] returnData = new String[featureValue.getOccurances()+1][data[0].length];
    returnData[0] = data[0];
    int counter = 1;
    for (int row = 1;row<data.length;row++)
        if(data[row][column] == featureValue.getName()) 
            returnData[counter++] = data[row];
    return new DataSet(deleteColumn(returnData,column));
}
String[][] deleteColumn(String[][] data, int deleteColumn) //  usuwanie kolumny w macierzy 
{
    String returnData[][] = new String[data.length][data[0].length-1];
    for (int row = 0;row<data.length;row++)
    {
        int columnCounter = 0;
        for (int column = 0;column < data[0].length;column++)
        {
            if (column != deleteColumn) returnData[row][columnCounter++] = data[row][column];
        }
    }
    
    return returnData;
}
Feature determineSplitonFeature(HashMap<Feature,Double> featuresInfoGain)
{
    Feature splitonFeature = null;
    Iterator<Feature> acz = featuresInfoGain.keySet().iterator();
    while(acz.hasNext())
    {
        Feature feature = acz.next();
        if (splitonFeature == null) splitonFeature = feature;
        if (featuresInfoGain.get(splitonFeature) < featuresInfoGain.get(feature))
        {
            splitonFeature = feature;
        }
    }
    return splitonFeature;
}
StringBuffer generateInfoGainDisplayTable(HashMap<Feature,Double> featuresInfoGain)
{
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("Feature        Information Gain\n");
    
    IntStream.range(0, 38).forEach(i -> stringBuffer.append("-"));
    stringBuffer.append("\n");
    
    Iterator<Feature> iterator = featuresInfoGain.keySet().iterator();
    while(iterator.hasNext())
    {
        Feature feature = iterator.next();
        stringBuffer.append(feature);
        IntStream.range(0, 21 - feature.getName().length()).forEach(i -> stringBuffer.append(" "));
        stringBuffer.append(String.format("%.8f", featuresInfoGain.get(feature))+"\n");
    }
    return stringBuffer;
    
}



            }
