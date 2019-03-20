import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
 */
public class ClimbingLeaderboard {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(climbingLeaderboard(
            new int[] {100, 100, 50, 50, 50, 40, 40, 20, 10},
            new int[] {5, 25, 50, 120})));
    }

    private static int[] climbingLeaderboard(int[] scores, int[] alice) {

        int i = 0;
        int j = scores.length - 1;
        int rank = 0;
        int[] aliceRanks = new int[alice.length];
        int lastScore = 0;
        while(i < alice.length) {
            while(j >= 0 && scores[j] <= alice[i]) {
                if (lastScore != scores[j]) {
                    rank++;
                }
                lastScore = scores[j--];
            }
            while(i < alice.length && (j < 0 || scores[j] > alice[i])) {
                aliceRanks[i] = rank;
                lastScore = alice[i++];
            }
        }

        int totalRanks = scores.length > 0 ? 1 : 0;

        for (int y = scores.length - 2; y >= 0; y--) {
            if (scores[y] != scores[y + 1]) {
                totalRanks++;
            }
        }

        for (int i1 = 0; i1 < aliceRanks.length; i1++) {
            aliceRanks[i1] = totalRanks - aliceRanks[i1] + 1;
        }

        return aliceRanks;
    }
}
