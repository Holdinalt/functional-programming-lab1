(ns second-task)

(defn splitDig [n]
  (map (comp read-string str) (str n)))

(defn mult5 [x] (* x x x x x))

(defn sumMultedDigOfNumber [x]
  (reduce
   #(+ %1 (mult5 %2))
   0
   (splitDig x)))

(defn task2 [maxE]
  (reduce
   +
   0
   (filter #(= (sumMultedDigOfNumber %1) %1)
           (range maxE))))

(defn task2-rec [now maxE]
  (cond
    (> now maxE) 0
    (= (sumMultedDigOfNumber now) now) (+ now (task2-rec (inc now) maxE))
    :else (recur (inc now) maxE)))

(defn task2-rec-tail [now accum maxE]
  (cond
    (> now maxE) accum
    (= (sumMultedDigOfNumber now) now) (recur (inc now) (+ now accum) maxE)
    :else (recur (inc now) accum maxE)))

(defn task2-lazy
  ([] (task2-lazy 10000))
  ([x] (reduce (fn [acc n] (if (= (sumMultedDigOfNumber n) n) (+ acc n) acc)) (range x))))





