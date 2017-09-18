package com.bondyk.ctci;


import java.util.*;

/**
 * Given two arrays of integers, find a pair of values (one value from each array) that you
 can swap to give the two arrays the same sum.
 EXAMPLE
 lnput:{4, 1, 2, 1, 1, 2}and{3, 6, 3, 3}
 Output: {1, 3}
 */
public class SumSwap {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findSwapPair(new int[] {4, 1, 2, 1, 1, 2}, new int[] {3, 6, 3, 3})));
        System.out.println(Arrays.toString(findSwapPair(new int[] {3, 6, 3, 3}, new int[] {4, 1, 2, 1, 1, 2})));

        System.out.println(Arrays.toString(findSwapPair1(new int[] {4, 1, 2, 1, 1, 2}, new int[] {3, 6, 3, 3})));
        System.out.println(Arrays.toString(findSwapPair1(new int[] {3, 6, 3, 3}, new int[] {4, 1, 2, 1, 1, 2})));
    }

    /**
     * O(Nlog(N) + Mlog(M))
     */
    private static int[] findSwapPair(int[] array1, int[] array2) {

        int difference = getDifference(array1, array2);
        if (difference == 0 || difference % 2 != 0) return new int[0];

        Arrays.sort(array1);
        Arrays.sort(array2);

        int halfDiff = difference / 2;

        int i1 = 0;
        int i2 = array2.length - 1;
        while (i1 < array1.length && i2 >= 0 && array2[i2] - array1[i1] != halfDiff) {
            if (array2[i2] - array1[i1] > halfDiff) {
                i2--;
            } else if (array2[i2] - array1[i1] < halfDiff) {
                i1++;
            }
        }

        if (i1 >= array1.length || i2 < 0) {
            return new int[0];
        }
        return new int[] {array1[i1], array2[i2]};
        // 1 3 4 7  = 15
        // 1 2 3 5 = 12
        // diff = 4
        // 1 1 4 7 = 13
        // 3 2 3 5 = 13
    }


    /**
     * O(N + M)
     */
    private static int[] findSwapPair1(int[] array1, int[] array2) {

        int difference = getDifference(array1, array2);
        if (difference == 0 || difference % 2 != 0) return new int[0];

        Set<Integer> map = new HashSet<>();
        for (int i : array2) {
            map.add(i);
        }

        for (int i : array1) {
            int sign = difference >= 0 ? 1 : -1;
            int expectedA2 = sign * difference - i;
            if (map.contains(expectedA2)) {
                return new int[] {i, expectedA2};
            }
        }

        return new int[0];
    }

    private static int getDifference(int[] array1, int[] array2) {
        int a1Sum = 0;
        int a2Sum = 0;
        for (int i : array1) {
            a1Sum += i;
        }
        for (int i : array2) {
            a2Sum += i;
        }

        return a2Sum - a1Sum;

    }
}
