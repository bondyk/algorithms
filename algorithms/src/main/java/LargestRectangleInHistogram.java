import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of
 * each bar is 1, find the area of largest rectangle in the histogram.
 *
 * Example:
 *
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[] {2,1,5,6,2,3})); // 10
        System.out.println(largestRectangleArea(new int[] {2})); // 2
        System.out.println(largestRectangleArea(new int[] {2,1,5,6,2,3,10,9,11,14,11,9})); // 54
    }


    public static int largestRectangleArea(int[] heights) {

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while(i < heights.length) {
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int t = stack.pop();
                int width = (stack.isEmpty() ? i : i - 1 - stack.peek());
                int area = width * heights[t];
                maxArea = Math.max(area, maxArea);
            }
            stack.push(i++);
        }

        while(!stack.isEmpty()) {
            int t = stack.pop();
            int width = (stack.isEmpty() ? i : i - 1 - stack.peek());
            int area = width * heights[t];
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }/*

    public static int largestRectangleArea(int[] heights) {

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while(i < heights.length) {
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int t = stack.pop();
                int width = (stack.isEmpty() ? i : i - 1 - stack.peek());
                int area = width * heights[t];
                maxArea = Math.max(area, maxArea);
            }
            stack.push(i++);
        }

        while(!stack.isEmpty()) {
            int t = stack.pop();
            int width = (stack.isEmpty() ? i : i - 1 - stack.peek());
            int area = width * heights[t];
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }*/
}
