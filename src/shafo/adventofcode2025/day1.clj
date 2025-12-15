(ns shafo.adventofcode2025.day1
  (:require [clojure.java.io :as io]))


(defn part1 []
  (with-open [input (io/reader (io/resource "input1-1.txt"))]
    (let [state {:passcode 0
                 :position 50}]
      (reduce (fn [state l]
                  (let [dir (first l)
                        num (->> l rest (apply str) Integer/parseInt)
                        op (if (= \L dir) - +)
                        new-position (mod (op (:position state) num) 100)]
                    (-> state
                        (assoc :position new-position)
                        (update :passcode (fn [p] (if (= 0 new-position) (inc p) p))))))
              state
              (line-seq input)))))

(defn part2[]
  (with-open [input (io/reader (io/resource "input1-1.txt"))]
    (let [state {:passcode 0
                 :position 50}]
      (reduce (fn [state l]
                (let [dir (first l)
                      num (->> l rest (apply str) Integer/parseInt)
                      op (if (= \L dir) - +)
                      delta (mod num 100)
                      next-position (op (:position state) delta)]
                  (-> state
                      ;; Adding full rotations
                      (update :passcode + (abs (int (/ num 100))))
                      (update :passcode (fn [p]
                                          (if (or (and (> next-position 99) (= dir \R))
                                                  (and (< next-position 1) (= dir \L) (not (= 0 (:position state)))))
                                            (inc p)
                                            p)))
                      (assoc :position (mod next-position 100)))))
              state
              (line-seq input)))))
