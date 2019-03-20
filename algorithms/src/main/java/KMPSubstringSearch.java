import java.util.Arrays;

/**
 * Knuth–Morris–Pratt algorithm
 *
 * https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
 */
public class KMPSubstringSearch {

    public static void main(String[] args) {

        printMatchingSubstrings("very big text with spaces", "big");
        printMatchingSubstrings("FINDINAHAYSTACKNEEDLE", "NEEDLE");
        printMatchingSubstrings("FINDINDI", "INDI");
        printMatchingSubstrings("FINDINDI", "INDIGO");
        printMatchingSubstrings("AABAACAABAAFAABARAABAABAAD", "AABAA");
    }

    public static int printMatchingSubstrings(String text, String pattern) {
        char[] txt = text.toCharArray();
        char[] pat = pattern.toCharArray();
        //longest proper prefix
        int[] lps = new int[pat.length];

        lps[0] = 0;
        for (int i = 1; i < pat.length; i++) {
            if (pat[i] == pat[lps[i - 1]]) {
                lps[i] = lps[i - 1] + 1;
            } else {
                lps[i] = 0;
            }
        }

        System.out.println("text: " + text + ", pattern: " + pattern);
        System.out.println(Arrays.toString(lps));

        for (int i = 0, j = 0; i < txt.length;) {
            if (txt[i] != pat[j]) {
                j = lps[j];
                if (j == 0) {
                    i++;
                }
            } else if (j == pat.length - 1) {
                int matchIdx = i - pat.length + 1;
                System.out.println(matchIdx + ": " + new String(Arrays.copyOfRange(txt, matchIdx, i + 1)));
                j = lps[j];
                i++;
            } else {
                j++;
                i++;
            }
        }

        return -1;
    }
}
