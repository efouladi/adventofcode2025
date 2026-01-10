(ns shafo.adventofcode2025.day7
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string]))

(let [lines (-> "input7.txt" io/resource slurp string/split-lines)
      first-line (first lines)
      s-index (.indexOf first-line "S")]
  (->> lines
       rest
       (reduce (fn [result l]
                 (let [splits (-> result :splits atom)]
                   {:beam-idx (->> result
                                   :beam-idx
                                   (map #(case (.charAt l %)
                                           \. %
                                           \^ (do (swap! splits inc)
                                                  [(dec %) (inc %)])))

                                   flatten
                                   (filter #(< %(.length l)))
                                   set)
                    :splits @splits}))
               {:splits 0
                :beam-idx [s-index]})
       :splits))
