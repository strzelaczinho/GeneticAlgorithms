package TREE_2;

import java.util.Objects;
public class FeatureValue {
     private String name;
     private int occurances;
    public FeatureValue(String name) 
    {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getOccurances() {
        return occurances;
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    public void setOccurances(int occurances) {
        this.occurances = occurances;
    }
    @Override
    public boolean equals(Object object) {
       boolean returnValue = true;
       if (object == null || (getClass() != object.getClass())) returnValue = false;
       if (name == null) if (((FeatureValue)object).name !=null) returnValue = false;
       else if (!name.equals(((FeatureValue)object).name)) returnValue = false;
       
       return returnValue;
    }
    
    
    
}