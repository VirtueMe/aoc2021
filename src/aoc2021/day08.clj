(ns aoc2021.day08
  (:gen-class)
  (:require [aoc2021.core :refer [get-lines split-words]] [clojure.set]))

(def sizes {2 1 4 4 3 7 7 8})

(def input
  "Input data"
  (map #(map (fn [str] (split-words str #" ")) %) (map #(split-words % #" \| ") (get-lines "resources/08.txt"))))
  ; (map #(split-words % #" \| ") (get-lines "resources/08.txt")))

(defn make-sum
  [input]
  ; (println input)
  (count input))

(defn make-filter
  [input]
  ; (println input)
  (some #(= % input) (keys sizes)))


(defn find-unique
  [input]
  (map #(let [[list sum] %] [list (filter make-filter (map count sum))]) input))

(defn calc-count
  [input]
  (apply + (map #(count (second %)) (find-unique input))))

(defn part-1
  ([] part-1 input)
  ([input] (calc-count input)))

(defn find-known
  [input]
  (reduce (fn [col val] (if (some #(= % (count val)) (keys sizes)) (conj col {(count val) [val (sizes (count val))]}) col)) {} input))

(defn solve-ciffer
  [input]
  ; (println input)
  (let [known (find-known input)]
    (letfn [(test [current, length, nohit]
              (= (count (clojure.set/difference (set current) (set (first (known length))))) nohit))
            (lookup [current]
              ; (println current)
              (let [length (count current)]
                (cond
                  (= length 7) [current 8]
                  (= length 6) [current (if (test current 4 2)
                                          9
                                          (if (test current 3 3)
                                            0
                                            6))]
                  (= length 5) [current (if (test current 3 2)
                                          3
                                          (if (test current 4 2) 5 2))]
                  (= length 4) [current 4]
                  (= length 3) [current 7]
                  (= length 2) [current 1])))]
      (map lookup input))))

(defn to-number
  [input]
  ; (println input)
  (Integer. (apply str (map second (reverse (take 4 (reverse input)))))))

(defn calc-results 
  [input] 
  (loop [list input result []]
    ; (println list)
    (if (empty? list)
      result 
      (recur (rest list) (conj result (to-number (solve-ciffer (flatten (first list)))))))))

(defn part-2 
  ([] (part-2 input))
  ([input] (apply + (calc-results input))))