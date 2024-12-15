(ns aoc2024.day-05
  (:require [aoc2024.core :refer :all]
            [clojure.string :as str]))

(defn center
  "find center element of a list"
  [col]
  (first (drop (int (/ (count col) 2)) col)))

(defn used-rule? [update-lst [a b]]
  (let [update-set (set update-lst)]
    (and (update-set a) (update-set b))))

(defn order-updates [rules update-lst]
  (let [used-rules (filter (partial used-rule? update-lst) rules)
        before-map (update-vals
                     (group-by second used-rules)
                     (fn [c] (count (map first c))))]
    (sort-by (fn [u] (get before-map u 0)) update-lst)))

(defn part-1 [input]
  (let [[rules-source _ update-source]
        (->> input
             str->lines
             (partition-by str/blank?))
        updates (map
                 (fn [s]
                   (map parse-long (str/split s #",")))
                 update-source)
        all-rules (map
                   (fn [s]
                     (map parse-long (str/split s #"\|")))
                   rules-source)]
    (->> updates
         (map (juxt identity (partial order-updates all-rules)))
         (filter (fn [[o s]] (= o s)))
         (map second)
         (map center)
         (reduce + 0))))

(comment
  (->> "input/day_05.txt"
       slurp
       part-1)
  ;; => 7024

  .)

(defn part-2 [input]
  (let [[rules-source _ update-source]
        (->> input
             str->lines
             (partition-by str/blank?))
        updates (map
                 (fn [s]
                   (map parse-long (str/split s #",")))
                 update-source)
        all-rules (map
                   (fn [s]
                     (map parse-long (str/split s #"\|")))
                   rules-source)]
    (->> updates
         (map (juxt identity (partial order-updates all-rules)))
         (remove (fn [[o s]] (= o s)))
         (map second)
         (map center)
         (reduce + 0))))

(comment
  (->> "input/day_05.txt"
       slurp
       part-2)
  ;; => 4151

  .)
