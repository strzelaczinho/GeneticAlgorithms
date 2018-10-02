package TREE_3;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
public class Driver extends JFrame {
     static String plik = "C:\\Users\\adam\\Documents\\NetBeansProjects\\SZTUCZNA_INTELIGENCJA\\src\\TREE_3\\sample.txt";
	private static final long serialVersionUID = 1L;
	static String[][] CONTACT_LENSES = 
		 {{"rozdaj oczu",                   "rodzaj widzenia",             "astygmatyzm",                  "produkcja łez oczu",     "recepta"},
                  {"osoba ponizej 18 roku",              "krotkowidz",	           "nie",                           "ograniczone",        "brak"},
                  {"osoba ponizej 18 roku",              "krotkowidz",	           "nie",                            "normalne",          "delikatne"},
		  {"osoba ponizej 18 roku",	         "krotkowidz",             "tak",                       "ograniczone",            "brak"},
		  {"osoba ponizej 18 roku",	         "krotkowidz"	,          "tak",                            "normalne",   	  "mocne"},
		  {"osoba ponizej 18 roku",	         "dalekowidz",             "nie",                         "ograniczone",          "brak"},
		  {"osoba ponizej 18 roku",	         "dalekowidz",             "nie",                          "normalne",            "delikatne"},
		  {"osoba ponizej 18 roku",	         "dalekowidz",             "tak",                       "ograniczone",            "brak"},
		  {"osoba ponizej 18 roku",	         "dalekowidz",             "tak",                         "normalne",             "mocne"},
		  {"starcze-przytepienie wzroku", "krotkowidz",                    "nie",                   "ograniczone",                "brak"},
		  {"starcze-przytepienie wzroku", "krotkowidz",                    "nie",                   "normalne",                   "delikatne"},
		  {"starcze-przytepienie wzroku", "krotkowidz",                    "tak",                 "ograniczone",                  "brak"},
		  {"starcze-przytepienie wzroku", "krotkowidz",                    "tak",                 "normalne",                     "mocne"},
		  {"starcze-przytepienie wzroku", "dalekowidz",                    "nie",                           "ograniczone",	  "brak"},
		  {"starcze-przytepienie wzroku", "dalekowidz",                    "nie",                           "normalne",           "delikatne"},
		  {"starcze-przytepienie wzroku", "dalekowidz",                    "tak",                        "ograniczone",           "brak"},
		  {"starcze-przytepienie wzroku", "dalekowidz",                    "tak",                       "normalne",               "brak"},
		  {"osoba powyzej 18 roku",	 "krotkowidz",                     "nie",                            "ograniczone",       "brak"},
		  {"osoba powyzej 18 roku",	 "krotkowidz",                     "nie",                          "normalne",            "brak"},
		  {"osoba powyzej 18 roku",	 "krotkowidz",                     "tak",                         "ograniczone",          "brak"},
		  {"osoba powyzej 18 roku",	 "krotkowidz",                     "tak",                         "normalne",             "mocne"},
		  {"osoba powyzej 18 roku",	 "dalekowidz",                     "nie",                      "ograniczone",            "brak"},
		  {"osoba powyzej 18 roku",	 "dalekowidz",                     "nie",                                  "normalne",    "delikatne"},
		  {"osoba powyzej 18 roku",	 "dalekowidz",                     "tak",	                     "ograniczone",      "brak"},
		  {"osoba powyzej 18 roku",	 "dalekowidz",                     "tak",                                "normalne",       "brak"}};

static String[][] WEATHER = 
                  {{"pogoda", "temperatura", "wilgotnosc", "wietrznie", "isc na dwor"},
                 {"slonecznie",   "goraco",         "duza",       "FALSE", "nie"},
                 {"slonecznie",   "goraco",         "duza",       "TRUE",  "nie"},
                 {"pochmurno",   "goraco",         "duza",       "FALSE",  "tak"},
                 {"deszczowo",   "letnia ",        "duza",       "FALSE", "tak"},
		  {"deszczowo",   "chlodno",        "normalne",    "FALSE", "tak"},
		  {"deszczowo",   "chlodno",        "normalne",    "TRUE",  "nie"},
		  {"pochmurno",   "chlodno",        "normalne",    "TRUE",  "tak"},
		  {"slonecznie",   "letnia ",        "duza",     "FALSE", "nie"},
		  {"slonecznie",   "chlodno",        "normalne",    "FALSE", "tak"},
		  {"deszczowo",   "letnia ",        "normalne",   "FALSE", "tak"},
		  {"slonecznie",   "letnia ",        "normalne",   "TRUE",  "tak"},
		  {"pochmurno",   "letnia ",        "duza",      "TRUE",  "tak"},
		  {"pochmurno",   "goraco",         "normalne",    "FALSE", "tak"},
		  {"deszczowo",   "letnia ",        "duza",      "TRUE",  "nie"}};


static String[][] DODATKOWA = zamienMacierz();
	static Map<String, String[][]> datas = Collections.unmodifiableMap(new HashMap<String, String[][]>() {
		private static final long serialVersionUID = 1L;
		{ 
            
			put("POGODA", WEATHER);
			put("SZKLO KONTAKTOWE", CONTACT_LENSES);
                        put("FUNKCJONALNOSC",DODATKOWA);
                       
                       
		}
	});
	static String dataKey = datas.keySet().iterator().next();
	public static void main(String[] args) throws Exception {
		Driver driver = new Driver();
		JTree tree = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                
                
		boolean flag = true;
		while (flag) {
//                    for (int i = 0;i<15;i++)
//                    {
//                        for (int j = 0;j<5;j++)
//                        {
//                            System.out.print(DODATKOWA[i][j]+" ");
//                        }
//                        System.out.println();
//                    }
			System.out.println("Co chcesz zrobic (zbudowac drzewo, wybrac baze, wyjsc) ?");
			String command = bufferedReader.readLine();
			switch (command) {
				case "zbudowac drzewo": 
					DataSet dataSet = new DataSet(dataKey, datas.get(dataKey));
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(dataSet.getSplitOnFeature().getName());
					driver.processDataSet(dataSet, node, "");
					if (tree != null) driver.remove(tree);
					tree = new JTree(node);
					driver.add(tree);
					driver.setSize(500,500);
                                        driver.wyswietlNasrodku(driver);
					driver.setTitle(dataKey + " BAZA");
					driver.setVisible(true);
                                        
					break;
				case "wybrac baze":
					System.out.println("> Wybierz baze "+datas.keySet()+" ?");
					String value = bufferedReader.readLine().toUpperCase();
					if (datas.keySet().contains(value)) dataKey = value;
					else System.out.println("Prosze wybrac poprawna baze");
					break;
				case "wyjsc":
					flag = false;
					break;
			}
		}
		System.exit(0);
	}
   public static void wyswietlNasrodku(Window frame) {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
    frame.setLocation(x, y);
}
	void processDataSet(DataSet dataSet, DefaultMutableTreeNode node, String featureValueName) // przechozenie po wartosciach drzewa W celu wypisania decyzji oraz budowania drzewa
        { 
		if (dataSet.toString() != null) System.out.println(dataSet);
		if (dataSet.getEntropy() != 0) {
			System.out.println("Najlepsza cecha do budowania decyzji jest " + dataSet.getSplitOnFeature() + " "+dataSet.getSplitOnFeature().getFeatureValues());
			HashMap<String, DataSet> featureDataSets = new HashMap<String, DataSet>();
			dataSet.getSplitOnFeature().getFeatureValues().forEach(featureValue -> 
				featureDataSets.put(featureValue.getName(), dataSet.StworzBaze(dataSet.getSplitOnFeature(), featureValue, dataSet.getData())));
			processDataSets(featureDataSets, node);
		} else {
			String[][] data = dataSet.getData();
			String decision = " ["+data[0][data[0].length-1]+" = "+ data[1][data[0].length-1]+"]";
			node.add(new DefaultMutableTreeNode(featureValueName +"  : "+ decision));
			System.out.println("Decyzja ==> "+ decision);
		}
	}
	void processDataSets(HashMap<String, DataSet> dataSets, DefaultMutableTreeNode node) { // wyswietlanie decyzji w miare przechodzenia po galeziach drzewa. oraz dodawania tych wartosci do 
		dataSets.keySet().forEach(dataSet -> {
			if (dataSets.get(dataSet).getEntropy() != 0) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(dataSet+"  :  ["+dataSets.get(dataSet).getSplitOnFeature().getName()+"]"); 
				node.add(newNode);
				processDataSet(dataSets.get(dataSet), newNode, dataSet);
			} else processDataSet(dataSets.get(dataSet), node, dataSet);
			
		});
	}
           private static List<String> MacierzzPliku(String filePath)
{
    List<String> lista = new ArrayList<>();
    try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
    {
        stream.forEach(s -> lista.add(s));
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
    List<String> acz2 = new ArrayList<>();
       for (int i = 0;i<lista.size();i++)
        {
        String s = lista.get(i);
        String[] words = s.split("\\s+");
        for (int j = 0;j<words.length;j++)
        {
            acz2.add(words[j]);
        }
     }
       
return acz2;
}
           public static String[][] zamienMacierz ()
           {
List<String> acz  = new ArrayList<>(MacierzzPliku(plik));
String[][] macierz = new String[15][5];
int k = 0;
      for (int i = 0;i<15;i++)
          for (int j = 0;j<5;j++)
              macierz[i][j] = acz.get(k++);
               
      return macierz;         
           }
      

}
