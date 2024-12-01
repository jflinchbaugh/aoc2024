(ns aoc2024.core-test
  (:require [clojure.test :refer :all]
            [aoc2024.core :refer :all]))

(deftest test-avg
  (testing "avg"
    (is (= 2 (avg [1 2 3])))
    (is (= 2 (avg [2])))
    (is (nil? (avg [])))))

(deftest test-file->lines
  (testing "file->lines"
    (is (= ["" "1" "2"] (file->lines "test/aoc2024/file.txt")))))

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
