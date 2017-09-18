package com.bondyk.ctci;

import java.util.Arrays;
import java.util.Random;

/**
 * Write a method to randomly generate a set of m integers from an array of size n. Each
 element must have equal probability of being chosen.
 */
public class RandomSet {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generate(4, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})));
    }

    private static int[] generate(int m, int[] source) {
        Random r = new Random();
        int[] result = new int[m];

        for (int i = 0; i < m; i++) {
            result[i] = source[i];
        }

        for (int i = m; i < source.length; i++) {
            int k = r.nextInt(i + 1);
            System.out.println(k);
            if (k < m) {
                result[k] = source[i];
            }
        }

        return result;
    }
}
