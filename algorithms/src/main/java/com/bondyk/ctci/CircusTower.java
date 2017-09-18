package com.bondyk.ctci;


import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * A circus is designing a tower routine consisting of people standing atop one another's
 shoulders. For practical and aesthetic reasons, each person must be both shorter and lighter than
 the person below him or her. Given the heights and weights of each person in the circus, write a
 method to compute the largest possible number of people in such a tower
 */
public class CircusTower {

    public static void main(String[] args) {
        printSolution(new Man[] {
                new Man(6, 8),
                new Man(10, 14),
                new Man(11, 11),
                new Man(9, 11),
                new Man(8, 10),
                new Man(13, 13),
                new Man(12, 11),
                new Man(7, 9),
                new Man(15, 16),
                new Man(10, 10),
                new Man(17, 17),
                new Man(17, 17),
                new Man(14, 15),
                new Man(10, 4),
                new Man(10, 18),
                new Man(10, 6),
                new Man(10, 12),
        });
    }

    private static void printSolution(Man[] men) {
        System.out.println("-------------- SOLUTION #1 -----------");
        System.out.println(increasingSequence1(Arrays.copyOf(men, men.length)));
        System.out.println("------ SOLUTION #2 (INCORRECT) -------");
        System.out.println(increasingSequence2(Arrays.copyOf(men, men.length)));
    }

    private static int increasingSequence1(Man[] men) {
        if (men.length <= 1) return men.length;

        //sort by 1)weight, 2)height
        Arrays.sort(men, (o1, o2) -> {
            int cmp = Integer.compare(o1.weight, o2.weight);
            if (cmp == 0) cmp = Integer.compare(o1.height, o2.height);
            return cmp;
        });

        System.out.println(Arrays.toString(men));

        int count = 1;
        Man previousMan = men[0];

        System.out.println("--------------");
        System.out.print(previousMan);

        for (int i = 1; i < men.length; i++) {
            Man man = men[i];
            if (man.weight > previousMan.weight && man.height > previousMan.height) {
                count++;
                System.out.print(man);
                previousMan = man;
            }
        }

        return count;
    }

    /**
     * This solution DOESN'T WORK!!!!!!!!
     * @param men
     * @return
     */
    private static int increasingSequence2(Man[] men) {
        if (men.length <= 1) return men.length;

        Arrays.sort(men, (o1, o2) -> {
            if (o1.weight > o2.weight && o1.height > o2.height) return 1;
            if (o1.weight < o2.weight && o1.height < o2.height) return -1;
            return 0;
        });

        System.out.println(Arrays.toString(men));

        int count = 1;

        System.out.println("--------------");
        System.out.print(men[0]);

        int previousHeight = men[0].height;
        for (int i = 1; i < men.length; i++) {
            Man man = men[i];
            if (man.height > previousHeight) {
                count++;
                System.out.print(man);
                previousHeight = man.height;
            }
        }

        return count;
    }


    private static class Man {
        final int weight;
        final int height;

        private Man(int weight, int height) {
            this.weight = weight;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Man{" +
                    "weight=" + weight +
                    ", height=" + height +
                    "}\n";
        }
    }
}
