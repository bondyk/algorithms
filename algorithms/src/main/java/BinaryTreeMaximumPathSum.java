import helper.TreeNode;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node
 * to any node in the tree along the parent-child connections. The path must contain at least
 * one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 */
public class BinaryTreeMaximumPathSum {

    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println(maxPathSum(root));// 6
    }

    public static int maxPathSum(TreeNode root) {
        getMaxSum(root);
        return max;
    }

    private static int getMaxSum(TreeNode node) {
        if (node == null) return 0;
        int leftSum = Math.max(0, getMaxSum(node.left));
        int rightSum = Math.max(0, getMaxSum(node.right));
        int sum = leftSum + rightSum + node.val;
        max = Math.max(max, sum);

        return Math.max(leftSum, rightSum) + node.val;
    }
}
