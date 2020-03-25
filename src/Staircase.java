import org.junit.Test;
import static org.junit.Assert.*;

/** @author Justin Thein
 *  My solution to one of the problems for Problem 3 of Google Foobar.
 *  Find the number of "staircase" designs given a certain number of bricks to build them with.
 *  */
public class Staircase {
    /** @param n: the number of bricks allowed to build a staircase with.
     *  Constraints: 3 <= n <= 200
     *  Rules:
     *          Every step must consist of a unique number of bricks.
     *          Every successive step must be taller than the last.
     *  Example:
     *          #
     *          #
     *          ##
     *          31
     *  @return the number of unique steps
     *  The only solution to n == 4; one step has 1 brick, the other has 3.
     *  In this case, solution(4) == 1.
     *
     *  REPHRASED: Find the number of unique integer sets whose elements are
     *  positive and sum to n.
     *  */
    public static int solution(int n) {
        // should output a long
        return 0;
    }

    /** Based on triangle number formula. For NUMBRICKS bricks, one can
     *  build up to LENGTH == (sqrt(1 + (8*NUMBRICKS)) - 1) / 2).
     *
     *  This is based on the formula NUMBRICKS == ((LENGTH * (LENGTH + 1)) / 2).
     *  LENGTH refers to the maximum number of steps that can be built; the steps
     *  start from 1 and increment by 1 until the number of bricks is exhausted.
     *
     *  One must conclude that the number of steps in the staircase <= LENGTH.
     *
     *  The answer is rounded down to the nearest integer.
     *  */
    private static int maxStepsLeft(int numBricks) {
        return (int) (Math.sqrt((8 * numBricks) + 1) - 1) / 2;
    }

    @Test
    public void testMaxStepsLeft() {
        assertEquals(15, maxStepsLeft(120));
        assertEquals(14, maxStepsLeft(119));
        assertEquals(15, maxStepsLeft(121));
        assertEquals(15, maxStepsLeft(135));
        assertEquals(16, maxStepsLeft(136));
    }
}
