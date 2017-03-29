#lang racket
;Scott Waldron
;W00970369
;CSCI301
;Assingment 3


;sampleMachine1
(define final '(4))
(define trans1a '(a 1 2))
(define trans1b '(b 1))
(define trans1c '(c 1))
(define trans2b '(b 3))
(define trans3c '(c 4))
(define trans1 (list 1 trans1a trans1b trans1c))
(define trans2 (list 2 trans2b))
(define trans3 (list 3 trans3c))
(define term-abc (list final trans1 trans2 trans3))

;sampleMachine2
(define tt0a '(a 1))
(define tt0eps '(eps 2))
(define tt1b '(b 3 ))
(define tt2a '(a 2 3))
(define f2 '(3))
(define mach2 (list f2 (list 0 tt0a tt0eps) (list 1 tt1b) (list 2 tt2a)))

;possiblePath
(define possiblePath
  (lambda (state symbol)
    (cond
      ((null? state) (quote()))
       ((eq? symbol (car (car state))) (cdr (car state)))
       (else
        (possiblePath (cdr state) symbol)))))





;Transistions
(define transitions
  (lambda (state symbol machine)
    (cond
      ((null? machine) (quote()))
      (else
       (cond
         ((eq? (car (car machine)) state) (possiblePath (cdr (car machine)) symbol))
         (else
          (transitions state symbol (cdr machine))))))))

;nfa-execute
(define nfa-execute
  (lambda (string start machine)
    (cond
      ((null? machine) (quote()))
      ((null? start) (quote()))
      ((null? string) (backTracker string start 'null 'null machine))
      (else
       (define possibleTrans (transitions start (car string) machine))
       (define possibleEps (transitions start 'eps machine))
       (backTracker string start possibleTrans possibleEps machine)))))





;backTracker
(define backTracker
  (lambda (string start trans epstrans machine)
    (cond
      ((null? string)
       (cond ((eq? start (car (car machine))) (list start))
             (else (quote()))))
      (else
       (cond
         ((not (null? trans))
          (define result (nfa-execute (cdr string) (car trans) machine))
          (cond
            ((null? result) (backTracker string start (cdr trans) epstrans machine))
            (else (append (list start) result))))
         (else
          (cond
            ((null? epstrans) (quote()))
            (else
          (define tResult (nfa-execute string (car epstrans) machine))
          (cond
            ((null? tResult) (backTracker string start trans (cdr epstrans) machine))
            (else (append (list start) tResult)))))))))))
          
       







          