import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * Example 1:
 *
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 */

public class SpiralMatrix {

    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{
            {1,2,3,4,5,6},
            {7,8,9,10,11,12},
            {13,14,15,16,17,18},
            {19,20,21,22,23,24}
        }));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < (int)Math.ceil(matrix.length / 2.0); i++) {
            layerToSpiral(matrix, i, result);
        }

        return result;
    }

    private static void layerToSpiral(int[][] matrix, int margin, List<Integer> result) {
        int n = matrix.length - 2 * margin;
        if (n <= 0) return;
        int m = matrix[n - 1].length - 2 * margin;
        if (m <= 0) return;

        for (int j = 0; j < m; j++) {
            result.add(matrix[margin][j + margin]);
        }

        for (int i = 1; i < n; i++) {
            result.add(matrix[i + margin][m - 1 + margin]);
        }

        for (int j = m - 2; n > 1 && j >= 0; j--) {
            result.add(matrix[n - 1 + margin][j + margin]);
        }

        for (int i = n - 2; m > 1 && i >= 1; i--) {
            result.add(matrix[i + margin][margin]);
        }
    }

}
