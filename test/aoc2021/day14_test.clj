(ns aoc2021.day14-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day14 :refer [parse-polymer parse-matchers polymerize run-polymerize part-1 part-2]]))


(def input {:template (parse-polymer "NNCB")
            :matchers (parse-matchers ["CH -> B"
                       "HH -> N"
                       "CB -> H"
                       "NH -> C"
                       "HB -> C"
                       "HC -> B"
                       "HN -> C"
                       "NN -> C"
                       "BH -> H"
                       "NC -> B"
                       "NB -> B"
                       "BN -> B"
                       "BB -> N"
                       "BC -> B"
                       "CC -> N"
                       "CN -> C"])})

(deftest test-polymerize
  (testing "Should create the correct string from template"
    (is (= (polymerize (input :template) (input :matchers)) {"NC" 1 "CN" 1 "NB" 1 "BC" 1 "CH" 1 "HB" 1}))))

(deftest test-run-polymerize-1
  (testing "Should create the correct string from template on first round"
    (is (= (run-polymerize input 1) {"NC" 1 "CN" 1 "NB" 1 "BC" 1 "CH" 1 "HB" 1}))))

(deftest test-run-polymerize-2
  (testing "Should create the correct string from template after second round"
    (is (= (run-polymerize input 2) {"NB" 2 "BC" 2 "CC" 1 "CN" 1 "BB" 2 "CB" 2 "BH" 1 "HC" 1}))))

(deftest test-run-polymerize-3
  (testing "Should create the correct string from template after third round"
    (is (= (run-polymerize input 3) {"CH" 2 "HH" 1 "BH" 1 "BN" 2 "NB" 4 "HB" 3 "BC" 3 "CN" 2 "CC" 1 "BB" 4 "NC" 1}))))

(deftest test-run-polymerize-4
  (testing "Should create the correct string from template after third round"
    (is (= (run-polymerize input 4) {"HH" 1 "BH" 3 "BN" 6 "NH" 1 "NB" 9 "BC" 4 "CN" 3 "CC" 2 "BB" 9 "CB" 5 "HN" 1 "HC" 3 "NC" 1}))))

(deftest test-part-1
  (testing "Should calculate the corrrect polymerazation value"
    (is (= (part-1 input) 1588))))

(deftest test-part-2
  (testing "Should calculate the correct polymerization value for 40 runs"
    (is (= (part-2 input) 2188189693529))))