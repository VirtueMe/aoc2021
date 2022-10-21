(ns aoc2021.day01-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day01 :refer [find-count create-sliding-windows]]))

(def input [199 200 208 210 200 207 240 269 260 263])

(deftest find-numbers-test
  (testing "Should return correct count from array"
    (is (= (find-count input) 7))))

(deftest create-sliding-windows-test
  (testing "Should create sliding window values"
    (is (= (count (create-sliding-windows input)) 8))))

(deftest find-numbers-sliding-window-test
  (testing "Should return correct count from sliding array"
    (is (= (find-count (create-sliding-windows input)) 5))))