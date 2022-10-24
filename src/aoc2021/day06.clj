(ns aoc2021.day06
  (:gen-class)
  (:require [aoc2021.core :refer [get-lines split-words]]))

(def input (frequencies (map #(Integer. %) (flatten (map #(split-words % #",") (get-lines "resources/06.txt"))))))



(def base {0 0 1 0 2 0 3 0 4 0 5 0 6 0 7 0 8 0})

(defn tick
  [input]
  (let [fish (merge-with + base input) newfishes (fish 0)]
    (-> fish
        (assoc 0 (fish 1))
        (assoc 1 (fish 2))
        (assoc 2 (fish 3))
        (assoc 3 (fish 4))
        (assoc 4 (fish 5))
        (assoc 5 (fish 6))
        (assoc 6 (+ (fish 7) newfishes))
        (assoc 7 (fish 8))
        (assoc 8 newfishes))))

(defn life-span
  [input days]
  (loop [items (range days) fish input]
    (if (empty? items)
      (apply + (vals fish))
      (recur (rest items) (tick fish)))))


(defn part-1
  ([] (part-1 input))
  ([input] (part-1 input 80))
  ([input days] (life-span input days)))

(defn part-2
  ([] (part-2 input))
  ([input] (part-2 input 256))
  ([input days] (part-1 input days)))