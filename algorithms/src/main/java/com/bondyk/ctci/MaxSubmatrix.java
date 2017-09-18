package com.bondyk.ctci;

import java.util.Arrays;

/**
 * Given an NxN matrix of positive and negative integers, write code to find the
 submatrix with the largest possible sum.
 * See {@link com.bondyk.ctci.MaxSumRectangle} where Kadane’s algorithm is used
 */
public class MaxSubmatrix {

    public static void main(String[] args) {
        printLargestSubmatrix(new int[][] {
                {9, -8, 1, 3, -2},
                {-3, 7, 6, -2, 4},
                {6, -4, -4, 8, -7}
        });
        printLargestSubmatrix(new int[][] {
                {-1, -8, 1, 3, -2},
                {-3, 7, 6, -2, 2},
                {-1, -4, 3, 8, -2}
        });
    }

    private static void printLargestSubmatrix(int[][] matrix) {
        int[][] prefixSum = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            prefixSum[i] = new int[matrix[i].length];
            int lineSum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                lineSum += matrix[i][j];
                int topLeftSquareSum = i == 0 ? 0 : prefixSum[i - 1][j];
                prefixSum[i][j] += (topLeftSquareSum + lineSum);
            }

//            System.out.println(Arrays.toString(prefixSum[i]));
        }


        int maxSum = 0;
        int row0 = -1;
        int col0 = -1;
        int row1 = -1;
        int col1 = -1;
        for (int i = 0; i < prefixSum.length; i++) {
            for (int j = 0; j < prefixSum[i].length; j++) {
                for (int i1 = i; i1 < prefixSum.length; i1++) {
                    for (int j1 = j; j1 < prefixSum[i1].length; j1++) {
                        int left = j == 0 ? 0 : prefixSum[i1][j - 1];
                        int top = i == 0 ? 0 : prefixSum[i - 1][j1];
                        int topLeft = (i == 0 || j == 0) ? 0 : prefixSum[i - 1][j - 1];
                        int sum = prefixSum[i1][j1] - left - top + topLeft;
//                        System.out.println(sum);
                        if (sum > maxSum) {
                            maxSum = sum;
                            row0 = i;
                            row1 = i1;
                            col0 = j;
                            col1 = j1;
                        }
                    }
                }
            }
        }

        System.out.println(maxSum);
        for (int i = row0; i <= row1; i++) {
            System.out.println(Arrays.toString(Arrays.copyOfRange(matrix[i], col0, col1 + 1)));
        }

    }
}
