import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        List<String> r = generateParenthesis(3);
        System.out.println(String.join("\n", r));
    }

    private static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        char[] combination = new char[2 * n];
        generate(0, n, 0, result, combination);
        return result;
    }

    private static void generate(int bracesToClose, int bracesToOpen, int i, List<String> pList, char[] combination){
        if (bracesToOpen == 0 && bracesToClose == 0) {
//            System.out.println(new String(combination));
            pList.add(new String(combination));
        }

//        System.out.println("left = " + deep + "; n = " + n);

        if (bracesToOpen > 0) {
            combination[i] = '(';
            generate(bracesToClose + 1, bracesToOpen - 1, i + 1, pList, combination);
        }
//        System.out.println("-left = " + deep + "; n = " + n);
        if (bracesToClose > 0) {
            combination[i] = ')';
            generate(bracesToClose - 1, bracesToOpen, i + 1, pList, combination);
        }
    }
}
