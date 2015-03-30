package quicksort;
import java.io.FileWriter;
import java.util.Comparator;

public class Sort{
    public final static int ASCENDING = 0;
    public final static int DESCENDING = 1;

    private <T> T[] _quickSort(int left, int right, T[] unSortedArray, Comparator comparator, int ordering){
        int initialLeft = left;
        int initialRight = right;
        int pivot = left;

        while(pivot != right || pivot != left){
            if(ordering == DESCENDING){
                if(pivot == right){
                    if(comparator.compare(unSortedArray[left], unSortedArray[pivot]) < 0){
                        left++;
                    }else{
                        T temp = unSortedArray[pivot];
                        unSortedArray[pivot] = unSortedArray[left];
                        unSortedArray[left] = temp;
                        pivot = left;
                    }
                }else{
                    if(comparator.compare(unSortedArray[pivot], unSortedArray[right]) < 0){
                        right--;
                    }else{
                        T temp = unSortedArray[pivot];
                        unSortedArray[pivot] = unSortedArray[right];
                        unSortedArray[right] = temp;
                        pivot = right;
                    }
                }
            }else{
                if(pivot == right){
                    if(comparator.compare(unSortedArray[left], unSortedArray[pivot]) > 0){
                        left++;
                    }else{
                        T temp = unSortedArray[pivot];
                        unSortedArray[pivot] = unSortedArray[left];
                        unSortedArray[left] = temp;
                        pivot = left;
                    }
                }else{
                    if(comparator.compare(unSortedArray[pivot], unSortedArray[right]) > 0){
                        right--;
                    }else{
                        T temp = unSortedArray[pivot];
                        unSortedArray[pivot] = unSortedArray[right];
                        unSortedArray[right] = temp;
                        pivot = right;
                    }
                }
            }
        }

        if((pivot - initialLeft) > 0){
            _quickSort(initialLeft, pivot - 1, unSortedArray, comparator, ordering);
        }
        if((initialRight - pivot) > 0){
            _quickSort(pivot + 1, initialRight, unSortedArray, comparator, ordering);
        }

        return unSortedArray;
    }


    public <T> T[] SortThis(Comparator comparator, T[] unSortedArray, int ordering ){
      String orderingString = "Descending ";
      if(ordering == ASCENDING){
        orderingString = "Ascending ";
      }
      Long s = System.nanoTime();
        _quickSort(0, unSortedArray.length -1, unSortedArray, comparator, ordering);
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
