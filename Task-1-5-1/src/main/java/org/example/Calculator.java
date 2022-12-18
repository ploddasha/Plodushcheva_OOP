package org.example;

import java.util.Stack;

public class Calculator {

    public Double calculate (String str) throws Exception {
        String[] array = str.split(" ");
        Stack<Double> stack = new Stack<>();
        for (int i = array.length-1; i >= 0; i--) {
            try {
                stack.push(Double.parseDouble(array[i]));
            }
            catch (NumberFormatException e) {
                switch (array[i]) {
                    case "+" -> stack.push(stack.pop() + stack.pop());
                    case "-" -> stack.push(stack.pop() - stack.pop());
                    case "*" -> stack.push(stack.pop() * stack.pop());
                    case "/" -> {
                        double a = stack.pop();
                        double b = stack.pop();
                        if (b != 0) {
                            stack.push(a / b);
                        } else {
                            throw new Exception("Division by 0");
                        }
                    }
                    case "log" -> stack.push(Math.log(stack.pop()));
                    case "pow" -> stack.push(Math.pow(stack.pop(), stack.pop()));
                    case "sqtr" -> stack.push(Math.sqrt(stack.pop()));
                    case "sin" -> stack.push(Math.sin(stack.pop()));
                    case "cos" -> stack.push(Math.cos(stack.pop()));
                    default -> throw new RuntimeException(" at " + array[i]);
                }
            }
        }
        return stack.pop();
    }
}
