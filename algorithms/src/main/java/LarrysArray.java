import java.util.Arrays;

/**
 * Larry has been given a permutation of a sequence of natural numbers incrementing from 1 as an array.
 * He must determine whether the array can be sorted using the following operation any number of times:
 * ABC -> BCA -> CAB -> ABC
 * Choose any 3 consecutive indices and rotate their elements in such a way that .
 * For example, if A = {1, 6, 5, 2, 4, 3}:
 *
 * A		        rotate
 * [1,6,5,2,4,3]	[6,5,2]
 * [1,5,2,6,4,3]	[5,2,6]
 * [1,2,6,5,4,3]	[5,4,3]
 * [1,2,6,3,5,4]	[6,3,5]
 * [1,2,3,5,6,4]	[5,6,4]
 * [1,2,3,4,5,6]
 *
 * YES
 * On a new line for each test case, print YES if A can be fully sorted. Otherwise, print NO.
 */
public class LarrysArray {

    public static void main(String[] args) {
        System.out.println(larrysArray(new int[]{1, 3, 4, 2}));// YES
        System.out.println(larrysArray(new int[]{1, 6, 5, 2, 4, 3}));// YES
        System.out.println(larrysArray(new int[]{1, 2, 3, 5, 4}));// NO
        System.out.println(larrysArray(new int[]{3, 1, 2}));// YES
    }

    /**
     * The formula in the link https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html is:
     *
     * ( (grid width odd) && (#inversions even) )
     * || ( (grid width even) && ((blank on odd row from bottom) == (#inversions even)) )
     *
     * Note how the width is a factor but not the height.
     *
     * The 3 consecutive indices rotation can be converted to a rectangle with width 3 and any height h.
     * Moving a tile up or down means rotating three consecutive indices in either direction.
     *
     * The given array can be then compared to a rectangle of width 3, and any height h,
     * so that total number of tiles is (3*h)-1.(Like it was (n*n)-1 in the original 15 tile puzzle)
     *
     * The question remains now is how to fit any number into 3*h-1, eg:n=15.
     * The next number after 15 that satisfies the form 3*h-1 is 17.
     * It does not make a difference if the permutation of a sequence of natural numbers incrementing
     * from 1 to 15 is extended to 17.Why? Because now the extended problem has 16 and 17 in the right places,
     * and cannot be involved in rotation anyways.It is as if the original array was a permutation
     * of first 17 natural numbers, where 16 and 17 were in the right place. Same goes for n=16.
     * Add 17 at the end,and assume as if the original array was a permutation of first 17 natural numbers,
     * where 17 was in the right place.
     *
     * So now, the first part of the formula: (grid width odd) && (#inversions even)
     */
    static String larrysArray(int[] A) {

        int inversionsCount = 0;
        for (int i = 1; i < A.length; i++) {
            int j = i;
            while(j > 0 && A[j] < A[j - 1]) {
                inversionsCount++;

                int tmp = A[j];
                A[j] = A[j - 1];
                A[j - 1] = tmp;
                j--;
            }
        }

        //width is 3, so check if inversionsCount is even
        return inversionsCount % 2 == 0 ? "YES" : "NO";
    }

}
