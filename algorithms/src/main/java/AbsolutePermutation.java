import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * https://www.hackerrank.com/challenges/absolute-permutation/problem
 *
 * A test case enlighted me
 * Input:
 *
 * 1
 * 100 2
 * Output:
 *
 * 3 4 1 2 7 8 5 6 11 12 9 10 15 16 13 14 19 20 17 18 23 24 21 22 27 28 25 26 31 32 29 30 35 36 33 34
 * 39 40 37 38 43 44 41 42 47 48 45 46 51 52 49 50 55 56 53 54 59 60 57 58 63 64 61 62 67 68 65 66
 * 71 72 69 70 75 76 73 74 79 80 77 78 83 84 81 82 87 88 85 86 91 92 89 90 95 96 93 94 99 100 97 98
 */
public class AbsolutePermutation {

    /* slow
    private static int[] absolutePermutation2(int n, int k) {
        LinkedHashSet<Integer> result = new LinkedHashSet<>();
        if (ap(1, n, k, result)) {
            int[] r = new int[n];
            int i = 0;
            for (Integer v : result) {
                r[i++] = v;
            }
            return r;
        }

        return new int[] { -1 };
    }

    private static boolean ap(int i, int n, int k, LinkedHashSet<Integer> result) {
//        System.out.println("i = " + i);
        if (i > n) {
            return true;
        }
        int c1 = i - k;
//        System.out.println("c1 = " + c1);
        if (c1 >= 1 && c1 <= n) {
            if (!result.contains(c1)) {
                result.add(c1);
                if (ap(i + 1, n, k, result)) return true;
                result.remove(c1);
            }
        }

        int c2 = i + k;
//        System.out.println("c2 = " + c2);
        if (c2 >= 1 && c2 <= n) {
            if (!result.contains(c2)) {
                result.add(c2);
                if (ap(i + 1, n, k, result)) return true;
                result.remove(c2);
            }
        }

        return false;
    }*/

    private static int[] absolutePermutation(int n, int k) {

        int[] result = new int[n];
        if (k == 0) {
            for (int i = 0; i < n; i++) {
                result[i] = i + 1;
            }
        } else {
            for (int i = 0; i < n / k / 2 + 1; i++) {
                int block = i * 2 * k;
                for (int j = block; j < block + k && j < n; j++) {
                    result[j] = k + j + 1;
                    if (result[j] > n)
                        return new int[] {-1};
                }
                block += k;
                for (int j = block; j < block + k && j < n; j++) {
                    result[j] = Math.abs(k - j - 1);
                    if (result[j] > n)
                        return new int[] {-1};
                }
            }
        }

        for (int i = 0; i < result.length; i++) {
            if (Math.abs(result[i] - i - 1) != k
                && Math.abs(result[i] + i + 1) != k ) {
                System.out.println("FAIL");
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(absolutePermutation(2, 1)));
        System.out.println(Arrays.toString(absolutePermutation(3, 0)));
        System.out.println(Arrays.toString(absolutePermutation(3, 2)));
        System.out.println(Arrays.toString(absolutePermutation(100, 2)));
        System.out.println(Arrays.toString(absolutePermutation(8, 4)));
        //---------------
//        System.out.println(Arrays.toString(absolutePermutation2(2, 1)));
//        System.out.println(Arrays.toString(absolutePermutation2(3, 0)));
//        System.out.println(Arrays.toString(absolutePermutation2(3, 2)));
//        System.out.println(Arrays.toString(absolutePermutation2(100, 2)));
//        System.out.println(Arrays.toString(absolutePermutation2(8, 4)));

    }
}
