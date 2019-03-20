import java.util.Arrays;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 *
 * Example 1:
 *
 * Input: [10,2]
 * Output: "210"
 * Example 2:
 *
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {

    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{3,30,34,5,9})); // 9534330
    }

    public static String largestNumber(int[] nums) {
        /*
        String s1 = "9";
        String s2 = "31";

        String case1 =  s1 + s2; // 931
        String case2 = s2 + s1; // 319
         */
        return Arrays.stream(nums).mapToObj(Integer::toString)
            .sorted((n1, n2) -> (n2 + n1).compareTo(n1 + n2))
            .reduce((x, y) -> x.equals("0") ? y : x + y).get();
    }

}
