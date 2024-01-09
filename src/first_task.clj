(ns first-task)

(defn mySeq [x] (or (zero? (mod x 5)) (zero? (mod x 3))))

(defn task1 [maxD]
  (reduce
    (fn [acc x]
      (cond
        (mySeq x) (+ acc x)
        :else acc
        )
      )
    (range maxD)
    )
  )

(defn task1-rec [now maxD]
  (if (< now maxD)

    (let [next (task1-rec (+ now 1) maxD)]

      (cond
        (mySeq now) (+ now next)
        :else next
        )

      )
    0
    ))

(defn task1-rec-tail [now maxD accum]
  (cond
    (< now maxD) (cond
                   (mySeq now) (recur (+ now 1) maxD (+ accum now))
                   :else (recur (+ now 1) maxD accum)
                   )
    :else accum
    )
  )

(defn task1-lazy
  ([] (task1-lazy 1000))
  ([x] (reduce (fn [acc n] (if (mySeq n) (+ acc n) acc)) (range x))
   )
  )

