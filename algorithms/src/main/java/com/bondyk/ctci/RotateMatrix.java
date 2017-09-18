package com.bondyk.ctci;

import java.util.Arrays;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
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

        int[][] rotated = rotate(matrix);

        for (int i = 0; i < expected.length; i++) {
            int[] line = expected[i];
            System.out.println(Arrays.toString(line));
            assert(Arrays.equals(line, rotated[i]));
        }
    }

    private static int[][] rotate(int[][] matrix) {

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

        return matrix;
    }
}
