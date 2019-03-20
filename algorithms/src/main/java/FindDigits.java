import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * You are given a number N. Find the digits in this number that exactly divide N and display their count.
 For N = 24, there are 2 digits - 2 & 4. Both these digits exactly divide 24. So our answer is 2.

 Note

 If the same number is repeated twice at different positions, it should be counted twice, e.g.,
 For N=122, 2 divides 122 exactly and occurs at ones' and tens' position. So it should be counted twice.
 So for this case, our answer is 3.
 Division by 0 is undefined.

 Input Format

 The first line contains T (number of Maze cases) followed by T lines (each containing an integer N).

 Constraints
 1 <=T <= 15
 0 < N < 1010

 Output Format

 For each Maze case, display the count of digits in N that exactly divide N in separate line.
 */
public class FindDigits {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int numberOfTests = Integer.parseInt(in.readLine());
            for (int i = 0; i < numberOfTests; i++) {
                String numberStr = in.readLine();
                int number = Integer.parseInt(numberStr);
                char[] chars = numberStr.toCharArray();
                int result = 0;
                for (int j = 0; j < chars.length; j++) {
                    int digit = Character.getNumericValue(chars[j]);
                    if (digit != 0 && number % digit == 0) {
                        result++;
                    }
                }
                System.out.println(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
