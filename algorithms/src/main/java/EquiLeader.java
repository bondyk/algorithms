import java.util.Date;
import java.util.Stack;

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
