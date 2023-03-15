(ns chapter2.lists)

; ex 2.17
(defn last-pair [x]
  (let [item (rest x)]
    (if (empty? item)
      x
      (last-pair item))))

(last-pair 1)
(last-pair (list 1 2 3 4 5))

; ex 2.18
(defn reverse-list [items]
  (defn reverse-iter [x work]
    (if (empty? x)
      work
      (reverse-iter
       (rest x)
       (cons (first x) work))))
  (reverse-iter items nil))

(reverse-list (list 1 2 3 4 5))

; ex 2.21

(defn square-list [items]
  ;(map (fn [x] (* x x)) items)
  (map #(* % %) items))

(square-list (list 1 2 3 4))

(defn square-list-cons [items]
  (if (empty? items)
    nil
    (cons (* (first items) (first items)) (square-list-cons (rest items)))))

(square-list (list 1 2 3 4))

; ex 2.23
(defn for-each [f xs]
  (if (empty? xs)
    nil
    (let [x (first xs)]
      (f x)
      (for-each f (rest xs)))))

(for-each #(println %) (list 57 321 88))

; ex 2.28
(defn deep-reverse [items]
  (defn iter2 [xs work]
    (cond
      (empty? xs) work
      (list? (first xs)) (iter2 (rest xs) (cons (deep-reverse (first xs)) work))
      :else (iter2 (rest xs) (cons (first xs) work))))

  (defn iter [xs work]
    (if (empty? xs)
      work
      (let [x (first xs)]
        (iter
         (rest xs)
         (cons (if (not (list? x)) x (deep-reverse x)) work)))))
  (iter2 items nil))

(deep-reverse (list 1 2 (list 3 4)))


