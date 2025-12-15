(ns shafo.adventofcode2025.day3-test
  (:require
   [clojure.test :refer [deftest is]]
   [shafo.adventofcode2025.day3 :as sut]))

(deftest part1-test
  (is (= 17766
         (sut/part1))))
