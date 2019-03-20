/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class WordSearch {

    public static void main(String[] args) {
        System.out.println(exist(new char[][] {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        }, "ABCCED"));
    }

    public static boolean exist(char[][] board, String word) {
        char[] wordChars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (exist(i, j, board, 0, wordChars)) return true;
            }
        }

        return false;
    }

    private static boolean exist(int i, int j, char[][] board, int wIdx, char[] word) {
        if (i >= 0 && i < board.length
            && j >= 0 && j < board[i].length
            && board[i][j] == word[wIdx]) {
            if (wIdx == word.length - 1) return true;
            char tmp = board[i][j];
            board[i][j] = 0;
            if (exist(i - 1, j, board, wIdx + 1, word)) return true;
            if (exist(i + 1, j, board, wIdx + 1, word)) return true;
            if (exist(i, j - 1, board, wIdx + 1, word)) return true;
            if (exist(i, j + 1, board, wIdx + 1, word)) return true;

            board[i][j] = tmp;
        }

        return false;
    }
}
