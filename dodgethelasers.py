from math import sqrt
from math import floor

"""
Python 2.7 implementation of Dodge The Lasers. 
This is a problem involving summing from 1 to n the elements of floor(n*sqrt(2)), 
documented as A001951 on OEIS.
Solution thanks to https://math.stackexchange.com/a/2053713
"""

# high precision sqrt(2) - 1
multiplicand = 414213562373095048801688724209698078569671875376948073176679737990732478462107038850387534327641572735013846230912297024924836055850737212644121497099L
denominator = pow(10, 150)


def solution(s):
    """With str input, return a str formatted output."""
    assert isinstance(s, str), "s must be string"
    return str(answer(long(s)))


def answer(n):
    """Return the sum of integers floor(1*sqrt(2)) to floor(n*sqrt(2))."""
    if n <= 0:
        return 0
    else:
        # n' = n * (sqrt(2) - 1)
        n_prime = (n * multiplicand) / denominator
        return (n * n_prime) + triangle(n) - triangle(n_prime) - answer(n_prime)


def triangle(n):
    """Return the triangle sum of n."""
    return (n * (n + 1)) / 2
