/**
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaxProductSubarray {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[] {2,3,-2,4})); // 6
        System.out.println(maxProduct(new int[] {-2,0,-1})); // 0
    }

    public static int maxProduct(int[] nums) {
        int globalMax = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int newMin = min * nums[i];
            int newMax = max * nums[i];
            max = Math.max(Math.max(newMax, newMin), nums[i]);
            min = Math.min(Math.min(newMax, newMin), nums[i]);
            globalMax = Math.max(globalMax, Math.max(max, min));
        }

        return globalMax;
    }
}
