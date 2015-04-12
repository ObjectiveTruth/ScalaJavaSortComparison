(ns merge.sort)

(defn split-at-vec [mid splitme]
      [(subvec splitme 0 mid) (subvec splitme mid)])

(defn mergestep [leftside rightside sorted customComparator]
  (if 
    (and (not (empty? leftside)) (not (empty? rightside)))
    (if 
      (> (customComparator (first leftside) (first rightside)) 0)
      (mergestep 
        (subvec leftside 1) 
        rightside 
        (into [] (conj sorted (first leftside)))
        customComparator)
      (mergestep 
        (subvec rightside 1) 
        leftside 
        (into [] (conj sorted (first rightside)))
        customComparator))
    (into [] (concat sorted leftside rightside))))

(defn mergesort [sorting customComparator]
  (if (> (count sorting) 1)
    (let [sides (split-at-vec (/ (count sorting) 2) sorting)] 
      (mergestep 
        (mergesort (get sides 0) customComparator)
        (mergesort (get sides 1) customComparator) 
        []
        customComparator))
    sorting
  )
)

(defn sortMe [unsortedVec customComparator] 
  (mergesort unsortedVec customComparator)
)
