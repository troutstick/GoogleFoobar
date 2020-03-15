/** @author Justin Thein
 *  Solution to one of the two Problem 2 parts on Google Foobar.
 * */
public class Triangle {

    /** For problem 2 of Google Foobar.
     *
     *  Calculates the output of a function f(x, y), where x and y are positive integers,
     *  and f(1, 1) outputs 1. The function lists all positive integers in diagonal rows
     *  pointing to the upper left, and generally, f(x, y) = f(x + 1, y - 1) + 1.
     *
     *  I initially tried to solve the problem using recursion, which led to stack overflow
     *  errors.
     *
     *  TODO: Constant time solution.
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
