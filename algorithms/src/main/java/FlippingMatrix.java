/**
 * Sean invented a game involving a [2n x 2n] matrix where each cell of the matrix contains an integer.
 * He can reverse any of its rows or columns any number of times. The goal of the game is
 * to maximize the sum of the elements in the  submatrix located in the upper-left quadrant of the matrix.
 *
 * Given the initial configurations for  matrices, help Sean reverse the rows and columns of each matrix
 * in the best possible way so that the sum of the elements in the matrix's upper-left quadrant is maximal.
 *
 * Source: https://www.hackerrank.com/challenges/flipping-the-matrix/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
 */

public class FlippingMatrix {

    // Complete the flippingMatrix function below.
    private static int flippingMatrix(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix[i].length / 2; j++) {
                int topLeft = matrix[i][j];
                int topRight = matrix[i][matrix[i].length - 1 - j];
                int bottomLeft = matrix[matrix.length - 1 - i][j];
                int bottomRight = matrix[matrix.length - 1 - i][matrix[i].length - 1 - j];
                sum += Math.max(Math.max(topLeft, topRight), Math.max(bottomLeft, bottomRight));
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(flippingMatrix(new int[][] {
            { 112, 42, 83, 119 },
            { 56, 125, 56, 49 },
            { 15, 78, 101, 43 },
            { 62, 98, 114, 108 }
        }));
    }
}

