(ns aoc2024.day-02
  (:require [aoc2024.core :refer :all]
            [clojure.string :as str]))

(defn safe? [reports]
  (->>
    reports
    (partition 2 1)
    (map (fn [[a b]] (- a b)))
    ((fn [rs]
       (or
         (every? #{1 2 3} rs)
         (every? #{-1 -2 -3} rs)))))

  )

(defn part-1 [input]
  (->>
    input
    str/trim
    str->lines
    (map (fn [s] (map parse-long (str/split s #" "))))
    (filter safe?)
    count
    )
  )

(comment
  (->> "input/day_02.txt"
    slurp
    part-1)
  ;; => 479

  )

