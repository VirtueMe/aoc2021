(ns aoc2021.day11-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day11 :refer [looper part-1 part-2]]))

(def input [[5 4 8 3 1 4 3 2 2 3]
            [2 7 4 5 8 5 4 7 1 1]
            [5 2 6 4 5 5 6 1 7 3]
            [6 1 4 1 3 3 6 1 4 6]
            [6 3 5 7 3 8 5 4 7 8]
            [4 1 6 7 5 2 4 6 4 5]
            [2 1 7 6 8 4 1 7 2 1]
            [6 8 8 2 8 8 1 1 3 4]
            [4 8 4 6 8 4 8 5 5 4]
            [5 2 8 3 7 5 1 5 2 6]])

(deftest test-looper
  (testing "Should return the correct amount of flashes"
    (is (= (looper input 10) 204))))

(deftest test-part-1
  (testing "Should return the correct amount of flashes for the puzzle"
    (is (= (part-1 input) 1656))))

(deftest test-part-2
  (testing "Should return the amount of ticks for the board to flash simultaneously"
    (is (= (part-2 input) 195))))