import java.util.ArrayList;
import java.util.List;

/**
 * You have an array of logs.  Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.
 * It is guaranteed that each log has at least one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.
 * The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
 * The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 * Example 1:
 *
 * Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 */
public class ReorderLogFiles {

    public static void main(String[] args) {
        String[] input = new String[] {
            "a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"
        };
        String[] result = reorderLogFiles(input);

        for (String s : result) {
            System.out.println(s);
        }
    }

    public static String[] reorderLogFiles(String[] logs) {
        List<String> digitLogs = new ArrayList<>();
        List<String> letterLogs = new ArrayList<>();
        for (final String log : logs) {
            char[] chars = log.toCharArray();
            char[] destChars = new char[chars.length];
            int j = 0;
            int idLength = 0;
            //a1 9 2 3 1
            while (chars[j] != ' ') {
                idLength++;
                j++;
            }
            if (Character.isDigit(chars[j + 1])) {
                digitLogs.add(log);
            } else {
                destChars[chars.length - 1 - j] = chars[j];
                for (int k = 0; k < j; k++) {
                    destChars[k + (chars.length - j)] = chars[k];
                }
                for (int k = j + 1; k < chars.length; k++) {
                    destChars[k - idLength - 1] = chars[k];
                }
                letterLogs.add(new String(destChars));
                System.out.println(new String(destChars));
            }
        }

        System.out.println("--");
        letterLogs.sort(String::compareTo);

        String[] sortedLogs = new String[logs.length];
        for (int i = 0; i < letterLogs.size(); i++) {
            String letterLog = letterLogs.get(i);
            char[] letterLogChars = letterLog.toCharArray();
            char[] chars = new char[letterLog.length()];
            int j = letterLogChars.length - 1;
            while(letterLogChars[j] != ' ') {
                j--;
            }

            //9 2 3 1 a1
            for (int k = j + 1; k < letterLogChars.length; k++) {
                chars[k - j - 1] = letterLogChars[k];
            }
            chars[chars.length - 1 - j] = letterLogChars[j];

            for (int k = 0; k < j; k++) {
                chars[k + (letterLogChars.length - j)] = letterLogChars[k];
            }
            sortedLogs[i] = new String(chars);
            System.out.println(new String(chars));
        }
        for (int i = 0; i < digitLogs.size(); i++) {
            sortedLogs[letterLogs.size() + i] = digitLogs.get(i);
        }

        return sortedLogs;
    }
}
