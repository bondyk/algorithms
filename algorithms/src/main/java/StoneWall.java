import java.util.*;

public class StoneWall {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{8, 8, 5, 7, 9, 8, 7, 4, 8}));// 7
        System.out.println(solution(new int[]{8, 8, 5, 7, 9, 8, 7, 4, 2}));// 7
        System.out.println(solution(new int[]{8, 8, 5, 7, 9, 8, 7, 4, 4}));// 6
        System.out.println(solution(new int[]{5, 8, 5, 7, 9, 8, 7, 4, 8}));// 7
    }

    public static int solution(int[] H) {
        int result = 1;

        Stack<Integer> queue = new Stack<Integer>();

        for (int i = 1; i < H.length; i++) {
            if (H[i] < H[i - 1]) {
                result++;
                while (!queue.isEmpty() && H[i] <= queue.peek()) {
                    if (queue.pop() != H[i]) {
                        result++;
                    }
                }
            } else if (H[i] > H[i - 1]) {
                queue.add(H[i - 1]);
            }
        }

        result += queue.size();

        return result;
    }
}
