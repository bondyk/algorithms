/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example 1:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public class InterleavingString {

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));// true
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));// false

        System.out.println(isInterleaveDp("aabcc", "dbbca", "aadbbcbcac"));// true
        System.out.println(isInterleaveDp("aabcc", "dbbca", "aadbbbaccc"));// false
    }

    /**
     * (Slow) Using recursion
     * ~1500 ms
     */
    private static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        return isInterleave(0, 0, 0, s1.toCharArray(), s2.toCharArray(), s3.toCharArray());
    }

    /**
     * Using Dynamic Programming
     * ~3 ms
     */
    private static boolean isInterleaveDp(String s1, String s2, String s3) {
        if (s1.length() == 0) return s2.equals(s3);
        if (s2.length() == 0) return s1.equals(s3);
        if (s1.length() + s2.length() != s3.length()) return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    private static boolean isInterleave(int i1, int i2, int i3, char[] ch1, char[] ch2, char[] ch3) {

        if (i1 == ch1.length && i2 == ch2.length && i3 == ch3.length) return true;

        char target = ch3[i3];

        if (i1 < ch1.length && ch1[i1] == target
            && isInterleave(i1 + 1, i2, i3 + 1, ch1, ch2, ch3)) {
            return true;
        }

        if (i2 < ch2.length && ch2[i2] == target
            && isInterleave(i1, i2 + 1, i3 + 1, ch1, ch2, ch3)) {
            return true;
        }

        return false;
    }
}
