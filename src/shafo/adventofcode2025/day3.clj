(ns shafo.adventofcode2025.day3
  (:require [clojure.java.io :as io]))

(defn part1 []
  (with-open [input (io/reader (io/resource "input3.txt"))]
    (reduce (fn [result jolts]
              (let [numbers (->> jolts
                                 seq
                                 (map #(Character/getNumericValue %)))
                    first-digit (reduce-kv (fn [r k v]
                                             (if (<= v (second r)) r [k v]))
                                           [0 0]
                                           (-> numbers
                                               butlast
                                               vec))  
                    second-digit (apply max (-> first-digit
                                                first
                                                inc
                                                (drop numbers)))]
                (+ result (* (second first-digit) 10) second-digit)))
            0
            (line-seq input))))
