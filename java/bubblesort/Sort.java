package bubblesort;
import java.io.FileWriter;
import java.util.Comparator;

public class Sort{
    public final static int ASCENDING = 0;
    public final static int DESCENDING = 1;
    
    public static <T> T[] SortThis(Comparator comparator, T[] unSortedArray, int ordering ){
      String orderingString = "Descending ";
      if(ordering == ASCENDING){
        orderingString = "Ascending ";
      }
      Long s = System.nanoTime();
        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for(int i = 0; i < unSortedArray.length -1; i++){
                if(ordering == DESCENDING){
                    if(comparator.compare(unSortedArray[i], unSortedArray[i+1]) > 0){
                        T temp = unSortedArray[i];
                        unSortedArray[i] = unSortedArray[i+1];
                        unSortedArray[i+1] = temp;
                        sorted = false;
                    }
                }
                else{
                    if(comparator.compare(unSortedArray[i], unSortedArray[i+1]) < 0){
                        T temp = unSortedArray[i];
                        unSortedArray[i] = unSortedArray[i+1];
                        unSortedArray[i+1] = temp;
                        sorted = false;
                    }

                }
            }
        }
      Long duration = System.nanoTime() - s;
      try{
      FileWriter fw = new FileWriter("java.csv", true) ; 
      fw.write(duration.toString() + ","); 
      fw.close();
      }catch(Exception e){
      }
        return unSortedArray;
    }

}
