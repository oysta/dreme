(
(lambdaFormals
    (let ((x 5)) ((lambda (y) (* y x)) 7))
    35)
(lambdaFinalReturn
    ((lambda () 1 2 3))
    3)
(lambdaListArgument
    ((lambda a a) 1 2 3)
    (1 2 3))
(lambdaListArgumentsAreEvaluated
    ((lambda a a) 1 (+ 1 1) 3)
    (1 2 3))
(lambdaRestArgument
    ((lambda (a b . c) (list a c)) 1 2 3 4)
    (1 (3 4)))
(lambdaRestArgumentsAreEvaluated
    ((lambda (a b . c) (list a c)) 1 (+ 1 1) 3 (+ 2 2))
    (1 (3 4)))
(lambdaLexicalClosure
    (let ((x 5))
        (let ((y (lambda (z) (lambda () (+ x z))))) ; Chris's crazy shit - worked first time!
            (let ((q (y 1)) (x 9)) 
                (q))))
    6)
(staticScoping
    (begin
        (define x 5)
        (define f (lambda () x))
        (define g (lambda ()
            (let ((x 10))
                (f))))
        (g))
    5) ; would be 10 if we had dynamic scoping
(recursionWithPassingSelf
    (let ((fact (lambda (fact x)
        (if (> x 1) (* x (fact fact (- x 1))) 1))))
        (fact fact 13))
    6227020800)
(recursionWithLetRec
    (letrec ((fact (lambda (x)
        (if (> x 1) (* x (fact (- x 1))) 1))))
        (fact 5))
    120)
(fibAccumulate
    (let ((fibonacci (lambda (n)
        (if (= n 0)
            0
            (letrec ((fib (lambda (i a1 a2)
                (if (= i 1)
                    a1
                    (fib (- i 1) (+ a1 a2) a1)))))
            (fib n 1 0))))))
        (fibonacci 50))
    12586269025)
(primeFactorsWithDefineFunction
    (begin
        (define (factor n)
            (let f ((n n) (i 2))
                (cond
                    ((>= i n) (list n))
                    ((integer? (/ n i))
                        (cons i (f (/ n i) i)))
                    (else (f n (+ i 1))))))
        (list
            (factor 0)
            (factor 1)
            (factor 12)
            (factor 3628800)
            (factor 137)))
    (   (0)
        (1)
        (2 2 3)
        (2 2 2 2 2 2 2 2 3 3 3 3 5 5 7)
        (137)))
)