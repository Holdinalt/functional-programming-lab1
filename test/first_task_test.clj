(ns first-task-test
  (:require [clojure.test :refer [deftest testing is]]
            [first-task :as first]))

(def answer 233168)

(deftest test-first-task
  (testing "Recursion"
    (is (= answer (first/task1-rec 0 1000)))
    (is (= answer (first/task1-rec-tail 0 1000 0))))
  (testing "Modular"
    (is (= answer (first/task1 1000))))
  (testing "Lazy"
    (is (= answer (first/task1-lazy 1000)))))