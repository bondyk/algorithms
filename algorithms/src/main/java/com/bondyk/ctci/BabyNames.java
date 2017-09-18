package com.bondyk.ctci;


import java.util.*;

/**
 * Each year, the government releases a list of the 10,000 most common baby names
 and their frequencies (the number of babies with that name). The only problem with this is that
 some names have multiple spellings. For example, "John" and ''.Jon" are essentially the same name
 but would be listed separately in the list. Given two lists, one of names/frequencies and the other
 of pairs of equivalent names, write an algorithm to print a new list of the true frequency of each
 name. Note that if John and Jon are synonyms, and Jon and Johnny are synonyms, then John and
 Johnny are synonyms. (It is both transitive and symmetric.) In the final list, any name can be used
 as the "real" name.
 EXAMPLE
 Input:
 Names: John (15), Jon (12), Chris (13), Kris (4), Christopher (19)
 Synonyms: (Jon, John), (John, Johnny), (Chris, Kris), (Chris, Christopher)
 Output: John (27), Kris (36)
 */
public class BabyNames {

    public static void main(String[] args) {
        Map<String, Integer> names = new HashMap<>();
        names.put("John", 15);
        names.put("Jon", 12);
        names.put("Chris", 13);
        names.put("Kris", 4);
        names.put("Christopher", 19);

        List<Pair> aliases = new ArrayList<>();
        aliases.add(new Pair("Jon", "John"));
        aliases.add(new Pair("John", "Johnny"));
        aliases.add(new Pair("Chris", "Kris"));
        aliases.add(new Pair("Chris", "Christopher"));
        System.out.println(groupStatistics(names, aliases));
    }

    private static Map<String, Integer> groupStatistics(Map<String, Integer> names, List<Pair> aliases) {
        Map<String, Name> nameGroups = new HashMap<>();
        for (Pair entry : aliases) {
            String alias1 = entry.left;
            String alias2 = entry.right;
            Name group;
            if (nameGroups.containsKey(alias1)) {
                group = nameGroups.get(alias1);
            } else if (nameGroups.containsKey(alias2)) {
                group = nameGroups.get(alias2);
            } else {
                group = new Name();
            }

            group.registerAlias(alias1);
            group.registerAlias(alias2);
            nameGroups.put(alias1, group);
            nameGroups.put(alias2, group);
        }

        for (Map.Entry<String, Integer> entry : names.entrySet()) {
            String name = entry.getKey();
            Integer value = entry.getValue();
            Name nameAlternatives = nameGroups.get(name);
            nameAlternatives.addCount(value);
        }

        Map<String, Integer> result = new HashMap<>();
        for (Name name : new HashSet<>(nameGroups.values())) {
            result.put(name.primary, name.count);
        }

        return result;
    }

    private static class Name {
        private Set<String> aliases = new HashSet<>();
        private int count;
        private String primary;

        private void registerAlias(String alias) {
            if (aliases.isEmpty()) {
                primary = alias;
            }
            aliases.add(alias);
        }

        private void addCount(int count) {
            this.count += count;
        }

    }

    public static class Pair {

        private final String left;
        private final String right;

        Pair(String left, String right) {
            this.left = left;
            this.right = right;
        }
    }
}
