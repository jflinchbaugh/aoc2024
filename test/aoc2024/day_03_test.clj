(ns aoc2024.day-03-test
  (:require [aoc2024.day-03 :refer :all]
            [clojure.test :as t]))

(def input-1 "
xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
")

(t/deftest test-part-1
  (t/is (= 161 (part-1 input-1))))

(def input-2 "
xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
don't()anything elsemul(2,2)
mul(3,5)
")

(t/deftest test-part-2
  (t/is (= 48 (part-2 input-2))))
