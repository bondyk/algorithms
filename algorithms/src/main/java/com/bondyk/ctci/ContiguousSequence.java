package com.bondyk.ctci;

/**
 * You are given an array of integers (both positive and negative). Find the
 contiguous sequence with the largest sum. Return the sum.
 EXAMPLE
 Input: 2, -8, 3, -2, 4, -10
 Output: 5 ( i. e • , { 3, -2, 4} )
 */
public class ContiguousSequence {

    public static void main(String[] args) {
        System.out.println(maxSum(new int[] {2, -8, 3, -2, 4, -10}));
    }

    private static int maxSum(int[] array) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int value : array) {
            sum += value;
            if (maxSum < sum) {
                maxSum = sum;
            }

            if (sum <= 0) {
                sum = 0;
            }
        }

        return maxSum;
    }
}
