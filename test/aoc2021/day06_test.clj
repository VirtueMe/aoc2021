(ns aoc2021.day06-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day06 :refer [tick life-span part-1]]))

(def input (frequencies [3 4 3 1 2]))

(deftest test-life
  (testing "Should increase fishes properly"
    (is (= (tick input) {0 1, 7 0, 1 1, 4 0, 6 0, 3 1, 2 2, 5 0, 8 0}))))

(deftest test-life-span-double
  (testing "Should increase fishes properly"
    (is (= (life-span input 2) 6))))

(deftest test-part-1
  (testing "Should calculate the right count of fishes"
    (is (= (part-1 input) 5934))))

(deftest test-part-1-forever
  (testing "Should calculate the right count of fishes when forever"
    (is (= (part-1 input 256) 26984457539))))

