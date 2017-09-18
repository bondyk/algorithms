package com.bondyk.ctci;

import java.math.BigInteger;

/**
 *An array A contains all the integers from O to n, except for one number which
 is missing. In this problem, we cannot access an entire integer in A with a single operation. The
 elements of A are represented in binary, and the only operation we can use to access them is "fetch
 the jth bit of A[i];' which takes constant time. Write code to find the missing integer. Can you do it
 in O(n) time?

 ** Missing Two **
 You are given an array with all the numbers from 1 to N appearing exactly once,
 except for one number that is missing. How can you find the missing number in O(N) time and
 0( 1) space? What if there were two numbers missing?
 */
public class MissingNumber {

    public static void main(String[] args) {
        printMissingNumber(new int[] {0, 1, 2, 3, 5, 6, 7});
        printMissingNumber(new int[] {0, 1, 2, 3, 5, 6, 7, 8, 9, 10});
        printMissingNumber(new int[] {0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17});
        int[] array = new int[9992];
        for (int i = array.length, j = 0; i >= 0; i--) {
            if (i != 6) {
                array[j++] = i;
            }
        }

        printMissingNumber(array);

        printTwoMissingNumbers(new int[] {0, 1, 2, 3, 5, 7});
        printTwoMissingNumbers(new int[] {0, 1, 2, 4, 5, 7});
    }

    private static void printMissingNumber(int[] array) {
        System.out.println(getMissingNumberSum(array, 1)
                + " == " + getMissingNumberXOR(array)
                + " == " + getMissingNumberProduct(array, 1));
    }

    private static void printTwoMissingNumbers(int[] array) {
        int sum = getMissingNumberSum(array, 2);
        int product = getMissingNumberProduct(array, 2);
        //x1 + x2 = sum
        //x1 * x2 = product
        //x1 = sum - x2;
        //(sum - x2) * x2 = product;
        // (x2 * x2) - sum * x2 + product = 0
        // x2 = (sum +- Math.sqrt(-sum * -sum - 4 * 1 * product )) / 2

        int x2_1 = (int) ((sum + Math.sqrt(-sum * -sum - 4 * 1 * product)) / 2);
        int x2_2 = (int) ((sum - Math.sqrt(-sum * -sum - 4 * 1 * product)) / 2);
        int x1_1 = sum - x2_1;
        int x1_2 = sum - x2_2;

        System.out.println(x1_1 + ", " + x2_1 + " OR " + x1_2 + ", " + x2_2);
    }

    private static int getMissingNumberXOR(int[] array) {
        int result = 0;
        int expectedResult = 0;
        for (int i = 0; i < array.length; i++) {
            expectedResult ^= i;
            result ^= array[i];
        }
        expectedResult ^= (array.length);

        return result ^ expectedResult;
    }

    private static int getMissingNumberSum(int[] array, int missingNumbersCount) {
        int n = array.length - 1 + missingNumbersCount;
        int expectedSum = n * (n + 1) / 2;
        int result = expectedSum;
        for (int i : array) {
            result -= i;
        }


        return result;
    }

    private static int getMissingNumberProduct(int[] array, int missingNumbersCount) {
        int n = array.length - 1 + missingNumbersCount;
        BigInteger expectedProduct = BigInteger.valueOf(n);
        for (int i = 1; i < n; i++) {
            expectedProduct = expectedProduct.multiply(BigInteger.valueOf(i));
        }

        BigInteger result = expectedProduct;
        for (int i : array) {
            if (i != 0) {
                result = result.divide(BigInteger.valueOf(i));
            }
        }

        return result.intValueExact();
    }
}
