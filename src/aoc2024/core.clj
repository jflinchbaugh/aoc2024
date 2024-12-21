(ns aoc2024.core
  (:require [clojure.string :as str]))

(defn avg
  "average of values in a list"
  [coll]
  (when (seq coll)
    (/ (reduce + coll) (count coll))))

(defn str->lines
  "parse a string into trimmed lines"
  [str-data]
  (->> str-data
       str/trim
       str/split-lines))

(defn all-range
  "produce an inclusive range in either direction"
  [s e]
  (if (<= s e)
    (range s (inc e))
    (reverse (range e (inc s)))))

(defn median [col]
  (let [sorted (sort col)
        size (count sorted)]
    (cond
      (zero? size) nil
      (odd? size) (nth sorted (dec (/ (inc size) 2)))
      :else (avg [(nth sorted (dec (int (/ size 2)))) (nth sorted (int (/ size 2)))]))))

(def ^:private inf Double/POSITIVE_INFINITY)

(defn update-costs
  "Returns costs updated with any shorter paths found to curr's unvisisted
  neighbors by using curr's shortest path"
  [g costs unvisited curr]
  (let [curr-cost (get costs curr)]
    (reduce-kv
     (fn [c nbr nbr-cost]
       (if (unvisited nbr)
         (update-in c [nbr] min (+ curr-cost nbr-cost))
         c))
     costs
     (get g curr))))

(defn dijkstra
  "Returns a map of nodes to minimum cost from src using Dijkstra algorithm.
  Graph is a map of nodes to map of neighboring nodes and associated cost.
  Optionally, specify destination node to return once cost is known"
  ([g src]
   (dijkstra g src nil))
  ([g src dst]
   (loop [costs (assoc (zipmap (keys g) (repeat inf)) src 0)
          curr src
          unvisited (disj (apply hash-set (keys g)) src)]
     (cond
       (= curr dst)
       (select-keys costs [dst])

       (or (empty? unvisited) (= inf (get costs curr)))
       costs

       :else
       (let [next-costs (update-costs g costs unvisited curr)
             next-node (apply min-key next-costs unvisited)]
         (recur next-costs next-node (disj unvisited next-node)))))))

(defn grid [chars]
  "produce a list of coordinates the size of a grid of lines"
  (for [y (range (count chars))
        x (range (count (first chars)))]
    [x y]))

(defn parse-grid
  "parse a multi-line grid of characters into a list
  of the characters and coordinates"
  [input]
  (let [chars (->> input
                   str->lines
                   (mapv vec))]
    (for [coord (grid chars)]
      (cons (get-in chars (reverse coord)) coord))))

(defn gcd
  "calculate greatest common divisor"
  [a b]
  (if
   (some nil? [a b]) nil
   (if (zero? b)
     (abs a)
     (recur b (mod a b)))))
