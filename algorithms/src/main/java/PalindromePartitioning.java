import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new LinkedList<>();
        addPalindromes(s.toCharArray(), 0, new LinkedList<>(), result);
        return result;
    }

    private static void addPalindromes(char[] chars, int from, List<String> path, List<List<String>> result) {
        if (from == chars.length) {
            result.add(new ArrayList<>(path));
        }
        for (int i = from; i < chars.length; i++) {
            if (isPalindrome(chars, from, i + 1)) {
                path.add(new String(Arrays.copyOfRange(chars, from, i + 1)));
                addPalindromes(chars, i + 1, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(char[] chars, int from, int to) {

        // System.out.println(from + " ; " + to + " -> " + ((to + from) / 2));
        for (int i = 0; i < (to - from) / 2; i++) {
            // System.out.println(chars[from + i] + " == " + chars[to - 1 - i]);
            if (chars[from + i] != chars[to - 1 - i]) return false;
        }

        // System.out.println("YES -> " + new String(Arrays.copyOfRange(chars, from, to)));
        return true;
    }
}
