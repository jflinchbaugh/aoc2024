(ns aoc2024.day-07
  (:require [aoc2024.core :refer :all]
            [clojure.string :as str]
            [clojure.math.combinatorics :as combo]))

(def select-operators combo/selections)

(defn collate [n o] [n o])

(defn calc [operands operators]
  (->>
   (map collate
        operands
        operators)
   (reduce
    (fn [acc [n o]]
      (o acc n))
    0)))

(defn computes? [operators [ans operands]]
  (->>
   operands
   count
   dec
   (select-operators operators)
   (map (partial cons +))
   (map (partial calc operands))
   (some (partial = ans))))

(defn part-1 [input]
  (->>
   input
   str->lines
   (map
    (fn [s]
      ((juxt first rest)
       (map parse-long (str/split s #"\D+")))))
   (filter (partial computes? [+ *]))
   (map first)
   (reduce +)))

(comment
  (->> "input/day_07.txt"
       slurp
       part-1)
  ;; => 6231007345478

  .)

