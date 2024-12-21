(ns aoc2024.day-08-test
  (:require [aoc2024.day-08 :refer :all]
   [clojure.test :as t]))


(def input "
......#....#
...#....0...
....#0....#.
..#....0....
....0....#..
.#....A.....
...#........
#......#....
........A...
.........A..
..........#.
..........#.
")

(t/deftest test-part-1
  (t/is (= 14 (part-1 input))))

(t/deftest test-part-2
  (t/is (= 34 (part-2 input))))
