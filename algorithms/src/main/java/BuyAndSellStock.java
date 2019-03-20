/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction
 * (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BuyAndSellStock {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[] {7,1,5,3,6,4}));// 5
        System.out.println(maxProfit(new int[] {7,6,4,3,1}));// 0
    }

    public static int maxProfit(int[] prices) {
        int max = 0;
        int result = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            if (i == prices.length - 1) {
                max = prices[prices.length - 1];
            } else {
                max = Math.max(max, prices[i]);
            }

            result = Math.max(result, max - prices[i]);
        }
        return result;
    }
}
