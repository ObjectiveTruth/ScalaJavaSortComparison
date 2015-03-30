package bubblesort{
  import java.util.Comparator;
  import java.lang.Integer;
  import java.io.FileWriter;
  object Sort{

      val ASCENDING = 0;
      val DESCENDING = 1;
      
      def SortThis[T](comparator : Comparator[T], unSortedArray : Array[T], ordering : Integer )(implicit m: reflect.Manifest[T]): Array[T] = {
          var orderingString = "Descending ";
          if(ordering == ASCENDING){
            orderingString = "Ascending ";
          }
          val s = System.nanoTime;
          var sorted = false;
          while(!sorted){
              sorted = true;
              for(i <- 0 until unSortedArray.length -1){
                  if(ordering == DESCENDING){
                      if(comparator.compare(unSortedArray(i), unSortedArray(i+1)) > 0){
                          var temp = unSortedArray(i);
                          unSortedArray(i) = unSortedArray(i+1);
                          unSortedArray(i+1) = temp;
                          sorted = false;
                      }
                  }
                  else{
                      if(comparator.compare(unSortedArray(i), unSortedArray(i+1)) < 0){
                          var temp = unSortedArray(i);
                          unSortedArray(i) = unSortedArray(i+1);
                          unSortedArray(i+1) = temp;
                          sorted = false;
                      }

                  }
              }
          }
          val duration = System.nanoTime - s;
          val fw = new FileWriter("scala.csv", true) ; 
          fw.write(duration.toString + ","); 
          fw.close()

          print(String.format("%-20s | %8s%n", orderingString + m.toString.split('.').last, 
                duration.toString));
          return unSortedArray;
      }
  }
}

