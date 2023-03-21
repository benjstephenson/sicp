(ns chapter2.painting)

(defn beside [x y]
  (x y))

(defn below [x y]
  (x y))

(defn flip-vert [x]
  x)

(defn flipped-pairs [painter]
  (let [painter2 (beside painter (flip-vert painter))]
    (below painter2 painter2)))

(defn right-split [painter n]
  (if (= n 0)
    painter
    (let [smaller (right-split painter (- n 1))]
      (beside painter
              (below smaller smaller)))))

; ex 2.44
(defn up-split [painter n]
  (if (= n 0)
    painter
    (let [smaller (up-split painter (- n 1))]
      (below painter
             (below smaller smaller)))))

; ex 2.45 {{
(defn split [left right]
  (defn split-iter [painter n]
    (if (= n 0)
      painter
      (let [smaller (split-iter painter (- n 1))]
        (left painter
              (right smaller smaller)))))
  split-iter)

(def right-split2 (split beside below))
(def up-split2 (split below beside))

;}}

(defn corner-split [painter n]
  (if (= n 0)
    painter
    (let [n-1 (- n 1)
          up (up-split painter n-1)
          right (right-split painter n-1)]
      (let [top-left (beside up up)
            bottom-right (below right right)
            corner (corner-split painter n-1)]
        (beside (below painter top-left)
                (below bottom-right corner))))))

