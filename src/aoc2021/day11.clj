(ns aoc2021.day11)

(def input [[1 2 2 4 3 4 6 3 8 4]
                  [5 6 2 1 1 2 8 5 8 7]
                  [6 3 8 8 4 2 6 5 4 6]
                  [1 5 5 6 2 4 7 7 5 6]
                  [1 4 5 1 8 1 1 5 7 3]
                  [1 8 3 2 3 8 8 1 2 2]
                  [2 7 4 8 5 4 5 6 4 7]
                  [2 5 8 2 8 7 7 4 3 2]
                  [3 1 8 5 6 4 3 8 7 1]
                  [2 2 2 4 8 7 6 6 2 7]])

(def steps [[-1 -1] [-1 0] [-1 1] [0 -1] [0 1] [1 -1] [1 0] [1 1]])

(defn ticker
  [input]
  (let [height (count input) length (count (first input))]
    (letfn [(blinker [table blinks spot]
              ; (println "blinker : " spot)
              (loop [items steps [result markers] [table  blinks]]
                (if (empty? items)
                  [result markers]
                  (let [current (first items)
                        pos (mapv + spot current)
                        [y x] pos
                        valid (and (< -1 y height) (< -1 x length))
                        blink (and valid (= (get-in result pos) 9) (false? (contains? markers pos)))
                        inc-table (if (true? valid) (update-in result pos inc) result)
                        ] 
                        (recur (rest items) (if (true? blink) (blinker inc-table (assoc markers pos 0) pos) [inc-table markers]))))))]
      ; (println "*** inside ***")
      ; (println input)
      (loop [items (for [y (range height) x (range length)] [y x]) [result blinks] [(mapv #(mapv inc %) input)  {}]]
        ; (println (count items))
        (if (empty? items)
          [result blinks]
          (let [current (first items)
                value (get-in result current)
                blink (and (> value 9) (false? (contains? blinks current)))
                ]
            ; (println series " : " current " ::: blink : " blink) 
              (recur (rest items) (if (true? blink) (blinker result (assoc blinks current 0) current) [result blinks]))))))))

(defn looper
  [input times]
  (loop [items (range times) result input blinks 0]
    (if (empty? items)
      blinks
      (let [[changed markers] (ticker result)]
        (recur (rest items) (reduce #(assoc-in %1 (key %2) 0) changed markers) (+ blinks (count markers)))))))

(defn simultaneously
  [input]
  (let [length (* (count input) (count (first input)))]
    (loop [items (range) result input]
      (let [[changed markers] (ticker result)]
        (if (= (count markers) length)          
          (+ (first items) 1)
          (recur (rest items) (reduce #(assoc-in %1 (key %2) 0) changed markers)))))))

(defn part-1
 ([] (part-1 input))
 ([input] (part-1 input 100))
 ([input times] (looper input times)))

(defn part-2
  ([] (part-2 input))
  ([input] (simultaneously input)))