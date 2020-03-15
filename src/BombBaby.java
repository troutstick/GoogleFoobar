/** @author Justin Thein.
 *  Solution to one of the problems for Problem 3 in Google Foobar.
 *  */

import java.math.BigInteger;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import static org.junit.rules.Timeout.seconds;
import static org.junit.Assert.*;

public class BombBaby {

    private static final BigInteger one = BigInteger.ONE;
    private static final BigInteger zero = BigInteger.ZERO;

    public static String solution(String x, String y) {
        BigInteger ans = numGenerations(new BigInteger(x), new BigInteger(y));
        if (ans == null) {
            return "impossible";
        } else {
            return ans.toString();
        }
    }

    /** Situation 1: If x > y > 1, subtract from x until it's smaller than y.
     *  Situation 2: If 1 < x < y, subtract from y until it's smaller than x.
     *  Situation 3: If x == y == 1, the answer has been found.
     *  Situation 4: If x or y are 1, subtract from the other until 1 is reached.
     *  Situation 5: The problem is impossible to solve.
     *  */
    private static BigInteger numGenerations(BigInteger x, BigInteger y) {
        BigInteger reps = zero;
        while (x.compareTo(one) > 0 && y.compareTo(one) > 0) {
            if (x.compareTo(y) == 0) {
                return null;
            } else if (x.compareTo(y) > 0) {
                // if x > y, subtract from x until it's less than y
                reps = reps.add(x.divide(y));
                x = x.mod(y);
            } else {
                // x < y
                reps = reps.add(y.divide(x));
                y = y.mod(x);
            }
        }

        if (x.compareTo(one) < 0 || y.compareTo(one) < 0) {
            return null;
        } else if (x.equals(one)) {
            return reps.add(y).subtract(one);
        } else {
            return reps.add(x).subtract(one);
        }
    }

    /** Bad recursive attempt; the problem is actually not recursive at all.
     *  This solution is prone to stock overflow errors. */
    private static BigInteger numGenerations(BigInteger x, BigInteger y, BigInteger reps) {
        if (x.equals(one) && y.equals(one)) {
            return reps;
        } else if (x.compareTo(one) < 0 || y.compareTo(one) < 0) {
            return null;
        } else if ((x.subtract(one)).mod(y).equals(zero) && !x.equals(one)) {
            reps = reps.add((x.subtract(one)).divide(y));
            return numGenerations(one, y, reps);
        } else if ((y.subtract(one)).mod(x).equals(zero) && !y.equals(one)) {
            reps = reps.add(y.subtract(one).divide(y));
            return numGenerations(x, one, reps);
        }
        BigInteger path1 = numGenerations(x.subtract(y), y, reps.add(one));
        BigInteger path2 = numGenerations(x, y.subtract(x), reps.add(one));
        if (path1 == null) {
            return path2;
        } else if (path2 == null) {
            return path1;
        } else {
            return path1.min(path2);
        }
    }

    /** These two functions below calculate a naive solution to the problem. */
    private static void recursive1(BigInteger x, BigInteger y, int numleft) {
        if (numleft == 0) {
            System.out.println(x + " and " + y);
        } else {
            recursive2(x.add(y), y, numleft - 1);
        }
    }

    private static void recursive2(BigInteger x, BigInteger y, int numleft) {
        if (numleft == 0) {
            recursive1(x, y, numleft);
        } else {
            recursive1(x, y.add(x), numleft - 1);
        }
    }

    /** Based on an integer array of instructions, this function calculates a solution
     *  and checks if the SOLUTION function can properly solve it. */
    private static void nonRecursive(BigInteger x, BigInteger y, int[] instructions) {
        boolean flip = true;
        int numIns = 0;
        for (int elem : instructions) {
            while (elem > 0) {
                if (flip) {
                    x = x.add(y);
                } else {
                    y = y.add(x);
                }
                elem--;
                numIns++;
            }
            flip = !flip;
        }
        System.out.println(x + " and " + y);
        System.out.println(numIns);
        System.out.println(solution(x.toString(), y.toString()));
    }

    /** A test for the problem. */
    @Test
    public void test() {
        assertEquals("4", solution("4", "7"));
        assertEquals("1", solution("2", "1"));
        assertEquals("30", solution("1346269", "2178309"));
        assertEquals("0", solution("1", "1"));
        assertEquals("14028366653498915298923760", solution("1", "14028366653498915298923761"));

    }

    /** This test checks if the solution encounters a stack overflow error with giant numbers. */
    @Test
    public void dontOverflow() {
        int[] ins = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 69, 69};
        nonRecursive(one, one, ins);
        int[] ins2 = {1, 11812451};
        nonRecursive(one, one, ins2);
        int[] ins3 = {1, 151124531, 1, 329982458, 1, 329982458};
        nonRecursive(one, one, ins3);
    }

    public static void main(String[] args) {
    }
}
