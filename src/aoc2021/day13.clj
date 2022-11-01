(ns aoc2021.day13
  (:gen-class)
  (:require [aoc2021.core :refer [get-lines split-words]]))

(defn parse-dots
  [input]
  (vec (map #(Integer. %) (split-words input #","))))

(defn parse-fold
  [input]
  (let [testfold (re-matcher #"fold along (?<method>.)=(?<pos>\d+)" input)]
    (if (.matches testfold)
      [(.group testfold "method") (Integer. (.group testfold "pos"))]
      nil)))

(defn parse-instructions
  [items]
  (loop [source items current [] results []]
    (if (empty? source)
      {:dots (map parse-dots results) :folds (map parse-fold current) }
      (recur (rest source) (if (empty? (first source)) [] (conj current (first source))) (if (empty? (first source)) current results)))))

(def input (parse-instructions (get-lines "resources/13.txt")))

(defn create-board
  [input]
  (loop [items input result { :x (inc (apply max (map first input))) :y (inc (apply max (map second input))) :board {} }]
    (if (empty? items)
      result
      (recur (rest items) (assoc-in result [:board (second (first items)) (first (first items))] \#)))))

(defn y
  [input pos]
  (loop [items (for [h (range pos) x (range (input :x))] [h x]) result (assoc-in input [:y] pos)]
    (if (empty? items)
      (assoc-in result [:board] (select-keys (result :board) (vec (range pos))))
      (let [[a b] (first items) mirror (+ (- pos a) pos)]
        (recur (rest items) (if (or (= (get-in result [:board mirror b] \.) \#) (= (get-in result [:board a b] \.) \#)) (assoc-in result [:board a b] \#) result))))))

(defn x
  [input pos]
  (loop [items (for [h (range (input :y)) x (range pos)] [h x]) result (assoc-in input [:x] pos)]
    (if (empty? items)
      (update-in result [:board] (fn [board]  (zipmap (keys board) (map #(select-keys % (vec (range pos))) (vals board))))) ;(asooc-in result [:board])
      (let [[a b] (first items) mirror (+ (- pos b) pos)]
        (recur (rest items) (if (or (= (get-in result [:board a mirror]) \#) (= (get-in result [:board a b]) \#)) (assoc-in result [:board a b] \#) result))))))

(defn count-dots
  [input]
  (apply + (map #(count (vals %)) (vals (input :board)))))

(defn run
  [input counter]
  (loop [items (if (= counter -1) (input :folds) (take counter (input :folds))) board (create-board (input :dots))]
    (if (empty? items)
      board
      (let [[method pos] (first items)]
        (recur (rest items) (apply (ns-resolve 'aoc2021.day13 (symbol method)) [board pos]))))))

(defn newline-str
  [& input]
  (str input "\n\r"))

(defn draw-board
  ([input]
   (loop [items (for [y (range (input :y)) x (range (input :x))] [y x]) result (vec (repeat (input :y) []))]
     (map println (map #(apply newline-str %) result))
     (if (empty? items)
       (map #(apply str %) result)
       (let [[h w] (first items)]
         (recur (rest items) (update-in result [h] #(conj % (get-in input [:board h w] \.)))))))))

(defn part-1
  ([] (part-1 input))
  ([input] (count-dots (run input 1))))

(defn part-2
  ([] (part-2 input))
  ([input] (map println (draw-board (run input -1)))))
      
