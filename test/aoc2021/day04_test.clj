(ns aoc2021.day04-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day04 :refer [create-board check play-part-1 calculate-score part-1 part-2]]))
(def input
  {:draw [7 4 9 5 11 17 23 2 0 14 21 24 10 16 13 6 15 25 12 22 18 20 8 19 3 26 1]
   :boards [(create-board [[22 13 17 11  0]
                           [8  2 23  4 24]
                           [21  9 14 16  7]
                           [6 10  3 18  5]
                           [1 12 20 15 19]])
            (create-board [[3 15  0  2 22]
                           [9 18 13 17  5]
                           [19  8  7 25 23]
                           [20 11 10 24  4]
                           [14 21 16 12  6]])
            (create-board [[14 21 17 24  4]
                           [10 16 15  9 19]
                           [18  8 23 26 20]
                           [22 11 13  6  5]
                           [2  0 12  3  7]])]})

(deftest test-check
  (testing "Should marked hit in rows and columns"
    (let [value (first (:draw input)) checked-board (check (first (:boards input)) value [])]
      (is (= (:r checked-board) [0 0 1 0 0]))
      (is (= (:c checked-board) [0 0 0 0 1]))))) 1

(deftest test-play
  (testing "Should find winner"
    (is (= (play-part-1 input) [(:b (nth (:boards input) 2)) 24 [7 4 9 5 11 17 23 2 0 14 21]]))))

(deftest test-calculate-score
  (testing "Should find correct score from selected board"
    (is (= (calculate-score (:b (nth (:boards input) 2)) 24 [7 4 9 5 11 17 23 2 0 14 21]) 4512))))

(deftest test-part-1
  (testing "Should find correct score"
    (is (= (part-1 input) 4512))))

(deftest test-part-1
  (testing "Should find correct score"
    (is (= (part-2 input) 1924))))









