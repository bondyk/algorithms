import java.util.Arrays;

/**
 * Created by y.bondaruk on 22.09.2014.
 */
public class MaxCounters {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solve(5, new int[]{3, 4, 4, 6, 1, 4, 4})));
        System.out.println(Arrays.toString(solve(5, new int[]{3, 4, 4, 3, 4, 6, 4, 3})));
        /*System.out.println(solve(new int[]{2, 3, 6, 4, 7, 2}));
        System.out.println(solve(new int[]{-1, -3, -6, -4, -1, -2}));
        System.out.println(solve(new int[]{-2, -3, 6, -4, -1, 0}));*/
    }


    private static int[] solve(int N, int[] A) {

        final int condition = N + 1;
        int currentMax = 0;
        int lastUpdate = 0;
        int result[] = new int[N];

        for (int i = 0; i < A.length; i++) {
            int currentValue = A[i];
            if (currentValue == condition) {
                lastUpdate = currentMax;
            } else {
                int position = currentValue - 1;
                if (result[position] < lastUpdate) {
                    result[position] = lastUpdate + 1;
                } else {
                    result[position]++;
                }

                if (result[position] > currentMax) {
                    currentMax = result[position];
                }
            }

        }

        for (int i = 0; i < N; i++) {
            if (result[i] < lastUpdate) {
                result[i] = lastUpdate;
            }
        }

        return result;
    }
}
