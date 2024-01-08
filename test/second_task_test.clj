(ns second-task-test
  (:require [clojure.test :refer [deftest testing is]]
            [second-task :as second])
  )

(def answer 8302)

(deftest test-second-task
  (testing "Recursion"
    (is (= answer (second/task2-rec 0 9999)))
    (is (= answer (second/task2-rec-tail 0 0 9999)))
    )
  )