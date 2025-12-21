(ns shafo.adventofcode2025.day6 
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string]))

(->> "input6.txt"
     io/resource
     slurp
     string/split-lines

     (map #(string/split % #"\s+"))
     (apply map vector)
     (map (fn [l]
            (let [args (->> l
                            butlast
                            (map parse-long))
                  op (-> l last symbol resolve)]
              (apply op args)
              )))
     (reduce +)
     println)


(->> "input6.txt"
     io/resource
     slurp
     string/split-lines
     (map seq)
     (apply map vector)
     (partition-by (fn [l]
                     (every? #(= \space %) l)))
     (take-nth 2)
     (map (fn [p]
            (let [op (-> p first last str symbol resolve)
                  get-num-fn (fn [ln] (-> ln string/join string/trim parse-long))]
              (->> p
                   (map #(-> % butlast get-num-fn))
                   (reduce op)))))
     (reduce +)
     println)

