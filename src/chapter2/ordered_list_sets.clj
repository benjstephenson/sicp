(ns chapter2.ordered-list-sets)

; ex 2.61

(defn element-of-set? [x set]
  (cond (empty? set) false
        (= x (first set)) true
        (< x (first set)) false
        :else (element-of-set? x (rest set))))

(element-of-set? 4 [1 2 3 4 5])

(defn intersection-set [set1 set2]
  (if (or (empty? set1) (empty? set2))
    '()
    (let [x1 (first set1) x2 (first set2)]
      (cond (= x1 x2)
            (cons x1 (intersection-set
                      (rest set1)
                      (rest set2)))
            (< x1 x2) (intersection-set
                       (rest set1)
                       set2)
            (< x2 x1) (intersection-set
                       set1
                       (rest set2))))))

(defn adjoin-set [x set]
  (defn adjoin-set-iter [xs tail]
    (cond (empty? tail) (concat xs (list x))
          (= x (first tail)) (concat xs tail)
          (< x (first tail)) (concat xs (cons x tail))
          :else (adjoin-set-iter (concat xs (list (first tail))) (rest tail))))
  (adjoin-set-iter [] set))

(adjoin-set 4 [1 2 3])
