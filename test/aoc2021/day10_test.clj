(ns aoc2021.day10-test 
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day10 :refer [points is-start validate check sum-values sum-missing part-2]]))

(def input ["[({(<(())[]>[[{[]{<()<>>"
            "[(()[<>])]({[<{<<[]>>("
            "{([(<{}[<>[]}>{[]{[(<()>"
            "(((({<>}<{<{<>}{[]{[]{}"
            "[[<[([]))<([[{}[[()]]]"
            "[{[{({}]{}}([{[{{{}}([]"
            "{<[[]]>}<{[{[{[]{()[[[]"
            "[<(<(<(<{}))><([]([]()"
            "<{([([[(<>()){}]>(<<{{"
            "<{([{{}}[<[[[<>{}]]]>[]]"])

(deftest test-points
  (testing "Should map points"
    (is (= (map points ")]}>") '(3 57 1197 25137)))))

(deftest test-is-start
  (testing "Should find start"
    (is (= (is-start (first "(")) true))))

(deftest test-validate-valid
  (testing "Should validate valid sequence"
    (is (nil? (validate "()")))))

(deftest test-validate-unvalid
  (testing "Should return last unvalid char"
    (is (= (validate "(>") [false (first ">")]))))

(deftest test-check
  (testing "Should return all illigals"
    (is (= (check input) [(first "}")  (first ")") (first "]") (first ")") (first ">")]))))

(deftest test-sum
  (testing "Should sum of illegal chars"
    (is (= (sum-values input) 26397))))

(deftest test-sum-missing
  (testing "Should calculate correctly the sum of missing endings"
    (is (= (sum-missing [(first "[") (first "(") (first "{") (first "<")]) 294))))

(deftest test-part-2
  (testing "Should return all values of incomplete lines"
    (is (= (part-2 input) 288957) )))