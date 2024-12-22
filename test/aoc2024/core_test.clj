(ns aoc2024.core-test
  (:require [clojure.test :refer :all]
            [aoc2024.core :refer :all]))

(deftest test-avg
  (testing "avg"
    (is (= 2 (avg [1 2 3])))
    (is (= 2 (avg [2])))
    (is (= 3/2 (avg [1 2])))
    (is (nil? (avg [])))))

(deftest test-all-range
  (are [expected actual] (= expected actual)
    [0] (all-range 0 0)
    [0 1] (all-range 0 1)
    [1 0] (all-range 1 0)))

(deftest test-median
  (are [expected col] (= expected (median (shuffle col)))
    nil []
    0 [0]
    1/2 [0 1]
    1 [0 1 2]
    1 [0 1 1 2]
    1 [0 1 15]
    1 [0 1 1 15]
    3/2 [0 1 2 15]
    ))

(deftest test-parse-grid
  (is (= [] (parse-grid "")))
  (is (= [[\1 0 0]] (parse-grid "1")))
  (is
    (=
      [[\1 0 0] [\2 1 0] [\3 2 0] [\4 0 1] [\5 1 1] [\6 2 1]]
      (parse-grid "
123
456"))))

(deftest test-gcd
  (are [expected a b] (= expected (gcd a b))
    nil nil nil
    0 0 0
    1 1 1
    1 2 1
    1 2 3
    2 4 6
    2 -4 6
    2 -4 -6
    ))
