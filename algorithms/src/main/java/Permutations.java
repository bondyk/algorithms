import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations {

    public static void main(String[] args) {
        List<List<Integer>> r = permute(new int[] {1,2,3});
        for (List<Integer> perm : r) {
            System.out.println(perm);
        }

        System.out.println("---------------");
        r = permute(new int[] {1,2,3,4});
        for (List<Integer> perm : r) {
            System.out.println(perm);
        }
    }

    /**
     * See https://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html
     */
    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(result, new ArrayList<>(nums.length), nums);
        return result;
    }

    private static void permute(List<List<Integer>> result, List<Integer> path, int[] nums) {

        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
        }

        for (final int num : nums) {
            if (!path.contains(num)) {
                path.add(num);
                permute(result, path, nums);
                path.remove(path.size() - 1);
            }
        }
    }
}
