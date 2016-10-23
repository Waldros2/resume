#lang racket

;Scott Waldron
;Program1

;Problem1
(define iff
  (lambda (value1 value2)
    (cond
      ((eq? value1 value2) #t)
      (else #f))))

;Problem2
(define exclusive-or
  (lambda (value1 value2)
    (cond
      ((eq? value1 value2) #f)
      (else #t))))

;Problem3
(define difference
  (lambda (lat1 lat2)
    (cond
      ((null? lat1) (quote()))
      ((member (car lat1) lat2) (difference (cdr lat1) lat2))
      (else (cons (car lat1) (difference (cdr lat1) lat2))))))

;Problem4
(define symmetric-difference
  (lambda (lat1 lat2)
    (cond
      ((null? lat1) (cond
                      ((null? lat2)(quote()))
                      (else
                       (cons (car lat2)(symmetric-difference lat1 (cdr lat2))))))
      ((null? lat2) (cond
                      ((null? lat1)(quote()))
                      (else
                       (cons (car lat1)(symmetric-difference (cdr lat1) lat2 )))))
      ((member (car lat1) lat2) (symmetric-difference (rember(car lat1) lat2) (cdr lat1)))
      (else
       (cons (car lat1) (symmetric-difference (cdr lat1) lat2))))))

;Problem5
(define stem
  (lambda (lat)
    (cond
      ((null? (cdr lat)) (quote()))
      (else
       (cons (car lat) (stem (cdr lat)))))))

;Problem6
(define replicate
  (lambda (atom lat)
    (cond
      ((null? lat) (quote()))
      (else
       (cond
         ((eq? atom (car lat)) (cons atom (cons atom (replicate atom (cdr lat)))))
         (else
          (cons (car lat) (replicate atom (cdr lat)))))))))

;Problem7
(define next
  (lambda (x)
    (reverse (next1 (reverse x)))))

(define next1
  (lambda(lat)
    (cond
      ((null? lat) (quote ()))
      ((eq? 1 (car lat)) (cons 0 (next1(cdr lat))))
      (else
       (cons 1 (cdr lat))))))
 
(define rember
  (lambda (atom list)
    (cond
      ((null? list) (quote()))
      (else (cond
              ((equal? (car list) atom) (cdr list))
              (else (cons (car list)
                          (rember atom (cdr list)))))))))
      