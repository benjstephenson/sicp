(ns chapter1-3
  (:require [chapter1]))

(defn square [x]
  (* x x))

; ex 1.32
(defn accum [f unit term a next b]
  (if (> a b)
    unit
    (f (term a)
       (accum f unit term (next a) next b))))

(defn sum [term a next b]
  (accum + 0 term a next b))

(sum #(+ % 1) 0 #(+ % 1) 9)

(defn product [term a next b]
  (accum * 1 term a next b))

(product #(+ % 1) 0 #(+ % 1) 9)

; ex 1.33
(defn filtered-accum [f unit term a next b pred?]
  (if (> a b)
    unit
    (f
     (if (pred? a) (term a) unit)
     (filtered-accum f unit term (next a) next b pred?))))

(defn sum-of-squares [a b]
  (filtered-accum + 0 square a inc b chapter1/prime?))

(defn dbl [f]
  #(f (f %1)))

(defn inc [a]
  (+ a 1))

((dbl inc) 5) ; 7

; ex 1.42
(defn compose [f g]
  #(f (g %)))

((compose square inc) 6) ; 49

; ex 1.43

(defn repeated [f x]
  (if (= x 0)
    #(f %)
    (repeated (compose f f) (- x 1))))

((repeated square 2) 5)

; ex 1.44

(defn smooth [f dx]
  #((/ 3
       (+
        (f (- % dx))
        (f %)
        (f (+ % dx))))))

(defn nth-smooth [f dx n]
  ((repeated smooth n) f dx))

; ex 1.46

(defn iterative-improve [good? improve]
  (defn work [guess]
    (if (good? guess)
      guess
      (work (improve guess))))

  #(work %))

((iterative-improve #(= 10 %) #(+ % 1)) 0)

