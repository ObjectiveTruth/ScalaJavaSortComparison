package quicksort{
  import java.util.Comparator;
  import java.io.FileWriter;

  object Sort{
    val ASCENDING = 0;
    val DESCENDING = 1;

    def _quickSort[T](lefti : Integer, righti : Integer, 
      unSortedArray : Array[T], comparator : Comparator[T], ordering : Integer): Array[T] = {
        var initialLeft = lefti;
        var left = lefti;
        var right = righti;
        var initialRight = righti;
        var pivot = lefti;

        while(pivot != right || pivot != left){
          if(ordering == DESCENDING){
            if(pivot == right){
              if(comparator.compare(unSortedArray(left), unSortedArray(pivot)) < 0){
                left += 1;
              }else{
                val temp = unSortedArray(pivot);
                unSortedArray(pivot) = unSortedArray(left);
                unSortedArray(left) = temp;
                pivot = left;
              }
              }else{
                if(comparator.compare(unSortedArray(pivot), unSortedArray(right)) < 0){
                  right -= 1;
                }else{
                  val temp = unSortedArray(pivot);
                  unSortedArray(pivot) = unSortedArray(right);
                  unSortedArray(right) = temp;
                  pivot = right;
                }
              }
              }else{
                if(pivot == right){
                  if(comparator.compare(unSortedArray(left), unSortedArray(pivot)) > 0){
                    left += 1;
                  }else{
                    val temp = unSortedArray(pivot);
                    unSortedArray(pivot) = unSortedArray(left);
                    unSortedArray(left) = temp;
                    pivot = left;
                  }
                  }else{
                    if(comparator.compare(unSortedArray(pivot), unSortedArray(right)) > 0){
                      right -= 1;
                    }else{
                      val temp = unSortedArray(pivot);
                      unSortedArray(pivot) = unSortedArray(right);
                      unSortedArray(right) = temp;
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


    def SortThis[T](comparator : Comparator[T], unSortedArray : Array[T], 
      ordering : Integer )(implicit m: reflect.Manifest[T]): Array[T] = {
        var orderingString = "Descending ";
        if(ordering == ASCENDING){
          orderingString = "Ascending ";
        }
      print(String.format("%-20s | %8s%n", orderingString + m.toString.split('.').last, 
        (time
          (_quickSort(0, unSortedArray.length -1, unSortedArray, comparator, ordering)))
            .toString));
        return unSortedArray;
    }
    def time(f: => Unit): Long ={
      val s = System.nanoTime;
      f
      val duration = System.nanoTime - s;
      val fw = new FileWriter("scala.csv", true) ; 
      fw.write(duration.toString + ","); 
      fw.close()
      return duration;
    }

  }
}
