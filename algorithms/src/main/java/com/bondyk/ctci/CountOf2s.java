package com.bondyk.ctci;

/**
 * Write a method to count the number of 2s between O and n
 */
public class CountOf2s {

    public static void main(String[] args) {
        System.out.println(count2s(132));
//        System.out.println(count2s(9));
//        System.out.println(count2s(20));
//        System.out.println(count2s(132));
    }

    private static int count2s(int n) {

        int digitsCount = (int)(Math.log10(n) + 1);
        int[] cache = new int[digitsCount];
        for (int i = 0; i < digitsCount; i++) {
            int level = (int) Math.pow(10, i);

            if (i > 0) {
                cache[i] = cache[i - 1] * 10;
            }
            cache[i] += level;
        }

        //TODO: NOT FINISHED!!!!!!

        int t = 0;
        for (int i = digitsCount - 2; i >= 0; i--) {
            int highest = (int) Math.pow(10, i + 1);
            t += cache[i] * (n / highest);
            n = n % highest;
        }

        return t;
    }
}
