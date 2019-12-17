(define (odd-divisor-helper n cur)
  (cond ((< n cur ) 0)
	((= (mod n cur) 0) (+ cur (odd-divisor-helper n (+ cur 2))))
	(else (odd-divisor-helper n (+ cur 2)))))

(define (odd-divisors n)
  (odd-divisor-helper n 1))
