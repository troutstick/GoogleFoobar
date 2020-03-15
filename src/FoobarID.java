/** @author Justin Thein
 *  Solutions to Problem 1 on Google Foobar.
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


}

