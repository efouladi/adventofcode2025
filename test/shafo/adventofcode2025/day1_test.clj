(ns shafo.adventofcode2025.day1-test
  (:require [clojure.test :refer [deftest is testing]]
            [shafo.adventofcode2025.day1 :as sut]))

(deftest part1-test
  (testing "Day 1 - Part 1"
    (is (= {:passcode 999 :position 17}
           (sut/part1)))))

(deftest part2-test
  (testing "Day 1 - Part 2"
    (is (= {:passcode 6099 :position 17}
           (sut/part2)))))
