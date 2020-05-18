from math import sqrt
from math import floor
from decimal import *

"""Python 2.7 implementation of Dodge The Lasers."""

# high precision sqrt(2) - 1
multiplicand = Decimal("0.4142135623730950488016887242096980785696718753769480731766797379907324784621" +
                       "07038850387534327641572735013846230912297024924836055850737212644121497099935831" +
                       "41322266592750559275579995050115278206057147010955997160597027453459686201472851" +
                       "74186408891986095523292304843087143214508397626036279952514079896872533965463318" + 
                       "08829640620615258352395054745750287759961729835575220337531857011354374603408498" +
                       "84716038689997069900481503054402779031645424782306849293691862158057846311159666" + 
                       "87130130156185689872372352885092648612494977154218334204285686060146824720771435")

def solution(s):
    """With str input, return a str formatted output."""
    assert isinstance(s, str), "s must be string"
    return str(answer(int(s)))

def answer(n):
    """Return the sum of integers floor(1*sqrt(2)) to floor(n*sqrt(2))."""
    assert isinstance(n, int) or isinstance(n, long), "n must be int or long"

    if n <= 0:
        return 0
    else:
        n_prime = long(floor(n * multiplicand))
        return (n * n_prime) + triangle(n) - triangle(n_prime) - answer(n_prime)

def triangle(n):
    """Return the triangle sum of n."""
    assert isinstance(n, int) or isinstance(n, long), "n must be int or long"
    return (n * (n + 1)) / 2
