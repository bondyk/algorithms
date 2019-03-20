package com.bondyk.ctci;

import helper.TreeNode;

/**
 * Write method to determine if tree is mirror.

   X
 X   X
 Is mirror

     X
   X  X
 X
 Not mirror
 */

public class TreeMirror {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.right.right = new TreeNode(0);
        root.left.right = new TreeNode(0);
        root.right.left = new TreeNode(0);

        System.out.println(isMirror(root));
        root.right.left.left = new TreeNode(0);
        System.out.println(isMirror(root));
        root.left.right.right = new TreeNode(0);
        System.out.println(isMirror(root));
    }

    private static boolean isMirror(TreeNode node) {
        return isMirror(node.left, node.right);
    }

    private static boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

}
