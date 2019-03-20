import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */

public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[] {2,3,6,7}, 7));
        System.out.println(combinationSum(new int[] {2,3,5}, 8));
    }

    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        Node pathStart = new Node(-1);
        sum(candidates, 0, target, pathStart, pathStart, result);
        return result;
    }

    private static void sum(int[] candidates, int from, int target, Node pathStart, Node pathEnd, List<List<Integer>> result) {

        for (int i = from; i < candidates.length; i++) {
            if (candidates[i] == target) {
                List<Integer> hitPath = new ArrayList<>();
                Node n = pathStart;
                while(n != null) {
                    if (n.val != -1) {
                        hitPath.add(n.val);
                    }
                    n = n.next;
                }
                hitPath.add(candidates[i]);
                result.add(hitPath);
            } else if (candidates[i] < target) {
                pathEnd.next = new Node(candidates[i]);
                sum(candidates, i, target - candidates[i], pathStart, pathEnd.next, result);
                pathEnd.next = null;
            } else {
                break;
            }
        }
    }

    private static class Node {
        private final int val;
        private Node next;
        private Node(final int val) {
            this.val = val;
        }
    }
}
