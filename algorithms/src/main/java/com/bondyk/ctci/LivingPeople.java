package com.bondyk.ctci;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a list of people with their birth and death years, implement a method to
 compute the year with the most number of people alive. You may assume that all people were born
 between 1900 and 2000 (inclusive). If a person was alive during any portion of that year, they should
 be included in that year's count. For example, Person (birth= 1908, death= 1909) is included in the
 counts for both 1908 and 1909unt. For example, Person (birth= 1908, death= 1909) is included in the
 counts for both 1908 and 1909
 */
public class LivingPeople {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person(1900, 1943));
        people.add(new Person(1904, 1980));
        people.add(new Person(1949, 1967));
        people.add(new Person(1936, 1984));
        people.add(new Person(1987, 1992));
        people.add(new Person(1917, 1953));
        people.add(new Person(1901, 1980));
        people.add(new Person(1922, 1999));
        people.add(new Person(1901, 1980));
        people.add(new Person(1921, 1934));
        people.add(new Person(1974, 1993));
        people.add(new Person(1933, 1941));
        System.out.println(findYearWithMostAlivePeopleFirstIdea(people));
        System.out.println("----------------------------------------------");
        System.out.println(findYearWithMostAlivePeopleOptimal(people));
    }



    private static int findYearWithMostAlivePeopleOptimal(List<Person> people) {
        int[] years = new int[101];
        for (Person person : people) {
            years[person.birth - 1900]++;
            years[person.death - 1900]--;
        }

        int maxYear = 0;
        int maxPopulation = 0;
        int population = 0;
        for (int i = 0; i < years.length; i++) {
            int persons = years[i];
            int year = 1900 + i;
            population += persons;
            if (population > maxPopulation) {
                maxYear = year;
                maxPopulation = population;
            }

            System.out.println("[" + year + "]=" + population);
        }

        return maxYear;
    }

    private static int findYearWithMostAlivePeopleFirstIdea(List<Person> people) {

        List<LifeEvent> events = new ArrayList<>(people.size() * 2);
        for (Person person : people) {
            events.add(new LifeEvent(person.birth, true));
            events.add(new LifeEvent(person.death, false));
        }

        Collections.sort(events);

        int maxAlive = 0;
        int year = 0;
        int alive = 0;
        int lastYear = year;
        for (LifeEvent event : events) {
            if (lastYear != year && maxAlive < alive) {
                maxAlive = alive;
                year = lastYear;
            }

            if (event.birth) {
                alive++;
            } else {
                alive--;
            }

            System.out.println("[" + event.year + "] = " + alive);

            lastYear = event.year;
        }

        if (maxAlive < alive) {
            year = lastYear;
        }

        return year;

    }

    private static class Person {
        private final int birth;
        private final int death;

        private Person(int birth, int death) {
            this.birth = birth;
            this.death = death;
        }
    }

    private static class LifeEvent implements Comparable<LifeEvent> {
        private final int year;
        private final boolean birth;

        private LifeEvent(int year, boolean birth) {
            this.year = year;
            this.birth = birth;
        }


        @Override
        public int compareTo(LifeEvent o) {
            return Integer.compare(year, o.year);
        }
    }
}
