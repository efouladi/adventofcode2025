(ns shafo.adventofcode2025.day2-test
  (:require [clojure.test :refer [deftest is testing]]
            [shafo.adventofcode2025.day2 :as sut]))

(deftest part1-test
  (testing "Day 2 - Part 1"
    (is (= 21898734247
           (sut/get-invalid-ids sut/invalid-part1?)))))

(deftest part2-test
  (testing "Day 2 - Part 2"
    (is (= 28915664389
           (sut/get-invalid-ids sut/invalid-part2?)))))
