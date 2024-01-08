(ns second-task)

(defn splitDig [n]
  (map (comp read-string str) (str n)))

(defn mult5 [x] (* x x x x x))

(defn sumMultedDigOfNumber [x]
  (reduce (fn
            [acc, n]
            (+ acc (mult5 n))
            )
          0
          (splitDig x)
          )
  )

(defn task2 [maxE]
  (reduce
    +
    0
    (filter
      (fn [x] (=
                (sumMultedDigOfNumber x)
                x
                )
        )
      (range maxE)
      )
    )
  )

(defn task2-rec [now maxE]
  (if (<= now maxE)
    (if (=
          (sumMultedDigOfNumber now)
          now
          )
      (+ now (task2-rec (inc now) maxE))
      (recur (inc now) maxE)
      )
    0
    )
  )

(defn task2-rec-tail [now accum maxE]
  (if (<= now maxE)
    (if (=
          (sumMultedDigOfNumber now)
          now
          )
         (recur (inc now) (+ now accum) maxE)
         (recur (inc now) accum maxE)
         )
    accum
    )
  )

(defn task2-lazy
  ([] (task2-lazy 10000))
  ([x] (reduce (fn [acc n] (if (= (sumMultedDigOfNumber n) n) (+ acc n) acc)) (range x))
   )
  )





