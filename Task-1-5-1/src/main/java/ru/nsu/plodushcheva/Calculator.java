package ru.nsu.plodushcheva;

import java.util.Stack;

/**
 * Class implements a calculator with a prefix form of recording operations
 */
public class Calculator {
    Stack<Double> stack = new Stack<>();

    /**
     * @param str line with an expression
     * @return the number is the result of operations
     * @throws Exception in operations and in reading an expression
     */
    public Double calculate (String str) throws Exception {
        String[] array = str.split(" ");

        for (int i = array.length-1; i >= 0; i--) {
            try {
                stack.push(Double.parseDouble(array[i]));
            }
            catch (NumberFormatException e) {
                switch (array[i]) {
                    case "+" -> stack.push(CheckedPop() + CheckedPop());
                    case "-" -> stack.push(CheckedPop() - CheckedPop());
                    case "*" -> stack.push(CheckedPop() * CheckedPop());
                    case "/" -> {
                        double a = CheckedPop();
                        double b = CheckedPop();
                        if (b != 0) {
                            stack.push(a / b);
                        } else {
                            throw new Exception("Division by 0");
                        }
                    }
                    case "log" -> {
                        double number = CheckedPop();
                        if (number > 0){
                            stack.push(Math.log(number));
                        }
                        else {
                            throw new Exception("Negative number for logarithm");
                        }
                    }
                    case "pow" -> stack.push(Math.pow(CheckedPop(), CheckedPop()));
                    case "sqrt" -> {
                        double number = CheckedPop();
                        if (number >= 0){
                            stack.push(Math.sqrt(number));
                        }
                        else {
                            throw new Exception("Negative number for root");
                        }
                    }
                    case "sin" -> stack.push(Math.sin(CheckedPop()));
                    case "cos" -> stack.push(Math.cos(CheckedPop()));
                    default -> throw new RuntimeException();
                }
            }
        }
        return CheckedPop();
    }

    /**
     * checks if the stack is empty and if a number can be taken from it.
     * @return number from the stack
     * @throws Exception if the stack is empty
     */
    public double CheckedPop() throws Exception{
        if (stack.isEmpty()){
            throw new Exception("Wrong expression");
        }
        else{
            return stack.pop();
        }
    }
}
