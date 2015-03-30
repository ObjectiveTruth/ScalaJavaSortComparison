package mergesort{
  import scala.collection.mutable.Queue;
  import java.util.Comparator;
  import java.lang.Integer;
  import java.io.FileWriter;

  object Sort{
      val ASCENDING = 0;
      val DESCENDING = 1;

      def _mergeSort[T] (left : Integer, right : Integer, array : Array[T], 
        comparator : Comparator[T], ordering : Integer): Array[T] = {
          val middle = (left + right) / 2;
          val middleRight = middle + 1;

          if(left < right){
              _mergeSort(left, middle, array, comparator, ordering);
              _mergeSort(middleRight, right, array, comparator, ordering);
              _merge(left, middle, middle + 1, right, array, comparator, ordering);
              return array;
          }else{
              return array;
          }
      }

      def _merge[T] (lefti : Integer, 
        middlei : Integer, rightMiddlei : Integer, righti : Integer, 
        array : Array[T], comparator : Comparator[T], ordering : Integer): Array[T] = {
          var left = lefti;
          var middle = middlei;
          var rightMiddle = rightMiddlei;
          var right = righti;
          var initial = left;
          var arraySize = right - left + 1;
          val tempArray:Queue[T] = Queue();
          for(i <- 0 until arraySize){
              if(left > middle && rightMiddle <= right){
                  tempArray += array(rightMiddle);
                  rightMiddle += 1;
              }else if(rightMiddle > right && left <= middle){
                  tempArray += array(left);
                  left += 1;
              }else{
                  if(ordering == DESCENDING){
                      if(comparator.compare(array(left), array(rightMiddle)) < 0){
                          tempArray += array(left);
                          left += 1;
                      }else{
                          tempArray += array(rightMiddle);
                          rightMiddle += 1;
                      }
                  }else{
                      if(comparator.compare(array(left), array(rightMiddle)) < 0){
                          tempArray += array(rightMiddle);
                          rightMiddle += 1;
                      }else{
                          tempArray += array(left);
                          left += 1;
                      }
                  }
              }
          }
          for (i <- 0 until arraySize){
            val something = tempArray.dequeue();
              array(initial + i) = something;
          }
          return array;
      }
      
      def SortThis[T](comparator : Comparator[T], unSortedArray : Array[T], 
        ordering : Integer )(implicit m: reflect.Manifest[T]): Array[T] = {

          var orderingString = "Descending ";
          if(ordering == ASCENDING){
            orderingString = "Ascending ";
          }
          print(String.format("%-20s | %8s%n", orderingString + m.toString.split('.').last, 
            (time
              (_mergeSort(0, unSortedArray.length -1, unSortedArray, comparator, ordering)))
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
