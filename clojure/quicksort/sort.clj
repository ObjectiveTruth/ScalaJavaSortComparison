(ns quick.sort)

(defn swap [v i1 i2] 
     (assoc v i2 (v i1) i1 (v i2)))

(defn findPivot [unsorted customComparator]
  (loop [pivot 0
         left 0
         right (dec (count unsorted))
         sorting unsorted
         ]
    (if
      (= left right)
      [sorting pivot]
      (if 
        (= pivot left)
        (if 
          (< (customComparator (get sorting pivot) (get sorting right)) 0)
          (recur left left (dec right) sorting)
          (recur right left right (swap sorting pivot right)))
        (if
          (> (customComparator (get sorting pivot) (get sorting left)) 0)
          (recur right (inc left) right sorting)
          (recur left left right(swap sorting pivot left)))))))

(defn _quicksort [unsorted customComparator]
  (let [[modified pivot] (findPivot unsorted customComparator)]
    (into []
      (concat  
        (if
          (>= (inc pivot) (count modified))
          []
          (_quicksort (subvec modified (inc pivot)) customComparator))
        [(get modified pivot)]
        (if
          (< (dec pivot) 0)
          []
          (_quicksort (subvec modified 0 pivot) customComparator))))))

(defn sortMe [unsortedVec customComparator] 
  (_quicksort unsortedVec customComparator)
)
