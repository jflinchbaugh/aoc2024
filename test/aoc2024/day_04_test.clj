(ns aoc2024.day-04-test
  (:require  [aoc2024.day-04 :refer :all]
             [clojure.test :as t]))


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


(t/deftest test-lookup
  (t/is (= \M (lookup [[\M 0 0]] [0 0]))))

(t/deftest test-part-1
  (t/is (= 18 (part-1 input-1))))
