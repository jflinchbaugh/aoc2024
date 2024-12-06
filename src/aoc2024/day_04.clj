(ns aoc2024.day-04
  (:require [aoc2024.core :refer :all]
            [clojure.string :as str]))

(defn grid [chars]
  (for [y (range (count chars))
        x (range (count (first chars)))]
    [x y]))

(defn parse-word-search [input]
  (let [chars (->> input
                   str->lines
                   (mapv vec))]
      (for [coord (grid chars)]
        (cons (get-in chars coord) coord))
     ))

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
    first
    ))

(defn part-1 [input]
  (let [letters (->>
                  input
                  (parse-word-search))
        starts (->>
                 letters
                 (filter (comp #{\X} first))
                 (map rest))
        segments (->>
                   (mapcat
                     (fn [[dx dy]]
                       (map (partial make-segment [dx dy]) starts))
                     (for [x (range -1 2)
                           y (range -1 2)
                           :when (not= 0 x y)]
                       [x y]))
                   (remove (partial some nil?) ))
        words (map
          (fn [segment]
            (map
              (fn [pos] (lookup letters pos))
              segment))
          segments)]

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
