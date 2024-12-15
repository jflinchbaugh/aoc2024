(ns aoc2024.day-05
  (:require [aoc2024.core :refer :all]
            [clojure.string :as str]))

(defn in-order?
  "given a map of all elements before others, and a list, tell us if it's in order"
  [before-map [n & rst]]
  (if (empty? rst)
    true
    (let [before (before-map n)]
      (if (nil? before)
        (in-order? before-map rst)
        (if (some (set before) rst)
          false
          (in-order? before-map rst))))))

(defn center
  "find center element of a list"
  [col]
  (first (drop (int (/ (count col) 2)) col)))

(defn part-1 [input]
  (let [[order-source _ update-source]
        (->> input
             str->lines
             (partition-by str/blank?))
        order (map
               (fn [s]
                 (map parse-long (str/split s #"\|")))
               order-source)
        updates (map
                 (fn [s]
                   (map parse-long (str/split s #",")))
                 update-source)
        before-map (update-vals (group-by second order) (partial map first))]
    (->> updates
         (filter (partial in-order? before-map))
         (map center)
         (reduce + 0))))

(comment
  (->> "input/day_05.txt"
       slurp
       part-1))

(defn used-rule? [update-lst [a b]]
  (let [update-set (set update-lst)]
    (and (update-set a) (update-set b))))

(defn order-updates [rules update-lst]
  (let [used-rules (filter (partial used-rule? update-lst) rules)
        before-map (update-vals
                    (group-by second used-rules)
                    (fn [c] (count (map first c))))]
    (sort-by (fn [u] (get before-map u 0)) update-lst)))

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
