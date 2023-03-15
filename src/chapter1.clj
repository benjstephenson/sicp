(ns chapter1
  (:require [java-time.api :as t]))

(defn square [n]
  (* n n))

(defn divides? [a b]
  (= (mod b a) 0))

(defn find-divisor [n test-divisor next]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (next test-divisor) next)))

(defn smallest-divisor [n]
  (find-divisor n 2 #(+ %1 1)))

(defn prime? [n]
  (= n (smallest-divisor n)))

(prime? 13) ; true

; Ex 1.21
(smallest-divisor 199) ; 199

(smallest-divisor 1999) ; 1999

(smallest-divisor 19999) ; 7

; Ex 1.22
(defn report-prime [elapsed-time]
  (println "***")
  (println elapsed-time))

(defn start-prime-test [n start-time]
  (cond (prime? n)
        (report-prime (- (t/to-millis-from-epoch (t/instant)) start-time))))

(defn timed-prime-test [n]
  (print n)
  (start-prime-test n (t/instant)))

(defn search-for-primes [count start]
  (let [start-time (t/to-millis-from-epoch (t/instant))]

    (defn work [n found]
      (if (= found count)
        (report-prime (- (t/to-millis-from-epoch (t/instant)) start-time))
        (if (prime? n)
          (do
            (println n)
            (work (+ n 1) (+ found 1)))
          (work (+ n 1) found))))

    (work start 0)))

(search-for-primes 3 1000)
(search-for-primes 3 10000)
(search-for-primes 3 100000)

; Ex 1.23

(defn nxt [n]
  (cond (< n 2) n
        (= 2 n) 3
        :else (+ n 2)))

(defn fast-smallest-divisor [n]
  (find-divisor n 2 nxt))

(fast-smallest-divisor 12347)
