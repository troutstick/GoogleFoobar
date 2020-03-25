import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

/** @author Justin Thein
 *  My solution to one of the problems for Problem 3 of Google Foobar.
 *  Find the number of "staircase" designs given a certain number of bricks to build them with.
 *  */
public class Staircase {
    /** @param n: the number of bricks allowed to build a staircase with.
     *  Constraints: 3 <= n <= 200
     *
     *  Rules:
     *  - Every step must consist of a unique number of bricks.
     *  - Every successive step must be taller than the last.
     *
     *  Example staircase:
     *      #
     *      #
     *      ##
     *      31
     *
     *  @return the number of unique steps
     *  The only solution to n == 4; one step has 1 brick, the other has 3.
     *  In this case, solution(4) == 1.
     *
     *  REPHRASED: Find the number of unique integer sets whose elements are
     *  positive and sum to n.
     *  */
    public static int solution(int n) {
        int maxSteps = maxStepsLeft(n, 1);
        return numStaircases(n, 1, maxSteps);
    }

    /** A recursive helper which knows how many steps it has left and
     *  requires its smallest step to be a certain height.
     *  */
    private static int numStaircases(int numBricks, int smallestStep, int maxSteps) {
        if (maxSteps == 1) {

        }

        int numStaircases = 0;
        Set<Integer> staircase = new HashSet<>();
        for (int stepSize = smallestStep; stepSize <= numBricks; stepSize++) {
            int bricksLeft = numBricks - stepSize;
            int nextStep = stepSize + 1;
            numStaircases += numStaircases(bricksLeft, nextStep, maxStepsLeft(bricksLeft, nextStep));
        }
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
     *
     *  If the MINSTEPSIZE > 1, the NUMBRICKS that must be used goes up by
     *  (MINSTEPSIZE - 1) * LENGTH. Accounting for this, the formula becomes
     *  (sqrt((1 - 2 * MINSTEPSIZE)^2 + 8*NUMBRICKS) - 2 * MINSTEPSIZE + 1) / 2
     *  */
    private static int maxStepsLeft(int numBricks, int minStepSize) {
        return (int) (Math.sqrt(Math.pow(1 - 2 * minStepSize, 2) + (8 * numBricks))
                - (2 * minStepSize)
                + 1)
                / 2;
    }

    @Test
    public void testMaxStepsLeft() {
        assertEquals(15, maxStepsLeft(120, 1));
        assertEquals(14, maxStepsLeft(119, 1));
        assertEquals(15, maxStepsLeft(121, 1));
        assertEquals(15, maxStepsLeft(135, 1));
        assertEquals(16, maxStepsLeft(136, 1));
        assertEquals(16, maxStepsLeft(168, 3));
        assertEquals(16, maxStepsLeft(169, 3));
        assertEquals(15, maxStepsLeft(167, 3));
    }
}
