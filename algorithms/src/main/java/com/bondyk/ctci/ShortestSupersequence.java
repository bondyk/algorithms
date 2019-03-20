package com.bondyk.ctci;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You are given two arrays, one shorter (with all distinct elements) and one
 longer. Find the shortest subarray in the longer array that contains all the elements in the shorter
 array. The items can appear in any order.
 EXAMPLE
 Input:
 {1, 5, 9}
 {7, 5, 9, 0, 2, 1, 3, (5, 7, 9, 1), 1, 5, 8, 8, 9, 7}
 Output:[7, 10] (the underlined portion above)
 */
public class ShortestSupersequence {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(shortestSupersequence(
            new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7},
            new int[] {1, 5, 9}
        )));
        System.out.println(Arrays.toString(shortestSupersequence(
            new int[]{3,5,7},
            new int[] {5}
        )));
        System.out.println(Arrays.toString(shortestSupersequence(
            new int[]{3,6,5,6,6,6,6,6,65,5,5,7},
            new int[] {3,7}
        )));
        System.out.println(Arrays.toString(shortestSupersequence(
            new int[]{3,6,5,8,7},
            new int[] {3,9}
        )));
    }

    /**
     * Use Map to store latest positions of chars and doubly linked list (Node) to get next
     * and previous indexes of the numbers from searchArray
     */
    private static int[] shortestSupersequence(int[] arr, int[] searchArray) {
        Set<Integer> s = new HashSet<>();
        Map<Integer, Node> sMap = new HashMap<>();
        for (Integer i : searchArray) {
            s.add(i);
        }

        int mapStart = -1;
        int rangeStart = -1;
        int rangeEnd = -1;
        Node head = null;
        for (int i = 0; i < arr.length; i++) {
            if (s.contains(arr[i])) {
                if (sMap.containsKey(arr[i])) {
                    // the number was in the array before, let's update it's index and remove
                    // all numbers from the map before the previous occurrence of arr[i] number
                    Node n = sMap.get(arr[i]);
                    mapStart = n.idx;
                    // save position of the first index in the map
                    if (n.next != null) {
                        mapStart = n.next.idx;
                    }
                    while (n != null) {
                        sMap.remove(n.val);
                        if (n == head) {
                            head = null;
                        }
                        Node tmp = n;
                        n = n.prev;
                        if (tmp.next != null) {
                            tmp.next.prev = null;
                        }
                        tmp.prev = null;
                        tmp.next = null;
                    }
                }

                if (mapStart == -1) {
                    mapStart = i;
                }
                Node n = new Node(i, arr[i]);
                if (head != null) {
                    head.next = n;
                }
                n.prev = head;
                head = n;
                sMap.put(arr[i], head);

                if (sMap.size() == searchArray.length
                    && (rangeStart == -1 || (i - mapStart) < (rangeEnd - rangeStart))) {
                    rangeStart = mapStart;
                    rangeEnd = i;
                }
            }
        }

        return new int[] {rangeStart, rangeEnd};
    }

    private static class Node {
        private Node prev;
        private Node next;
        private int idx;
        private int val;

        public Node(final int idx, final int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}
