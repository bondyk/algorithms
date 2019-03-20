import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */

public class CombinationSum2 {

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[] {10,1,2,7,6,1,5}, 8));
        System.out.println(combinationSum2(new int[] {2,5,2,1,2}, 5));
    }

    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(candidates);
        sum(candidates, 0, target, new ArrayList<>(), result);
        return new ArrayList<>(result);
    }

    private static void sum(int[] candidates, int from, int target, List<Integer> path, Set<List<Integer>> result) {

        for (int i = from; i < candidates.length; i++) {
            if (candidates[i] == target) {
                List<Integer> hitPath = new ArrayList<>(path);
                hitPath.add(candidates[i]);
                result.add(hitPath);
            } else if (candidates[i] < target) {
                path.add(candidates[i]);
                sum(candidates, i + 1, target - candidates[i], path, result);
                path.remove(path.size() - 1);
            } else {
                break;
            }
        }
    }
}
