package com.bondyk.ctci;


/**
 * Write a function that adds two numbers. You should not use+ or any arithmetic operators.
 */
public class AddWithoutPlus {

    public static void main(String[] args) {

        System.out.println(add(13, 7));
        System.out.println(add(0, 7));
        System.out.println(add(-3, 5));
        System.out.println(add(-3, -5));
        System.out.println(add(759, 674));
    }

    private static int add(int a, int b) {
        /*
         1. Add 759 + 674, but"forget" to carry. I then get 323.
         2. Add 759 + 674 but only do the carrying, rather than the addition of each digit. I then get 1110.
         3. Add the result of the first two operations (recursively, using the same process described in step 1 and 2):
         1110 + 323 = 1433.
         Now, how would we do this in binary?
         1. If I add two binary numbers together, but forget to carry, the ith bit in the sum will be 0 only if a and b
         have the same ith bit (both 0 or both 1). This is essentially an XOR.
         2. If I add two numbers together but only carry, I will have a 1 in the ith bit of the sum only if bits i - 1 of
         a and b are both ls. This is an AND, shifted.
         3. Now, recurse until there's nothing to carry.
         */

        int sum;
        int carry;
        do {
            carry = (a & b) << 1;
            sum = a ^ b;
            a = sum;
            b = carry;
        } while (carry != 0);

        return sum;

    }
}
