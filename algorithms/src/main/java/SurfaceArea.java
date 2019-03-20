/**
 * Madison, is a little girl who is fond of toys. Her friend Mason works in a toy manufacturing factory .
 * Mason has a 2D board A of size H x W with H rows and W columns. The board is divided into cells
 * of size 1 x 1 with each cell indicated by it's coordinate (i, j). The cell (i, j) has an integer
 * A(i, j) written on it. To create the toy Mason stacks A(i, j) number of cubes of size 1 x 1 x 1 on the cell (i, j).
 *
 * Given the description of the board showing the values of A(i, j) and that the price of the toy
 * is equal to the 3d surface area find the price of the toy.
 *
 * https://www.hackerrank.com/challenges/3d-surface-area/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign&h_r=next-challenge&h_v=zen
 */
public class SurfaceArea {

    public static void main(String[] args) {

        System.out.println(surfaceArea(new int[][]{
            { 1, 3, 4 },
            { 2, 2, 3 },
            { 1, 2, 4 }
        }));
    }

    private static int surfaceArea(int[][] A) {

        int result = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                result += getSurfaceArea(i, j, A);
            }
        }
        return result;
    }

    private static int getSurfaceArea(int i, int j, int[][] A) {

        if (A[i][j] == 0) {
            return 0;
        }

        //top & bottom
        int result = 2;

        //left
        if (i == 0) {
            result += A[i][j];
        } else if (A[i][j] > A[i - 1][j]) {
            result += (A[i][j] - A[i - 1][j]);
        }
        //right
        if (i == A.length - 1) {
            result += A[i][j];
        } else if (A[i][j] > A[i + 1][j]) {
            result += (A[i][j] - A[i + 1][j]);
        }

        if (j == 0) {
            result += A[i][j];
        } else if (A[i][j] > A[i][j - 1]) {
            result += (A[i][j] - A[i][j - 1]);
        }
        //right
        if (j == A[i].length - 1) {
            result += A[i][j];
        } else if (A[i][j] > A[i][j + 1]) {
            result += (A[i][j] - A[i][j + 1]);
        }

        return result;
    }
}
