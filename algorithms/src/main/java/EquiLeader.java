import java.util.Date;
import java.util.Stack;

/**
 * The equilibrium index of a sequence is an index such that the sum of elements at lower indexes is equal to the sum of elements at higher indexes. For example, in a sequence A:
    A[0]=-7 A[1]=1 A[2]=5 A[3]=2 A[4]=-4 A[5]=3 A[6]=0
 3 is an equilibrium index, because:
    A[0]+A[1]+A[2]=A[4]+A[5]+A[6]
 6 is also an equilibrium index, because:
    A[0]+A[1]+A[2]+A[3]+A[4]+A[5]=0
 (The sum of zero elements is zero) 7 is not an equilibrium index - because it is not a valid index of sequence A.
 If you still have doubts, here is a precise definition:
    The integer k is an equilibrium index of a sequence A[0],A[1]..,A[n-1] if and only if 0<= k and sum(A[0..(k-1)])=sum(A[(k+1)..(n-1)]).
    Assume the sum of zero elements is equal to zero.

 Write a function
    int equi(int A[], int n)
 that, given a sequence, returns its equilibrium index (any) or -1 if no equilibrium index exists. Assume that the sequence may be very long.
 * */
public class EquiLeader {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{4, 3, 4, 4, 4, 2}));// 2
        System.out.println(solution(new int[]{4, 3, 4, 4, 1, 2, 3}));// 0
        System.out.println(solution(new int[]{4, 3, 4, 4, 4, 2, 3}));// 0
        System.out.println(solution(new int[]{4, 4, 4, 4, 4, 2, 3}));// 2
        System.out.println(solution(new int[]{4, 4, 4, 4, 4, 2, 3, 3, 4, 3, 4, 4}));// 8
        System.out.println(new Date(1427583600000L));
    }

    public static int solution(int[] A) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < A.length; i++) {
            int aA = A[i];
            if (!stack.isEmpty() && A[stack.peek()] != aA) {
                stack.pop();
            } else {
                stack.push(i);
            }
        }

        if (!stack.isEmpty()) {
            int result = 0;
            int candidate = stack.pop();
            int count = 0;
            for (int aA : A) {
                if (aA == A[candidate]) {
                    count++;
                }
            }

            if (count * 2 > A.length) {
                int countLeft = 0;
                int countRight;
                for (int i = 0; i < A.length; i++) {
                    if (A[i] == A[candidate]) {
                        countLeft++;
                    }
                    countRight = count - countLeft;
                    if (countLeft * 2 > (i + 1) && countRight * 2 > (A.length - (i + 1))) {
                        result++;
                    }
                }

                return result;
            }
        }

        return 0;
    }
}
