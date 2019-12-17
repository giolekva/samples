(let ((x 5)
      (y 10))
  (* x y))

((lambda (x y)
   (* x y))
 5 10)


(define (contains elem seq)
  (cond ((null? seq) #f)
	((= elem (car seq)) #t)
	(else (contains elem (cdr seq)))))

(define (uniqum-helper left right)
  (cond ((null? right) left)
	((not (contains (car right) left))
	 (let ((rec-sol
	       (uniqum-helper
		(append left (list (car right)))
		(cdr right))))
	   (if (> (length left) (length rec-sol)) left rec-sol)))
	(else
	 (let ((rec-sol
	       (uniqum-helper (cdr left) right)))
	   (if (> (length left) (length rec-sol)) left rec-sol)))))

(define (longestUniqum seq)
  (uniqum-helper '() seq))
