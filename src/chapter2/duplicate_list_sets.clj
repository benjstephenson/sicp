(ns chapter2.duplicate-list-sets)

; ex 2.60 non-distinct list as a set

(defn element-of-set? [x set]
  (cond (empty? set) false
        (=  x (first set)) true
        :else (element-of-set? x (rest set))))

(defn adjoin-set [x set]
  (conj set x))

(defn union-set [set1 set2]
  (if (empty? set2) set1
      (concat set1 set2)))

(union-set [1 2 3 4 5] [3 4 5 6 7])

(defn intersection-set [set1 set2]
  (cond (or (empty? set1) (empty? set2)) '()
        (element-of-set? (first set1) set2)
        (cons (first set1)
              (intersection-set (rest set1)
                                set2))
        :else (intersection-set (rest set1)
                                set2)))

(intersection-set [1 2 2 3 3 4] [5 5 1 3 6])
