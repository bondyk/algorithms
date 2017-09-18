package com.bondyk.ctci;

/**
 * A positive integer is called a palindrome if its representation in the decimal system is the same when read from left to right and from right to left. For a given positive integer K of not more than 1000000 digits, write the value of the smallest palindrome larger than K to output. Numbers are always displayed without leading zeros.
 Input: The first line contains integer t, the number of Maze cases. Integers K are given in the next t lines.
 Output: For each K, output the smallest palindrome larger than K. Example

 Input:\
 2
 808
 2133

 Output:
 818
 2222
 */
public class NextPalindrome {
    public static void main(String[] args) {
        System.out.println(getNextPalindrome(808));
        System.out.println(getNextPalindrome(2133));
        System.out.println(getNextPalindrome(1234554322));
    }

    private static int getNextPalindrome(int number) {
        //http://www.ardendertat.com/2011/12/01/programming-interview-questions-19-find-next-palindrome-number/
        int[] digits = toArray(number);
        int[] palindrome = makePalindrome(digits);
        if (compare(palindrome, digits) > 0) return toInteger(palindrome);

        int n = toInteger(palindrome);
        if (digits.length % 2 == 0) {
            int middleLeft = digits.length / 2 - 1;
            int middleRight = middleLeft + 1;
            n += Math.pow(10, middleLeft);
            n += Math.pow(10, middleRight);
        } else {
            int middle = digits.length / 2;
            n += Math.pow(10, middle);
        }

        if (isPalindrome(toArray(n))) return n;
        return getNextPalindrome(n);
    }

    private static boolean isPalindrome(int[] number) {
        int middle = number.length / 2;
        for (int i = 0; i < middle; i++) {
            if (number[i] != number[number.length - 1 - i]) {
                return false;
            }
        }

        return true;
    }

    private static int[] makePalindrome(int[] number) {
        int[] palindrome = new int[number.length];
        int middle = number.length / 2;
        for (int i = 0; i < middle; i++) {
            palindrome[i] = number[i];
            palindrome[number.length - 1 - i] = number[i];
        }
        return palindrome;
    }

    private static int compare(int[] n1, int[] n2) {
        if (n1.length != n2.length) return Integer.compare(n1.length, n2.length);
        for (int i = 0; i < n1.length; i++) {
            if (n1[i] != n2[i]) return Integer.compare(n1[i], n2[i]);
        }
        return 0;
    }

    private static int toInteger(int[] number) {
        int result = 0;
        for (int i = 0; i < number.length; i++) {
            result += (number[i] * (int) Math.pow(10, number.length - 1 - i));
        }
        return result;
    }

    private static int[] toArray(int number) {
        int digitsCount = (int) Math.log10(number) + 1;
        int[] result = new int[digitsCount];
        for (int i = 0; i < result.length; i++) {
            int divider = (int) Math.pow(10, result.length - 1 - i);
            result[i] = number / divider;
            number = number % divider;
        }
        return result;
    }
}
