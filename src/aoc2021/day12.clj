(ns aoc2021.day12
  (:gen-class)
  (:require [clojure.string :refer [upper-case split]] [aoc2021.core :refer [get-lines]]))

(def input (map #(split % #"-")(get-lines "resources/12.txt")))

(defn all-uppercase? [s]
  (= s (upper-case s)))

(defn size-filter
 [path branch]
   (let [result (frequencies (filter #(not (all-uppercase? %)) path))]
     (if (nil? (result branch))
       false
       (> (apply + (vals  result)) (count (vals result))))))

(defn only-once
  [path branch]
  (not (nil? (some #(= branch %) path))))

(defn build-cave
  [input]
  (letfn [(build [tree leaf-1 leaf-2]
                 (if (= leaf-1 "end")
                   tree
                   (if (= leaf-2 "start")
                     tree
                     (let [branch (get-in tree [leaf-1] [])]
                       (if (nil? (some #(= leaf-2 %) branch))
                         (assoc-in tree [leaf-1] (conj branch leaf-2))
                         tree)))))]
  (loop [items input result {}]
    (if (empty? items)
      result
      (let [[leaf-1 leaf-2] (first items)]
        (recur (rest items) (-> result (build leaf-1 leaf-2) (build leaf-2 leaf-1))))))))

(defn walker
  ([input pos path] (walker input pos path only-once))
  ([input pos path limit]
  ; (println pos path)
  (let [steps (input pos)]
    ; (println steps)
    (if (or (nil? steps) (empty? steps))
      [path]
      (loop [items steps result []]
        ;(println result)
        (if (empty? items)
          result
          (let [branch (first items) visited (and (limit path branch) (not (all-uppercase? (str (first branch)))))]
            ; (println branch visited)
            (recur (rest items) (if (true? visited) result (reduce #(conj %1 %2) result (walker input branch (conj path branch) limit)))))))))))

(defn calc-length
  [input limit]
  (let [tree (build-cave input)]
    (count (walker tree "start" ["start"] limit))))

(defn part-1
  ([] (part-1 input))
  ([input] (calc-length input only-once)))

(defn part-2
  ([] (part-2 input))
  ([input] (calc-length input size-filter)))