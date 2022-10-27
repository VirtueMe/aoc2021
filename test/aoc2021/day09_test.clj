(ns aoc2021.day09-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day09 :refer [part-1 find-basin part-2]]))

(def input [
        [2 1 9 9 9 4 3 2 1 0]
        [3 9 8 7 8 9 4 9 2 1]
        [9 8 5 6 7 8 9 8 9 2]
        [8 7 6 7 8 9 6 7 8 9]
        [9 8 9 9 9 6 5 6 7 8]
])

(deftest test-part-1
  (testing "Should find the correct risk"
    (is (= (part-1 input) 15))))

(deftest test-find-basin
  (testing "Should find all spots in basin"
    (is (= (find-basin input [0 1]) [[0 1] [0 0] [1 0]]))))

(deftest test-find-basin-right
  (testing "Should find all spots in basin for right top"
    (is (= (find-basin input [0 9]) [[0 9] '(0 8) '(1 9) '(0 7) '(1 8) '(2 9) '(0 6) '(0 5) '(1 6)]))))

(deftest test-part-2
  (testing "Should be able to calculate the product of the 3 largest basins"
    (is (= (part-2 input) 1134))))