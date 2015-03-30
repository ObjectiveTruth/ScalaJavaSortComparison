package mergesort;
import java.io.FileWriter;
import java.util.Comparator;

public class Sort{
    public final static int ASCENDING = 0;
    public final static int DESCENDING = 1;

    private <T> T[] _mergeSort(int left, int right, T[] array, Comparator comparator, int ordering){
        int middle = (left + right) / 2;
        int middleRight = middle + 1;

        if(left < right){
            _mergeSort(left, middle, array, comparator, ordering);
            _mergeSort(middleRight, right, array, comparator, ordering);
            _merge(left, middle, middle + 1, right, array, comparator, ordering);
            return array;
        }else{
            return array;
        }
    }

    private <T> void _merge(int left, int middle, int rightMiddle, int right, 
            T[] array, Comparator comparator, int ordering){
        int initial = left;
        int arraySize = right - left + 1;
        T[] tempArray = (T[])new Object[arraySize];
        for(int i = 0; i < tempArray.length; i++){
            if(left > middle && rightMiddle <= right){
                tempArray[i] = array[rightMiddle];
                rightMiddle++;
            }else if(rightMiddle > right && left <= middle){
                tempArray[i] = array[left];
                left++;
            }else{
                if(ordering == DESCENDING){
                    if(comparator.compare(array[left], array[rightMiddle]) < 0){
                        tempArray[i] = array[left];
                        left++;
                    }else{
                        tempArray[i] = array[rightMiddle];
                        rightMiddle++;
                    }
                }else{
                    if(comparator.compare(array[left], array[rightMiddle]) < 0){
                        tempArray[i] = array[rightMiddle];
                        rightMiddle++;
                    }else{
                        tempArray[i] = array[left];
                        left++;
                    }
                }
            }
        }
        for (int i = 0; i < tempArray.length; i++){
            array[initial + i] = tempArray[i];
        }
    }
    
    public <T> T[] SortThis(Comparator comparator, T[] unSortedArray, int ordering ){
      String orderingString = "Descending ";
      if(ordering == ASCENDING){
        orderingString = "Ascending ";
      }
      Long s = System.nanoTime();

        _mergeSort(0, unSortedArray.length -1, unSortedArray, comparator, ordering);
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
