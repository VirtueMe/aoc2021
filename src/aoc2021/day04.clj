(ns aoc2021.day04
  (:gen-class)
  (:require [clojure.set])
  (:require [aoc2021.core :refer [get-lines split-words split-letters]])
  (:require [clojure.string]))

(defn create-board
  [input]
  {:b input
   :c [0 0 0 0 0]
   :r [0 0 0 0 0]
   :w false})

(defn create-numbers
  ([input]
   (map #(Integer/parseInt % 10) (split-words input)))
  ([input splitter]
   (map #(Integer/parseInt % 10) (split-letters input splitter))))


(defn create-game
  [input]
  (let [drawn (first input)]
    (loop [items (rest input) boards []]
      (let [board (take 5 (rest items))]
        ; (println board (map #(create-numbers (clojure.string/replace % "  " " ")) board))
        (if (empty? board)
          {:draw (create-numbers drawn #",")
           :boards (map create-board boards)}
          (recur (drop 6 items) (conj boards (map #(create-numbers (clojure.string/trim (clojure.string/replace % "  " " "))) board))))))))

(def input
  (create-game (get-lines "resources/04.txt")))

(defn update-win
  [input draw drawn]
  (if (and (or (some #{5} (:c input)) (some #{5} (:r input))) (false? (:w input)))
    (-> input
        (assoc :w true)
        (assoc :last draw)
        (assoc :drawn drawn))

    input))

(defn check
  [input value drawn]
  ; (println value drawn)
  (loop [squares (for [y (range 5) x (range 5)] [y x])]
    (if (empty? squares)
      input
      (let [[y x] (first squares)]
        ; (println y " ::: " x " ==> " value " = " (nth (nth (:b input) y) x))
        (if (= value (nth (nth (:b input) y) x))
          (-> input ; with board
              (update-in [:r y] inc)
              (update-in [:c x] inc)
              (update-win value drawn))
          (recur (rest squares)))))))

(defn play-all
  [input]
  (let [draw-list (:draw input) boards (:boards input)]
    (if (nil? draw-list)
      (println "Somethings missing")
      (loop [items draw-list boardlist boards drawn [] winners []]
        (if (empty? boardlist)
          winners
          (let [value (first items) checked-boards (map #(check % value drawn) boardlist) winner (filter #(true? (:w %)) checked-boards)]
            (recur (rest items) (filter #(false? (:w %)) checked-boards) (conj drawn value) (into [] (concat winners winner)))))))))

(defn play-part-1
  [input]
  (let [list (play-all input) winner (first list)]
    [(:b winner) (:last winner) (:drawn winner)]))

(defn calculate-score
  [board winner drawn]
  ; (println board " * " winner " ** " drawn)
  (* winner (apply + (clojure.set/difference (set (flatten board)) (set (conj drawn winner))))))

(defn part-1
  ([]
   (part-1 input))
  ([input]
   (apply calculate-score (play-part-1 input))))


(defn play-part-2
  [input]
  (let [list (play-all input) winner (last list)]
    [(:b winner) (:last winner) (:drawn winner)]))

(defn part-2
  ([]
   (part-2 input))
  ([input]
   (apply calculate-score (play-part-2 input))))

