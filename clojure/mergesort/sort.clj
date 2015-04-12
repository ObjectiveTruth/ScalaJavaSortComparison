(ns merge.sort)

(defn swap [v i1 i2] 
     (assoc v i2 (v i1) i1 (v i2)))

(defn sortMe [unsortedVec customComparator] 
  (loop [i 0
         sorted true
         sorting unsortedVec
        ]
    (if (< (inc i) (count unsortedVec))    
      (if (< (customComparator (nth sorting i) (nth sorting (inc i))) 0)
        (recur (inc i) false (swap sorting i (inc i)))
        (recur (inc i) sorted sorting)
      )
      (if (true? sorted)
        sorting
        (recur 0 true sorting)
      )
    )
  )
)
