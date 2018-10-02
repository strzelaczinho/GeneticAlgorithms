package TREE_3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.IntStream;
public class DataSet {
	private String name;
	private String[][] data = null;
	private double entropy = 0;
	private HashMap<Feature, Double> infoGains = new HashMap<Feature, Double>();
	private Feature splitOnFeature = null;
	public DataSet(String name, String[][] data) {
		this.name = name;
		this.data = data;
		obliczFunkcjeEntropii().obliczInfoGains().znajdzPodzialCech();
	}
	private DataSet obliczFunkcjeEntropii() {
		new Feature(data, data[0].length - 1).getFeatureValues().stream().forEach(featureValue -> 
			entropy += minusPlog2((double)featureValue.getOccurences() / (data.length - 1)));
		return this;
	}
	private DataSet obliczInfoGains() { // oblicza Information Gain
		IntStream.range(0, data[0].length -1).forEach(column -> {
			 Feature feature = new Feature(data, column);
			 ArrayList<DataSet> dataSets = new ArrayList<DataSet>();
			 feature.getFeatureValues().stream().forEach(featureValue ->
			 	dataSets.add(StworzBaze(feature, featureValue, data)));
			 double summation = 0;
			 for (int i = 0; i < dataSets.size(); i++)
				 summation += ((double)(dataSets.get(i).getData().length -1)/(data.length-1))*dataSets.get(i).getEntropy();
			 infoGains.put(feature, entropy - summation);
		});
		return this;
	}
	private DataSet znajdzPodzialCech() {
		Iterator<Feature> iterator = infoGains.keySet().iterator();
		while(iterator.hasNext()) {
			Feature feature = iterator.next();
			if (splitOnFeature == null || infoGains.get(splitOnFeature) < infoGains.get(feature)) splitOnFeature = feature; // najlepsza cecha do budowania drzewa
		}
		return this;
	}
	DataSet StworzBaze(Feature feature, FeatureValue featureValue, String[][] data) {
		int column = pobierzNumerKolumny(feature.getName());
		String[][] returnData = new String[featureValue.getOccurences()+1][data[0].length];
		returnData[0] =  data[0];
		int counter = 1;
		for (int row = 1; row < data.length; row++)
			if (data[row][column] == featureValue.getName()) returnData[counter++] =  data[row];
		return new DataSet(feature.getName() + ": "+ featureValue.getName(), usunKolumne(returnData, column));
	}
	private double minusPlog2(double p) {  // funkcja pomocna do obliczania entroprii
		double returnValue = 0;
		if (p != 0) returnValue = (-1) * p * Math.log(p) / Math.log(2);
		return returnValue;
	}
	private String[][] usunKolumne(String[][] data, int toDeleteColumb) { // pobrana z bazy 
		String returnData[][] = new String[data.length][data[0].length - 1];
		IntStream.range(0, data.length).forEach(row -> {
			int columnCounter = 0;
			for (int column = 0; column < data[0].length; column++) 
				if (column != toDeleteColumb) returnData[row][columnCounter++] = data[row][column];
		});
		return returnData;
	}
	public int pobierzNumerKolumny(String colName) { // z bazy
		int returnValue = -1;
		for (int column = 0; column < data[0].length -1; column++)
			if (data[0][column] == colName) {returnValue = column; break;}
		return returnValue;
	}
	public String[][] getData() { return data; }
	public double getEntropy() { return entropy; }
	public HashMap<Feature, Double> getInfoGains() { return infoGains; }
	public Feature getSplitOnFeature() { return splitOnFeature; }
	public String toString() { return name; }
}
