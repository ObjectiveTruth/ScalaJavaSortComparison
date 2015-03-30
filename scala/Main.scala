import java.util.Comparator;
import java.util.Random;
import java.lang.Integer;
import java.io.FileWriter;

object Main {
  var ARRAY_SIZE = 10;
  val STRING_SIZE = 20;

  def printTable(
    sortedStringA: Array[String],    
    sortedIntegerA: Array[Integer],   
    sortedFloatA: Array[Float],     
    sortedStringD: Array[String],    
    sortedIntegerD: Array[Integer],   
    sortedFloatD: Array[Float],     
    sortType: String
  ): Unit = {
    println(String.format("%n%40s%n============================================================", 
      sortType + " Ascending"));
    print(String.format("%n%" + STRING_SIZE + "s |  %s  | %17s%n", 
      "Sorted Strings", "Sorted Integers", "Sorted Floats"));
    for(i <- 0 until ARRAY_SIZE){
      print(String.format("%s | %17d | %17s%n", 
        sortedStringA(i), sortedIntegerA(i), sortedFloatA(i).toString));
    }
    println(String.format("%n%40s%n============================================================", 
      sortType + " Descending"));
    print(String.format("%n%" + STRING_SIZE + "s |  %s  | %17s%n", 
      "Sorted Strings", "Sorted Integers", "Sorted Floats"));
    for(i <- 0 until ARRAY_SIZE){
      print(String.format("%s | %17d | %17s%n", 
        sortedStringD(i), sortedIntegerD(i), sortedFloatD(i).toString));
    }
  }

  object IntegerComparator extends Comparator[Integer]{
    override def compare(c1:Integer, c2:Integer): Int = {
      c2.compareTo(c1);
    }
  }
  object StringComparator extends Comparator[String]{
    override def compare(c1:String, c2:String): Int = {
      c1.compareTo(c2);
    }
  }
  object FloatComparator extends Comparator[Float]{
    override def compare(c1:Float, c2:Float): Int = {
      c2.compareTo(c1);
    }
  }

  def main(args: Array[String]) {
    if(args.length > 0){
      ARRAY_SIZE = args(0).toInt;
    }



    //Create the random object
    val r = new Random();

    val unSortedStringArray = new Array[String](ARRAY_SIZE);
    val unSortedFloatArray = new Array[Float](ARRAY_SIZE);
    val unSortedIntegerArray = new Array[Integer](ARRAY_SIZE);

    //Fill the array with strings with random characters 
    //up to the size of the constants above
    System.out.format("%" + STRING_SIZE + "s | %s | %17s%n", 
      "Unsorted Strings", "Unsorted Integers", "Unsorted Floats");

    for(j <- 0  until ARRAY_SIZE){
      val sb = new StringBuilder(STRING_SIZE);
      for (i <- 0 until STRING_SIZE){
        sb.append((r.nextInt(26) + 'a').asInstanceOf[Char]);
      }
      unSortedStringArray(j) = new String(sb.toString());
      unSortedIntegerArray(j) = new Integer(r.nextInt());
      unSortedFloatArray(j) = r.nextFloat();
      print(String.format("%s | %17d | %17s%n", 
        unSortedStringArray(j), unSortedIntegerArray(j), unSortedFloatArray(j).toString));
    }

    println("\n==== BubbleSort Performance ====");
    println("         in nanoseconds      ");

    printTable(
      bubblesort.Sort.SortThis(StringComparator, 
        unSortedStringArray.clone(), bubblesort.Sort.ASCENDING),
      bubblesort.Sort.SortThis(IntegerComparator, 
        unSortedIntegerArray.clone(), bubblesort.Sort.ASCENDING),
      bubblesort.Sort.SortThis(FloatComparator, 
        unSortedFloatArray.clone(), bubblesort.Sort.ASCENDING),
      bubblesort.Sort.SortThis(StringComparator, 
        unSortedStringArray.clone(), bubblesort.Sort.DESCENDING),
      bubblesort.Sort.SortThis(IntegerComparator, 
        unSortedIntegerArray.clone(), bubblesort.Sort.DESCENDING),
      bubblesort.Sort.SortThis(FloatComparator, 
        unSortedFloatArray.clone(), bubblesort.Sort.DESCENDING),
      "BubbleSort"
    );
    var fw = new FileWriter("scala.csv", true) ; 
    fw.write('\n'); 
    fw.close()

    println("\n==== MergeSort Performance ====");
    println("         in nanoseconds      ");

    printTable(
      mergesort.Sort.SortThis(StringComparator, 
        unSortedStringArray.clone(), mergesort.Sort.ASCENDING),
      mergesort.Sort.SortThis(IntegerComparator, 
        unSortedIntegerArray.clone(), mergesort.Sort.ASCENDING),
      mergesort.Sort.SortThis(FloatComparator, 
        unSortedFloatArray.clone(), mergesort.Sort.ASCENDING),
      mergesort.Sort.SortThis(StringComparator, 
        unSortedStringArray.clone(), mergesort.Sort.DESCENDING),
      mergesort.Sort.SortThis(IntegerComparator, 
        unSortedIntegerArray.clone(), mergesort.Sort.DESCENDING),
      mergesort.Sort.SortThis(FloatComparator, 
        unSortedFloatArray.clone(), mergesort.Sort.DESCENDING),
      "MergeSort"
    );
    fw = new FileWriter("scala.csv", true) ; 
    fw.write('\n'); 
    fw.close()

    println("\n==== QuickSort Performance ====");
    println("         in nanoseconds      ");

    printTable(
      quicksort.Sort.SortThis(StringComparator, 
        unSortedStringArray.clone(), quicksort.Sort.ASCENDING),
      quicksort.Sort.SortThis(IntegerComparator, 
        unSortedIntegerArray.clone(), quicksort.Sort.ASCENDING),
      quicksort.Sort.SortThis(FloatComparator, 
        unSortedFloatArray.clone(), quicksort.Sort.ASCENDING),
      quicksort.Sort.SortThis(StringComparator, 
        unSortedStringArray.clone(), quicksort.Sort.DESCENDING),
      quicksort.Sort.SortThis(IntegerComparator, 
        unSortedIntegerArray.clone(), quicksort.Sort.DESCENDING),
      quicksort.Sort.SortThis(FloatComparator, 
        unSortedFloatArray.clone(), quicksort.Sort.DESCENDING),
      "QuickSort"
    );
    fw = new FileWriter("scala.csv", true) ; 
    fw.write('\n'); 
    fw.close()

    println("\nIf this is too long, probably best to pipe it to less");
    println("scala Main | less\n");
    println("Don't forget to compile first with:");
    println("scalac Main.scala bubblesort/Sort.scala mergesort/Sort.scala quicksort/Sort.scala\n");
  }
}
