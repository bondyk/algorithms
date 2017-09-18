package com.bondyk.ctci;

import java.util.Arrays;
import java.util.Stack;

public class Calculator {

    public static void main(String[] args) {
        System.out.println(calculate("2", "*", "3", "+", "5", "/", "6", "*", "3", "+", "15"));
        System.out.println(calculate("2", "*", "3", "+", "5", "/", "6", "*", "3", "-", "15", "+", "7"));
    }


    private static double calculate(String... expression) {

        // Convert to inverse polish notion

        Stack<String> stack = new Stack<>();
        String[] polish = new String[expression.length];
        int i = 0;
        for (String element : expression) {
            if ("+".equals(element)) {
                while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/")
                || stack.peek().equals("+") || stack.peek().equals("-"))) {
                    polish[i++] = stack.pop();
                }
                stack.push(element);
            } else if ("-".equals(element)) {

                while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/")
                || stack.peek().equals("+") || stack.peek().equals("-"))) {
                    polish[i++] = stack.pop();
                }
                stack.push(element);
            } else if ("*".equals(element)) {

                while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                    polish[i++] = stack.pop();
                }
                stack.push(element);
            } else if ("/".equals(element)) {

                while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                    polish[i++] = stack.pop();
                }
                stack.push(element);
            } else {
                polish[i++] = element;
            }
        }

        while (!stack.isEmpty()) {
            polish[i++] = stack.pop();
        }


        System.out.println(Arrays.toString(polish));
        // Calculate expression

        for (String element : polish) {
            if ("+".equals(element)) {
                stack.push(String.valueOf(Double.parseDouble(stack.pop()) + Double.parseDouble(stack.pop())));
            } else if ("-".equals(element)) {
                double first = Double.parseDouble(stack.pop());
                double second = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(second - first));
            } else if ("*".equals(element)) {
                stack.push(String.valueOf(Double.parseDouble(stack.pop()) * Double.parseDouble(stack.pop())));
            } else if ("/".equals(element)) {
                double first = Double.parseDouble(stack.pop());
                double second = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(second / first));
            } else {
                stack.push(element);
            }
        }

        return Double.parseDouble(stack.pop());

    }


}
