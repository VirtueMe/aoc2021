(ns aoc2021.day12-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day12 :refer [part-1 part-2]]))

(def input [
            ["start" "A"]
            ["start" "b"]
            ["A" "c"]
            ["A" "b"]
            ["b" "d"]
            ["A" "end"]
            ["b" "end"]
])

(deftest test-part-1
  (testing "Should calculate all paths through the cave"
    (is (= (part-1 input) 10))))

(deftest test-part-2
  (testing "Should calculate all paths through the cave"
    (is (= (part-2 input) 36))))