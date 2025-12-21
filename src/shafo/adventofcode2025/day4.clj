(ns shafo.adventofcode2025.day4 
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string]))

;; part 1
(let [input (-> "input4.txt"
                io/resource
                slurp
                string/split-lines)
      result (for [[i row] (map-indexed list input)
                   [j cell] (map-indexed list (seq row))
                   :when (= \@ cell)]
               {:x i :y j})
      rt (set result)]
  (->> result
      (filter (fn [roll]
                (let [a (-> roll (update :x dec) (update :y dec))
                      b (-> roll (update :y dec))
                      c (-> roll (update :x inc) (update :y dec))
                      d (-> roll (update :x inc))
                      e (-> roll (update :x inc) (update :y inc))
                      f (-> roll (update :y inc))
                      g (-> roll (update :x dec) (update :y inc))
                      h (-> roll (update :x dec))]
                  (> 4 (reduce (fn [i x] (if (rt x) (inc i) i)) 0 [a b c d e f g h] )))))
      count))



;; part 2
(let [input (-> "input4.txt"
                io/resource
                slurp
                string/split-lines)]
  (loop [result (for [[i row] (map-indexed list input)
                      [j cell] (map-indexed list (seq row))
                      :when (= \@ cell)]
                  {:x i :y j})
         removed 0]
    (let [rt (set result)
          filtred (filter (fn [roll]
                            (let [a (-> roll (update :x dec) (update :y dec))
                                  b (-> roll (update :y dec))
                                  c (-> roll (update :x inc) (update :y dec))
                                  d (-> roll (update :x inc))
                                  e (-> roll (update :x inc) (update :y inc))
                                  f (-> roll (update :y inc))
                                  g (-> roll (update :x dec) (update :y inc))
                                  h (-> roll (update :x dec))]
                              (< 3 (reduce (fn [i x] (if (rt x) (inc i) i)) 0 [a b c d e f g h])))) result)
          rm-count (- (count result) (count filtred))]
      (if (= 0 rm-count)
        removed
        (recur filtred
               (+ removed rm-count))))))
