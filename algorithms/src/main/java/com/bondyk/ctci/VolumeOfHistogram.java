package com.bondyk.ctci;

/**
 * Imagine a histogram (bar graph). Design an algorithm to compute the
 volume of water it could hold if someone poured water across the top. You can assume that each
 histogram bar has width 1.
 EXAMPLE
 input{0, 0 , 4, 0, 0, 6, 0, 0, 3, 0, 5, 0 , 1, 0, 0, 0}
 Output:26
 */
//TrappingWater -> www.geeksforgeeks.org/trapping-rain-water/
public class VolumeOfHistogram {

    public static void main(String[] args) {
        System.out.println(getVolume(new int[] {0, 0 , 4, 0, 0, 6, 0, 0, 3, 0, 5, 0 , 1, 0, 0, 0})); //26
        System.out.println(getVolume(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); //6
        System.out.println(getVolume(new int[] {3, 0, 0, 2, 0, 4})); //10
    }

    private static int getVolume(int[] input) {

        int[] leftToRight = new int[input.length];
        int[] rightToLeft = new int[input.length];

        for (int i = 0, j = input.length - 1; i < input.length; i++, j--) {
            leftToRight[i] = i == 0 ? input[i] : Math.max(leftToRight[i - 1], input[i]);
            rightToLeft[j] = j == input.length - 1 ? input[j] : Math.max(rightToLeft[j + 1], input[j]);
        }

//        System.out.println(Arrays.toString(leftToRight));
//        System.out.println(Arrays.toString(rightToLeft));

        int volume = 0;
        for (int i = 0; i < input.length; i++) {
            int water = Math.min(leftToRight[i], rightToLeft[i]) - input[i];
            volume += water;
        }

        return volume;
    }
}
