(ns aoc2021.day05
  (:gen-class)
  (:require [aoc2021.core :refer [get-lines]]))


(defn build
  [input]
  (let [testcoords (re-matcher #"(?<x1>\d+),(?<y1>\d+) -> (?<x2>\d+),(?<y2>\d+)" input)]
    (if (.matches testcoords)
      [[(Integer. (.group testcoords "x1")) (Integer. (.group testcoords "y1"))] [(Integer. (.group testcoords "x2")) (Integer. (.group testcoords "y2"))]]
      [[] []])))

(def input (map build (get-lines "resources/05.txt")))

(defn vert-or-hort
  [input]
  (filter #(or (= (first (first %)) (first (second %))) (= (second (first %)) (second (second %)))) input))

(defn create-path
  [input]
  ; (println input)
  (let [[[x1 y1] [x2 y2]] input]
    (if (= x1 x2)
      (let [step1 (if (> y1 y2) -1 1)]
        (map #(vec [x1 (+ y1 (* % step1))]) (range (+ 1 (abs (- y2 y1))))))
      (if (= y1 y2)
        (let [step2 (if (> x1 x2) -1 1)]
          (map #(vec [(+ x1 (* % step2)) y1]) (range (+ 1 (abs (- x2 x1))))))
        (let [stepx (if (> x1 x2) -1 1) stepy (if (> y1 y2) -1 1)]
          (map #(vec [(+ x1 (* % stepx)) (+ y1 (* % stepy))]) (range (+ 1 (abs (- x2 x1))))))))))

(defn mark-map
  [input]
  ; (println input)
  (loop [items input result []]
    (if (empty? items)
      result
      (let [hits (create-path (first items))]
        ; (println hits)
        (recur (rest items) (concat result hits))))))

(defn find-intersections
  [input]
  (filter  (fn [[key val]]
             (> val 1)) (frequencies input)))

(defn part-1
  ([] (part-1 input))
  ([input]
   (count (find-intersections (mark-map (vert-or-hort input))))))

(defn part-2
  ([] (part-2 input))
  ([input]
   (count (find-intersections (mark-map input)))))