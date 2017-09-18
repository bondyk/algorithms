package com.bondyk.ctci;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Oh, no! You have accidentally removed all spaces, punctuation, and capitalization in a
 lengthy document. A sentence like "I reset the computer. It still didn't boot!"
 became "iresetthecomputeritstilldidntboot''. You'll deal with the punctuation and capi­
 talization later; right now you need to re-insert the spaces. Most of the words are in a dictionary but
 a few are not. Given a dictionary (a list of strings) and the document (a string), design an algorithm
 to unconcatenate the document in a way that minimizes the number of unrecognized characters.
 EXAMPLE
 Input
 jesslookedjustliketimherbrother
 Output: jess looked just like tim her brother (7 unrecognized characters)
 */
public class ReSpace {


    public static void main(String[] args) {
        reSpace("jesslookedjustliketimherbrother", "looked", "just", "like", "her", "brother");
        reSpace("jesslookedjustliketimherbrother", "looked", "just", "like", "her", "brother", "jess", "tim");
        reSpace("jesslookedjustliketimherbrother", "sister", "aunt");
        reSpace("jesslookedjustliketimherbrother", "jesslookedjustliketimherbrother");
        reSpace("jesslookedjustliketimherbrother");
    }

    private static String reSpace(String input, String... dict) {
        System.out.println("---------------------------------------");
        System.out.println("In: " + input);
        System.out.println("Dict: " + Arrays.toString(dict));
        Trie trie = new Trie(dict);
        char[] chars = input.toCharArray();
        Map<Character, Letter> letters = trie.letters;
        Letter lastLetter = null;
        StringBuilder sb = new StringBuilder();
        int unrecognizedCharsCount = 0;
        for (int i = 0; i < chars.length; i++) {
            int j = 0;
            while (i + j < chars.length && letters.containsKey(chars[i + j])) {
                lastLetter = letters.get(chars[i + j]);
                letters = lastLetter.children;
                j++;
            }

            if (lastLetter != null && lastLetter.wordEnd) {
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(' ');
                }
                sb.append(new String(Arrays.copyOfRange(chars, i, i + j)));
                sb.append(' ');
                i += (j - 1);
            } else {
                sb.append(chars[i]);
                unrecognizedCharsCount++;
            }
            lastLetter = null;
            letters = trie.letters;
        }

        System.out.println("Out: " + sb);
        System.out.println("Unrecognized chars count: " + unrecognizedCharsCount);
        return sb.toString();
    }

    private static class Trie {
        private Map<Character, Letter> letters = new HashMap<>();

        public Trie(String... strings) {
            for (String string : strings) {
                Map<Character, Letter> candidates = letters;
                char[] charArray = string.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    char c = charArray[i];
                    Letter l = new Letter(i == charArray.length - 1);
                    if (!candidates.containsKey(c)) {
                        candidates.put(c, l);
                        candidates = l.children;
                    } else {
                        candidates = candidates.get(c).children;
                    }

                }

            }

        }
    }

    private static class Letter {
        private final boolean wordEnd;
        private Map<Character, Letter> children = new HashMap<>();

        public Letter(boolean wordEnd) {
            this.wordEnd = wordEnd;
        }
    }
}
