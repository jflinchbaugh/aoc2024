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

(defn parse-line [s]
  (map parse-long (str/split s #" ")))

(defn part-1 [input]
  (->>
    input
    str->lines
    (map parse-line)
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

(defn dampen [reports]
  (cons reports
    (for [to-remove (range (count reports))]
      (keep-indexed (fn [i o] (if (not= i to-remove) o)) reports)))
  )

(defn part-2 [input]
  (->>
    input
    str->lines
    (map parse-line)
    (map
      (fn [reports]
        (->>
          reports
          dampen
          (some safe?)
          )))
    (keep identity)
    count
    )
  )

(comment
  (->> "input/day_02.txt"
    slurp
    part-2)
  ;; => 531

  )
