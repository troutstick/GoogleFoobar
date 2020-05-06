import java.util.ArrayList;
import java.util.Arrays;

/** @author Justin Thein
 *  My solution to one of the Problem 2 problems in Google Foobar. */
public class CodedMessages {
    /** Return the largest digit divisible by 3 out of L.
     *  For example:
     *      If L = {1, 2, 3}, the answer would be 321.
     *  */
    public static int solution(int[] L) {
        Arrays.sort(L);
        ArrayList<Integer> ints = new ArrayList<>();
        for (int elem : L) {
            ints.add(elem);
        }
        return codedMessage(ints);
    }

    /** Given a sorted arraylist L, return the largest digit divisible by 3
     *  that can be constructed from said list. */
    private static int codedMessage(ArrayList<Integer> L) {
        int sum = 0;
        for (int elem : L) {
            sum += elem;
        }
        if (sum % 3 == 0) {
            return arrToInt(L);
        } else if (L.size() > 1) {
            int ans = 0;
            for (int i = 0; i < L.size(); i++) {
                ArrayList<Integer> cloneL = (ArrayList<Integer>) L.clone();
                cloneL.remove(i);
                ans = Math.max(codedMessage(cloneL), ans);
            }
            return ans;
        } else {
            return 0;
        }
    }

    /** Flatten arraylist to int. */
    private static int arrToInt(ArrayList<Integer> L) {
        StringBuilder sb = new StringBuilder();
        for (int i = L.size() - 1; i >= 0; i--) {
            sb.append(L.get(i));
        }
        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) {
        int[] L = new int[]{4, 3, 1, 9, 5, 1};
        System.out.println(solution(L));
    }
}
