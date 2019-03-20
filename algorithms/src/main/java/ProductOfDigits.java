/**
 * You are given an int N. Find the smallest positive integer X
 * such that the product of its digits (in decimal notation) is equal to N.
 * Return the number of digits in X, or return -1 if such a number does not exist.
 */
public class ProductOfDigits {

    public static void main(String[] args) {
        ProductOfDigits task = new ProductOfDigits();
//        System.out.println("Expected 1. Actual: " + task.smallestNumber(1));
//        System.out.println("Expected 2. Actual: " + task.smallestNumber(10));
//        System.out.println("Expected -1. Actual: " + task.smallestNumber(26));
//        System.out.println("Expected 3. Actual: " + task.smallestNumber(100));
        System.out.println("Expected 4. Actual: " + task.smallestNumber(2520));
//        System.out.println("Expected 4. Actual: " + task.smallestNumber(864));
//        System.out.println("Expected -1. Actual: " + task.smallestNumber(17));
    }

    public int smallestNumber(int N) {

        if (N < 10) return 1;

        System.out.println("N = " + N);
        int number = N;
        int count = 0;
        for (int i = 9; i > 1; i--) {
//            System.out.println("-" + i + "-");
            if (number % i == 0) {
                count++;
                number = number / i;
                System.out.print(i);
                i = 10;
            }
        }

        System.out.println();
//        System.out.println("===========");

        return (count == 0 || number != 1) ? -1 : count;
    }
}
