import java.util.Stack;

/**
 * A zero-indexed array A consisting of N integers is given. The dominator of array A is the value
 * that occurs in more than half of the elements of A.
 For example, consider array A such that
     A[0] = 3    A[1] = 4    A[2] =  3
     A[3] = 2    A[4] = 3    A[5] = -1
     A[6] = 3    A[7] = 3
 The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7) and 5 is more than a half of 8.

 Write a function
    int solution(int A[], int N);
 that, given a zero-indexed array A consisting of N integers, returns index of any element of array A in which the dominator of A occurs. The function should return −1 if array A does not have a dominator.

 Assume that:
     N is an integer within the range [0..100,000];
     each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].

 For example, given array A such that
     A[0] = 3    A[1] = 4    A[2] =  3
     A[3] = 2    A[4] = 3    A[5] = -1
     A[6] = 3    A[7] = 3
 the function may return 0, 2, 4, 6 or 7, as explained above.

 Complexity:
     - expected worst-case time complexity is O(N);
     - expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).

 Elements of input arrays can be modified.
 */
public class Dominator {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 4, 3, 2, 3, -1, 3, 3}));
        System.out.println(solution2(new int[]{3, 4, 3, 2, 3, -1, 3, 3}));
    }

    public static int solution(int[] A) {

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            int aA = A[i];
            if (!stack.isEmpty() && A[stack.peek()] != aA) {
                stack.pop();
            } else {
                stack.push(i);
            }
        }

        if (!stack.isEmpty()) {
            int candidate = stack.pop();
            int count = 0;
            for (int aA : A) {
                if (aA == A[candidate]) {
                    count++;
                }
            }

            if (count * 2 > A.length) {
                return candidate;
            }
        }

        return -1;
    }

    private static int solution2(int[] A) {

        int count = 1;
        int candidate = A[0];
        for (int i = 1; i < A.length; i++) {
            if (candidate == A[i]) {
                count++;
            } else if (count > 0) {
                count--;
            } else {
                candidate = A[i];
            }
        }

        int lastCandidateOccurance = -1;
        int totalCount = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == candidate) {
                totalCount++;
                lastCandidateOccurance = i;
            }
        }

        return totalCount >= A.length / 2 ? lastCandidateOccurance : -1;
    }
}
