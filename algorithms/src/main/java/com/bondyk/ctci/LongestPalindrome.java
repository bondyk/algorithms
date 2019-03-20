package com.bondyk.ctci;

/**
 * Given a string, find the longest substring which is palindrome.
 * For example, if the given string is “forgeeksskeegfor”, the output should be “geeksskeeg”.
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        // More efficient
        System.out.println(getLongestPalindrome("ABCBA"));
        System.out.println(getLongestPalindrome("ABCBE"));
        System.out.println(getLongestPalindrome("forgeeksskeegfor"));
        System.out.println(getLongestPalindrome("BBABCBAB"));

        System.out.println("------ Dynamic Programming ------");
        //Less efficient, using dynamic programming
        System.out.println(getLongestPalindromeLength("ABCBA"));
        System.out.println(getLongestPalindromeLength("ABCBE"));
        System.out.println(getLongestPalindromeLength("forgeeksskeegfor"));
        System.out.println(getLongestPalindromeLength("BBABCBAB"));
    }

    private static int getLongestPalindromeLength(String string) {

        //Dynamic programing
        boolean[][] table = new boolean[string.length()][string.length()];

        int longestPalindromeStart = -1;
        int longestPalindromeEnd = -1;
        char[] chars = string.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            for (int j = i; j < chars.length; j++) {
                if (j - i == 0) {
                    //1-char string is a palindrome
                    table[i][j] = true;
                } else if (j - i == 1) {
                    table[i][j] = chars[i] == chars[j];
                } else {
                    table[i][j] = chars[i] == chars[j] && (i >= chars.length - 1 || table[i + 1][j - 1]);
                }

                if (table[i][j] && longestPalindromeEnd - longestPalindromeStart < j - i) {
                    longestPalindromeStart = i;
                    longestPalindromeEnd = j;
                }
            }
        }

        return longestPalindromeEnd - longestPalindromeStart + 1;
    }

    private static int getLongestPalindrome(String string) {
        char[] chars = string.toCharArray();

//        int longestPalindromeStart = 0;
//        int longestPalindromeEnd = -1;
        int maxLength = 0;
        for (int i = 0; i < chars.length; i++) {
            //k == 0 - odd palindrome check (AAABAAA)
            //k == 1 - even palindrome check (AAABBAAA)
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < Math.min(chars.length - i, i + 1) && i + j + k < chars.length ; j++) {
                    int leftIdx = i - j;
                    int rightIdx = i + j + k;
                    if (chars[leftIdx] != chars[rightIdx]) {
                        break;
                    } else if (maxLength < rightIdx - leftIdx + 1) {
                        maxLength = rightIdx - leftIdx + 1;
//                        longestPalindromeStart = leftIdx;
//                        longestPalindromeEnd = rightIdx;
                    }
                }
            }
        }

        return maxLength;
    }
}
