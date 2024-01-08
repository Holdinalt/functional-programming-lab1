(defn mySeq [x] (or (zero? (mod x 5)) (zero? (mod x 3))))

(defn task1 []
  (reduce
    (fn [acc, x]
      (if (mySeq x)
        (+ acc x)
        acc
        )
      )
    (range 1000)
    )
  )

(task1)

(defn task1-rec [now maxD]
  (if (<= now maxD)

    (let [next (task1-rec (+ now 1) maxD)]

      (if (mySeq now)
        (+ now next)
        next
        )

      )
    0
    ))



(task1-rec 0 1000)

(defn task1-rec-tail [now maxD accum]
  (if (<= now maxD)
    (if (mySeq now)
      (task1-rec-tail (+ now 1) maxD (+ accum now))
      (task1-rec-tail (+ now 1) maxD accum)
      )
    accum
    ))

(task1-rec-tail 0 1000 0)


