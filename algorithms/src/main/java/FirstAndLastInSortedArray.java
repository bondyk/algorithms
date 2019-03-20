import java.util.Arrays;

/**
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class FirstAndLastInSortedArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[] {5,7,7,8,8,10}, 8)));// 3:4
        System.out.println(Arrays.toString(searchRange(new int[] {5,7,7,8,8,10}, 7)));// 1:2
        System.out.println(Arrays.toString(searchRange(new int[] {5,7,7,8,8,10}, 6)));// -1:-1
        System.out.println(Arrays.toString(searchRange(new int[] {5,7,7,8,8,10}, 10)));// 5:5
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[] {-1,-1};
        int idxLeft = binarySearch(nums, 0, nums.length - 1, target, true);
        int idxRight = binarySearch(nums, idxLeft, nums.length - 1, target, false);
        return new int[] {idxLeft, idxRight};
    }

    private static int binarySearch(int[] nums, int from, int to, int target, boolean left) {
        if (from >= to) return nums[from] == target ? from : -1;

        int i = (from + to) / 2;
        if (nums[i] == target) {
            if (left && (i > 0 && nums[i - 1] == nums[i])) {
                int lIdx = binarySearch(nums, from, i - 1, target, left);
                if (lIdx != -1) return lIdx;
            }
            if (!left && (i < nums.length - 1 && nums[i] == nums[i + 1])) {
                int rIdx = Math.max(i, binarySearch(nums, i + 1, nums.length - 1, target, left));
                if (rIdx != -1) return rIdx;
            }
            return i;
        };

        if (nums[i] > target) return binarySearch(nums, from, i, target, left);
        return binarySearch(nums, i + 1, to, target, left);
    }
}
