package com.bondyk.ctci;


import java.util.Arrays;
import java.util.Random;

/**
 * Write a method to shuffle a deck of cards. It must be a perfect shuffle-in other words, each
  of the 52! permutations of the deck has to be equally likely. Assume that you are given a random
 number generator which is perfect.
 */
public class Shuffle {

    private static final Random rand = new Random();


    public static void main(String[] args) {
        System.out.println(Arrays.toString(shuffle(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0})));
        System.out.println(Arrays.toString(shuffle(new int[]{1, 1, 2, 2, 3, 3, 4, 4})));
    }

    private static int[] shuffle(int[] deck) {
        if (deck.length == 0) return deck;

        /*

        https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle

        Suppose we had a method shuffle ( ... ) that worked on n - 1 elements. Could we use this to shuffle n elements?
        Sure. In fact, that's quite easy. We would first shuffle the first n - 1 elements. Then, we would take the nth
        element and randomly swap it with an element in the array. That's it!
         */
        shuffle(deck, deck.length - 1);
        return deck;
    }

    private static void shuffle(int[] deck, int to) {

        int idx = rand.nextInt(to + 1);
        int tmp = deck[idx];
        deck[idx] = deck[to];
        deck[to] = tmp;

        if (to > 1) {
            shuffle(deck, to - 1);
        }
    }
}
