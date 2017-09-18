package com.bondyk.ctci;

import java.util.Arrays;

/**
 * Given a binary matrix, find the maximum size rectangle binary-sub-matrix with all 1’s.

 Input :   0 1 1 0
 1 1 1 1
 1 1 1 1
 1 1 0 0

 Output :  1 1 1 1
 1 1 1 1

 Using this method another task can be solved: "Largest Rectangular Area in a Histogram" - http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 Build array from 0s and 1s:
 1 0 0 0 0 0
 1 0 0 1 0 0
 1 0 1 1 0 1
 1 0 1 1 0 1
 1 1 1 1 0 1
 */
public class MaxSumRectangleWithAll1s {

    public static void main(String[] args) {
        printMaxSumRectangle(new int[][] {
                {0, 1, 1, 0} ,
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0}
        });
        printMaxSumRectangle(new int[][] {
                {0, 0, 1, 0} ,
                {1, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 1, 1, 0},
                {1, 1, 0, 0}
        });
    }

    private static void printMaxSumRectangle(int[][] array) {

        int maxRect = Integer.MIN_VALUE;
        int bestRowStart = -1;
        int bestRowEnd = -1;
        int bestColStart = -1;
        int bestColEnd = -1;
        for (int topRow = 0; topRow < array.length; topRow++) {
            int[] colSum = new int[array[topRow].length];
            for (int bottonRow = topRow; bottonRow < array.length; bottonRow++) {
                for (int col = 0; col < array[bottonRow].length; col++) {
                    colSum[col] += array[bottonRow][col];
                }

                int bestStartLeftCol = 0;
                int sum = 0;
                int bestRectangleLeftCol = 0;
                int bestRectangleRightCol = 0;
                int bestRectSize = 0;
                for (int c = 0; c < colSum.length; c++) {
                    sum += colSum[c];
                    int rectSize = (bottonRow - topRow + 1) * (c - bestStartLeftCol + 1);
                    if (sum == rectSize) {
                        //it's a rectangle with all 1's
                        if (rectSize > bestRectSize) {
                            bestRectSize = rectSize;
                            bestRectangleLeftCol = bestStartLeftCol;
                            bestRectangleRightCol = c;
                        }
                    } else {
                        bestStartLeftCol = c + 1;
                        sum = 0;
                    }
                }

                if (bestRectSize > maxRect) {
                    maxRect = bestRectSize;
                    bestRowStart = topRow;
                    bestRowEnd = bottonRow;
                    bestColStart = bestRectangleLeftCol;
                    bestColEnd = bestRectangleRightCol;
                }
            }
        }

        for (int i = bestRowStart; i <= bestRowEnd; i++) {
            System.out.println(Arrays.toString(Arrays.copyOfRange(array[i], bestColStart, bestColEnd + 1)));
        }
    }
}
