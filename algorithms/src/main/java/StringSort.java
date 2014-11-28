import java.util.Arrays;

/**
 * Least Significant Digit first algorithm for sorting strings
 */
public class StringSort {

    public static void main(String[] args) {
        String[] strings = new String[] {
                "4PGC938",
                "2IYE230",
                "3CIO720",
                "1ICK750",
                "1OHV845",
                "4JZY524",
                "1ICK750",
                "3CIO720",
                "1OHV845",
                "1OHV845",
                "2RLA629",
                "2RLA629",
                "3ATW723"
        };

        System.out.println("Unsorted: " + Arrays.toString(strings));
        sort(strings);
        System.out.println("Sorted: " + Arrays.toString(strings));
    }

    private static void sort(String[] strings) {

        int n = 0;
        for (String str : strings) {
            n = Math.max(n, str.length());
        }

        String[] aux = new String[strings.length];

        for (int i = n - 1; i >= 0; i--) {
            int[] count = new int[256];
            for (int j = 0; j < strings.length; j++) {
                count[charAt(strings[j], i) + 1]++;
            }

            for (int j = 1; j < count.length - 1; j++) {
                count[j] += count[j - 1];
            }

            for (int j = 0; j < strings.length; j++) {
                aux[count[charAt(strings[j], i)]++] = strings[j];
            }

            for (int j = 0; j < strings.length; j++) {
                strings[j] = aux[j];
            }
        }
    }

    private static char charAt(String str, int idx) {
        if (idx < str.length()) {
            return str.charAt(idx);
        }

        return (0);
    }
}
