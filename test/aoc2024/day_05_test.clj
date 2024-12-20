(ns aoc2024.day-05-test
  (:require  [aoc2024.day-05 :refer :all]
             [clojure.test :as t]))

(def input-1 "
47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47
")

(t/deftest test-part-1
  (t/is (= 143 (part-1 input-1))))

(t/deftest test-part-2
  (t/is (= 123 (part-2 input-1))))
