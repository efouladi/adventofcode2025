(ns shafo.adventofcode2025.day5 
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string]))

;; part 1
(with-open [input (-> "input5.txt" io/resource io/reader)]
  (:fresh-count (reduce (fn [init l]
                          (let [[x y] (string/split l #"-")]
                            (if (empty? x)
                              init
                              (if (empty? y)
                                (update init :fresh-count (fn [c]
                                                            (let [x (parse-long x)]
                                                              (if (some (fn [[s e]] (and (>= x s) (<= x e))) (:ranges init))
                                                                (inc c)
                                                                c))))
                                (update init :ranges conj  [(parse-long x) (parse-long y)] )))))
                        {:fresh-count 0
                         :ranges []}
                        (line-seq input))))

;; part 2
(with-open [input (-> "input5.txt" io/resource io/reader)]
  (->> input
      line-seq
      (filter (partial re-matches #"\d+-\d+"))
      (map (fn [l]
             (let [[s e] (string/split l #"-")]
               [(parse-long s) (parse-long e)])))
      (sort-by first)
      
      (reduce (fn [result rng]
                (if (empty? result)
                  [rng]
                  (let [tail (last result)]
                    (if (<= (first rng) (second tail))
                      (conj (vec (butlast result)) [(first tail) (max (second rng) (second tail))])
                      (conj (vec result) rng)))))
              [])
      (map #(inc (- (second %) (first %))))
      (reduce +)))
