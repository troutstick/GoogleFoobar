# GoogleFoobar
My solutions to Google Foobar problems.

## Problem 1:
### FoobarID.java
* Given two nearly identical arrays of integers, find the integer that's in only one array.

## Problem 2:
### Triangle.java
* Cleverly dealing with triangular numbers.
  * An optimized solution probably works in constant time; my current solution is linear.
  * Update 3/16/20: Works in theta(1) now, yay!
### CodedMessages.java
* Given an array of digits, find the largest number consisting of those digits that is divisible by 3

## Problem 3:
### LuckyTriples.java
* Find triplets of elements of an integer array which perfectly divide into each other
  * A bit more complicated than that but that's the gist
  
### BombBaby.java
  * Starting from (1, 1), each turn increase x by y or y by x
  * Find the minimum number of turns to get to some point (x, y), which can be any pair of positive integers
    * If no solution, state as such

### Staircase.java
  * Find the number of unique sets of positive integers where:
    * The set has at least 2 elements
    * The elements together sum to n
    
## Problem 4:
### Free Bunny Prisoners
  * Combinatorics problem: Find a way to construct N sets such that any K of them can produce ints from 0 to the binomial coefficient of N and K, but K - 1 sets would not.
### Escape Pod
  * One of my favorites! A max flow problem: Given a graph of flow capacities, find the max possible flow from a set of source nodes to a set of sink nodes.
