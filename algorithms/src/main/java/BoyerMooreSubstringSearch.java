/**
 * Boyer-Moore substring search (mismatched character heuristic)
 */
public class BoyerMooreSubstringSearch {

    public static void main(String[] args) {

        System.out.println("Expected: 5. Actual: " + containsSubstring("very big text with spaces", "big"));
        System.out.println("Expected: 15. Actual: " + containsSubstring("FINDINAHAYSTACKNEEDLE", "NEEDLE"));
        System.out.println("Expected: 1. Actual: " + containsSubstring("FINDINDI", "INDI"));
        System.out.println("Expected: -1. Actual: " + containsSubstring("FINDINDI", "INDIGO"));
    }

    public static int containsSubstring(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        int[] right = new int[256];
        for (int i = 0; i < right.length; i++) {
            right[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            right[pattern.charAt(i)] = i;
        }

        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    skip = j - right[text.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            }

            if (skip == 0) {
                return i;
            }
        }

        return -1;
    }
}
