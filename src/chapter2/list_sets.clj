(ns chapter2.list-sets)

; ----- unordered list sets {{{
(defn element-of-set? [x set]
  (cond (empty? set) false
        (=  x (first set)) true
        :else (element-of-set? x (rest set))))

(element-of-set? 5 (list 1 2 3 4))
(element-of-set? "2" (list "1" "2" "3" "4"))

; ex 2.59 
(defn union-set [set1 set2]
  (cond (empty? set2) set1
        (element-of-set? (first set2) set1) (union-set set1 (rest set2))
        :else (union-set (conj set1 (first set2)) (rest set2))))

(union-set [1 2 3 4 5] [3 4 5 6 7])

; }}}
