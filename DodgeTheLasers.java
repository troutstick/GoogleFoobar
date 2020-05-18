import org.junit.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.lang.Math;

/** My solution to the first of Foobar part 5.
 *  @author Justin Thein */
public class DodgeTheLasers {

    /** This is a problem involving summing from 1 to n the elements of floor(n*sqrt(2)),
     *  documented as A001951 on OEIS.
     *
     *  Solution thanks to https://math.stackexchange.com/a/2053713
     *  */
    public String solution(String s) {
        BigInteger ans = beattySum(new BigInteger(s));
        return ans.toString();
    }

    /** Calculate the solution. */
    private BigInteger beattySum(BigInteger n) {
        if (n.equals(ZERO) || n.compareTo(ZERO) < 0) {
            return ZERO;
        }
        BigDecimal nSqrt = (new BigDecimal(n)).multiply(MULTIPLICAND);
        BigInteger nPrime = nSqrt.toBigInteger();
        BigInteger
                term1 = n.multiply(nPrime),
                term2 = triangle(n),
                term3 = triangle(nPrime);
        return term1.add(term2).subtract(term3).subtract(beattySum(nPrime));
    }

    /** Return the triangle sum of N. */
    private BigInteger triangle(BigInteger n) {
        return n.multiply(n.add(ONE)).divide(TWO);
    }

    /** Sqrt(2) - 1. */
    private static final BigDecimal MULTIPLICAND;
    static {
        String _multiStr = Double.toString(Math.sqrt(2) - 1);
        MULTIPLICAND = new BigDecimal(_multiStr);
    }

    private static final BigInteger
            ZERO = BigInteger.ZERO,
            ONE = BigInteger.ONE,
            TWO = new BigInteger("2");

    @Test
    public void testSolution() {
        assertEquals(solution("77"), "4208");
        assertEquals(solution("5"), "19");
        System.out.println(solution("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        System.out.println(solution("1000"));

    }
}
