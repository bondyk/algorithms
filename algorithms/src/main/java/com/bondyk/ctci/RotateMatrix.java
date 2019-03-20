package com.bondyk.ctci;

import java.util.Arrays;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 *
 * Example:
 *
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 */
public class RotateMatrix {

    public static void main(String[] args) {

        int[][] matrix = new int[][] {
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5},

        };

        int[][] expected = new int[][] {
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},

        };

        rotate(matrix);

        for (int i = 0; i < expected.length; i++) {
            int[] line = expected[i];
            System.out.println(Arrays.toString(line));
            assert(Arrays.equals(line, matrix[i]));
        }
    }

    private static void rotate(int[][] matrix) {

        int n = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length - i - 1; j++) {
                int tmp = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = matrix[i][j];
                int tmp1 = matrix[n - j - 1][n - i - 1];
                matrix[n - j - 1][n - i - 1] = tmp;
                int tmp2 = matrix[n - j - 1][n - i - 1];
                matrix[n - j - 1][i] = tmp1;
                matrix[i][j] = tmp2;
            }
        }
    }
}
