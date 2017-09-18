package com.bondyk.ctci;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array filled with letters and numbers, find the longest subarray with
 an equal number of letters and numbers.
 */
public class LettersAndNumbers {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getLongestSubarray(new char[] {'a', '1', '3', 'c', 'd', '2', '1', '3', '3', 'a', 'd', 'b', 'n'})));
        System.out.println(Arrays.toString(getLongestSubarray(new char[] {'a', 'b', 'c', 'd', 'e', '2', '1', '3', '3', 'f', '2', 'g', 'h'})));
        System.out.println(Arrays.toString(getLongestSubarray(new char[] {'1', 'b', 'c', 'd', 'e', '2', '1', 'v', 'a', 'f', 's', 'g', 'h'})));
        System.out.println(Arrays.toString(getLongestSubarray(new char[] {'1', 'b', 'c', 'd', 'e', '2', '1', '3', 'a', 'f', 's', 'g', 'h'})));
    }

    private static char[] getLongestSubarray(char[] array) {
        //Use prefix sum. Treat all digits as +1 and letters as -1
        int prefixSum = (Character.isDigit(array[0]) ? 1 : -1);
        Map<Integer, Integer> prefixSums = new HashMap<>();
        prefixSums.put(prefixSum, 0);
        int from = -1;
        int to = -1;
        for (int i = 1; i < array.length; i++) {
            prefixSum += (Character.isDigit(array[i]) ? 1 : -1);
            if (prefixSum == 0) {
                //If sum is 0 then subarray is from the beginning to current index
                from = 0;
                to = i;
            } else if (prefixSums.containsKey(prefixSum)) {
                //Sum between elements in prefix-sum is: prefixSum = prefixSum[hi_idx] - prefixSum[low_idx] and first index is prefixSums['prefixSum'] + 1
                int fromCandidate = prefixSums.get(prefixSum) + 1;
                if (to - from < i - fromCandidate) {
                    to = i;
                    from = fromCandidate;
                }
            } else {
                prefixSums.put(prefixSum, i);
            }
        }

        return Arrays.copyOfRange(array, from, to + 1);
    }
}
