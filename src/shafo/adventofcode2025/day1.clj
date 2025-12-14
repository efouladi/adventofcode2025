(ns shafo.adventofcode2025.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))


(defn part1 []
  (with-open [input (io/reader (io/resource "input1-1.txt"))]
    (let [passcode (atom 0)
          start (atom 50)]
      (doseq [l (line-seq input)]
        (let [dir (first l)
              num (->> l rest (apply str) Integer/parseInt)
              op (if (= \L dir) - +)
              val (swap! start #(mod (op %1 %2) 100) num)]
          (when (= 0 val)
            (swap! passcode inc))))
      @passcode)))

(defn part2[]
  (with-open [input (io/reader (io/resource "input1-1.txt"))]
    (let [passcode (atom 0)
          position (atom 50)]
      (doseq [l (line-seq input)]
        (let [dir (first l)
              num (->> l rest (apply str) Integer/parseInt)
              op (if (= \L dir) - +)
              delta (mod num 100)
              next-position (op @position delta)]
          ;; Adding full rotations
          (swap! passcode + (abs (int (/ num 100))))
          (when (or (and (> next-position 99) (= dir \R))
                    (and (< next-position 1) (= dir \L) (not (= 0 @position))))
            (swap! passcode inc))
          (reset! position (mod next-position 100))))
      @passcode)))
