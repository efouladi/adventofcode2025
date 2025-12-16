(ns shafo.adventofcode2025.day3
  (:require [clojure.java.io :as io]))

(defn get-batteries [length jolts]
  (loop [list (->> jolts
                   seq
                   (map #(Character/getNumericValue %)))
         tail-drop (dec length)
         result 0]
    (if (neg-int? tail-drop)
      result
      (let [without-tail (drop-last tail-drop list)
            ;; get the first biggest digit with its index
            first-biggest-digit-with-index (apply max-key second (->> without-tail
                                                                      (map-indexed vector)
                                                                      ;; max-key returns the last biggest
                                                                      ;; if there are duplicates.
                                                                      ;; We want first biggest hence reverse
                                                                      reverse))]
        (recur (drop (inc (first first-biggest-digit-with-index)) list)
               (dec tail-drop)
               (+ result (-> first-biggest-digit-with-index
                             second
                             (* (long (Math/pow 10 tail-drop))))))))))

(defn get-solution-for-length [l]
  (with-open [input (io/reader (io/resource "input3.txt"))]
    (->> input
         line-seq
         (map (partial get-batteries l))
         (reduce + 0))))

(defn part1 []
  (get-solution-for-length 2))

(defn part2 []
  (get-solution-for-length 12))

