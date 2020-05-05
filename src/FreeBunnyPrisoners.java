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
        assert numBuns >= numReq;
        int numKeys = numCombinations(numBuns, numReq - 1);
        int keysPerBunny = (numKeys * numReq) / numBuns;
        return null;
    }

    /** Iteratively generate subsets of N elements containing K sets.
     *  @author Chandra Prakesh
     *  */
    public List<int[]> subsets(int n, int k) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[k];

        // initialize with lowest lexicographic combination
        for (int i = 0; i < k; i++) {
            combination[i] = i;
        }

        while (combination[k - 1] < n) {
            combinations.add(combination.clone());

            // generate next combination in lexicographic order
            int t = k - 1;
            while (t != 0 && combination[t] == n - k + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < k; i++) {
                combination[i] = combination[i - 1] + 1;
            }
        }
        return combinations;
    }

    /** Return the number of ways to pull K elements from
     *  a set of N elements. */
    private static int numCombinations(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    /** Return N factorial. */
    private static int factorial(int n) {
        assert n >= 0;
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    @Test
    public void printCombinations() {
        List<int[]> subsets = subsets(5, 3);
        for (int[] subset : subsets) {
            for (int num : subset) {
                System.out.print(num + " ");
            }
            System.out.print("\n");
        }
    }

    @Test
    public void factorialTest() {
        assertEquals(3628800, factorial(10));
        assertEquals(1, factorial(1));
        assertEquals(1, factorial(0));
        assertEquals(126, numCombinations(9, 4));
    }
}
