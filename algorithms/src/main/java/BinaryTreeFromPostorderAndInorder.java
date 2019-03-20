import java.util.HashMap;
import java.util.Map;

import helper.TreeNode;
import helper.TreeNodePrinter;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class BinaryTreeFromPostorderAndInorder {

    public static void main(String[] args) {
        TreeNodePrinter.print(buildTree(new int[] {9,3,15,20,7}, new int[] {9,15,7,20,3}));
    }

    /**
     * See also {@link BinaryTreeFromPreorderAndInorder}
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length == 0) return null;

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return addNode(postorder.length - 1, 0, inorder.length - 1, postorder, inorderMap)
            .node;
    }

    private static Pair addNode(int index, int inIdxStart, int inIdxEnd, int[] postorder, Map<Integer, Integer> inorderMap) {
        int idx = index;
        if (idx < 0 || inIdxEnd < inIdxStart) {
            return null;
        }

        int inorderIdx = inorderMap.get(postorder[idx]);
        if (inorderIdx < inIdxStart) return null;

        TreeNode node = new TreeNode(postorder[idx]);

        if (idx > 0) {
            Pair right = addNode(idx - 1, inorderIdx + 1, inIdxEnd, postorder, inorderMap);
            if (right != null) {
                node.right = right.node;
                idx = right.index;
            }
        }

        if (idx > 0) {
            Pair left = addNode(idx - 1, inIdxStart, inorderIdx - 1, postorder, inorderMap);
            if (left != null) {
                node.left = left.node;
                idx = left.index;
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
