/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * Example:
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
public class ContainerWithMaxWater {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
    }

    public static int maxArea(int[] height) {
        int lo = 0;
        int hi = height.length - 1;

        int maxArea = 0;

        while(lo < hi) {
            int area = Math.min(height[lo], height[hi]) * (hi - lo);
            maxArea = Math.max(area, maxArea);
            if (height[lo] > height[hi]) {
                hi--;
            } else {
                lo++;
            }
        }

        return maxArea;
    }
}
