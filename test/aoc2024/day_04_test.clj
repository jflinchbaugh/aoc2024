(ns aoc2024.day-04-test
  (:require  [aoc2024.day-04 :refer :all]
             [clojure.test :as t]))

(t/deftest test-lookup
  (t/is (= \M (lookup [[\M 0 0]] [0 0]))))

(t/deftest test-make-segment
  (t/is (= [[0 0] [1 1] [2 2] [3 3]] (make-segment [1 1] [0 0])))
  (t/is (= [[0 0] [1 0] [2 0] [3 0]] (make-segment [1 0] [0 0])))
  (t/is (= [[0 0] nil nil nil] (make-segment [-1 -1] [0 0]))))

(def input-1 "
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX
")

(t/deftest test-part-1
  (t/is (= 18 (part-1 input-1))))

(t/deftest test-part-2
  (t/is (= 9 (part-2 input-1))))
