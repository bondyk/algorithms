import java.util.BitSet;

/**
 *

 Write a function:

 class Solution { public int solution(int[] A); }

 that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer that does not occur in A.

 For example, given:

 A[0] = 1
 A[1] = 3
 A[2] = 6
 A[3] = 4
 A[4] = 1
 A[5] = 2

 the function should return 5.

 Assume that:

 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].

 Complexity:

 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).

 Elements of input arrays can be modified.

 */
public class MissingInteger {

    public static void main(String[] args) {
        System.out.println(solve(new int[]{1, 3, 6, 4, 1, 2}));
        System.out.println(solve(new int[]{2, 3, 6, 4, 7, 2}));
        System.out.println(solve(new int[]{-1, -3, -6, -4, -1, -2}));
        System.out.println(solve(new int[]{-2, -3, 6, -4, -1, 0}));
    }

    private static int solve(int[] A) {

        boolean[] flags = new boolean[A.length + 1];

        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0 && A[i] <= A.length) {
                flags[A[i] - 1] = true;
            }
        }

        for (int i = 0; i < flags.length; i++) {
            if (!flags[i]) return i + 1;
        }

        return A.length + 1;
    }
}
