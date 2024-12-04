(ns aoc2024.day-03
  (:require [aoc2024.core :refer :all]
            [clojure.string :as str]))

(def mul-regex #"mul\((\d+),(\d+)\)")

(defn part-1 [input]
  (->>
    input
    (re-seq mul-regex)
    (map (fn [[_ a b]] (* (parse-long a) (parse-long b))))
    (reduce +)
    ))

(comment
  (->> "input/day_03.txt"
       slurp
       part-1)
  ;; => 159833790
  )
