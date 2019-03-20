/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2,3,1,1,4}));//true
        System.out.println(canJump(new int[]{3,2,1,0,4}));//false
    }

    public static boolean canJump(int[] nums) {
        return isReachable(nums);
    }

    private static boolean isReachable(int[] nums) {

        int maxIdx = 0;
        for (int i = 0; i <= nums.length - 1; i++) {
            if (maxIdx < i) return false;
            maxIdx = Math.max(maxIdx, i + nums[i]);
        }

        return maxIdx >= nums.length - 1;
    }
}
