(ns first-task)

(defn mySeq [x] (or (zero? (mod x 5)) (zero? (mod x 3))))

(defn task1 [maxD]
  (reduce
    (fn [acc x]
      (if (mySeq x)
        (+ acc x)
        acc
        )
      )
    (range maxD)
    )
  )

(defn task1-rec [now maxD]
  (if (< now maxD)

    (let [next (task1-rec (+ now 1) maxD)]

      (if (mySeq now)
        (+ now next)
        next
        )

      )
    0
    ))

(defn task1-rec-tail [now maxD accum]
  (if (< now maxD)
    (if (mySeq now)
      (task1-rec-tail (+ now 1) maxD (+ accum now))
      (task1-rec-tail (+ now 1) maxD accum)
      )
    accum
    ))

(defn task1-lazy
  ([] (task1-lazy 1000))
  ([x] (reduce (fn [acc n] (if (mySeq n) (+ acc n) acc)) (range x))
   )
  )

