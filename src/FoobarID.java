/** @author Justin Thein
 *  Solutions to Problem 1 and the first part of Problem 2 on Google Foobar.
 * */

public class FoobarID {
    /** Problem 1 of Google Foobar. Given two integer arrays of integers,
     *  where one array is one element longer than the other, find the single
     *  element of the longer array that is not found in the shorter array. */
    public static int solution(int[] x, int[] y) {
        if (x.length >= y.length) {
            for (int i = 0; i < x.length; i++) {
                if (!contains(y, x[i])) {
                    return x[i];
                }
            }
            return 0;
        } else {
            return solution(y, x);
        }
    }

    /** Naively searches ARR for an integer X in linear time. */
    private static boolean contains(int[] arr, int x) {
        for (int value : arr) {
            if (value == x) {
                return true;
            }
        }
        return false;
    }

    /** For problem 2 of Google Foobar.
     *
     *  Calculates the output of a function f(x, y), where x and y are positive integers,
     *  and f(1, 1) outputs 1. The function lists all positive integers in diagonal rows
     *  pointing to the upper left, and generally, f(x, y) = f(x + 1, y - 1) + 1.
     *
     *  I initially tried to solve the problem using recursion, which led to stack overflow
     *  errors.
     *  */
    private static long triangle(long x, long y) {
        long answer = 0;
        while (y > 1) {
            answer += y + x - 2;
            y--;
        }
        answer += (x * (x + 1)) / 2;
        return answer;
    }

    /** Some basic sanity checking. */
    public static void main(String[] args) {
        System.out.println(triangle(5, 10));
    }
}

