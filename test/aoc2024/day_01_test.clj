(ns aoc2024.day-01-test
  (:require [aoc2024.day-01 :refer :all]
            [clojure.test :as t]))

(def input-1 "
3   4
4   3
2   5
1   3
3   9
3   3
")

(t/deftest test-part-1
  (t/is (= 11 (part-1 input-1))))

(t/deftest test-part-2
  (t/is (= 31 (part-2 input-1))))
