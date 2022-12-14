(ns aoc2021.day13-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day13 :refer [count-dots run]]))

(def input
  {
   :dots [[6 10]
          [0 14]
          [9 10]
          [0 3]
          [10 4]
          [4 11]
          [6 0]
          [6 12]
          [4 1]
          [0 13]
          [10 12]
          [3 4]
          [3 0]
          [8 4]
          [1 10]
          [2 14]
          [8 10]
          [9 0]]
   
   :folds [["y" 7]
           ["x" 5]]})
  
(deftest test-run-1
  (testing "Should calculate all dots after folds"
    (is (= (count-dots (run input 1)) 17))))

(deftest test-run-2
  (testing "Should calculate all dots after folds"
    (is (= (count-dots (run input 2)) 16))))