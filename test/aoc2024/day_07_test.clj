(ns aoc2024.day-07-test
  (:require [aoc2024.day-07 :refer :all]
            [clojure.test :as t]))

(def input "
190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20
")

(t/deftest test-part-1
  (t/is (= 3749 (part-1 input))))
