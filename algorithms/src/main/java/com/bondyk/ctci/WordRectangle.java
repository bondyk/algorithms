package com.bondyk.ctci;

import java.util.*;
import java.util.stream.Collectors;

public class WordRectangle {
/**
 * Given a list of millions of words, design an algorithm to create the largest possible
 rectangle of letters such that every row forms a word (reading left to right) and every column forms
 a word (reading top to bottom). The words need not be chosen consecutively from the list, but all
 rows must be the same length and all columns must be the same height.
 */
    public static void main(String[] args) {

    }

    private static char[][] getWordRectangle(String... dictionary) {

//        Arrays.sort(dictionary, (o1, o2) -> -Integer.compare(o1.length(), o2.length()));

//        Trie trie = new Trie(dictionary);

        Map<Integer, List<String>> wordGroups = Arrays.stream(dictionary).collect(Collectors.groupingBy(String::length));
        SortedMap<Integer, Trie> triesMap = new TreeMap<>((o1, o2) -> -Integer.compare(o1, o2));

        for (Map.Entry<Integer, List<String>> entry : wordGroups.entrySet()) {
            int wordLength = entry.getKey();
            if (!triesMap.containsKey(wordLength)) {
                triesMap.put(wordLength, new Trie());
            }
            entry.getValue().forEach(triesMap.get(wordLength)::add);
        }

        for (Map.Entry<Integer, List<String>> entry : wordGroups.entrySet()) {
            int wordLength = entry.getKey();
            List<String> words = entry.getValue();
            for (String word : words) {
                char[][] rectangle = build(word, triesMap);
                if (rectangle != null) return rectangle;
            }
        }

        return null;
    }

    private static char[][] build(String word, Map<Integer, Trie> triesMap) {
        //Check if we have words that start with new prefixes
        char[] chars = word.toCharArray();
        List<Letter> charCandidates = new ArrayList<>();
        for (Map.Entry<Integer, Trie> entry : triesMap.entrySet()) {
            Letter letter = entry.getValue();

            charCandidates.clear();
            for (int i = 0; i < chars.length; i++) {

                //Check if there are words in dictionary that start on each char of the horizontal word
                if (!letter.children.containsKey(chars[i])) {
                    break;
                }
                //Collect all candidates that match a char
                Letter charCandidateLetter = letter.children.get(chars[i]);
                charCandidates.add(charCandidateLetter);
            }

            if (charCandidates.size() == chars.length) {
                //Found words for each char in horizontal word
                char[][] result = getHorizontalWords(charCandidates, triesMap.get(chars.length));
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    private static char[][] getHorizontalWords(List<Letter> letters, Letter dict) {
        //TODO: try all permutations (1) ser<vers> and 2)ser<vice>, then 1) ser<vice> and 2) ser<vers>)
        //TODO: and check if composed horizontal words exist in dictionary
        //TODO: if rectangle is built then calculate it's size and continue
        

        return null;
    }

    private static StringBuilder[] appendChars(StringBuilder sb, Letter letter) {
        if (letter != null && !letter.children.isEmpty()) {
            StringBuilder[] result = new StringBuilder[letter.children.size()];
            int i = 0;
            for (Map.Entry<Character, Letter> entry : letter.children.entrySet()) {
                result[i] = new StringBuilder(sb);
                result[i].append(entry.getKey());
                appendChars(result[i], entry.getValue());
                i++;
            }
            return result;
        }

        return new StringBuilder[0];
    }

    private static class Trie extends Letter {

        public Trie(String... strings) {
            super(false);
            for (String string : strings) {
                add(string);
            }
        }

        private void add(String string) {

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

    private static class Letter {
        private final boolean wordEnd;
        protected Map<Character, Letter> children = new HashMap<>();

        public Letter(boolean wordEnd) {
            this.wordEnd = wordEnd;
        }
    }
}
