package icake;


import java.util.Arrays;

/**
 *  You rank players in the game from highest to lowest score. So far you're using an algorithm that sorts in O(nlgn) time,
 *  but players are complaining that their rankings aren't updated fast enough. You need a faster sorting algorithm.

 Write a function that takes:

    - a list of unsorted_scores
    - the highest_possible_score in the game

 and returns a sorted list of scores in less than O(nlgn)O(n\lg{n})O(nlgn) time.

 For example:

 unsorted_scores = [37, 89, 41, 65, 91, 53]
 HIGHEST_POSSIBLE_SCORE = 100

 sort_scores(unsorted_scores, HIGHEST_POSSIBLE_SCORE)
 // returns [37, 41, 53, 65, 89, 91]

 We’re defining nnn as the number of unsorted_scores because we’re expecting the number of players to keep climbing.

 And we'll treat highest_possible_score as a constant instead of factoring it into our big O time and space costs,
 because the highest possible score isn’t going to change. Even if we do redesign the game a little,
 the scores will stay around the same order of magnitude.
 */

public class AngryBirdsScoreSorting {


    public static void main(String[] args) {

        System.out.println(Arrays.toString(sortScores(new int[] {37, 89, 41, 65, 91, 53, 41, 89, 99}, 100)));
    }

    private static int[] sortScores(int[] unsortedScores, int maxScore) {
        int[] sortingArray = new int[maxScore + 1];

        for (int unsortedScore : unsortedScores) {
            sortingArray[unsortedScore]++;
        }

        int[] sortedArray = new int[unsortedScores.length];
        int k = 0;
        for (int i = 0; i < sortingArray.length; i++) {
            for (int j = 0; j < sortingArray[i]; j++) {
                sortedArray[k++] = i;
            }
        }

        return sortedArray;
    }
}
