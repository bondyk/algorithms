import java.util.ArrayList;
import java.util.List;

/**
 * You are given a 2D matrix of dimension m x n and a positive integer r.
 * You have to rotate the matrix r times and print the resultant matrix.
 * Rotation should be in anti-clockwise direction.
 *
 * It is guaranteed that the minimum of m and n will be even.
 *
 * As an example rotate the Start matrix by 2:
 *
 * Start         First           Second
 *  1 2 3 4      2  3  4  5      3  4  5  6
 * 12 1 2 5  ->  1  2  3  6 ->   2  3  4  7
 * 11 4 3 6      12  1  4  7     1  2  1  8
 * 10 9 8 7      11 10  9  8     12 11 10  9
 *
 * https://www.hackerrank.com/challenges/matrix-rotation-algo/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=7-day-campaign
 */
public class MatrixLayerRotation {

    /**
     * For each matrix layer perform rotation by jumping into final destination instead of doing it step by step
     */
    static void matrixRotation(List<List<Integer>> matrix, int r) {
        if (matrix.isEmpty()) return;

        int maxMargin = Math.min(matrix.size(), matrix.get(0).size()) / 2;
        // for each matrix layer
        for (int margin = 0; margin < maxMargin; margin++) {
            int rows = matrix.size() - 2 * margin;
            int cols = matrix.get(margin).size() - 2 * margin;
            int perimeter = 2 * (rows - 1) + 2 * (cols - 1);
            int actualRotates = r % perimeter;

            // save first chunk of data that will be replaced by jumps
            int[] mem = new int[actualRotates];
            for (int i = 0; i < actualRotates; i++) {
                int[] c = getCoordinates(i, rows, cols, margin);
                mem[i] = matrix.get(c[0]).get(c[1]);
            }

            // perform jumps
            for (int i = actualRotates; i < perimeter; i++) {
                int[] to = getCoordinates(i - actualRotates, rows, cols, margin);
                int[] from = getCoordinates(i, rows, cols, margin);
                int value = matrix.get(from[0]).get(from[1]);
                matrix.get(to[0]).set(to[1], value);
                //System.out.println("[" + to[0] + ";" + to[1] + "] = " + value);
            }

            // jump first chunk
            for (int i = perimeter - actualRotates; i < perimeter; i++) {
                int[] c = getCoordinates(i, rows, cols, margin);
                //System.out.println(" [" + c[0] + ";" + c[1] + "] = " + (mem[i - perimeter + actualRotates]));
                matrix.get(c[0]).set(c[1], mem[i - perimeter + actualRotates]);
            }
        }

        for (List<Integer> line : matrix) {
            for (int j = 0; j < line.size(); j++) {
                System.out.print(line.get(j));
                if (j < line.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Convert index on the perimeter of the array into matrix coordinates
     */
    private static int[] getCoordinates(int absIdx, int rows, int cols, int margin) {
        int perimeter = 2 * (rows - 1) + 2 * (cols - 1);
        int perimeterIdx = absIdx % perimeter;

        if (perimeterIdx < cols)
            return new int[] { margin, perimeterIdx + margin };
        if (perimeterIdx < rows + cols - 1)
            return new int[] { perimeterIdx - cols + 1 + margin, cols - 1 + margin };
        if (perimeterIdx < 2 * (cols - 1) + rows)
            return new int[] { rows - 1 + margin, cols - 1 - (perimeterIdx - rows - cols + 2) + margin };

        return new int[] { rows - 1 - (perimeterIdx - 2 * (cols - 1) - rows + 1) + margin, margin };
    }

    public static void main(String[] args) {
        int[][] matrixArr = new int[][] {
            {1, 2, 3, 4},
            {14, 15, 16, 5},
            {13, 20, 17, 6},
            {12, 19, 18, 7},
            {11, 10, 9, 8}
        };

        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < matrixArr.length; i++) {
            List<Integer> l = new ArrayList<>();
            for (int j = 0; j < matrixArr[i].length; j++) {
                l.add(matrixArr[i][j]);
            }
            matrix.add(l);
        }

        matrixRotation(matrix, 3);
    }
}
