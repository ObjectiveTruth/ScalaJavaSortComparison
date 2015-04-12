(ns main)
(use '[clojure.string :only (join)])

(require `[bubblesort.sort])
(require `[mergesort.sort])
(require `[quicksort.sort])
(require `[helper])

(def sequenceSize 10)

(def unsortedInts (vec (helper/makeSequenceInt sequenceSize)))
(def unsortedFloats (vec (helper/makeSequenceFloat sequenceSize)))
(def unsortedStrings (vec (helper/makeSequenceString sequenceSize)))

(def intFloatComparatorAscending 
  (comparator (fn [x y] (> x y))))

(def intFloatComparatorDescending 
  (comparator (fn [x y] (< x y))))

(defn stringComparatorAscending [x y]
  (* -1 (compare x y)))

(defn stringComparatorDescending [x y]
  (compare x y))

(helper/printPretty 
  unsortedInts 
  unsortedFloats 
  unsortedStrings
  "Unsorted         "
)

(helper/printPretty
 (bubble.sort/sortMe unsortedInts intFloatComparatorAscending)
  (bubble.sort/sortMe unsortedFloats intFloatComparatorAscending)
  (bubble.sort/sortMe unsortedStrings stringComparatorAscending)
  "Bubblesort Ascending"
)

(helper/printPretty
  (bubble.sort/sortMe unsortedInts intFloatComparatorDescending)
  (bubble.sort/sortMe unsortedFloats intFloatComparatorDescending)
  (bubble.sort/sortMe unsortedStrings stringComparatorDescending)
  "Bubblesort Descending"
)

(helper/printPretty
  (merge.sort/sortMe unsortedInts intFloatComparatorAscending)
  (merge.sort/sortMe unsortedFloats intFloatComparatorAscending)
  (merge.sort/sortMe unsortedStrings stringComparatorAscending)
  "Mergesort Ascending"
)

(helper/printPretty
  (merge.sort/sortMe unsortedInts intFloatComparatorDescending)
  (merge.sort/sortMe unsortedFloats intFloatComparatorDescending)
  (merge.sort/sortMe unsortedStrings stringComparatorDescending)
  "Mergesort Descending"
)

(helper/printPretty
  (quick.sort/sortMe unsortedInts intFloatComparatorAscending)
  (quick.sort/sortMe unsortedFloats intFloatComparatorAscending)
  (quick.sort/sortMe unsortedStrings stringComparatorAscending)
  "Quicksort Ascending"
)

(helper/printPretty
  (quick.sort/sortMe unsortedInts intFloatComparatorDescending)
  (quick.sort/sortMe unsortedFloats intFloatComparatorDescending)
  (quick.sort/sortMe unsortedStrings stringComparatorDescending)
  "Quicksort Descending"
)
