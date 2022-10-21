(ns aoc2021.day03-test
    (:require [clojure.test :refer [deftest testing is]]
            [aoc2021.day03 :refer [mirror strip gamma-rate part-1 oxygen-generator co2-scrubber part-2]]))

(def input ["00100"
   "11110"
   "10110"
   "10111"
   "10101"
   "01111"
   "00111"
   "11100"
   "10000"
   "11001"
   "00010"
   "01010"])

(deftest test-mirror
  (testing "Should map each position to a new sequence"
    (is (= (mirror input) ["011110011100" "010001010101" "111111110000" "011101100011" "000111100100"]))))

(deftest test-strip
  (testing "Should strip away all zero's"
    (is (= (strip "110011001100") "111111"))))

(deftest test-gamma-rate
  (testing "Should map to correct result"
    (is (= (gamma-rate input) "10110"))))

(deftest test-part-1
  (testing "Should map to correct result"
    (is (= (part-1 input) 198))))

(deftest test-oxygen-generator
  (testing "Should get the correct oxygen generator value"
    (is (= (oxygen-generator input) "10111"))))

(deftest test-co2-scrubber
  (testing "Should get the correct co2 scrubber value"
    (is (= (co2-scrubber input) "01010"))))

(deftest test-life-support
  (testing "Should get the correct life support value"
    (is (= (part-2 input) 230))))