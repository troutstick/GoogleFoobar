/** @author Justin Thein
 *  Solution to one of the two Problem 2 parts on Google Foobar.
 * */
public class Triangle {

    /** For problem 2 of Google Foobar.
     *
     *  Calculates the output of a function f(x, y), where x and y are positive integers,
     *  and f(1, 1) outputs 1. The function lists all positive integers in diagonal rows
     *  increasing to the upper left, and generally, f(x, y) = f(x + 1, y - 1) + 1.
     *
     *  I initially tried to solve the problem using recursion, which led to stack overflow
     *  errors.
     *
     *  I arrived at my constant time solution by taking advantage of the triangle number summation;
     *  it's more obvious along the x axis but can be seen on the y axis as well.
     *  */
    private static long triangle(long x, long y) {
        return (x - 2) * (y - 1) - 1 + triangleNumber(x) + triangleNumber(y);
    }

    /** The result of summing all integers from 1 to x. */
    private static long triangleNumber(long x) {
        return ((x * (x + 1)) / 2);
    }

    /** Some basic sanity checking. */
    public static void main(String[] args) {
        System.out.println(triangle(5, 10)); // 96
    }
}
