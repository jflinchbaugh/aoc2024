(ns aoc2024.day-06
  (:require [aoc2024.core :refer :all]
            [clojure.string :as str]))

(def heading {\^ :up \> :right \< :left \v :down})

(def step {:up [0 -1] :right [1 0] :left [-1 0] :down [0 1]})

(defn turn-guard [[d x y]]
  [({:up :right :right :down :down :left :left :up} d)
   x y])

(defn move-guard [[d x y]]
  (let [[dx dy] (step d)]
    [d (+ dx x) (+ dy y)]))

(defn dedup [steps]
  (->>
   steps
   (map rest)
   set))

(defn out? [[dx dy] [_ x y]]
  (or (> 0 x) (> 0 y) (<= dx x) (<= dy y)))

(defn move [dimensions obstacles steps]
  (let [current-pos (first steps)
        next-pos (move-guard current-pos)]
    (cond
      ((set steps) next-pos)
      [:loop steps]

      (out? dimensions next-pos)
      [:out steps]

      (obstacles (rest next-pos))
      (recur dimensions obstacles (cons (turn-guard current-pos) steps))

      :else
      (recur dimensions obstacles (cons next-pos steps)))))

(defn part-1 [input]
  (let [src-map (->> input
                     parse-grid)
        dimensions [(->> src-map
                         (map second)
                         (apply max)
                         inc)
                    (->> src-map
                         (map (fn [c] (nth c 2)))
                         (apply max)
                         inc)]
        guard (->> src-map
                   (filter (fn [[c _ _]] (heading c)))
                   (map (fn [[h x y]] [(heading h) x y]))
                   first)
        obstacles (->> src-map
                       (filter (fn [[c _ _]] (= \# c)))
                       (map rest)
                       set)]
    (->> guard
         list
         (move dimensions obstacles)
         second
         dedup
         count)))

(comment
  (->> "input/day_06.txt"
       slurp
       part-1)

  ;; => 4433

  .)

(defn part-2 [input]
  (let [src-map (->> input
                     parse-grid)
        dimensions [(->> src-map
                         (map second)
                         (apply max)
                         inc)
                    (->> src-map
                         (map (fn [c] (nth c 2)))
                         (apply max)
                         inc)]
        guard (->> src-map
                   (filter (fn [[c _ _]] (heading c)))
                   (map (fn [[h x y]] [(heading h) x y]))
                   first)
        obstacles (->> src-map
                       (filter (fn [[c _ _]] (= \# c)))
                       (map rest)
                       set)
        guard-path (->> guard
                        list
                        (move dimensions obstacles)
                        second
                        dedup
                        seq)]
    (->>
      guard-path
      (remove #{(rest guard)})
      (pmap
        (fn [block]
          (let [obstacles (conj obstacles block)
                new-guard-path (->> guard
                                 list
                                 (move dimensions obstacles))]
            (first new-guard-path))))
      (filter #{:loop})
      count
     )))

(comment
  (->> "input/day_06.txt"
       slurp
       part-2)
  ;; => 1516


  .)
