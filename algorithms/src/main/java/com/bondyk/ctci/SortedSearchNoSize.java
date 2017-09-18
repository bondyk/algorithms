package com.bondyk.ctci;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * You are given an array-like data structure Listy which lacks a size
 method. It does, however, have an elementAt ( i) method that returns the element at index i in
 0( 1) time. If i is beyond the bounds of the data structure, it returns -1. (For this reason, the data
 structure only supports positive integers.) Given a Listy which contains sorted, positive integers,
 find the index at which an element x occurs. If x occurs multiple times, you may return any index.
 */
public class SortedSearchNoSize {

    public static void main(String[] args) {

        int[] a = {2, 4, 5, 7, 9, 11, 12, 13, 13};
        int[] array = new int[30];
        Arrays.fill(array, a.length, array.length, -1);
        System.arraycopy(a, 0, array, 0, a.length);
        System.out.println(search(array, 11));
        System.out.println(search(array, 13));
        System.out.println(search(array, 7));
        System.out.println(search(array, 3));
    }

    private static int search(int[] array, int searchNumber) {
        int i = 1;
        while(array[i] != -1 && array[i] < searchNumber) {
            i = i * 2 + 1;
        }

        return binarySearch(array, i / 2, i, searchNumber);
    }

    private static int binarySearch(int[] array, int from, int to, int term) {
        if (from <= to) {
            int middle = (from + to) / 2;
            if (array[middle] == term) return middle;
            if (array[middle] == -1 || array[middle] > term) {
                return binarySearch(array, from, middle - 1, term);
            } else {
                return binarySearch(array, middle + 1, to, term);
            }
        }

        return -1;
    }
}
