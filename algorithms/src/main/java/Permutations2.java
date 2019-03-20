import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class Permutations2 {

    public static void main(String[] args) {
        List<List<Integer>> r = permuteUnique(new int[] {1,1,2});
        for (List<Integer> perm : r) {
            System.out.println(perm);
        }

        System.out.println("---------------");
        r = permuteUnique(new int[] {1,2,2,4});
        for (List<Integer> perm : r) {
            System.out.println(perm);
        }
    }

    /**
     * See https://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html
     */
    private static List<List<Integer>>permuteUnique(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        permuteUnique(result, new LinkedHashMap<>(), nums);
        return new ArrayList<>(result);
    }

    private static void permuteUnique(Set<List<Integer>> result, Map<Integer, Integer> path, int[] nums) {

        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path.values()));
        }

        for (int i = 0; i < nums.length; i++) {
            if (!path.containsKey(i)) {
                path.put(i, nums[i]);
                permuteUnique(result, path, nums);
                path.remove(i);
            }
        }
    }
}
