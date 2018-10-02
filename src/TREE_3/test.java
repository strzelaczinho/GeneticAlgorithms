package TREE_3;
import static TREE_3.Driver.plik;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class test {
    static String plik = "C:\\Users\\adam\\Documents\\NetBeansProjects\\SZTUCZNA_INTELIGENCJA\\src\\TREE_3\\sample.txt";
    public static void main(String[]args) throws Exception
    {

       String[][] lista = zamienMacierz(MacierzzPliku(plik));
       for (int i = 0;i<15;i++)
       {
           for (int j = 0;j<5;j++)
           {
               System.out.print(lista[i][j]+" ");
           }
           System.out.println();
       }
    }
  //  private static String readLineByLineJava8(String filePath)
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
           public static String[][] zamienMacierz (List<String> acz2)
           {
List<String> acz  = new ArrayList<>(MacierzzPliku(plik));
String[][] lista2 = new String[15][5];
int k = 0;
      for (int i = 0;i<15;i++)
          for (int j = 0;j<5;j++)
              lista2[i][j] = acz.get(k++);
               
      return lista2;         
           }
}
//
//rodzaj          chlodzenie   cena 	gwarancja 	kupic
//laptop          brak       duza          FALSE           nie
//laptop          brak       duza          TRUE            nie 
//playstation	brak       duza          FALSE           tak
//PC              wiatrak      duza          FALSE         tak
//PC              Azot     normalna	  FALSE          tak
//PC              Azot     normalna	  TRUE           nie
//playstation	Azot     normalna	  TRUE           tak
//laptop          wiatrak      duza          FALSE         nie
//laptop          Azot     normalna	  FALSE          tak
//PC              wiatrak      normalna	  FALSE          tak
//laptop          wiatrak      normalna	  TRUE           tak
//playstation     brak        niewielka     TRUE           tak
//playstation      brak       niewielka	  FALSE          tak
//PC              wiatrak      duza          TRUE          nie
