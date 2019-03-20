/**
 * Bob has a strange counter. At the first second, it displays the number 3.
 * Each second, the number displayed by the counter decrements by 1 until it reaches 1.
 *
 * The counter counts down in cycles. In next second, the timer resets and continues counting down.
 * Find and print the value displayed by the counter at time t.
 *
 * https://www.hackerrank.com/challenges/strange-code/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
 */
public class StrangeCounter {

    // Complete the strangeCounter function below.
    static long strangeCounter(long t) {
        /*
        v0 = 3 * 2 ^ 0;
        v1 = 3 * 2 ^ 1;
        v2 = 3 * 2 ^ 2;
        v3 = 3 * 2 ^ 3;

        t0 = 1;
        t1 = t0 + v0;
        t2 = t0 + v0 + 2 * x0;
        t3 = t0 + v0 + v0 * 2 + v0 * 2 * 2;
          => t0 + v0 * (2^0 + 2^1 + 2^2)
        */

        int multiplyFactor = 2;
        int t0 = 1;
        int v0 = 3;
        // https://math.stackexchange.com/questions/1897059/simple-sum-of-finite-exponential-series
        //long t = (long) (t0 + v0 * (1 - Math.pow(multiplyFactor, bucket)) / (1 - multiplyFactor));
        //int (1 - Math.pow(multiplyFactor, bucket)) = ((t - t0) * (1 - multiplyFactor) / v0)
        final int bucket = (int) (Math.log(1 - ((t - t0) * (1 - multiplyFactor) / v0)) / Math.log(multiplyFactor));

        long bucketStartTime = (long) (t0 + v0 * (1 - Math.pow(multiplyFactor, bucket)) / (1 - multiplyFactor));
        long bucketMaxValue = v0 * (long) Math.pow(multiplyFactor, bucket);
        return bucketMaxValue - (t - bucketStartTime);
    }

    public static void main(String[] args) {
        System.out.println(strangeCounter(4));// 6
        System.out.println(strangeCounter(17));// 5
        System.out.println(strangeCounter(21));// 1
        System.out.println(strangeCounter(10));// 12
        System.out.println(strangeCounter(1));// 3
        System.out.println(strangeCounter(2));// 2
        System.out.println(strangeCounter(9));// 1
        System.out.println(strangeCounter(23));// 23
    }
}
