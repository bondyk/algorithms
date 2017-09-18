package com.bondyk.ctci;


import java.util.HashMap;
import java.util.Map;

/**
 * A majority element is an element that makes up more than half of the items in
 an array. Given a positive integers array, find the majority element. If there is no majority element,
 return -1. Do this in O(N) time and 0(1) space.
 Input: 1 2 5 9 5 9 5 5 5
 Output: 5

 */
public class MajorityElement {

    public static void main(String[] args) {
        System.out.println(findMajorityElement(new int[] {1, 2, 5, 9, 5, 9, 5, 5, 5}));
        System.out.println(findMajorityElement(new int[] {5, 2, 5, 9, 5, 2, 5, 2, 5}));
        System.out.println(findMajorityElement(new int[] {5, 2, 5, 9, 5, 2, 5, 2}));
        // TODO: BUG! Must be -1
        System.out.println(findMajorityElement(new int[] {1, 2, 4, 5, 3, 2, 2, 2}));
    }

    private static int findMajorityElement(int[] array) {
        return findMajorityElement(array, 0, array.length);
    }

    private static int findMajorityElement(int[] array, int from, int to) {
        if (to - from <= 3) {
            Map<Integer, Integer> map = new HashMap<>(3);
            for (int i = from; i < to; i++) {
                if (!map.containsKey(array[i])) {
                    map.put(array[i], 0);
                }
                map.put(array[i], map.get(array[i]) + 1);
            }

            if (map.size() == 3) return 0;
            for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
                if (integerIntegerEntry.getValue() > 1) return integerIntegerEntry.getKey();
            }

            return -1;
        }

        int middle = (from + to) / 2;
        int leftMajor = findMajorityElement(array, from, middle);
        int rightMajor = findMajorityElement(array, middle, to);
        if (leftMajor == rightMajor) return leftMajor;
        if (leftMajor == -1) return rightMajor;
        if (rightMajor == -1) return leftMajor;
        return -1;
    }
}
