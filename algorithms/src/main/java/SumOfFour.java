import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d
 * in nums such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class SumOfFour {

    public static void main(String[] args) {

        for (List<Integer> nums : fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0)) {
            System.out.println(nums);
        }

        System.out.println("----------");
        for (List<Integer> nums : fourSum(new int[] {-3,-2,-1,0,0,1,2,3}, 0)) {
            System.out.println(nums);
        }
    }

    private static List<List<Integer>> fourSum(int[] nums, int target) {

        long s = System.currentTimeMillis();
        //Convert to pairs
        Map<Integer, List<Pair>> pairs = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (!pairs.containsKey(sum)) {
                    pairs.put(sum, new ArrayList<>());
                }
                pairs.get(sum).add(new Pair(i, j));
            }
        }

        Set<List<Integer>> result = new HashSet<>();
        for (final Map.Entry<Integer, List<Pair>> entry : pairs.entrySet()) {
            int sum = entry.getKey();
            List<Pair> oppoSums = pairs.get(target - sum);
            if (oppoSums != null) {
                for (final Pair p : oppoSums) {
                    for (Pair p1 : entry.getValue()) {
                        if (p.idx1 != p1.idx1 && p.idx2 != p1.idx2
                            && p.idx1 != p1.idx2 && p.idx2 != p1.idx1) {
                            List<Integer> v = new ArrayList<>(Arrays.asList(
                                nums[p.idx1],
                                nums[p.idx2],
                                nums[p1.idx1],
                                nums[p1.idx2]));
                            // Ensure result doesn't have duplicate quadriples
                            Collections.sort(v);
                            result.add(v);
                        }
                    }
                }
            }
        }

        System.out.println(System.currentTimeMillis() - s);

        return new ArrayList<>(result);
    }

    private static class Pair {
        private int idx1;
        private int idx2;

        Pair(int idx1, int idx2) {
            this.idx1 = idx1;
            this.idx2 = idx2;
        }
    }

}
