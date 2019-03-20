import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words of equal length that are in a dictionary, write a method to
 transform one word into another word by changing only one letter at a time. The new word you get
 in each step must be in the dictionary.
 EXAMPLE
 Input: DAMP, LIKE
 Output: DAMP-> LAMP-> LIMP-> LIME-> LIKE
 See also WordTransformer
 */
public class WordLadder {
    public static void main(String[] args) {
        transform(new String[] {"DAMP", "LAMP", "LIMP", "LIME", "LIKE", "LITE"}, "DAMP", "LIKE");
        System.out.println("--------------------------");
        transform(new String[] {"DAMP", "LAMP", "LIMP", "RAMP", "LIME", "LIKE", "ROME", "ROMP"}, "DAMP", "ROME");
    }

    public static void transform(String[] dict, String input, String target) {
        // build a graph where each connected vertex differs for one letter only
        Set<String> dictionary = new HashSet<>(Arrays.asList(dict));
        Queue<String> bfs = new LinkedList<>();
        bfs.offer(input);
        dictionary.remove(input);
        while(!bfs.isEmpty()) {
            String word = bfs.poll();
            System.out.println(word);// incorrect way of printing!!

            for (Iterator<String> iterator = dictionary.iterator(); iterator.hasNext(); ) {
                final String s = iterator.next();
                if (isOneLetterDiff(word, s)) {
                    if (s.equals(target)) {
                        System.out.println("*" + s + "*");
                        return;
                    }
                    bfs.offer(s);
                    iterator.remove();
                }
            }
        }
    }

    private static boolean isOneLetterDiff(String w, String candidate) {
        int changes = 0;
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) != candidate.charAt(i)) {
                changes++;
            }

            if (changes > 1) return false;
        }

        return changes == 1;
    }
}
