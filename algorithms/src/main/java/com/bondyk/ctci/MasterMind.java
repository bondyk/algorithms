package com.bondyk.ctci;


/**
 * The Game of Master Mind is played as follows:
 The computer has four slots, and each slot will contain a ball that is red (R), yellow (Y), green (G) or
 blue (B). For example, the computer might have RGGB (Slot #1 is red, Slots #2 and #3 are green, Slot
 #4 is blue).
 You, the user, are trying to guess the solution. You might, for example, guess YRGB.
 When you guess the correct color for the correct slot, you get a "hit:' If you guess a color that exists
 but is in the wrong slot, you get a "pseudo-hit:' Note that a slot that is a hit can never count as a
 pseudo-hit.
 For example, if the actual solution is RGBY and you guess GGRR , you have one hit and one pseudo­
 hit
 Write a method that, given a guess and a solution, returns the number of hits and pseudo-hits.
 */
public class MasterMind {

    private static final byte R = 1;
    private static final byte G = 2;
    private static final byte B = 4;
    private static final byte Y = 8;

    public static void main(String[] args) {
        printResult("RGBY", "GGRR");
        printResult("RGBR", "GGRR");
    }

    private static void printResult(String solution, String guess) {

        short pat = toBits(solution, 4);
        short sol = toBits(guess, 4);
        short hitBits = (short) (pat & sol);
        byte setChars = (byte) toBits(guess, 0);
        String hitCharsStr = toChars(hitBits, 4);
        byte hitChars = (byte) toBits(hitCharsStr, 0);
        byte patChars = (byte) toBits(solution, 0);
        byte guessesBits = (byte) ((~hitChars) & setChars & patChars);
        //     input          hits    setChars   patChars
        //("RGBR", "GGRR") -> 1100 -> 1100   ->    1100   ->  ~1100 & 1100 & 1100 -> 0000
        //("RGBY", "GGRR") -> 0100 -> 1100   ->    1100   ->  ~0100 & 1100 & 1100 -> 1000


        System.out.println("Hits: " + countSetBits(hitBits));
        System.out.println("Pseudo Hits: " + countSetBits(guessesBits));
    }

    private static String toChars(int bits, int shift) {
        char[] chars  = new char[4];
        for (int i = 0; i < 4; i++) {
            if ((bits & R) == R) chars[(int) log2(R)] = 'R';
            if ((bits & G) == G) chars[(int) log2(G)] = 'G';
            if ((bits & B) == B) chars[(int) log2(B)] = 'B';
            if ((bits & Y) == Y) chars[(int) log2(Y)] = 'Y';
            bits = bits >> shift;
        }
        return new String(chars);
    }

    private static int log2(int number) {
        return (int) (Math.log(number) / Math.log(2));
    }

    private static short toBits(String str, int shift) {
        short result = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < 4; i++) {
            if (chars[i] == 'R') result |= R;
            if (chars[i] == 'G') result |= G;
            if (chars[i] == 'B') result |= B;
            if (chars[i] == 'Y') result |= Y;
            result = (short) (result << shift);
        }
        return result;
    }

    private static int countSetBits(short bits) {
        if (bits == 0) return 0;
        int count = 0;
        for (int i = 0; i < 16; i++) {
            if ((bits & 1) == 1) {
                count++;
            }
            bits = (short) (bits >> 1);
        }

        return count;
    }
}
