import java.util.Arrays;

/**
 * Let's say you have a binary string such as the following: 011100011
 One way to encrypt this string is to add to each digit the sum of its adjacent digits. For example, the above string would become: 123210122
 In particular, if P is the original string, and Q is the encrypted string, then Q[i] = P[i-1] + P[i] + P[i+1] for all digit positions i. Characters off the left and right edges of the string are treated as zeroes.
 An encrypted string given to you in this format can be decoded as follows (using 123210122 as an example):
 Assume P[0] = 0.
 Because Q[0] = P[0] + P[1] = 0 + P[1] = 1, we know that P[1] = 1.
 Because Q[1] = P[0] + P[1] + P[2] = 0 + 1 + P[2] = 2, we know that P[2] = 1.
 Because Q[2] = P[1] + P[2] + P[3] = 1 + 1 + P[3] = 3, we know that P[3] = 1.
 Repeating these steps gives us P[4] = 0, P[5] = 0, P[6] = 0, P[7] = 1, and P[8] = 1.
 We check our work by noting that Q[8] = P[7] + P[8] = 1 + 1 = 2. Since this equation works out, we are finished, and we have recovered one possible original string.
 Now we repeat the process, assuming the opposite about P[0]:
 Assume P[0] = 1.
 Because Q[0] = P[0] + P[1] = 1 + P[1] = 1, we know that P[1] = 0.
 Because Q[1] = P[0] + P[1] + P[2] = 1 + 0 + P[2] = 2, we know that P[2] = 1.
 Now note that Q[2] = P[1] + P[2] + P[3] = 0 + 1 + P[3] = 3, which leads us to the conclusion that P[3] = 2. However, this violates the fact that each character in the original string must be '0' or '1'. Therefore, there exists no such original string P where the first digit is '1'.
 Note that this algorithm produces at most two decodings for any given encrypted string. There can never be more than one possible way to decode a string once the first binary digit is set.
 Given a String message, containing the encrypted string, return a String[] with exactly two elements. The first element should contain the decrypted string assuming the first character is '0'; the second element should assume the first character is '1'. If one of the tests fails, return the string "NONE" in its place. For the above example, you should return {"011100011", "NONE"}.
     
 Time limit (s): 2.000
 Memory limit (MB): 64
 Constraints
     - message will contain between 1 and 50 characters, inclusive.
     - Each character in message will be either '0', '1', '2', or '3'.
 Examples
 0) "123210122"
     Returns: { "011100011",  "NONE" }
     The example from above.
 1) "11"
     Returns: { "01",  "10" }
     We know that one of the digits must be '1', and the other must be '0'. We return both cases.
 2) "22111"
     Returns: { "NONE",  "11001" }
     Since the first digit of the encrypted string is '2', the first two digits of the original string must be '1'. Our test fails when we try to assume that P[0] = 0.
 3) "123210120"
     Returns: { "NONE",  "NONE" }
     This is the same as the first example, but the rightmost digit has been changed to something inconsistent with the rest of the original string. No solutions are possible.
 4) "3"
     Returns: { "NONE",  "NONE" }
 5) "12221112222221112221111111112221111"
     Returns:
     { "01101001101101001101001001001101001",
     "10110010110110010110010010010110010" }
 */
public class BinaryCode {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(decode("123210122")));//{"011100011", "NONE"}
        System.out.println(Arrays.toString(decode("11")));//{ "01",  "10" }
        System.out.println(Arrays.toString(decode("22111")));//{ "NONE",  "11001" }
        System.out.println(Arrays.toString(decode("123210120")));//{ "NONE",  "NONE" }
        System.out.println(Arrays.toString(decode("3")));//{ "NONE",  "NONE" }
        System.out.println(Arrays.toString(decode("12221112222221112221111111112221111")));// { "01101001101101001101001001001101001", "10110010110110010110010010010110010" }
    }

    public static String[] decode(String message) {
        char[] chars = message.toCharArray();
        StringBuilder sb0 = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        int[] arr0 = new int[] {0, 0};
        int[] arr1 = new int[] {1, 0};
        sb0.append(arr0[0]);
        sb1.append(arr1[0]);
        for (int i = 0; i < chars.length - 1; i++) {
            int encNum = Character.getNumericValue(chars[i]);
            arr0 = calcNext(arr0, encNum, sb0, false);
            arr1 = calcNext(arr1, encNum, sb1, false);
        }

        int encNum = Character.getNumericValue(chars[chars.length - 1]);
        calcNext(arr0, encNum, sb0, true);
        calcNext(arr1, encNum, sb1, true);

        return new String[]{sb0.toString(), sb1.toString()};
    }

    private static int[] calcNext(int[] arr, int encNum, StringBuilder sb, boolean checkConstrViolOnly) {
        if (arr != null) {
            int next0 = encNum - arr[0] - arr[1];
            if (next0 > 1 || next0 < 0) {
                sb.delete(0, sb.length());
                sb.append("NONE");
                return null;
            } else if (!checkConstrViolOnly) {
                sb.append(next0);
                arr[1] = arr[0];
                arr[0] = next0;
            }
        }

        return arr;
    }
}
