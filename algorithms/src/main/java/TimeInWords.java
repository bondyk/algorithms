/**
 * Given the time in numerals we may convert it into words.
 *
 * At minutes = 0, use o' clock. For 1 <= minutes <= 30, use past, and for minutes > 30 use to.
 * Note the space between the apostrophe and clock in o' clock.
 * Write a program which prints the time in words for the input given in the format described.
 *
 * h: an integer representing hour of the day
 * m: an integer representing minutes after the hour
 *
 * https://www.hackerrank.com/challenges/the-time-in-words/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
 */
public class TimeInWords {
    private static final String[] numbers = new String[]{
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };
    private static final String[] numbers_tens = new String[]{
        "", "twenty", "thirty", "forty", "fifty"
    };

    public static void main(String[] args) {
        System.out.println(timeInWords(5, 0));
        System.out.println(timeInWords(5, 15));
        System.out.println(timeInWords(5, 28));
        System.out.println(timeInWords(5, 30));
        System.out.println(timeInWords(5, 45));
        System.out.println(timeInWords(5, 47));
        System.out.println(timeInWords(12, 47));
        System.out.println(timeInWords(12, 59));
        System.out.println(timeInWords(5, 1));
    }

    static String timeInWords(int h, int m) {

        if (m == 0) {
            return numbers[h - 1] + " o' clock";
        }
        if (m <= 30) {
            return minutesAsString(m) + " past " + numbers[h - 1];
        }

        return minutesAsString(60 - m) + " to " + numbers[h % 12];
    }

    private static String minutesAsString(int num) {
         if (num == 15) return "quarter";
         if (num == 30) return "half";
         if (num == 1) return numbers[num - 1] + " minute";
         if (num < 20) return numbers[num - 1] + " minutes";
         int tens = num / 10;
         StringBuilder sb = new StringBuilder(numbers_tens[tens - 1]);
         int remainingNumber = num % 10;
         if (remainingNumber > 0) {
             sb.append(" ").append(numbers[remainingNumber - 1]);
         }

        sb.append(" minutes");

         return sb.toString();
    }
}
