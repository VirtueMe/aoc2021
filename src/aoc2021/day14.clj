(ns aoc2021.day14
  (:require [aoc2021.core :refer [get-lines]]))

(defn parse-matcher
  [input]
  (let [testfold (re-matcher #"(?<letters>.*) -> (?<letter>.)" input)]
    (if (.matches testfold)
      (let [letter (.group testfold "letter") letters (.group testfold "letters")]
        { letters [(str (first letters) letter) (str letter (second letters))]}) 
      nil)))

(defn parse-matchers
  [input]
  (apply merge (map parse-matcher input)))

(defn parse-polymer
  [input]
  (loop [items input result {}]
    (if (< (count items) 2)
      result
      (recur (subs items 1) (update-in result [(subs items 0 2)] (fnil inc 0))))))

(defn parse-instructions
  [items]
  (loop [source items current [] results []]
    (if (empty? source)
      {:template (parse-polymer (first results)) :matchers (parse-matchers current) }
      (recur (rest source) (if (empty? (first source)) [] (conj current (first source))) (if (empty? (first source)) current results)))))

(def input (parse-instructions (get-lines "resources/14.txt")))

(defn polymerize
  [polymer  matchers]
  (loop [items (keys polymer) result {}]
    (if (empty? items)
      result
      (let [current (first items) [item1 item2] (matchers current)]
        (recur (rest items) (-> result (update-in [item1] #((fnil + 0) % (polymer current))) (update-in [item2] #((fnil + 0) % (polymer current)))))))))

(defn run-polymerize
  [input times]
  (loop [items (range times) result (input :template)]
    (if (empty? items)
      result
      (recur (rest items) (polymerize result (input :matchers))))))

(defn count-polymerization
  [input]
  (loop [items (keys input) result {}]
    (if (empty? items)
      result
      (let [current (first items) [item1 item2] (vec (seq current))]
        (recur (rest items) (-> result (update-in [item1] #((fnil + 0) % (input current))) (update-in [item2] #((fnil + 0) % (input current)))))))))

(defn calc
  [input]
  (let [items (vals input) highest (apply max items) lowest (apply min items)]
    (- (long (Math/ceil (/ highest 2))) (long (Math/ceil (/ lowest 2))))))

(defn calc-polymerization
  [input]
  (calc (count-polymerization input)))

(defn part-1
  ([] (part-1 input))
  ([input] (part-1 input 10))
  ([input times] (calc-polymerization (run-polymerize input times))))

(defn part-2
  ([] (part-2 input))
  ([input] (part-2 input 40))
  ([input times] (part-1 input times)))
