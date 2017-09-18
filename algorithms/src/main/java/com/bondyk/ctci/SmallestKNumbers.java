package com.bondyk.ctci;

import java.util.Arrays;
import java.util.Random;

/**
 * Design an algorithm to find the smallest K numbers in an array.
 */
public class SmallestKNumbers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findSmallestNumbers(new int[] {3, 6, 2, 7, 1, 5, 9, 4, 8}, 4)));
        System.out.println(Arrays.toString(findSmallestNumbers(new int[] {3, 6, 2, 7, 1, 5, 9, 4, 8, 11, 3, 2, 6}, 4)));
        System.out.println(Arrays.toString(findSmallestNumbers(new int[] {3, 6, 2, 7, 1, 5, 9, 4, 8, 11, 3, 2, 6}, 6)));
        System.out.println(Arrays.toString(findSmallestNumbers(new int[] {7, 7, 7, 7, 7, 7}, 6)));
        System.out.println(Arrays.toString(findSmallestNumbers(new int[] {3, 6, 2, 7, 1, 5, 9, 4, 8, 11, 3, 2, 6, 7, 3, 4, 1, 1, 8, 9}, 7)));
    }

    private static int[] findSmallestNumbers(int[] input, int k) {
        return findSmallestNumbers(input, k, 0, input.length - 1);
    }

    private static int[] findSmallestNumbers(int[] input, int k, int from, int to) {
        Random r = new Random();
        int pivotIdx = from + r.nextInt(to - from);
        int pivot = input[pivotIdx];
        pivotIdx = partition(input, pivot, from, to, k);
        if (pivotIdx == k) {
            return Arrays.copyOfRange(input, 0, pivotIdx);
        }

        if (pivotIdx < k) {
            return findSmallestNumbers(input, k, pivotIdx, to);
        }

        return findSmallestNumbers(input, k, from, pivotIdx);
    }

    private static int partition(int[] input, int pivot, int from, int to, int k) {
        int head = from;
        int tail = to;
        int pivots = 0;

        while (head <= tail) {
            if (input[head] < pivot)  {
                head++;
            } else if (input[tail] > pivot) {
                tail--;
            } else if (input[tail] == pivot) {
                tail--;
                pivots++;
            } else {
                swap(input, head, tail);
                head++;
                tail--;
            }
        }

        if (k >= head && k <= head + pivots) {
            for (int i = head; i < head + pivots; i++) {
                input[i] = pivot;
            }
            return k;
        }
        return head;
    }

    private static void swap(int[] arr, int i1, int i2) {

        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

}
