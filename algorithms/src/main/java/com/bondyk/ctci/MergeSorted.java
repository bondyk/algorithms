package com.bondyk.ctci;

import java.util.Arrays;

/**
 * You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B.
 * Write a method to merge B into A in sorted order.
 */
public class MergeSorted {

    public static void main(String[] args) {
        int[] a = {2, 4, 6, 8};
        int[] b = {1, 3, 5, 7, 9};
        int[] ae = new int[a.length + b.length];
        System.arraycopy(a, 0, ae, 0, a.length);
        mergeSorted(ae, a.length, b);
        System.out.println(Arrays.toString(ae));
    }


    private static void mergeSorted(int[] a, int alength, int[] b) {
        // It's better to insert elements into the back of the array, where there's empty space.
        int ai = alength - 1;
        int bi = b.length - 1;
        int ri = a.length - 1;
        while (ai >= 0 && bi >= 0) {
            if (a[ai] > b[bi]) {
                a[ri--] = a[ai--];
            } else {
                a[ri--] = b[bi--];
            }
        }

        while (bi >= 0) a[ri--] = b[bi--];
        //Note that you don't need to copy the contents of A after running out of elements in B. They are already in place.
//        while (ai >= 0) a[ri--] = a[ai--];
    }

    /*private static void mergeSorted(int[] a, int alength, int[] b) {
        int ai = 0;
        int bi = 0;
        while (ai < alength) {
            if (a[ai] > b[bi]) {
                int tmp = a[ai];
                a[ai] = b[bi];
                int tmpbi = bi;
                b[bi] = tmp;
                while (tmpbi < b.length && b[tmpbi] > b[tmpbi + 1]) {
                    tmp = b[tmpbi + 1];
                    b[tmpbi + 1] = b[tmpbi];
                    b[tmpbi] = tmp;
                    tmpbi++;
                }
            }
            ai++;
        }

        while (bi < b.length) {
            a[ai++] = b[bi++];
        }
    }*/
}
