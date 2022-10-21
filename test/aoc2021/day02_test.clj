(ns aoc2021.day02-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day02 :refer [traverse forward up down aimcalculate]]))

(def input [["forward" "5"]
            ["down" "5"]
            ["forward" "8"]
            ["up" "3"]
            ["down" "8"]
            ["forward" "2"]])

(deftest test-forward-from-zero
  (testing "Should advance first element 5 position"
    (is (= (forward [0 0] 5) [5 0]))))

(deftest test-down-from-zero
  (testing "Should advance second element 5 position"
    (is (= (down [0 0] 5) [0 5]))))

(deftest test-up-to-zero
  (testing "Should advance second element 5 position"
    (is (= (up [0 2] 2) [0 0]))))

(deftest find-correct-position-part1
  (testing "Should return correct position from array" 
    (is (= (traverse input) [15 10]))))

(deftest find-correct-position-part2
  (testing "Should return correct position from array" 
    (is (= (traverse input aimcalculate) [15 60 10]))))