import java.util.Comparator;
import java.util.Random;
import java.lang.StringBuilder;
import java.io.FileWriter;

class Main {
    static int ARRAY_SIZE = 10;
    final static int STRING_SIZE = 20;

    static class IntegerComparator implements Comparator<Integer>{
        public int compare(Integer c1, Integer c2){
            return c2.compareTo(c1);
        }
    }
    static class StringComparator implements Comparator<String>{
        public int compare(String c1, String c2){
            return c1.compareTo(c2);
        }
    }
    static class FloatComparator implements Comparator<Float>{
        public int compare(Float c1, Float c2){
            return new Float(c2).compareTo(new Float(c1));
        }
    }
    //Helper function to print a nice table, requires the sorted arrays with Ascending and Descending
    //Also takes the sort type for printing
    static public void printTable(
            String[]    sortedStringA, 
            Integer[]   sortedIntegerA, 
            Float[]     sortedFloatA,
            String[]    sortedStringD, 
            Integer[]   sortedIntegerD, 
            Float[]     sortedFloatD, 
            String      sortType
            ){
        System.out.format("%n%40s%n============================================================", 
                sortType + " Ascending");
        System.out.format("%n%" + STRING_SIZE + "s |  %s  | %17s%n", 
                "Sorted Strings", "Sorted Integers", "Sorted Floats");
        for(int i =0; i < ARRAY_SIZE; i++){
            System.out.format("%s | %17d | %17f%n", 
                    sortedStringA[i], sortedIntegerA[i], sortedFloatA[i]);
        }
        System.out.format("%n%40s%n============================================================", 
                sortType + " Descending");
        System.out.format("%n%" + STRING_SIZE + "s |  %s  | %17s%n", 
                "Sorted Strings", "Sorted Integers", "Sorted Floats");
        for(int i =0; i < ARRAY_SIZE; i++){
            System.out.format("%s | %17d | %17f%n", 
                    sortedStringD[i], sortedIntegerD[i], sortedFloatD[i]);
        }


    }

    public static void main(String[] args) {
        if(args.length > 0){
            ARRAY_SIZE = Integer.parseInt(args[0]);
        }

        //Create the random object
        Random r = new Random();

        //Create the comparator to pass in
        IntegerComparator integerComparator = new IntegerComparator();
        StringComparator stringComparator = new StringComparator();
        FloatComparator floatComparator = new FloatComparator();

        //Initialize the unsorted int arrays
        String[] unSortedStringArray = new String[ARRAY_SIZE];
        Float[] unSortedFloatArray = new Float[ARRAY_SIZE];
        Integer[] unSortedIntegerArray = new Integer[ARRAY_SIZE];

        //Fill the array with strings with random characters 
        //up to the size of the constants above
        System.out.format("%" + STRING_SIZE + "s | %s | %17s%n", 
                "Unsorted Strings", "Unsorted Integers", "Unsorted Floats");

        for (int j = 0; j < ARRAY_SIZE; j++){
            StringBuilder sb = new StringBuilder(STRING_SIZE);
            for (int i = 0; i < STRING_SIZE; i++){
                sb.append((char)(r.nextInt(26) + 'a'));
            }
            unSortedStringArray[j] = new String(sb.toString());
            unSortedIntegerArray[j] = new Integer(r.nextInt());
            unSortedFloatArray[j] = new Float(r.nextFloat());
            System.out.format("%s | %17d | %17f%n", 
                    unSortedStringArray[j], unSortedIntegerArray[j], unSortedFloatArray[j]);
        }
        printTable(
            bubblesort.Sort.SortThis(stringComparator, unSortedStringArray.clone(), bubblesort.Sort.ASCENDING),
            bubblesort.Sort.SortThis(integerComparator, unSortedIntegerArray.clone(), bubblesort.Sort.ASCENDING),
            bubblesort.Sort.SortThis(floatComparator, unSortedFloatArray.clone(), bubblesort.Sort.ASCENDING),
            bubblesort.Sort.SortThis(stringComparator, unSortedStringArray.clone(), bubblesort.Sort.DESCENDING),
            bubblesort.Sort.SortThis(integerComparator, unSortedIntegerArray.clone(), bubblesort.Sort.DESCENDING),
            bubblesort.Sort.SortThis(floatComparator, unSortedFloatArray.clone(), bubblesort.Sort.DESCENDING),
            "BubbleSort"
            );
        try{
        FileWriter fw = new FileWriter("java.csv", true) ; 
        fw.write('\n'); 
        fw.close();
        }catch(Exception e){
        }
     
        mergesort.Sort mergeSort = new mergesort.Sort();
        printTable(
            mergeSort.SortThis(stringComparator, unSortedStringArray.clone(), mergesort.Sort.ASCENDING),
            mergeSort.SortThis(integerComparator, unSortedIntegerArray.clone(), mergesort.Sort.ASCENDING),
            mergeSort.SortThis(floatComparator, unSortedFloatArray.clone(), mergesort.Sort.ASCENDING),
            mergeSort.SortThis(stringComparator, unSortedStringArray.clone(), mergesort.Sort.DESCENDING),
            mergeSort.SortThis(integerComparator, unSortedIntegerArray.clone(), mergesort.Sort.DESCENDING),
            mergeSort.SortThis(floatComparator, unSortedFloatArray.clone(), mergesort.Sort.DESCENDING),
            "MergeSort"
            );
        try{
        FileWriter fw = new FileWriter("java.csv", true) ; 
        fw.write('\n'); 
        fw.close();
        }catch(Exception e){
        }

        quicksort.Sort quickSort = new quicksort.Sort();
        printTable(
            quickSort.SortThis(stringComparator, unSortedStringArray.clone(), quicksort.Sort.ASCENDING),
            quickSort.SortThis(integerComparator, unSortedIntegerArray.clone(), quicksort.Sort.ASCENDING),
            quickSort.SortThis(floatComparator, unSortedFloatArray.clone(), quicksort.Sort.ASCENDING),
            quickSort.SortThis(stringComparator, unSortedStringArray.clone(), quicksort.Sort.DESCENDING),
            quickSort.SortThis(integerComparator, unSortedIntegerArray.clone(), quicksort.Sort.DESCENDING),
            quickSort.SortThis(floatComparator, unSortedFloatArray.clone(), quicksort.Sort.DESCENDING),
            "QuickSort"
            );
        try{
        FileWriter fw = new FileWriter("java.csv", true) ; 
        fw.write('\n'); 
        fw.close();
        }catch(Exception e){

        }

        System.out.println("\nIf this is too long, probably best to pipe it to less");
        System.out.println("scala Main | less\n");
        System.out.println("Don't forget to compile first with:");
        System.out.println("scalac Main.scala bubblesort/Sort.scala mergesort/Sort.scala quicksort/Sort.scala\n");
    }
}
