import java.util.Arrays;

/**
 * Given an integer array, find a maximum product of a triplet in array.
 */
public class MaxProductOfThree {

    public static void main(String[] args) {

        System.out.println(solution(new int[]{-3, 1, 2, -2, 5, 6}));
        System.out.println(solution(new int[]{-30, -10, -2, -2, 5, 6}));
        System.out.println(solution(new int[]{-30, -1, -2, -2, 5, 60}));
        System.out.println(solution(new int[]{-30, -1, -2, -2, -1, -3}));
    }

    public static int solution(int[] A) {

        Arrays.sort(A);
        int n = A.length;
        int test1 = A[n - 1] * A[n - 2] * A[n - 3];
        int test2 = A[0] * A[1] * A[n - 1];

        return Math.max(test1, test2);
    }
}
