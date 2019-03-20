/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 4)); // 0
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 2)); // 6
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 7)); // 3
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0)); // 4
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3)); // -1
        System.out.println(search(new int[]{0,1,2,3,5}, 3)); // 3
        System.out.println(search(new int[]{0,1,2,3,5}, 4)); // -1
    }


    public static int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        if (target == nums[0]) return 0;
        return find(nums, 0, nums.length - 1, target);
    }

    private static int find(int[] nums, int from, int to, int target) {
        //System.out.println(from + " : " + to);

        if (to == from) return nums[from] == target ? from : -1;

        int i = (from + to) / 2;

        if (nums[i] == target) return i;

        if (nums[0] < nums[nums.length - 1]) {
            //array is sorted (or rotated around idx 0)
            // 0,1,2,3,4,5
            if (nums[i] > target) {
                return find(nums, from, i, target);
            } else {
                return find(nums, i + 1, to, target);
            }
        } else if (target > nums[0]) {
            // target is in the left subarray
            // [3,4,5,6,7],0,1,2
            if (nums[i] > nums[0]) {
                // target == 4, i -> 3,4,5,[6],7,0,1,2
                if (nums[i] > target) {
                    // target == 4, i -> 3,4,5,[6],7,0,1,2
                    return find(nums, from, i, target);
                } else {
                    // target == 4, i -> [3],4,5,6,7,0,1,2
                    return find(nums, i + 1, to, target);
                }
            } else {
                // target == 4, i -> 3,4,5,6,7,0,[1],2
                return find(nums, from, i, target);
            }
        } else {
            // target is in the right subarray
            // 4,5,6,7,[0,1,2]
            if (nums[i] < nums[0]) {
                // target == 1, i -> 4,5,6,7,0,1,[2],3
                if (nums[i] > target) {
                    // target == 1, i -> 4,5,6,7,0,1,[2],3
                    return find(nums, from, i, target);
                } else {
                    // target == 1, i -> 4,5,6,7,[0],1,2,3
                    return find(nums, i + 1, to, target);
                }
            } else {
                // target == 1, i -> 4,5,[6],7,0,1,2,3
                return find(nums, i + 1, to, target);
            }
        }
    }
}
