import java.util.Arrays;
import java.util.Stack;

/**
 * Created by y.bondaruk on 24.09.2014.
 */
public class Dominator {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 4, 3, 2, 3, -1, 3, 3}));
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
            int candidate = stack.pop();
            int count = 0;
            for (int aA : A) {
                if (aA == A[candidate]) {
                    count++;
                }
            }

            if (count * 2 > A.length) {
                return candidate;
            }
        }

        return -1;
    }
}
