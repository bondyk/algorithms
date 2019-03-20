import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Subsets {

    public static void main(String[] args) {
        List<List<Integer>> r = subsets(new int[] {1,2,3});
        for (List<Integer> perm : r) {
            System.out.println(perm);
        }


        System.out.println("---------------");
        r = subsets(new int[] {1,2,3,4});
        for (List<Integer> perm : r) {
            System.out.println(perm);
        }
    }

    /**
     * See Permutations
     */
    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsets(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void subsets(List<List<Integer>> list , List<Integer> path, int [] nums, int start) {
        list.add(new ArrayList<>(path));
        for(int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            subsets(list, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
