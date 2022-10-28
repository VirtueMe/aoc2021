(ns aoc2021.day10
  (:gen-class)
  (:require [aoc2021.core :refer [get-lines]]))

(def input (get-lines "resources/10.txt"))

(def points {(first ")") 3 (first "]") 57 (first "}") 1197 (first ">") 25137})
(def points-missing { (first "(") 1 (first "[") 2 (first "{") 3 (first "<") 4 })

(def matchers {(first ")") (first "(") (first "]") (first "[") (first "}") (first "{") (first ">") (first "<")})

(defn is-start
  [input]
  (not (nil? (some #{input} [ (first "(") (first "[") (first "{") (first "<") ]))))

(defn unvalid
  [char-first char-last]
  (not (= (matchers char-first) char-last))) 

(defn validate
  [input]
  (loop [items input members []]
    (if (empty? items)
      (if (empty? members ) nil [true members]) 
      (if (and (not (is-start (first items))) (unvalid (first items) (first members)))
        [false (first items)]
        (recur (rest items) (if (is-start (first items)) (concat [(first items)] members) (rest members)))))))

(defn basker
  [chooser items item]
  (let [[valid result] item]
    (if (= chooser valid)
      (conj items result)
      items)))

(defn check
  [& args]
  (let [[input merger] (if (= (count args) 2) args [(first args) (partial basker false)])]
    (loop [items input result []]
      (if (empty? items)
        result
        (let [item (validate (first items))]
          (recur (rest items) (if (nil? item) result (merger result item))))))))

(defn sum-values
  [input]
  (let [result (check input (partial basker false))]
    (apply + (map points result))))

(defn sum-missing
  [input]
  (reduce #(+ (* 5 %1) %2) 0 (map points-missing input)))

(defn part-1
  ([] (part-1 input))
  ([input] (sum-values input)))

(defn find-middle-value
  [input]
  (let [result (sort (map sum-missing (check input (partial basker true))))]
    (nth result (quot (count result) 2))))

(defn part-2
  ([] (part-2 input))
  ([input] (find-middle-value input)))


