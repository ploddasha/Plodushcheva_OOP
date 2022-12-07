package org.example;

import java.util.Stack;

public class Calculator {

    public Double calculate (String str){
        String[] array = str.split(" ");
        Stack<Double> stack = new Stack<>();
        for (int i = array.length-1; i >= 0; i--) {
            switch (array[i]) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> stack.push(stack.pop() - stack.pop());
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> stack.push(stack.pop() / stack.pop());
                case "log" -> stack.push(Math.log(stack.pop()));
                case "pow" -> stack.push(Math.pow(stack.pop(), stack.pop()));
                case "sqtr" -> stack.push(Math.sqrt(stack.pop()));
                case "sin" -> stack.push(Math.sin(stack.pop()));
                case "cos" -> stack.push(Math.cos(stack.pop()));
                default -> stack.push(Double.parseDouble(array[i]));
            }
        }
        return stack.pop();
    }
    public double mySqrt (int x) {
        if(x == 0)
            return 0;
        if(x < 0)
            return -1;

        double rez = x;

        for(int i = 0; i < 1000; i++)
            rez = (rez + (x/rez))/2;

        return rez;
    }

    public double myPow (int x, int y) {
        double rez = x;
        for (int i=2; i<y; i++){
            rez = rez * x;
        }
        return rez;
    }
}
