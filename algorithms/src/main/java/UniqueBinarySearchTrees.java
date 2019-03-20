import java.util.LinkedList;
import java.util.List;

import helper.TreeNode;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 * <p>
 * Example:
 * <p>
 * Input: 3
 * Output:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 * <p>
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *   3      2     1      1   3      2
 *  /     /       \                 \
 * 2     1         2                 3
 */
public class UniqueBinarySearchTrees {

    public static void main(String[] args) {
        System.out.println(generateTrees(3));
    }

    private static List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        return generateTrees(0, n);
    }

    private static List<TreeNode> generateTrees(int from, int to) {
        List<TreeNode> trees = new LinkedList<>();
        if (from >= to) {
            trees.add(null);
            return trees;
        }

        for (int i = from; i < to; i++) {
            List<TreeNode> left = generateTrees(from, i);
            List<TreeNode> right = generateTrees(i + 1, to);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode node = new TreeNode(i + 1);
                    node.left = l;
                    node.right = r;
                    trees.add(node);
                }
            }
        }

        return trees;
    }
}
