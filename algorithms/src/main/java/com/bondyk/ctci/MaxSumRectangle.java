package com.bondyk.ctci;

/**
 *
 Given a 2D array, find the maximum sum subarray in it. For example, in the following 2D array,
 the maximum sum subarray is highlighted with blue rectangle and sum of this subarray is 29.
 * See {@link com.bondyk.ctci.MaxSubmatrix} where prefix-sum methon is used
 */

public class MaxSumRectangle {

    public static void main(String[] args) {

        System.out.println(calcMaxSumRectangle(new int[][] {
                {1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6},
        }));
    }


    /**
     * Kadane’s algorithm for 1D array can be used to reduce the time complexity to O(n^3).
     * The idea is to fix the left and right columns one by one and find the maximum sum contiguous rows
     * for every left and right column pair. We basically find top and bottom row numbers (which have maximum sum)
     * for every fixed left and right column pair. To find the top and bottom row numbers,
     * calculate sun of elements in every row from left to right and store these sums in an array say temp[].
     * So temp[i] indicates sum of elements from left to right in row i. If we apply Kadane’s 1D algorithm on temp[],
     * and get the maximum sum subarray of temp, this maximum sum would be the maximum possible sum with left and right as boundary columns.
     * To get the overall maximum sum, we compare this sum with the maximum sum so far.
     */
    private static int calcMaxSumRectangle(int[][] array) {

        int maxSum = Integer.MIN_VALUE;
        for (int topRow = 0; topRow < array.length; topRow++) {
            int[] colSum = new int[array[topRow].length];
            for (int bottonRow = topRow; bottonRow < array.length; bottonRow++) {
                for (int col = 0; col < array[bottonRow].length; col++) {
                    colSum[col] += array[bottonRow][col];
                }

                int sumStartCol = 0;
                int sum = 0;
                int maxRectangleSum = sum;
                for (int c = 0; c < colSum.length; c++) {
                    sum += colSum[c];
                    if (sum < 0) {
                        sum = 0;
                        sumStartCol = c + 1;
                    } else if (sum > maxRectangleSum) {
                        maxRectangleSum = sum;
                        System.out.println("[" + topRow + "; " + sumStartCol + "] - [" + bottonRow + "; " + c + "] = " + maxRectangleSum);
                    }
                }
                if (maxRectangleSum > maxSum) {
                    maxSum = maxRectangleSum;
                }

            }
        }

        return maxSum;
    }
}
