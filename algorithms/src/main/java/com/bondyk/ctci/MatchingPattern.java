package com.bondyk.ctci;

/**
 * You are given two strings, pattern and value. The pattern string consists of
 just the letters a and b, describing a pattern within a string. For example, the string catcatgocatgo
 matches the pattern aabab (where cat is a and go is b). It also matches patterns like a, ab, and b.
 Write a method to determine if value matches pattern.
 */
public class MatchingPattern {

    public static void main(String[] args) {
        System.out.println(matches("aabab", "catcatgocatgo"));
        System.out.println(matches("ab", "catcatgocatgo"));
        System.out.println(matches("ba", "catcatgocatgo"));
        System.out.println(matches("a", "catcatgocatgo"));
        System.out.println(matches("b", "catcatgocatgo"));
        System.out.println(matches("abbab", "catcatgocatgo"));
        System.out.println(matches("bbbab", "catcatgocatgo"));
        System.out.println(matches("bbaba", "catcatgocatgo"));
        System.out.println(matches("abb", "catcatgocatgo"));
    }

    private static boolean matches(String pattern, String value) {
        int a = 0;
        int b = 0;

        char[] charArray = pattern.toCharArray();
        boolean invertPattern = charArray.length > 0 && charArray[0] == 'a';
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (invertPattern) {
                if (c == 'a') a++;
                else if (c == 'b') b++;
            } else if (c == 'a') {
                charArray[i] = 'b';
                b++;
            } else if (c == 'b') {
                charArray[i] = 'a';
                a++;
            }
        }

        StringBuilder candidate = new StringBuilder();
        for (char c : value.toCharArray()) {
            candidate.append(c);
            if (matchesCharsCount(candidate, a, b, charArray, value.toCharArray())) {
                return true;
            }
        }

        return false;
    }

    private static boolean matchesCharsCount(StringBuilder candidateA, int aRepetitions, int bRepetitions, char[] pattern, char[] value) {
        int candidateASize = candidateA.length();
        int totalCountOfA = candidateASize * aRepetitions;
        int totalCountOfB = value.length - totalCountOfA;
        if (totalCountOfB < 0 || bRepetitions > 0 && totalCountOfB % bRepetitions != 0) {
            return false;
        }

        StringBuilder candidateB = null;
        int candidateBSize = bRepetitions > 0 ? (totalCountOfB / bRepetitions) : 0;
        int idx = 0;
        for (char patternSymbol : pattern) {
            if (patternSymbol == 'a') {
                for (int i = idx; i < idx + candidateASize; i++) {
                    if (value[i] != candidateA.charAt(i - idx)) {
                        return false;
                    }
                }
                idx += candidateASize;
            } else if (patternSymbol == 'b') {
                boolean candidateBInit = candidateB == null;
                if (candidateBInit) {
                    candidateB = new StringBuilder(candidateBSize);
                }
                for (int i = idx; i < idx + candidateBSize; i++) {
                    if (candidateBInit) {
                        candidateB.append(value[i]);
                    } else if (value[i] != candidateB.charAt(i - idx)) {
                        return false;
                    }
                }
                idx += candidateBSize;
            }
        }

        return true;
    }
}
