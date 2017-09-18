package com.bondyk.ctci;


/**
 * Consider a simple data structure called BiNode, which has pointers to two other nodes. The
 data structure BiNode could be used to represent both a binary tree (where node1 is the left node
 and node2 is the right node) or a doubly linked list (where node1 is the previous node and node2
 is the next node). Implement a method to convert a binary search tree (implemented with BiNode)
 into a doubly linked list. The values should be kept in order and the operation should be performed
 in place (that is, on the original data structure).
 */
public class BiNode {

    private final String value;
    private BiNode left;
    private BiNode right;

    public BiNode(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        BiNode root = new BiNode("10");
        BiNode left = new BiNode("5");
        BiNode right = new BiNode("15");
        BiNode leftLeft = new BiNode("3");
        BiNode leftRight = new BiNode("8");
        BiNode rightLeft = new BiNode("13");
        BiNode rightRight = new BiNode("18");
        BiNode rightRightLeft = new BiNode("16");
        BiNode leftRightRight = new BiNode("9");

        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        right.left = rightLeft;
        right.right = rightRight;
        rightRight.left = rightRightLeft;
        leftRight.right = leftRightRight;

        printTree(root);

        toLinkedList(root);

        System.out.println("\n---------------------------------------");
        printList(root);

        System.out.println("\n---------------------------------------");
        printReverseList(root);
    }

    private static void toLinkedList(BiNode treeRoot) {
        addToList(null, treeRoot);
    }

    private static BiNode addToList(BiNode lastListNode, BiNode rightChild) {
        if (rightChild != null) {
            lastListNode = addToList(lastListNode, rightChild.left);
            if (lastListNode == null) {
                lastListNode = rightChild;
            } else {
                rightChild.left = lastListNode;
                lastListNode.right = rightChild;
                lastListNode = rightChild;
            }
            lastListNode = addToList(lastListNode, rightChild.right);
            return lastListNode;
        }

        return lastListNode;
    }

    private static void printList(BiNode node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            printList(node.left);
            return;
        }

        while (node != null) {
            System.out.print(node.value + " ");
            node = node.right;
        }
    }

    private static void printReverseList(BiNode node) {
        if (node == null) {
            return;
        }

        if (node.right != null) {
            printReverseList(node.right);
            return;
        }

        while (node != null) {
            System.out.print(node.value + " ");
            node = node.left;
        }
    }

    private static void printTree(BiNode node) {
        if (node == null) {
            return;
        }

        printTree(node.left);
        System.out.print(node.value + " ");
        printTree(node.right);
    }
}
