(ns shafo.adventofcode2025.day2
  (:require [clojure.string :as string]))

(defn invalid-part1? [n]
  (let [s (str n)
        c (count s)
        middle (/ c 2)]
    (when (int? middle)
      (= (take middle s) (drop middle s)))))

(defn invalid-part2? [n]
  (let [s (str n)
        middle (int (/ (count s) 2))
        parts (map #(partition-all %1 s) (range 1 (inc middle)))]
    (seq (filter #(apply = %1) parts))))

(defn get-invalid-ids [validity-fn]
  (let [ranges-str (-> "resources/input2.txt"
                       slurp 
                       string/trim
                       (string/split #","))
        ranges (map (fn [r] (map parse-long (string/split r #"-"))) ranges-str)]
    (reduce (fn [i r]
              (let [ar (apply range r)
                    sum-invalids (->> ar (filter validity-fn) (reduce +))]
                (+ i sum-invalids)))
            0
            ranges)))

(get-invalid-ids invalid-part1?)
(get-invalid-ids invalid-part2?)
