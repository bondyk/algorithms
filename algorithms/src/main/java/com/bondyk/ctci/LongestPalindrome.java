package com.bondyk.ctci;


import java.util.Arrays;

/**
 * Given a string, find the longest substring which is palindrome. For example, if the given string is “forgeeksskeegfor”, the output should be “geeksskeeg”.
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(getLongestPalindrome("forgeeksskeegfor"));
        System.out.println(getLongestPalindrome("BBABCBAB"));
    }

    private static int getLongestPalindromeLength(String string) {
        //Dynamic programing
        boolean[][] table = new boolean[string.length()][string.length()];

        int longestPalindrome = -1;
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if (j - i == 0) {
                    //1-char string is a palindrome
                    table[i][j] = true;
                } else if (j - i == 1) {
                    table[i][j] = chars[i] == chars[j];
                } else {
                    table[i][j] = table[i + 1][j - 1] && chars[i] == chars[j];
                }
                if (table[i][j] && longestPalindrome < j - i) {
                    longestPalindrome = j - i;
                    System.out.println(string.substring(i, j+1));
                }
            }
        }

        return longestPalindrome + 1;
    }

    private static int getLongestPalindrome(String string) {
        char[] chars = string.toCharArray();

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
                    }
                }
            }
        }

        return maxLength;
    }
}
