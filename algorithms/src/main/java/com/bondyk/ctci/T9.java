package com.bondyk.ctci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * On old cell phones, users typed on a numeric keypad and the phone would provide a list of words
 that matched these numbers. Each digit mapped to a set of O - 4 letters. Implement an algorithm
 to return a list of matching words, given a sequence of digits. You are provided a list of valid words
 (provided in whatever data structure you'd like). The mapping is shown in the diagram below:
 1
 4
 ghi
 7
 pqrs
 2
 abc
 5
 jkl
 8
 tuv
 3
 def
 6
 mno
 9
 wxyz
 0

 EXAMPLE
 Input: 8733
 Output: tree, used
 */
public class T9 {

    public static void main(String[] args) {

        Trie trie = new Trie("tree", "used", "trial", "avers", "sony", "yuriy", "development");
        System.out.println(getWords(trie, 8, 7, 3, 3));
        System.out.println(getWords(trie, 8, 7, 4, 2, 5));
        System.out.println(getWords(trie, 8, 7, 4, 2));
        System.out.println(getWords(trie, 2, 8, 3, 7, 7));
        System.out.println(getWords(trie, 2));
        System.out.println(getWords(trie, 3, 3, 8, 3, 5, 6, 7, 6, 3, 6, 8));
        System.out.println(getWords(trie));


    }

    /**
     * Most Optimal (Not implemented here!!!)
     This problem is asking us to list all the words represented by a particular number in T9. Instead of trying to
     do this "on the fly" (and going through a lot of possibilities, many of which won't actually work), we can just
     do this in advance.
     Our algorithm now has a few steps:
     Pre-Computation:
     1. Create a hash table that maps from a sequence of digits to a list of strings.
     2. Go through each word in the dictionary and decode it to its T9 representation (e.g., APPLE - > 27753).
     Store each of these in the above hash table. For example, 8733 would map to {used, tree}.
     */

    private static Set<String> getWords(Trie trie, int... keyPresses) {
        if (keyPresses.length == 0) return Collections.emptySet();
        Map<Character, Letter> candidates = trie.letters;
        List<List<Character>> result = new ArrayList<>();
        process(keyPresses, 0, candidates, result);
        Set<String> words = new HashSet<>();
        for (List<Character> characters : result) {
            char[] wordChars = new char[characters.size()];
            for (int i = 0; i < characters.size(); i++) {
                wordChars[i] = characters.get(i);
            }
            words.add(new String(wordChars));
        }

        return words;
    }

    private static List<List<Character>> process(int[] keyPresses, int pressIdx, Map<Character, Letter> lettersMap, List<List<Character>> result) {

        List<List<Character>> changedWords = new ArrayList<>();
        for (char ch : getCandidates(keyPresses[pressIdx])) {
            if (lettersMap.containsKey(ch)) {
                Letter l = lettersMap.get(ch);
                if (l != null) {
                    if (pressIdx < keyPresses.length - 1) {
                        List<List<Character>> words = process(keyPresses, pressIdx + 1, l.children, result);
                        for (List<Character> chars : words) {
                            chars.add(0, ch);
                            changedWords.add(chars);
                        }
                    } else if (l.wordEnd) {
                        List<Character> word = new LinkedList<>();
                        word.add(ch);
                        result.add(word);
                        changedWords.add(word);
                    }
                }
            }
        }

        return changedWords;
    }

    private static char[] getCandidates(int keyCode) {
        switch (keyCode) {
            case 2: return new char[]{'a', 'b', 'c'};
            case 3: return new char[]{'d', 'e', 'f'};
            case 4: return new char[]{'g', 'h', 'i'};
            case 5: return new char[]{'j', 'k', 'l'};
            case 6: return new char[]{'m', 'n', 'o'};
            case 7: return new char[]{'p', 'q', 'r', 's'};
            case 8: return new char[]{'t', 'u', 'v'};
            case 9: return new char[]{'w', 'x', 'y', 'z'};
            default: return new char[0];
        }
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
