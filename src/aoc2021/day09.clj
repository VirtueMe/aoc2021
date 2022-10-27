(ns aoc2021.day09
  (:gen-class)
  (:require [aoc2021.core :refer [get-lines]]))

(def numbers {\0 0 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7 \8 8 \9 9})

(def input (vec (map vec (map #(map numbers %) (get-lines "resources/09.txt")))))

(def connected [[-1 0] [0 -1] [0 1] [1 0]])

(defn spot-it
  [& input] (first input))

(defn value-it
  [& input] (second input))

(defn mark-hits
  [input hits spot selector]
  (let [height (count input) length (count (first input)) value (get-in input spot) spots (filter #(let [[y x] %] (and (< -1 y height) (< -1 x length))) (map #(map + spot %) connected))]
      ; (println spots (map #(get-in input %) spots))
    (if (nil? (some #(>= value %) (map #(get-in input %) spots)))
      (conj hits (selector spot value))
      hits)))

(defn walk
  [input selector]
  (let [height (count input) length (count (first input))]
    (loop [items (for [y (range height) x (range length)] [y x]) hits []]
      (if (empty? items)
        hits
        (recur (rest items) (mark-hits input hits (first items) selector))))))

(defn calc-risk
  [input]
  (+ (count input) (apply + input)))

(defn part-1
  ([] (part-1 input))
  ([input] (calc-risk (walk input value-it))))

(defn find-basin
  [input spot]
  (let [height (count input) length (count (first input))]
    (loop [items [spot] result [spot]]
      (if (empty? items)
        result
        (let [item (first items)
              neighbours (filter #(let [[y x] %] (and (< -1 y height) (< -1 x length) (nil? (some #{[y x]} result)) (< (get-in input [y x]) 9))) (map #(map + item %) connected))]
          (recur (if (empty? neighbours) (rest items) (concat (rest items) neighbours)) (if (empty? neighbours) result (concat result neighbours))))))))

(defn basin-walker
  [input spots]
  (loop [items spots result []]
    (if (empty? items)
      result
      (recur (rest items) (conj result (find-basin input (first items)))))))

(defn calc-size-largest-basins
  [input] 
  (apply * (take 3 (reverse (sort (map count (basin-walker input (walk input spot-it))))))))

(defn part-2
  ([] (part-2 input))
  ([input] (calc-size-largest-basins input)))