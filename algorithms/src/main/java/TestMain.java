/**
 * Created by y.bondaruk on 18.09.2014.
 */
public class TestMain {


    public static void main(String[] args) {

        int[] A = new int[] {0, 1, 3, -2, 0, 1, 0, -3, -4, -5, 2, 3, 4, 7, 8, 11, 12, 12};
        Solution solution = new Solution();
        System.out.println("Solution: " + solution.solution(A));


        System.out.println(solution(new int[]{4, 2, 2, 5, 1, 5, 8}));

    }

    public static int solution(int[] A) {
        // write your code in Java SE 8

        int[] sum = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            if (i > 0) {
                sum[i] = sum[i - 1] + A[i];
            } else {
                sum[i] = A[i];
            }
        }

        double minSliceAverage = Double.MAX_VALUE;
        int result = -1;
        for (int p = 0; p < A.length - 1; p++) {
            for (int q = p + 1; q < A.length; q++) {
                double slice = (sum[q] - (p == 0  ? 0 : sum[p - 1])) / (q - p + 1.0);
                if (slice < minSliceAverage) {
                    minSliceAverage = slice;
                    result = p;
                }
            }
        }

        return result;
    }

    public static class Solution {
        public int solution(int[] A) {
            int result = -1;

            int p = -1, q = -1, r = -1;

            for (int i = 1; i < A.length; i++) {

                if (p != -1 && q != -1 && r != -1) {
                    System.out.println("p = " + p + " q = " + q + " r = " + r);
                    result = calcResult(p, q, r, A, result);
                    p = -1;
                    q = -1;
                    r = -1;
                    i--;
                }

                if (A[i - 1] > A[i]) {
                    if (p == -1) {
                        p = i - 1;
                    } else if (q != -1) {
                        r = i - 1;
                    }
                } else if (A[i - 1] < A[i]) {
                    if (p != -1 && q == -1) {
                        q = i - 1;
                    }
                } else if (q != -1) {
                    r = i - 1;
                } else {
                    p = -1;
                }

                if (i + 1 == A.length && r == -1) {
                    r = i;
                }

            }

            if (p != -1 && q != -1 && r != -1) {
                System.out.println("p = " + p + " q = " + q + " r = " + r);
                result = calcResult(p, q, r, A, result);
            }


            return result;
        }

        private int calcResult(int p, int q, int r, int[] A, int result) {
            return Math.max(result, Math.min(A[p] - A[q], A[r] - A[q]));
        }
    }
}
