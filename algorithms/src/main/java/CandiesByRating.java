/**
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 *
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *              The third child gets 1 candy because it satisfies the above two conditions.
 */
public class CandiesByRating {

    public static void main(String[] args) {
        System.out.println(candy(new int[] {1,0,2})); // 5
        System.out.println(candy(new int[] {1,0,25,7,4,3,6,8,6,8,9,1,11,12,8,67,45,34,98,56,57,99})); // 45
    }

    public static int candy(int[] ratings) {
        if (ratings.length == 0) return 0;
        int[] leftToRight = new int[ratings.length];
        leftToRight[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            leftToRight[i] = ratings[i - 1] < ratings[i] ? leftToRight[i - 1] + 1 : 1;
        }

        int rightToLeft = leftToRight[ratings.length - 1];
        int totalCandies = rightToLeft;
        for (int i = ratings.length - 2; i >= 0; i--) {
            rightToLeft = ratings[i + 1] < ratings[i] ? rightToLeft + 1 : 1;
            int candies = Math.max(rightToLeft, leftToRight[i]);
            totalCandies += candies;
        }

        return totalCandies;
    }
}
