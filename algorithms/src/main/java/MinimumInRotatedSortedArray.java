/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * The array may contain duplicates.
 *
 * Example 1:
 *
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: [2,2,2,0,1]
 * Output: 0
 */
public class MinimumInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(findMin(new int[] {2,2,2,0,1}));// 0
        System.out.println(findMin(new int[] {2,2,2,1,2}));// 1
        System.out.println(findMin(new int[] {2,2,2,2,2}));// 2
        System.out.println(findMin(new int[] {1,2,3,4,5}));// 1
        System.out.println(findMin(new int[] {3,4,5,1,2}));// 1
    }

    public static int findMin(int[] nums) {

        return find(nums, 0, nums.length);
    }

    private static int find(int[] nums, int from, int to) {
        // System.out.println("from = " + from + ", to = " + to);
        int n = to - from;
        if (n == 1) return nums[from];
        if (nums[from] < nums[to - 1]) return nums[from];

        int middle = (to + from) / 2;
        if (middle > 0 && nums[middle - 1] > nums[middle]) {
            return nums[middle];
        }

        if (nums[middle] < nums[from]) {
            return find(nums, from, middle);//go left
        } else if (nums[middle] > nums[from]) {
            return find(nums, middle, to);//go right
        } else if (nums[middle] > nums[to - 1]) {
            return find(nums, middle, to);//go right
        } else {
            return Math.min(find(nums, from, middle), find(nums, middle, to));
        }
    }
}
