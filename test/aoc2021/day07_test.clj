(ns aoc2021.day07-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day07 :refer [initialize calculate part-1 part-2]]))

(def input (frequencies [16 1 2 0 4 2 7 1 2 14]))

(deftest test-initialize
  (testing "Should set up all possible paths"
    (is (= (initialize input) {0 {16 16, 1 1, 2 2, 0 0, 4 4, 7 7, 14 14}, 7 {16 9, 1 6, 2 5, 0 7, 4 3, 7 0, 14 7}, 1 {16 15, 1 0, 2 1, 0 1, 4 3, 7 6, 14 13}, 4 {16 12, 1 3, 2 2, 0 4, 4 0, 7 3, 14 10}, 15 {16 1, 1 14, 2 13, 0 15, 4 11, 7 8, 14 1}, 13 {16 3, 1 12, 2 11, 0 13, 4 9, 7 6, 14 1}, 6 {16 10, 1 5, 2 4, 0 6, 4 2, 7 1, 14 8}, 3 {16 13, 1 2, 2 1, 0 3, 4 1, 7 4, 14 11}, 12 {16 4, 1 11, 2 10, 0 12, 4 8, 7 5, 14 2}, 2 {16 14, 1 1, 2 0, 0 2, 4 2, 7 5, 14 12}, 11 {16 5, 1 10, 2 9, 0 11, 4 7, 7 4, 14 3}, 9 {16 7, 1 8, 2 7, 0 9, 4 5, 7 2, 14 5}, 5 {16 11, 1 4, 2 3, 0 5, 4 1, 7 2, 14 9}, 14 {16 2, 1 13, 2 12, 0 14, 4 10, 7 7, 14 0}, 16 {16 0, 1 15, 2 14, 0 16, 4 12, 7 9, 14 2}, 10 {16 6, 1 9, 2 8, 0 10, 4 6, 7 3, 14 4}, 8 {16 8, 1 7, 2 6, 0 8, 4 4, 7 1, 14 6}}))))

(deftest test-calculate
  (testing "Should find all sums of paths"
    (is (= (calculate input) {0 49, 7 53, 1 41, 4 41, 15 103, 13 89, 6 49, 3 39, 12 83, 2 37, 11 77, 9 65, 5 45, 14 95, 16 111, 10 71, 8 59}))))

(deftest test-part-1
  (testing "Should find the shortest path for all crabs"
    (is (= (part-1 input) [2 37]))))

(deftest test-part-2
  (testing "Should find the shortest path for all crabs"
    (is (= (part-2 input) [5 168]))))