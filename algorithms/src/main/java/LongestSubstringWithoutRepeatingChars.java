import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingChars {

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(lengthOfLongestSubstring("bbbbb"));
//        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("powdhwkewtor"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

   /* public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charPos = new HashMap<>();
        char[] chars = s.toCharArray();
        int maxLength = 0;
        int rangeStart = 0;
        for (int i = 0; i < chars.length; i++) {
            if (charPos.containsKey(chars[i])) {
                maxLength = Math.max(maxLength, charPos.size());
                int lastCharIdx = charPos.get(chars[i]);
                for (int j = rangeStart; j <= lastCharIdx; j++) {
                    charPos.remove(chars[j]);
                }
                rangeStart = lastCharIdx + 1;
            }

            charPos.put(chars[i], i);
        }

        maxLength = Math.max(maxLength, charPos.size());

        return maxLength;
    }*/
}
