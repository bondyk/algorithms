import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
public class WordLadderLength {
    public static void main(String[] args) {
        System.out.println(ladderLength("DAMP", "LIKE",
            Arrays.asList("DAMP", "LAMP", "LIMP", "LIME", "LIKE", "LITE"))); // 5

        System.out.println(ladderLength("DAMP", "ROME",
            Arrays.asList("DAMP", "LAMP", "LIMP", "RAMP", "LIME", "LIKE", "ROME", "ROMP"))); // 4
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // build a graph where each connected vertex differs for one letter only
        Set<String> dictionary = new HashSet<>(wordList);
        Queue<Elem> bfs = new LinkedList<>();
        bfs.offer(new Elem(beginWord, 1));
        dictionary.remove(beginWord);
        while(!bfs.isEmpty()) {
            Elem elem = bfs.poll();
            String word = elem.word;

            List<String> candidates = getCandidates(word, dictionary);
            for (String c : candidates) {
                if (c.equals(endWord)) {
                    return elem.pathLength + 1;
                }
                dictionary.remove(c);
                bfs.offer(new Elem(c, elem.pathLength + 1));
            }
        }

        return 0;
    }

    private static List<String> getCandidates(String w, Set<String> words) {
        // This method seems to be more efficient than checking all words if they differ for 1 char
        // for big dictionaries
        List<String> result = new LinkedList<>();
        for (int i = 0; i < w.length(); i++) {
            char[] chars = w.toCharArray();
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                chars[i] = ch;
                String candidate = new String(chars);
                if (words.contains(candidate)) {
                    result.add(candidate);
                }
            }
        }
        return result;
    }

    private static class Elem {
        String word;
        int pathLength;
        Elem(String word, int pathLength) {
            this.word = word;
            this.pathLength = pathLength;
        }
    }
}
