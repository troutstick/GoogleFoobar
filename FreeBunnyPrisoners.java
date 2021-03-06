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
     *  */
    public static int[][] solution(int numBuns, int numReq) {
        assert numBuns > 0 && numBuns < 10;
        assert numReq >= 0 && numReq < 10;
        assert numBuns >= numReq;

        List<List<Integer>> bunniesList = new ArrayList<>();
        for (int i = 0; i < numBuns; i++) {
            bunniesList.add(new ArrayList<>());
        }

        /* Each combination represents the bunnies containing the ith key.
         * E.g. if bunniesWithKeys.get(2) is [1, 3, 5],
         * that means Bunny 1, Bunny 3, and Bunny 5 all have Key 2.
         * */
        List<int[]> bunniesWithKeys = subsets(numBuns, numBuns - numReq + 1);
        for (int key = 0; key < bunniesWithKeys.size(); key++) {
            int[] keyArray = bunniesWithKeys.get(key);
            for (int bunny : keyArray) {
                bunniesList.get(bunny).add(key);
            }
        }

        // convert the list to an array
        int[][] bunnies = new int[numBuns][];
        for (int i = 0; i < bunnies.length; i++) {
            List<Integer> convertToArr = bunniesList.get(i);
            bunnies[i] = convertToArr.stream().mapToInt(x -> x).toArray();
        }

        return bunnies;
    }

    /** Iteratively generate subsets of N elements containing K sets.
     *  Thanks to https://www.baeldung.com/java-combinations-algorithm
     *  for the implementation.
     *  @author Chandra Prakesh
     *  */
    private static List<int[]> subsets(int n, int k) {
        List<int[]> allCombinations = new ArrayList<>();
        int[] subset = new int[k];

        // initialize with lowest lexicographic combination
        for (int i = 0; i < k; i++) {
            subset[i] = i;
        }

        while (subset[k - 1] < n) {
            allCombinations.add(subset.clone());

            // generate next combination in lexicographic order
            int t = k - 1;
            while (t != 0 && subset[t] == n - k + t) {
                t--;
            }
            subset[t]++;
            for (int i = t + 1; i < k; i++) {
                subset[i] = subset[i - 1] + 1;
            }
        }
        return allCombinations;
    }

    /** Return the number of ways to pull K elements from
     *  a set of N elements. */
    @Deprecated
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
    public void testSolutions() {
        int[][] test53 =
                {{0, 1, 2, 3, 4, 5},
                {0, 1, 2, 6, 7, 8},
                {0, 3, 4, 6, 7, 9},
                {1, 3, 5, 6, 8, 9},
                {2, 4, 5, 7, 8, 9}};
        assertArrayEquals(test53, solution(5, 3));

        int[][] test44 = {{0}, {1}, {2}, {3}};
        assertArrayEquals(test44, solution(4, 4));

        int[][] test21 = {{0}, {0}};
        assertArrayEquals(test21, solution(2, 1));

        // print out solution(9, 5)
        int[][] solution95 = solution(9, 5);
        for (int[] subset : solution95) {
            System.out.print("[");
            for (int num : subset) {
                System.out.print(num + " ");
            }
            System.out.print("]\n");
        }

        // test if solution(9, 5) works
        Set<Integer> intSet = new HashSet<>();
        List<int[]> allSets = subsets(9, 5);
        for (int[] subset : allSets) {
            intSet.clear();
            for (int elem : subset) {
                int[] intsToAdd = solution95[elem];
                for (int intToAdd : intsToAdd) {
                    intSet.add(intToAdd);
                }
            }
            for (int i = 0; i < 126; i++) {
                assertTrue(intSet.contains(i));
            }
        }
    }
}
