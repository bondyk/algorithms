import java.util.PriorityQueue;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom
 * right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 *
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid = new int[][] {
            {1,3,1},
            {1,5,1},
            {4,2,1}
        };
        System.out.println(minPathDP(grid));
        System.out.println(minPathRecursive(grid, 0, 0, 0, Integer.MAX_VALUE));
        System.out.println(minPathDijkstra(grid));
    }

    public static int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;

        return minPathDP(grid);// the most time efficient
        //return minPathRecursive(grid, 0, 0, 0, Integer.MAX_VALUE);
        //return minPathDijkstra(grid);
    }

    private static int minPathDP(int[][] grid) {

        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                dp[i][j] = grid[i][j];
                if (i > 0 && j > 0) {
                    dp[i][j] += Math.min(dp[i - 1][j], dp[i][j - 1]);
                } else if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                } else if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }

    private static int minPathDijkstra(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        PriorityQueue<Vertex> queue = new PriorityQueue<>((v1, v2) -> v1.path - v2.path);
        queue.offer(new Vertex(0, 0, grid[0][0]));
        while(!queue.isEmpty()) {
            Vertex v = queue.poll();
            visited[v.i][v.j] = true;
            if (v.i < grid.length - 1 && !visited[v.i + 1][v.j]) {
                queue.offer(new Vertex(v.i + 1, v.j, v.path + grid[v.i + 1][v.j]));
            }
            // only down and right moves are allowed
            /*if (v.i > 0 && !visited[v.i - 1][v.j]) {
                queue.offer(new Vertex(v.i - 1, v.j, v.path + grid[v.i - 1][v.j]));
            }*/
            if (v.j < grid[v.i].length - 1 && !visited[v.i][v.j + 1]) {
                queue.offer(new Vertex(v.i, v.j + 1, v.path + grid[v.i][v.j + 1]));
            }
            /*if (v.j > 0 && !visited[v.i][v.j - 1]) {
                queue.offer(new Vertex(v.i, v.j - 1, v.path + grid[v.i][v.j - 1]));
            }*/

            if (v.i == grid.length - 1 && v.j == grid[v.i].length - 1) {
                return v.path;
            }
        }

        return 0;
    }

    private static int minPathRecursive(int[][] grid, int path, int i, int j, int minPath) {
        int newPath = path + grid[i][j];

        if (i == grid.length - 1 && j == grid[i].length - 1) {
            return Math.min(minPath, newPath);
        }

        if (newPath < minPath) {
            int leftPath = Integer.MAX_VALUE;
            if (j < grid[i].length - 1) {
                leftPath = minPathRecursive(grid, newPath, i, j + 1, minPath);
            }

            int bottomPath = Integer.MAX_VALUE;
            if (i < grid.length - 1) {
                bottomPath = minPathRecursive(grid, newPath, i + 1, j, minPath);
            }

            return Math.min(leftPath, bottomPath);
        }

        return minPath;
    }

    private static class Vertex {
        private int i;
        private int j;
        private int path;
        public Vertex(int i, int j, int path) {
            this.i = i;
            this.j = j;
            this.path = path;
        }
    }
}
