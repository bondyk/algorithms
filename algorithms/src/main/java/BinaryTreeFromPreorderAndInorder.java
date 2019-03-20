import java.util.HashMap;
import java.util.Map;

import helper.TreeNode;
import helper.TreeNodePrinter;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class BinaryTreeFromPreorderAndInorder {

    public static void main(String[] args) {
        TreeNodePrinter.print(buildTree(new int[] {3,9,20,15,7}, new int[] {9,3,15,20,7}));
    }

    /**
     * The first element in the preorder list is a root. This root splits inorder list into
     * left and right subtrees. Now one have to pop up the root from preorder list since it's
     * already used as a tree node and then repeat the step above for the left and right subtrees.
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return addNode(0, 0, inorder.length - 1, preorder, inorderMap)
            .node;
    }

    private static Pair addNode(int index, int inIdxStart, int inIdxEnd, int[] preorder, Map<Integer, Integer> inorderMap) {
        int idx = index;
        if (idx >= preorder.length || inIdxEnd < inIdxStart) {
            return null;
        }

        int inorderIdx = inorderMap.get(preorder[idx]);
        if (inorderIdx < inIdxStart) return null;

        TreeNode node = new TreeNode(preorder[idx]);
        if (idx < preorder.length - 1) {
            Pair left = addNode(idx + 1, inIdxStart, inorderIdx - 1, preorder, inorderMap);
            if (left != null) {
                node.left = left.node;
                idx = left.index;
            }
        }

        if (idx < preorder.length - 1) {
            Pair right = addNode(idx + 1, inorderIdx + 1, inIdxEnd, preorder, inorderMap);
            if (right != null) {
                node.right = right.node;
                idx = right.index;
            }
        }

        return new Pair(idx, node);
    }

    private static class Pair {
        private Integer index;
        private TreeNode node;
        Pair(Integer index, TreeNode node) {
            this.index = index;
            this.node = node;
        }
    }
}
