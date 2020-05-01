import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

/** @author Justin Thein
 *  My solution to the first of Problem 4 in Google Foobar. */
public class FreeBunnyPrisoners {

    /** Input a number of bunnies NUMBUNS,
     *  and NUMREQ, the number of keys required to open a cell.
     *  Return a 2d int array whose elements are the set of keys
     *  owned by each bunny.
     *
     *  OR: Find a 2d array of which any NUMBUNS elements are
     *  together able to produce all numbers from 0 through NUMREQ.
     *  */
    public static int[][] solution(int numBuns, int numReq) {
        assert numBuns > 0 && numBuns < 10;
        assert numReq >= 0 && numReq < 10;

        return null;
    }

    /** Return n factorial. */
    private static int factorial(int n) {
        assert n >= 0;
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    @Test
    public void factorialTest() {
        assertEquals(3628800, factorial(10));
        assertEquals(1, factorial(1));
        assertEquals(1, factorial(0));

    }
}
