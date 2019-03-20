import java.util.Arrays;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PalindromePartitioningMinCut {

    public static void main(String[] args) {
//        System.out.println(minCut("aab")); // 1
        System.out.println(minCut("asdfdsere")); // 2
//        System.out.println(minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp")); // 1
    }

    public static int minCut(String s) {
        if (s.length() == 0) return 0;

        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[chars.length][chars.length];

        int[] cut = new int[chars.length];
        for (int i = 0; i < chars.length; i++) cut[i] = i - 1;

        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (j - i == 0) dp[i][j] = true;
                else if (j - i == 1) dp[i][j] = chars[i] == chars[j];
                else dp[i][j] = chars[i] == chars[j] && (i >= chars.length - 1 || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    cut[i] = Math.min(cut[i], cut[j] + 1);
                }
            }

//            System.out.println(Arrays.toString(dp[i]));
        }

        /*for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[i].length - 1; j >= i; j--) {
                if (dp[i][j]) {
                    cut[j] = Math.min(cut[i] + 1, cut[j]);
                    //                    min = j == dp[i].length - 1 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
        }*/
//        return minPartitions(dp, 0, 0) - 1;
//        return minPartitionsCount - 1;
        System.out.println(Arrays.toString(cut));
        return cut[chars.length - 1];
    }

    private static int minPartitions(boolean[][] dp, int cutIdx, int partitionsCount) {
        if (cutIdx == dp.length) {
            return partitionsCount;
        }

        for(int i = dp.length - 1; i >= cutIdx ; i--) {
            if (dp[cutIdx][i]) {
                return minPartitions(dp, i + 1, partitionsCount + 1);
            }
        }
        return dp.length - cutIdx;
    }
}
