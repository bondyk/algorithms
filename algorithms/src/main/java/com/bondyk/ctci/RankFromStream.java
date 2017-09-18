package com.bondyk.ctci;

/**
 * Imagine you are reading in a stream of integers. Periodically, you wish
 to be able to look up the rank of a number x (the number of values less than or equal to x).
 Implement the data structures and algorithms to support these operations. That is, implement
 the method track(int x), which is called when each number is generated, and the method
 getRankOfNumber(int x), which returns the number of values less than or equal to x (not
 including x itself).
 EXAMPLE
 Stream(in order of appearance):5, 1, 4, 4, 5, 9, 7, 13, 3
 getRankOfNumber(l) 0
 getRankOfNumber(3) 1
 getRankOfNumber(4) 3

 1 3 4 4 5 5 7 9 13

             5
      1             9
         4      7      13
      4    5
    3
 */
public class RankFromStream {

    private Node root;

    public static void main(String[] args) {
        int[] input = {5, 1, 4, 4, 5, 9, 7, 13, 3};
        RankFromStream rfs = new RankFromStream();
        for (int i : input) {
            rfs.track(i);
        }

        System.out.println(rfs.getRankOfNumber(1));
        System.out.println(rfs.getRankOfNumber(3));
        System.out.println(rfs.getRankOfNumber(4));
        System.out.println(rfs.getRankOfNumber(6));
        System.out.println(rfs.getRankOfNumber(7));

    }

    public void track(int x) {
        root = track(root, x);
    }

    public int getRankOfNumber(int x) {
        return getRankOf(root, x);
    }

    private Node track(Node n, int value) {
        if (n == null) {
            return new Node(value);
        }

        if (value <= n.value) {
            n.left = track(n.left, value);
        } else {
            n.right = track(n.right, value);
        }

        int leftSize = n.left != null ? n.left.size : 0;
        int rightSize = n.right != null ? n.right.size : 0;
        n.size = leftSize + rightSize + 1;
        return n;
    }

    private int getRankOf(Node n, int value) {
        if (n == null) {
            return 0;
        }

        int leftSize = n.left != null ? n.left.size : 0;
        if (value == n.value) {
            return leftSize + 1;
        }

        if (value < n.value) {
            return getRankOf(n.left, value);
        }

        return leftSize + 1 + getRankOf(n.right, value);
    }

    private static class Node {

        private final int value;
        private int size = 1;

        private Node left;
        private Node right;


        private Node(int value) {
            this.value = value;
        }
    }
}
