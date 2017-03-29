## Main function
## Return the nth Fibonacci number
## n must be an integer >= 0
def fib(n):
    ## Handle special case when n == 0
    if n == 0:
        return 0
    ## General case, return the first of the
    ## two values returned by fibaux
    else:
        return fibaux(n)[0]

## Auxiliary function
## Return the nth and (n-1)th Fibonacci numbers
## n must be an integer >= 1
def fibaux(n):
    ## Base case of for recursion
    if n == 1:
        return 1, 0
    else:
        ## Recursive case
        f2, f1 = fibaux(n - 1)
        return f2 + f1, f2

## Bad version of Fibonacci, can be very inefficient
def badfib(n):
    if n <= 1:
        return n
    else:
        return badfib(n - 1) + badfib(n - 2)

