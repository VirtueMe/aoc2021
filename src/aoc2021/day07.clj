(ns aoc2021.day07
  (:gen-class)
  (:require [aoc2021.core :refer [get-lines split-words]]))

(def input (frequencies (map #(Integer. %) (flatten (map #(split-words % #",") (get-lines "resources/07.txt"))))))

(defn part-1-calc
  [input]
  input)

(defn part-2-calc
  [input]
  (apply + (range 1 (+ input 1))))

(defn initialize
  ([input] (initialize input part-1-calc))
  ([input calc]
  (loop [paths (range (+ 1 (apply max (keys input)))) result {}]
    (if (empty? paths)
      result
      (recur (rest paths) (assoc result (first paths) (zipmap (keys input) (map #(calc (abs (- (first paths) %))) (keys input)))))))))

(defn calculate
  ([input] (calculate input part-1-calc))
  ([input calc]
  (let [calcs (initialize input calc)]
    (reduce-kv (fn [m k v]
                 (assoc  m k (reduce-kv (fn [m2 k2 v2] (+ m2 (* (input k2) v2))) 0 v))) {} calcs))))

(defn find-min
  ([input] (find-min input part-1-calc))
  ([input calc]
  (let [results (calculate input calc)]
    (apply min-key val results))))

(defn part-1
  ([] (part-1 input))
  ([input] (find-min input)))

(defn part-2
  ([] (part-2 input))
  ([input] (find-min input part-2-calc)))