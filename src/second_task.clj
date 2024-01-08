(ns second-task)

(defn splitDig [n]
  (map (comp read-string str) (str n)))

(defn mult5 [x] (* x x x x x))

(defn task2 [maxE]
  (reduce
    +
    0
    (filter
      (fn [x] (=
                (reduce (fn
                          [acc, n]
                          (+ acc (mult5 n))
                          )
                        0
                        (splitDig x)
                        )
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
          (reduce (fn
                    [acc, n]
                    (+ acc (mult5 n))
                    )
                  0
                  (splitDig now)
                  )
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
                (reduce (fn
                          [acc, n]
                          (+ acc (mult5 n))
                          )
                        0
                        (splitDig now)
                        )
                now
                )
         (recur (inc now) (+ now accum) maxE)
         (recur (inc now) accum maxE)
         )
    accum
    )
  )





