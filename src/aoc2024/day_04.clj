(ns aoc2024.day-04
  (:require [aoc2024.core :refer :all]
            [clojure.string :as str]))

(defn make-segment [[dx dy] [x y]]
  (for [step (range 4)]
    (let [nx (+ (* dx step) x)
          ny (+ (* dy step) y)]
      (when (and (>= nx 0) (>= ny 0))
        [nx ny]))))

(defn lookup [letters [x y]]
  (->>
   letters
   (filter
    (fn [[_ lx ly]]
      (= [lx ly] [x y])))
   first
   first))

(defn make-segments
  [starts]
  (->>
   (mapcat
    (fn [[dx dy]]
      (map (partial make-segment [dx dy]) starts))
    (for [x (range -1 2)
          y (range -1 2)
          :when (not= 0 x y)]
      [x y]))
   (remove (partial some nil?))))

(defn make-words
  [letters segments]
  (map
   (fn [segment]
     (map
      (fn [pos] (lookup letters pos))
      segment))
   segments))

(defn part-1 [input]
  (let [letters (->>
                 input
                 (parse-grid))
        starts (->>
                letters
                (filter (comp #{\X} first))
                (map rest))
        segments (make-segments starts)
        words (make-words letters segments)]

    (->>
     words
     (filter
      (fn [w] (= "XMAS" (str/join w))))
     count)))

(comment
  (->> "input/day_04.txt"
       slurp
       part-1)
  ;; => 2543
  )

(defn make-x-segment [[x y]]
  (->>
   [[-1 -1] [1 -1] [1 1] [-1 1]]
   (map
    (fn [[dx dy]]
      (let [nx (+ x dx)
            ny (+ y dy)]
        (when (and (>= nx 0) (>= ny 0))
          [nx ny]))))))

(defn make-x-segments [starts]
  (->>
   starts
   (map make-x-segment)
   (remove (partial some nil?))))

(defn part-2 [input]
  (let [letters (->>
                 input
                 (parse-grid))
        starts (->>
                letters
                (filter (comp #{\A} first))
                (map rest))
        segments (make-x-segments starts)
        words (make-words letters segments)]
    (->>
     words
     (filter
      (fn [w] (#{"MMSS" "SMMS" "SSMM" "MSSM"} (str/join w))))
     count)))

(comment
  (->> "input/day_04.txt"
       slurp
       part-2)
  ;; => 1930

  )
