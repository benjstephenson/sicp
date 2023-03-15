(ns chapter2.make-rat)

(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (mod a b))))

(defn numer [x]
  (first x))

(defn denum [x]
  (last x))

(defn print-rat [x]
  (newline)
  (print (numer x))
  (print "/")
  (print (denum x)))

(defn make-rat [n d]
  (let [g (gcd n d)]
    (cons (/ n g)
          [(/ d g)])))

(print-rat (make-rat 1 3))
