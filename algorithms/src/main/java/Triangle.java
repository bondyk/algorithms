import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move
 * to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Note:
 *
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total
 * number of rows in the triangle.
 */
public class Triangle {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4,1,8,3));
        System.out.println(minimumTotal(triangle)); // 11
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);

        int[] dp = new int[triangle.get(triangle.size() - 1).size()];
        dp[0] = triangle.get(0).get(0);

        minPath(1, triangle, dp);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            min = Math.min(min, dp[i]);
        }

        return min;
    }

    private static void minPath(int rowIdx, List<List<Integer>> triangle, int[] dp) {
        List<Integer> row = triangle.get(rowIdx);
        for (int i = row.size() - 1; i >= 0; i--) {
            int val1 = i > 0 ? dp[i - 1] + row.get(i) : Integer.MAX_VALUE;
            int val2 = i < row.size() - 1 ? dp[i] + row.get(i) : Integer.MAX_VALUE;
            dp[i] = Math.min(val1, val2);
        }
        if (rowIdx < triangle.size() - 1) {
            minPath(rowIdx + 1, triangle, dp);
        }
    }

    /*
    // Dijkstra algorithm which doesn't work with negative numbers
    // Worked quite slow even for non-negative values
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) return 0;
        PriorityQueue<Elem> queue = new PriorityQueue<>((e1, e2) -> e1.pathSum - e2.pathSum);
        Elem root = new Elem(0, 0, triangle.get(0).get(0));
        queue.offer(root);

        while(!queue.isEmpty()) {
            Elem e = queue.poll();
            System.out.println(e.pathSum);
            if (e.row == triangle.size() - 1) {
                return e.pathSum;
            }

            List<Integer> row = triangle.get(e.row + 1);
            row.get(e.idx);
            Elem e1 = new Elem(e.row + 1, e.idx, e.pathSum + row.get(e.idx));
            Elem e2 = new Elem(e.row + 1, e.idx + 1, e.pathSum + row.get(e.idx + 1));
            queue.offer(e1);
            queue.offer(e2);
        }
        return 0;
    }

    private static class Elem {
        int row;
        int idx;
        int pathSum;
        Elem(int row, int idx, int pathSum) {
            this.row = row;
            this.idx = idx;
            this.pathSum = pathSum;
        }
    }*/
}
