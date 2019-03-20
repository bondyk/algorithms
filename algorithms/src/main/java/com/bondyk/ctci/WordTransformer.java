package com.bondyk.ctci;


import java.util.HashMap;
import java.util.Map;

/**
 * Given two words of equal length that are in a dictionary, write a method to
 transform one word into another word by changing only one letter at a time. The new word you get
 in each step must be in the dictionary.
 EXAMPLE
 Input: DAMP, LIKE
 Output: DAMP-> LAMP-> LIMP-> LIME-> LIKE
 */
public class WordTransformer {

    public static void main(String[] args) {
        System.out.println(transform(new String[] {"DAMP", "LAMP", "LIMP", "LIME", "LIKE", "LITE"}, "DAMP", "LIKE"));
        System.out.println("--------------------------");
        System.out.println(transform(new String[] {"DAMP", "LAMP", "LIMP", "RAMP", "LIME", "LIKE", "ROME", "ROMP"}, "DAMP", "ROME"));
    }

    public static String transform(String[] dict, String input, String target) {
        Trie trie = new Trie(dict);
        char[] inputChars = input.toCharArray();
        char[] targetChars = target.toCharArray();

        if (inputChars.length != target.length()) {
            throw new IllegalArgumentException();
        }

        Letter letter = trie;
        for (int i = 0; i < inputChars.length; i++) {
            char iChar = inputChars[i];
            char tChar = targetChars[i];
            if (iChar != tChar) {
                int replacedCharIdx = findReplacementIdx(i, inputChars, targetChars, letter);
                if (replacedCharIdx == -1) return null;
                inputChars[replacedCharIdx] = targetChars[replacedCharIdx];
                if (replacedCharIdx == i) {
                    letter = letter.children.get(tChar);
                } else {
                    i--;
                }
            } else {
                letter = letter.children.get(tChar);
            }

            System.out.println(new String(inputChars));
        }
        return new String(inputChars);
    }

    private static int findReplacementIdx(int idx, char[] input, char[] target, Letter letter) {
        if (target[idx] != input[idx] && letter.children.containsKey(target[idx]) && isCompleteWord(idx + 1, input, letter.children.get(target[idx]))) {
            return idx;
        }

        if (idx < target.length - 1) {
            return findReplacementIdx(idx + 1, input, target, letter.children.get(input[idx]));

        }
        return -1;

    }

    private static boolean isCompleteWord(int idx, char[] input, Letter letter) {
        while (idx < input.length) {
            letter = letter.children.get(input[idx++]);
            if (letter == null) return false;
        }

        return letter.wordEnd;
    }

    private static class Trie extends Letter {

        public Trie(String... strings) {
            super(false);
            for (String string : strings) {
                Map<Character, Letter> candidates = children;
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
        protected Map<Character, Letter> children = new HashMap<>();

        public Letter(boolean wordEnd) {
            this.wordEnd = wordEnd;
        }
    }
}
