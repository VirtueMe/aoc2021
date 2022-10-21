(ns aoc2021.day01
  "This solves the first and second part of the puzzle for day 1.

     See <https://adventofcode.com/2021/day/1>
   
     As the submarine drops below the surface of the ocean, it automatically performs a sonar sweep of the nearby sea floor. On a small screen, the sonar sweep report (your puzzle input) appears: each line is a measurement of the sea floor depth as the sweep looks further and further away from the submarine.

     For example, suppose you had the following report:

     199
     200
     208
     210
     200
     207
     240
     269
     260
     263
     
     This reort indicates that, scanning outward from the submarine, the sonar sweep found depths of 199, 200, 208, 210, and so on.
     
     The first order of business is to figure out how quickly the depth increases, just so you know what you're dealing with - you never know if the keys will get carried into deeper water by an ocean current or a fish or something.
     
     To do this, count the number of times a depth measurement increases from the previous measurement. (There is no measurement before the first measurement.) In the example above, the changes are as follows:"
  (:gen-class)
  (:require [aoc2021.core :refer [get-lines]]))

(def input
    "Fetch the numbers from the input given. The input might not be the same as you have, 
   but the solution to solve it will be the same."
    (vec (map #(Integer. %) (get-lines "resources/01.txt"))))

(defn find-count
  "How many measurements are larger than the previous measurement?"
  [input] 
  (loop [lastvalue (first input) items (rest input) count 0]
    (if (empty? items) 
      count 
      (let [value (first items)] 
        (recur value (rest items) (if (< lastvalue value) (inc count) count))))))

(defn create-sliding-windows
  [input]
  (loop [items input result []]
    (if (< (count items) 3)
      result
      (let [window (take 3 items)]
        (recur (rest items) (conj result (apply + window)))))))

(defn part-1
  "Find the two entries that sum to 2020; what do you get if you multiply them together?"
  ([]
    (part-1 input))
  ([input]
    (find-count input)))

(defn part-2
  "Consider sums of a three-measurement sliding window. How many sums are larger than the previous sum?"
  ([]
   (part-2 input))
  ([input]
   (find-count (create-sliding-windows input))))

(defn solve
  ([]
   (solve part-1))
  ([part]
   (solve part input))
  ([part input]
   (part input)))