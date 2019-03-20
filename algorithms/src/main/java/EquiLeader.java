/**
 * The equilibrium index of a sequence is an index such that the sum of elements at lower indexes
 * is equal to the sum of elements at higher indexes. For example, in a sequence A:
    A[0]=-7 A[1]=1 A[2]=5 A[3]=2 A[4]=-4 A[5]=3 A[6]=0
 3 is an equilibrium index, because:
    A[0]+A[1]+A[2]=A[4]+A[5]+A[6]
 6 is also an equilibrium index, because:
    A[0]+A[1]+A[2]+A[3]+A[4]+A[5]=0
 (The sum of zero elements is zero) 7 is not an equilibrium index - because it is not a valid index of sequence A.
 If you still have doubts, here is a precise definition:
    The integer k is an equilibrium index of a sequence A[0],A[1]..,A[n-1] if and only if 0<= k
        and sum(A[0..(k-1)])=sum(A[(k+1)..(n-1)]).
    Assume the sum of zero elements is equal to zero.

 Write a function
    int equi(int A[], int n)
 that, given a sequence, returns its equilibrium index (any) or -1 if no equilibrium index exists.
    Assume that the sequence may be very long.
 * */
public class EquiLeader {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{-7, 1, 5, 2, -4, 3, 0}));// 3 or 6
    }

    public static int solution(int[] A) {

        //use prefix-sum

        for (int i = 1; i < A.length; i++) {
            A[i] = A[i - 1] + A[i];
        }

        for (int i = 0; i < A.length; i++) {
            int sumBefore = i == 0 ? 0 : A[i - 1];
            int sumAfter = i == A.length - 1 ? 0 : A[A.length - 1] - A[i];
            int equilibrium = i == 0 ? A[i] : A[i] - A[i - 1];
            if (sumAfter == sumBefore) {
                System.out.println("Equilibrium ["  +  i + "] = " + equilibrium);
                return i;
            }
        }

        return -1;
    }
}
