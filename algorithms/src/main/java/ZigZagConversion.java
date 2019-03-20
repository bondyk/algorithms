import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string decode(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        System.out.println(decode("YOKUBNURYDRIA", 4));

        System.out.println("Expected: 'PAHNAPLSIIGYIR' Actual: '" + encode("PAYPALISHIRING", 3) + "'");
        System.out.println("Expected: 'PINALSIGYAHRPI' Actual: '" + encode("PAYPALISHIRING", 4) + "'");
        System.out.println("Expected: 'PYAIHRNAPLSIIG' Actual: '" + encode("PAYPALISHIRING", 2) + "'");
        System.out.println("Expected: 'PAYPALISHIRING' Actual: '" + encode("PAYPALISHIRING", 1) + "'");

        System.out.println("Expected: 'PAHNAPLSIIGYIR' Actual: '" + convert("PAYPALISHIRING", 3) + "'");
        System.out.println("Expected: 'PINALSIGYAHRPI' Actual: '" + convert("PAYPALISHIRING", 4) + "'");
        System.out.println("Expected: 'PYAIHRNAPLSIIG' Actual: '" + convert("PAYPALISHIRING", 2) + "'");
        System.out.println("Expected: 'PAYPALISHIRING' Actual: '" + convert("PAYPALISHIRING", 1) + "'");
    }

    /**
     * It's NOT A SOLUTION!
     * Implementation decodes encoded string!
     * For example, converts YOKUBNURYDRIA to YURIYBONDARUK
     */
    public static String decode(String s, int numRows) {

        char[] chars = s.toCharArray();

        /*
        Split input into buckets
           0      1       2
        Y . . | O . . | K . .
        U . B | N . U | . . .
        R Y . | D R . | . . .
        I . . | A . . | . . .
         */
        int numCharsInBucket = numRows + (numRows - 2);
        int numOfBuckets = (int)Math.ceil(chars.length / (double) numCharsInBucket);
        int colsPerBucket = numRows - 1;
        char[][] matrix = new char[numRows][colsPerBucket * numOfBuckets];

        for (int i = 0; i < chars.length; i++) {
            int rowNumber;
            int colNumber;
            if (i < colsPerBucket) {
                rowNumber = 0;
                colNumber = i * colsPerBucket;
            } else {
                rowNumber = (i - colsPerBucket) / numRows + 1;
                int idxN = (i - colsPerBucket) % numRows;
                if (rowNumber == numRows - 1) {
                    colNumber = idxN * colsPerBucket;
                } else {
                    if (idxN % 2 == 0) {
                        colNumber = colsPerBucket * (idxN / 2);
                    } else {
                        colNumber = colsPerBucket * (idxN / 2) + (numRows - rowNumber - 1);
                    }
                }
            }

//            System.out.println("[" + rowNumber + "][" + colNumber + "] = " + chars[i]);

            matrix[rowNumber][colNumber] = chars[i];
        }

        System.out.println("----------------");
        print(matrix);
        System.out.println("----------------");
        int iStep = 1;
        int jStep = 0;
        int c = 0;
        int i = 0;
        int j = 0;

        char[] converted = new char[chars.length];
        while(j < matrix[i].length && matrix[i][j] != 0) {
            System.out.println("[" + i + "][" + j + "] = " + matrix[i][j]);
            if (matrix[i][j] != 0) {
                converted[c++] = matrix[i][j];
            }
            if (i == numRows - 1) {
                iStep = -1;
                jStep = 1;
            }
            if (i == 0) {
                iStep = 1;
                jStep = 0;
            }
            i += iStep;
            j += jStep;
        }


        return new String(converted);
    }

    /**
     * Solution of the actual problem
     */
    private static String encode(String s, int numRows) {

        if (numRows <= 1) return s;

        char[] chars = s.toCharArray();
        int numCharsInBucket = numRows + (numRows - 2);
        int numOfBuckets = (int)Math.ceil(chars.length / (double) numCharsInBucket);
        int colsPerBucket = numRows - 1;
        char[][] matrix = new char[numRows][colsPerBucket * numOfBuckets];
        int iStep = 1;
        int jStep = 0;
        int c = 0;
        int i = 0;
        int j = 0;
        while(c < chars.length) {
            matrix[i][j] = chars[c++];
            System.out.println("[" + i + "][" + j + "] = " + matrix[i][j]);
            if (i == numRows - 1) {
                iStep = -1;
                jStep = 1;
            }
            if (i == 0) {
                iStep = 1;
                jStep = 0;
            }
            i += iStep;
            j += jStep;
        }


        System.out.println("----------------");
        print(matrix);
        System.out.println("----------------");

        c = 0;
        char[] encoded = new char[chars.length];
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    encoded[c++] = matrix[i][j];
                }
            }
        }

        return new String(encoded);
    }

    /**
     * Optimal solution: Sort by Row
     *
     * = Intuition
     *
     * By iterating through the string from left to right, we can easily determine which row in the
     * Zig-Zag pattern that a character belongs to.
     *
     * = Algorithm
     *
     * We can use \text{min}( \text{numRows}, \text{len}(s))min(numRows,len(s)) lists to represent
     * the non-empty rows of the Zig-Zag Pattern.
     *
     * Iterate through ss from left to right, appending each character to the appropriate row.
     * The appropriate row can be tracked using two variables: the current row and the current direction.
     *
     * The current direction changes only when we moved up to the topmost row or moved down to the
     * bottommost row.
     *
     * = Complexity Analysis
     *
     * Time Complexity: O(n)O(n), where n == \text{len}(s)n==len(s)
     * Space Complexity: O(n)O(n)
     */
    private static String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    private static void print(char[][] decoded) {
        System.out.println("-----------------");
        for (char[] chars : decoded) {
            System.out.println(Arrays.toString(chars));
        }
    }
}
