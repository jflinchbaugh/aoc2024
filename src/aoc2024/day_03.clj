(ns aoc2024.day-03
  (:require [aoc2024.core :refer :all]
            [clojure.string :as str]))

(def mul-regex #"mul\((\d+),(\d+)\)")

(defn part-1 [input]
  (->>
    input
    str->lines
    (str/join)
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

(def dont-do-regex #"don't\(\).*?do\(\)")
(def dont-regex #"don't\(\).*")

(defn remove-dont-sections [s]
  (-> s
    (str/replace dont-do-regex "")
    (str/replace dont-regex "")
    ))

(defn part-2 [input]
  (->>
    input
    str->lines
    (str/join)
    (remove-dont-sections)
    (re-seq mul-regex)
    (map (fn [[_ a b]] (* (parse-long a) (parse-long b))))
    (reduce +)
    ))

(comment
  (->> "input/day_03.txt"
       slurp
       part-2)
  ;; => 89349241

  )
