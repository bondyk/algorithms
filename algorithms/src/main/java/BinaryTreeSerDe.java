import helper.TreeNode;
import helper.TreeNodePrinter;

/**
 * Leetcode #297
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection
 * link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction
 * on how your serialization/deserialization algorithm should work. You just need to ensure
 * that a binary tree can be serialized to a string and this string can be deserialized
 * to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and come up with
 * different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 */
public class BinaryTreeSerDe {

    public static void main(String[] args) {
        BinaryTreeSerDe codec = new BinaryTreeSerDe();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String ser = codec.serialize(root);
        System.out.println(ser);
        TreeNode desRoot = codec.deserialize(ser);
        TreeNodePrinter.print(desRoot);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "null";
        StringBuilder sb = new StringBuilder();
        ser(root, sb);
        //System.out.println(sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("null")) return null;
        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        des(nodes, 1, root);
        return root;
    }


    private int des(String[] nodes, int i, TreeNode parent) {
        if (!nodes[i].equals("null")) {
            parent.left = new TreeNode(Integer.parseInt(nodes[i]));
            i = des(nodes, i + 1, parent.left);
        }

        i++;

        if (!nodes[i].equals("null")) {
            parent.right = new TreeNode(Integer.parseInt(nodes[i]));
            i = des(nodes, i + 1, parent.right);
        }

        return i;
    }

    private void ser(TreeNode n, StringBuilder sb) {
        sb.append(n == null ? "null" : Integer.toString(n.val));
        sb.append(",");
        if (n == null) return;
        ser(n.left, sb);
        ser(n.right, sb);
    }
}
