(ns aoc2021.day05-test

  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day05 :refer [build vert-or-hort create-path mark-map find-intersections part-1 part-2]]))

(def input (map build ["0,9 -> 5,9"
                       "8,0 -> 0,8"
                       "9,4 -> 3,4"
                       "2,2 -> 2,1"
                       "7,0 -> 7,4"
                       "6,4 -> 2,0"
                       "0,9 -> 2,9"
                       "3,4 -> 1,4"
                       "0,0 -> 8,8"
                       "5,5 -> 8,2"]))

(deftest test-build
  (testing "Should build correct"
    (is (= (first input) [[0 9] [5 9]]))))

(deftest test-vert-or-hort
  (testing "Should filter away other directions"
    (is (= (count (vert-or-hort input)) 6))))

(deftest test-create-path
  (testing "Should create perfect path"
    (is (= (create-path (first input)) [[0 9] [1 9] [2 9] [3 9] [4 9] [5 9]]))))

(deftest test-mark-map
  (testing "Should add paths to map"
    (is (= (mark-map (take 1 input)) [[0 9] [1 9] [2 9] [3 9] [4 9] [5 9]]))))

(deftest test-intersections
  (testing "Should find all intersections"
    (let [items (find-intersections (mark-map (vert-or-hort input)))]
      (is (= (count items) 5)))))

(deftest test-part-1
  (testing "Should find all intersections"
    (is (= (part-1 input) 5))))

(deftest test-part-2
  (testing "Should find all intersections"
    (is (= (part-2 input) 12))))

