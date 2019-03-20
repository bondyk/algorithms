/**
 * Leetcode
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
//        System.out.println(findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
        System.out.println(findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
    }

    // 1 2 3
    // 4 5 6
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // FIXME: INCOMPLETE!
        int length = nums1.length + nums2.length;
        int middle = length / 2;
        int idx1, idx2;
        do {
            idx1 = middle / 2;
            idx2 = middle  - idx1;
            if (nums1[idx1] < nums2[idx2]) {

            } else {

            }
            middle = Math.max(1, middle / 2);
        } while(haveItemsBetween(nums1, idx1, nums2, idx2));

        return nums1[idx1];
    }

    private static boolean haveItemsBetween(int[] n1, int i1, int[] n2, int i2) {
        int[] greater, smaller;
        int gi, si;
        if (n1[i1] > n2[i2]) {
            greater = n1;
            gi = i1;
            smaller = n2;
            si = i2;
        } else {
            greater = n2;
            gi = i2;
            smaller = n1;
            si = i1;
        }

        if (si < smaller.length - 1) {
            return smaller[si + 1] < greater[gi];
        } else if (gi > 0) {
            return smaller[si] < greater[gi - 1];
        }

        return false;
    }

    private static int jumpLeft(int[] n, int i, int step) {
        if (i - step >= 0) {
            return i - step;
        }

        return 0;
    }

    private static int jumpRight(int[] n, int i, int step) {
        if (i + step <= n.length - 1) {
            return i + step;
        }

        return n.length - 1;
    }

    /*private static int find(int[] nums1, int i1, int[] nums2, int i2, int middleIdx) {
        if (i1 + i2 > middleIdx) {
            //requires shrinking
            if (nums1[i1] > nums2[i2]) {
                if (i1 > 0) {
                    find(nums1, i1 / 2, nums2, i2, middleIdx);
                } else if (i2 > 0) {
                    find(nums1, i1, nums2, i2 / 2, middleIdx);
                } else {
                    //impossible!
                    System.out.println("---impossible");
                }
            } else {
                if (i2 > 0) {
                    find(nums1, i1, nums2, i2 / 2, middleIdx);
                } else if (i1 > 0) {
                    find(nums1, i1 / 2, nums2, i2, middleIdx);
                } else {
                    //impossible!
                    System.out.println("---impossible");
                }
            }
        } else if (i1 + i2 < middleIdx) {
            if (nums1[i1] < nums2[i2]) {
                if (i1 < nums1.length - 1) {
                    find(nums1, i1 + (nums1.length - i1) / 2, nums2, i2, middleIdx);
                } else if (i2 < nums2.length - 1) {
                    find(nums1, i1, nums2, i2 + (nums2.length - i2) / 2, middleIdx);
                } else {
                    //impossible!
                    System.out.println("---impossible");
                }

            } else {
                if (i2 < nums2.length - 1) {
                    find(nums1, i1, nums2, i2 + (nums2.length - i2) / 2, middleIdx);
                } else if (i1 < nums1.length - 1) {
                    find(nums1, i1 + (nums1.length - i1) / 2, nums2, i2, middleIdx);
                } else {
                    //impossible!
                    System.out.println("---impossible");
                }
            }
        } else {
            // hit length!
            if (nums1[i1])
        }
        return -1;
    }*/
}
