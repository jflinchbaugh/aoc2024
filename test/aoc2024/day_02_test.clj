(ns aoc2024.day-02-test
  (:require [aoc2024.day-02 :refer :all]
            [clojure.test :as t]))

(def input-1 "
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9
1 2 3 4 5 6 6 6
")

(t/deftest test-part-1
  (t/is (= 2 (part-1 input-1))))

(t/deftest test-part-2
  (t/is (= 4 (part-2 input-1))))
