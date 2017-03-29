#lang racket
;Scott Waldron
;W00970369
;CSCI301 Program2

;Problem1
(define set-equal?
  (lambda (set1 set2)
    (and (subset? set1 set2) (subset? set2 set1))))

;Problem2
(define RemoveFirst
  (lambda (n lat)
    (cond
      ((< n 0) #f)
      ((= n 0) lat)
      ((null? lat) '())
      (else
       (RemoveFirst (sub1 n) (cdr lat))))))

;Problem3
(define RemoveLast
  (lambda (n lat)
    (cond
      ((< n 0) #f)
      ((= n 0) lat)
      ((null? lat) '())
      (else
       (RemoveLast (sub1 n) (reverse(cdr(reverse lat))))))))

;Problem4
(define getFirstN
  (lambda (n lat)
    (cond
      ((< n 0) #f)
      ((= n 0) (quote()))
      ((null? lat) (quote()))
      (else
       (cons (car lat) (getFirstN (sub1 n) (cdr lat)))))))

(define SliceList
  (lambda (m n lat)
    (cond
    ((or (< n 1) (< n m) (< m 1) (> n (length lat)) ) #f) 
    (else
     (getFirstN (+ 1 (- n m)) (RemoveFirst (- m 1) lat))))))

;Problem5
(define pair
  (lambda (lat1 lat2)
    (cond
      ((not(= (length lat1) (length lat2))) #f)
      ((null? lat1) (quote()))
      (else
       (cons (list (car lat1) (car lat2)) (pair (cdr lat1) (cdr lat2)))))))

;Problem6
(define pair2
  (lambda (lat1 lat2)
    (cond
      ((null? lat1) (quote()))
      ((null? lat2) (quote()))
      (else
       (cons (list (car lat1) (car lat2)) (pair2 (cdr lat1) (cdr lat2)))))))

;Problem7
(define addKeep
  (lambda (lat)
    (cons (+ 1 (car lat)) (cdr lat))))

(define div
  (lambda (q d)
    (cond
      ((= d 0) #f)
      ((> (- q d) 0) (addKeep (div (- q d) d)))
      ((zero? (- q d)) (list 1 0))
      (else
       (list 0 q)))))
      