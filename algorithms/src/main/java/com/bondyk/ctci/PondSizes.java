package com.bondyk.ctci;


/**
 * You have an integer matrix representing a plot of land, where the value at that location
 represents the height above sea level. A value of zero indicates water. A pond is a region of water
 connected vertically, horizontally, or diagonally. The size of the pond is the total number of
 connected water cells. Write a method to compute the sizes of all ponds in the matrix.
 EXAMPLE
 Input:
 0 2 1 0
 1 1 0 1
 0 1 0 1
 0 1 0 1
 Output: 2, 4, 1 (in any order)
 */
public class PondSizes {

    public static void main(String[] args) {
        int[][] ponds = new int[][] {
                {0, 2, 1, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 1},
                {0, 1, 0, 1},
        };

        print(ponds);
    }

    private static void print(int[][] array) {
        boolean[][] visited = new boolean[array.length][];
        for (int i1 = 0; i1 < array.length; i1++) {
            visited[i1] = new boolean[array[i1].length];
        }

        for (int i1 = 0; i1 < array.length; i1++) {
            for (int j1 = 0; j1 < array[i1].length; j1++) {
                int size = getPondSize(array, visited, i1, j1);
                if (size > 0) {
                    System.out.println(size);
                }
            }
        }

    }

    private static int getPondSize(int[][] array, boolean[][] visited, int i, int j) {
        int size = 0;
        if (i >= 0 && i < array.length
                && j >= 0 && j < array[i].length && !visited[i][j]) {
            visited[i][j] = true;
            if (array[i][j] == 0) {
                size++;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int m = j - 1; m <= j + 1; m++) {
                        if (k != i || m != j) {
                            size += getPondSize(array, visited, k, m);
                        }
                    }
                }
            }
        }

        return size;
    }
}
