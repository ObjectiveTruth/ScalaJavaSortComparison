(ns helper)

(use '[clojure.string :only (join)])
(def charset "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn makeString [] 
  (join (repeatedly 20 #(.charAt charset (rand-int 51)))))

(defn makeSequenceInt [seqMaxSize] 
  (repeatedly seqMaxSize #(rand-int Integer/MAX_VALUE)))

(defn makeSequenceFloat [seqMaxSize] 
  (repeatedly seqMaxSize #(rand)))

(defn makeSequenceString [seqMaxSize] 
  (repeatedly 20 #(makeString)))

(defn printPretty [seq1 seq2 seq3 message]
  (do
    (println "\n==================" (format "%24s" message) "==================")
    (println 
      (format "%13s" "Ints")
      (format "%22s" "Floats")
      (format "%25s" "String")
    )
    (dotimes [n (count seq1)]
      (println 
        (format "%13s" (nth seq1 n)) 
        (format "%22s" (nth seq2 n)) 
        (format "%25s" (nth seq3 n))
      )
    )
    (println)
  )
)


