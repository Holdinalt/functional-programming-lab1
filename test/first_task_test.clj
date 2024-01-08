(ns first-task-test
  (:require [clojure.test :refer [deftest testing is]]
            [first-task :as first])
  )

(def answer 234168)

(deftest test-first-task
  (testing "Recursion"
    (is (= answer (first/task1-rec 0 1000)))
    (is (= answer (first/task1-rec-tail 0 1000 0)))
    )
  )