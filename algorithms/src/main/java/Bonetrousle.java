import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Here's a humerus joke:
 *
 * > Why did Papyrus the skeleton go to the store by himself? Because he had no body to go with him!
 *
 * Did you like it? Don't worry, I've got a ton more. A skele-ton.
 *
 * Once upon a time, Papyrus the skeleton went to buy some pasta from the store.
 * The store's inventory is bare-bones and they only sell one thing — boxes of uncooked spaghetti!
 * The store always stocks exactly x boxes of pasta, and each box is numbered sequentially from 1 to k.
 * This box number also corresponds to the number of sticks of spaghetti in the box,
 * meaning the first box contains 1 stick, the second box contains 2 sticks, the third box contains
 * 3 sticks, …, and the k-th box contains k sticks. Because they only stock one box of each kind,
 * the store has a tendon-cy to sell out of spaghetti.
 *
 * During each trip to the store, Papyrus likes to buy exactly n sticks of spaghetti by
 * purchasing exactly b boxes (no more, no less). Not sure which boxes to purchase,
 * Papyrus calls Sherlock Bones for help but he's also stumped!
 * Do you have the guts to solve this puzzle?
 *
 * Given the values of n, k, and b for t trips to the store, determine which boxes Papyrus must
 * purchase during each trip. For each trip, print a single line of b distinct space-separated
 * integers denoting the box number for each box of spaghetti Papyrus purchases
 * (recall that the store only has one box of each kind).
 * If it's not possible to buy n sticks of spaghetti by purchasing b boxes, print -1 instead.
 *
 * For example, Papyrus wants to purchase n = 14 sticks of spaghetti in b = 3 boxes
 * and the store has k = 8 different box sizes. He can buy boxes of sizes [8, 4, 2], [7, 5, 2], [7, 6, 1]
 * and other combinations. Any of the combinations will work.
 *
 * https://www.hackerrank.com/challenges/bonetrousle/problem
 */
public class Bonetrousle {

    static long[] bonetrousle(long n, long k, int b) {
        long[] boxes = getBoxes(n,  Math.min(k, n), b);
        return boxes == null ? new long[] {-1} : boxes;
    }

    private static long[] getBoxes(long n, long k, int b) {
        int maxValue = (int) ((k * (k + 1)) / 2);
        if (maxValue < n || k < b || n <= 0 || b <= 0) {
            return null;
        }

        if (b == 1 && n - k == 0) return new long[] { k };

        for (long i = k; i > 0; i--) {
            long[] boxes = getBoxes(n - i, Math.min(i - 1, n - i), b - 1);
            if (boxes != null) {
                long[] result = new long[boxes.length + 1];
                result[0] = i;
                System.arraycopy(boxes, 0, result, 1, boxes.length);
                return result;
            }
        }

        return null;
    }

    private static final Scanner scanner = new Scanner(new ByteArrayInputStream((
          "18\n"
        + "38 10 7\n"
        + "2 3 1\n"
        + "125 16 14\n"
        + "77 18 7\n"
        + "3 3 1\n"
        + "172 17 9\n"
        + "124 19 11\n"
        + "3 2 2\n"
        + "173 19 18\n"
        + "3 2 2\n"
        + "50 16 7\n"
        + "122 16 13\n"
        + "73 19 6\n"
        + "7 14 1\n"
        + "6 3 3\n"
        + "13 17 4\n"
        + "59 13 7\n"
        + "31 8 7").getBytes()));

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(scanner.nextLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nkb = scanner.nextLine().split(" ");

            long n = Long.parseLong(nkb[0].trim());

            long k = Long.parseLong(nkb[1].trim());

            int b = Integer.parseInt(nkb[2].trim());

            long[] result = bonetrousle(n, k, b);

            bufferedWriter.write("[n = " + n + ", k = " + k + ", b = " + b + "] = ");
            for (int resultItr = 0; resultItr < result.length; resultItr++) {
                bufferedWriter.write(String.valueOf(result[resultItr]));

                if (resultItr != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
