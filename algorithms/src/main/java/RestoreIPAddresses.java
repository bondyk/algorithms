import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * Example:
 *
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIPAddresses {

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        restoreIpAddresses(s.toCharArray(), 0, new ArrayList<>(), result);
        return result;
    }

    private static void restoreIpAddresses(char[] chars, int i, List<String> groups, List<String> result) {
        if (groups.size() == 4) {
            if (i == chars.length) {
                result.add(String.join(".", groups));
            }
            return;
        }

        tryToRestore(chars, i, i, groups, result);
        tryToRestore(chars, i, i + 1, groups, result);
        tryToRestore(chars, i, i + 2, groups, result);
    }

    private static void tryToRestore(char[] chars, int start, int end, List<String> groups, List<String> result) {
        // Check if there is sufficient number of remaining chars left if we add chars[end - start] as a new group
        if (end < chars.length && isLengthPossible(chars.length, end + 1, groups.size() + 1)) {
            String n = new String(Arrays.copyOfRange(chars, start, end + 1));
            if (isValidNumber(n)) {
                groups.add(n);
                restoreIpAddresses(chars, end + 1, groups, result);
                groups.remove(groups.size() - 1);
            }
        }
    }

    private static boolean isLengthPossible(int totalChars, int usedChars, int usedGroupsCount) {
        int remainingChars = totalChars - usedChars;
        int remainingGroups = 4 - usedGroupsCount;
        return remainingChars >= remainingGroups && remainingChars <= remainingGroups * 3;
    }

    private static boolean isValidNumber(String num) {
        if (num.length() == 1) return true;
        if (num.length() == 2 && num.charAt(0) != '0') return true;
        if (num.length() == 3 && num.charAt(0) != '0' && Integer.parseInt(num) <= 255) return true;
        return false;
    }
}
