package com.bondyk.ctci;

import java.util.*;

/**
 * Write a method to sort an array of strings so that all the anagrams are next to each other.
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strings = {"cort", "aloc", "mers", "rast", "troc", "tsar", "cola", "eureka", "star"};

        System.out.println(Arrays.toString(groupAnagrams(strings)));
        System.out.println(Arrays.toString(groupAnagrams1(strings)));
    }

    private static String[] groupAnagrams(String[] input) {
        Arrays.sort(input, (s1, s2) -> {
            char[] ch1 = s1.toCharArray();
            char[] ch2 = s2.toCharArray();
            Arrays.sort(ch1);
            Arrays.sort(ch2);
            return new String(ch1).compareTo(new String(ch2));
        });
        return input;
    }

    private static String[] groupAnagrams1(String[] input) {
        Map<String, List<String>> listMap = new HashMap<>();
        for (String s : input) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sortedChars = new String(chars);
            if (!listMap.containsKey(sortedChars)) {
                listMap.put(sortedChars, new ArrayList<>());
            }
            listMap.get(sortedChars).add(s);
        }

        int i = 0;
        for (List<String> entry : listMap.values()) {
            for (String s : entry) {
                input[i++] = s;
            }
        }

        return input;
    }
}
