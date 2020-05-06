import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Set;

/** @author Justin Thein
 *  My solution to one of the Problem 3 problems in Google Foobar. */
public class LuckyTriples {

    public static int solution(int[] array) {
        return calculateTriples(array);
    }

    /** Given an array of integers, find the number of triplets of
     *  integers arr[i], arr[j], arr[k], where i > j > k,
     *  arr[j] % arr[i] = 0, and arr[k] % arr[j] = 0. If no triplets
     *  are found, return 0.
     *  */
    private static int calculateTriples(int[] arr) {
        _fastSearch = Arrays.stream(arr).boxed().collect(Collectors.toSet());
        int ans = 0;
        _max = Collections.max(_fastSearch);
        for (int i = 0; i < arr.length - 2; i++) {
            int iValue = arr[i];
            for (int jValue = iValue; jValue <= _max; jValue += iValue) {
                if (_fastSearch.contains(jValue)) {
                    // search array for index of jValue where j > i
                    for (int j = i + 1; j < arr.length - 1; j++) {
                        if (arr[j] == jValue) {
                            ans += numLuckyThrees(arr, jValue, j);
                        }
                    }
                }
            }
        }
        return ans;
    }

    /** Helper to find 3rd number, given the index and the value of the 2nd.
     *  Returns the number of valid k's that exist.
     * */
    private static int numLuckyThrees(int[] arr, int jValue, int j) {
        int ans = 0;
        for (int kValue = jValue; kValue <= _max; kValue += jValue) {
            if (_fastSearch.contains(kValue)) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (arr[k] == kValue) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    /** Some basic tests. */
    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5, 6};
        System.out.println(solution(test));

        int[] test2 = {1, 1, 1};
        System.out.println(solution(test2));

        int[] test3 = {1, 2, 3, 4, 5, 6, 22345, 23576, 473467, 28246732, 25468358, 35789357, 4363456, 3487388};
        System.out.println(solution(test3));
    }

    /** Set to quickly find multiples. */
    private static Set<Integer> _fastSearch;

    /** Max value of the collection. */
    private static int _max;
}
