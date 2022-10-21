(ns aoc2021.day02(:gen-class)
  (:require [aoc2021.core :refer [get-lines split-words]]))

(def input
    "Fetch the numbers from the input given. The input might not be the same as you have, 
   but the solution to solve it will be the same."
    (vec (map #(split-words % #" ") (get-lines "resources/02.txt"))))

(defn forward
  [position step]
  (let [[x y] position]
    [(+ x step) y]))

(defn up
  [position step]
  (let [[x y] position]
    [x (- y step)]))

(defn down
  [position step]
  (let [[x y] position]
    [x (+ y step)]))

(defn aimforward
  [position step]
  (let [[x y aim] position]
    [(+ x step) (+ y (* step aim)) aim]))

(defn aimup
  [position step]
  (let [[x y aim] position]
    [x y (- aim step)]))

(defn aimdown
  [position step]
  (let [[x y aim] position]
    [x y (+ aim step)]))

(defn calculate-prefix
  [position operation prefix]
  (let [[method step] operation]
    (let [params [position (Integer. step)]]
      (apply (ns-resolve 'aoc2021.day02 (symbol (str prefix method))) params))))

(defn calculate
  ([position operation]
   (calculate position operation ""))
  ([position operation prefix]
   (calculate-prefix position operation prefix)))
  
(defn aimcalculate
  ([position operation]
   (calculate position operation "aim")))

(defn traverse
  ([input]
   (traverse input calculate))
  ([input method]
   (reduce method [0 0 0] input)))

(defn calculate-vector
  ([input]
   (calculate-vector input calculate))
  ([input method]
   (apply * (take 2 (traverse input method)))))

(defn part-1
  "Find the two entries that sum to 2020; what do you get if you multiply them together?"
  ([]
    (part-1 input))
  ([input]
    (calculate-vector input)))

(defn part-2
  "Using this new interpretation of the commands, calculate the horizontal position and depth you would have after following the planned course."
  ([]
    (part-2 input))
  ([input]
    (calculate-vector input aimcalculate)))