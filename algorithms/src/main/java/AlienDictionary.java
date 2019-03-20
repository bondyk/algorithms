/**
 * Given a sorted dictionary of an alien language having N words and k starting alphabets
 * of standard dictionary the task is to complete the function which returns a string denoting
 * the order of characters in the language..
 *
 * Note: Many orders may be possible for a particular test case, thus you may return any valid order.
 *
 *
 * Examples:
 *
 * Input:  Dict[] = { "baa", "abcd", "abca", "cab", "cad" }, k = 4
 * Output: Function returns "bdac"
 * Here order of characters is 'b', 'd', 'a', 'c'
 * Note that words are sorted and in the given language "baa"
 * comes before "abcd", therefore 'b' is before 'a' in output.
 * Similarly we can find other orders.
 *
 * Input:  Dict[] = { "caa", "aaa", "aab" }, k = 3
 * Output: Function returns "cab"
 */
public class AlienDictionary {

    public static void main(String[] args) {
        System.out.println(solve(new String[] { "baa", "abcd", "abca", "cab", "cad" }, 4));
    }

    private static String solve(String[] dictionary, int k) {
        //FIXME
        char[] alphabet = new char[k];
        boolean anyChar = true;
        int aIdx = 0;
        int charIdx = 0;
        while (anyChar) {
            anyChar = false;
            for (int i = 0; i < dictionary.length; i++) {
                final String w = dictionary[i];
                if (w.length() > charIdx) {
                    char ch = w.charAt(charIdx);
                    if (indexOf(alphabet, ch) < 0) {
                        alphabet[aIdx++] = ch;
                    } //else if (charIdx > 0 && indexOf(alphabet, alphabet[charIdx - 1]) )
                    anyChar = true;
                }
            }
            charIdx++;
        }

        return new String(alphabet);
    }

    private static int indexOf(char[] chars, char ch) {
        for (int i = 0; i < chars.length; i++) {
            final char c = chars[i];
            if (c == ch) return i;
        }

        return -1;
    }
}
