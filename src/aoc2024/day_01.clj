(ns aoc2024.day-01
  (:require [aoc2024.core :refer :all]
            [clojure.string :as str]))

(defn- solve-1 [lst]
  (let [as (sort (map first lst))
        bs (sort (map second lst))]
    (reduce + 0 (map (fn [a b] (abs (- a b))) as bs))
    ))

(defn part-1 [input]
  (->>
    input
    str->lines
    (remove str/blank?)
    (map (fn [s] (str/split s #"\s+")))
    (map (juxt (comp parse-long first) (comp parse-long second)))
    solve-1
    )
  )

(comment
  (->> "input/day_01.txt"
    slurp
    part-1)
  ;; => 1110981

  )

(defn- solve-2 [lst]
  (let [as (sort (map first lst))
        bs (sort (map second lst))]
    (reduce + 0 (map (fn [a] (* a (count (filter #{a} bs)))) as))
    ))

(defn part-2 [input]
  (->>
    input
    str->lines
    (remove str/blank?)
    (map (fn [s] (str/split s #"\s+")))
    (map (juxt (comp parse-long first) (comp parse-long second)))
    solve-2
    )
  )

(comment
  (->> "input/day_01.txt"
    slurp
    part-2)
  ;; => 24869388

  )
