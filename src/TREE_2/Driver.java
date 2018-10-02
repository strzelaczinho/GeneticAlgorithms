/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TREE_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;

public class Driver extends JFrame{
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
  static String[][] CONTACT_LENSES =     {{"age",                        "spectacle-prescrip","astigmatism","tear-prod_rate","prescription"},
                                         {"young",                        "myope",           "no",   "reduced",           "none"},
                                         {"young",                        "myope",           "no",   "normal",            "soft"},
                                         {"young",                        "myope",           "yes",  "reduced",           "none"},
                                         {"young",                        "myope",           "yes",  "normal",            "hard"},
                                         {"young",                        "hypermetrope",    "no",   "reduced",           "none"},
                                         {"young",                        "hypermetrope",    "no",   "normal",            "soft"},
                                         {"young",                        "hypermetrope",    "yes",  "reduced",           "none"},
                                         {"young",                        "hypermetrope",    "yes",  "normal",            "hard"},
                                         {"pre-presbyopic",               "myope",           "no",   "reduced",           "none"},
                                         {"pre-presbyopic",               "myope",           "no",   "normal",            "none"},
                                         {"pre-presbyopic",               "myope",           "no",   "reduced",           "none"},
                                         {"pre-presbyopic",               "myope",           "no",   "normal",            "soft"},
                                         {"pre-presbyopic",               "myope",           "yes",  "reduced",           "none"},
                                         {"pre-presbyopic",               "myope",           "yes",  "normal",            "none"},
                                         {"pre-presbyopic",               "myope",           "no",   "reduced",           "none"},
                                         {"pre-presbyopic",               "myope",           "no",   "normal",            "hard"},
                                         {"presbyopic",                   "myope",           "yes",  "reduced",           "none"},
                                         {"presbyopic",                   "myope",           "yes",  "normal",            "soft"},
                                         {"presbyopic",                   "myope",           "no",   "reduced",           "none"},
                                         {"presbyopic",                   "myope",           "no",   "normal",            "soft"},
                                         {"presbyopic",                   "myope",           "yes",  "reduced",           "none"},
                                         {"presbyopic",                   "myope",           "yes",  "normal",            "hard"}};
  
  static Map<String, String[][]> datas = Collections.unmodifiableMap(new HashMap<String, String[][]>() // kod statyczny wykona sie zawsze przy probie odpalenia programu
  {
      private static final long serialVersionUID = 1L;
          {
              
              put("WEATHER",WEATHER);
              put("CONTACT LENSES",CONTACT_LENSES);
              
          }
  });
  static String dataKey = datas.keySet().iterator().next();
  
  
    public static void main(String[]args) throws IOException
    {
        Driver driver = new Driver();
        JTree tree = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        while(flag)
        {
            System.out.println("> Co chciałbyś zrobić (zbudowac drzewo, wybrac baze, wyjsc) ?");
            String command = bufferedReader.readLine();
            switch(command)
            {
                case "zbudowac drzewo":
                    DataSet dataSet = new DataSet(dataKey,datas.get(dataKey)); // tutaj wstawiam klucz ktory jest jedna z wartosi w bazie czyli maceirzy
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(dataSet.getSplitOnFeature().getName()); // node zwraca wartosc funkcji 
                    driver.processDataSet(dataSet, node, ""); // budujemy drzewo decyzyjne
                    if (tree != null) driver.remove(tree);
                    if (tree != null) driver.remove(tree);
                    tree = new JTree(node);
                    driver.add(tree);
                    driver.setSize(350,350);
                    driver.setTitle(dataKey+" DATASET");
                    driver.setVisible(true); // wyswietla drzewo i okno
                    break;
                case "wybrac baze":
                    System.out.println("  Wybierz baze( " +datas.keySet()+"  ?"); // wybiera baze z tych wyswietlonych 
                    String value = bufferedReader.readLine(); // wpisuje
                    if (datas.keySet().contains(value)) dataKey = value;// pobiera klucz wybranej bazy
                    else System.out.println("Prosze wpisz nazwe bazy"); 
                    break;
                case "wyjsc":
                    flag = false;
                    break;
                     
            }
        }
        System.exit(0);
    }
    void processDataSet(DataSet dataSet, DefaultMutableTreeNode node , String featureValueName) //sprawdz baze i dodaje do drzewa
    {
        if (dataSet.toString() != null) System.out.println(dataSet);
        if (dataSet.getEntropy() != 0) // jesli nie jestesmy w lisciu  
        {
        System.out.println("Najlepsza cecha do podzialu  "+dataSet.getSplitOnFeature()+" "+dataSet.getSplitOnFeature().getFutureValues());
        HashMap<String,DataSet> featureDataSets = new HashMap<String, DataSet>();
        dataSet.getSplitOnFeature().getFutureValues().forEach(featureValue -> // dla kazdejwartosci featureValue
                featureDataSets.put(featureValue.getName(), dataSet.createDataSet(dataSet.getSplitOnFeature(), featureValue, dataSet.getData())));// tworzymy nowa Baze dataset
        // gdzie nazwa jest kluczem
                processDataSets(featureDataSets, node); //tutaj przelatujemy po calej utworzonej bazie 
        }
        else // jestli jestesmy w lisciu
        {
                String[][] data = dataSet.getData();
                String decision = " ["+data[0][data[0].length-1]+" = "+data[1][data[0].length-1]+"]";
                node.add(new DefaultMutableTreeNode(featureValueName+" : "+decision));
                System.out.println("Dezycja ==> "+decision);
        }
        
    }
    void processDataSets(HashMap<String, DataSet> dataSets, DefaultMutableTreeNode node){
        dataSets.keySet().forEach(dataSet -> {
        if (dataSets.get(dataSet).getEntropy() != 0) {  //jesli posiada wartosc
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(dataSet+"  :   ["+dataSets.get(dataSet).getSplitOnFeature().getName()+"]"); //dataSet jako kazda liczba od 0
           // dla kazdej wartosci dataSet utworzymy node . 
            node.add(newNode); // dodamy do galezi drzewa
            processDataSet(dataSets.get(dataSet), newNode, dataSet); // oraz sprwadzimy przy dodawaniu 
        }
        else 
        {
            processDataSet(dataSets.get(dataSet), node, dataSet); // jesli masz wartosc 0 , musismy sprawdzic czy ostatnia wartosc jest lisciem czy nie
            
        }
        
        });
    }
}
