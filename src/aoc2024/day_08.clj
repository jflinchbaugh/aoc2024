(ns aoc2024.day-08
  (:require [aoc2024.core :refer :all]
            [clojure.string :as str]
            [clojure.math.combinatorics :as combo]))

(defn dist [[ax ay] [bx by]]
  [(- ax bx) (- ay by)])

(defn antinodes [[antenna-a antenna-b]]
  (let [[dx dy] (dist antenna-a antenna-b)
        [ax ay] antenna-a
        [bx by] antenna-b]
    (->> [[(+ ax dx) (+ ay dy)] [(- ax dx) (- ay dy)]
          [(+ bx dx) (+ by dy)] [(- bx dx) (- by dy)]]
         (remove #{antenna-a antenna-b}))))

(defn all-antinodes [locs]
  (->>
   (combo/selections locs 2)
   (remove (fn [[a b]] (= a b)))
   (mapcat antinodes)))

(defn inside? [max-x max-y [x y]]
  (and
   (<= 0 x) (>= max-x x)
   (<= 0 y) (>= max-y y)))

(defn part-1 [input]
  (let [whole-map (parse-grid input)
        max-x (->> whole-map (map second) (apply max))
        max-y (->> whole-map (map (comp first (partial drop 2))) (apply max))
        antennas (->>
                  whole-map
                  (remove (comp #{\. \#} first))
                  (group-by first)
                  ((fn [m] (update-vals m (partial map rest)))))]
    (->
     antennas
     (update-vals all-antinodes)
     (update-vals (partial filter
                           (partial inside? max-x max-y)))
     (->>
      (mapcat second)
      (into #{})
      count))))

(comment
  (->> "input/day_08.txt"
       slurp
       part-1)
  ;; => 220

  .)

(defn dist-simplified [[ax ay] [bx by]]
  (let [dx (- ax bx)
        dy (- ay by)
        s (gcd dx dy)]
    [(/ dx s) (/ dy s)]))

(defn antinodes-more [steps [antenna-a antenna-b]]
  (let [[dx dy] (dist-simplified antenna-a antenna-b)
        [ax ay] antenna-a]
    (mapcat concat
            (for [step (range steps)]
              [[(+ ax (* step dx)) (+ ay (* step dy))]
               [(- ax (* step dx)) (- ay (* step dy))]]))))

(defn all-antinodes-2 [antinodes locs]
  (->>
   (combo/selections locs 2)
   (remove (fn [[a b]] (= a b)))
   (mapcat antinodes)))

(defn part-2 [input]
  (let [whole-map (parse-grid input)
        max-x (->> whole-map (map second) (apply max))
        max-y (->> whole-map (map (comp first (partial drop 2))) (apply max))
        antennas (->>
                  whole-map
                  (remove (comp #{\. \#} first))
                  (group-by first)
                  ((fn [m] (update-vals m (partial map rest)))))]
    (->
     antennas
     #_(update-vals all-antinodes)
     (update-vals (partial all-antinodes-2 (partial antinodes-more (max max-x max-y))))
     (update-vals (partial filter
                             (partial inside? max-x max-y)))
     (->>
        (mapcat second)
        (into #{})
        count))))

(comment
  (->> "input/day_08.txt"
       slurp
       part-2)
  ;; => 813

  .)
