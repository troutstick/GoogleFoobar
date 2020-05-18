from math import floor
from math import sqrt

"""Python 2.7 implementation of Dodge The Lasers."""

multiplicand = sqrt(2) - 1

def solution(s):
    """With str input, return a str formatted output."""
    return str(answer(int(s)))

def answer(n):
    """Return the sum of integers floor(1*sqrt(2)) to floor(n*sqrt(2))."""
    if n <= 0:
        return 0
    else:
        n_prime = int(n * multiplicand)
        return (n * n_prime) + triangle(n) - triangle(n_prime) - answer(n_prime)

def triangle(n):
    """Return the triangle sum of n."""
    return (n * (n + 1)) / 2
