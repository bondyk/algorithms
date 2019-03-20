/**
 * https://www.hackerrank.com/challenges/bomber-man/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
 */
public class BombermanGame {

    public static void main(String[] args) {
        //step 4
        String[] grid = bomberMan(21, new String[] {
            "........",
            "...O....",
            "....O...",
            "........",
            "OO...O..",
            "OO......"
        });
        for (String s : grid) {
            System.out.println(s);
        }
        System.out.println("----------------");
        String[] grid1 = bomberMan(1, new String[] {
             ".......",
             "...O.O.",
             "....O..",
             "..O....",
             "OO...OO",
             "OO.O..."
        });
        for (String s : grid1) {
            System.out.println(s);
        }
    }

    // Complete the bomberMan function below.
    static String[] bomberMan(int n, String[] grid) {

        int[][] g = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            char[] chars = grid[i].toCharArray();
            g[i] = new int[chars.length];
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == 'O') {
                    // Bomberman spends the first second doing nothing
                    // So let's assume one second elapsed
                    g[i][j] = 2;
                }
            }
        }

//        print(g);
//        int iterations = n % 4 + 4 ;
        int iterations = n <= 4 ? n : n % 4 + 4;

        for (int s = 1; s < iterations; s++) {
            //plant bombs
//            System.out.println("STEP " + s);
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length(); j++) {
                    if (g[i][j] == 0) {
                        g[i][j] = 4;
                    }
                }
            }

            //explode
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length(); j++) {
                    if (g[i][j] > 1) {
                        g[i][j]--;
                    } else if (g[i][j] == 1) {
                        explode(i, j, g);
                    }
                }
            }
//            print(g);
        }

        String[] r = new String[grid.length];
        for (int i = 0; i < g.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j] == 0) {
                    sb.append(".");
                } else {
                    sb.append("O");
                }
            }
            r[i] = sb.toString();
        }

        return r;
    }

    private static void explode(int i, int j, int[][] g) {
        if (g[i][j] == 1) {
            g[i][j] = 0;
            if (i > 0) explode(i - 1, j, g);
            if (i < g.length - 1) explode(i + 1, j, g);
            if (j > 0) explode(i, j - 1, g);
            if (j < g[i].length - 1) explode(i, j + 1, g);
        }
        g[i][j] = 0;
    }

    private static void print(int[][] g) {
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }
}
