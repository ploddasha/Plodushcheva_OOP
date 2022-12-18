package ru.nsu.plodushcheva;

import java.util.Stack;

/**
 * Class implements a calculator with a prefix form of recording operations.
 */
public class Calculator {
    Stack<Double> stack = new Stack<>();

    /**
     * method splits the string by spaces and adds from the end to the stack
     * by execution operation.
     *
     * @param str line with an expression
     * @return the number is the result of operations
     * @throws Exception in operations and in reading an expression
     */
    public Double calculate(String str) throws Exception {
        String[] array = str.split(" ");

        for (int i = array.length - 1; i >= 0; i--) {
            try {
                stack.push(Double.parseDouble(array[i]));
            }
            catch (NumberFormatException e) {
                switch (array[i]) {
                    case "+" :
                        stack.push(checkedPop() + checkedPop());
                        break;
                    case "-" :
                        stack.push(checkedPop() - checkedPop());
                        break;
                    case "*" :
                        stack.push(checkedPop() * checkedPop());
                        break;
                    case "/" :
                        double a = checkedPop();
                        double b = checkedPop();
                        if (b != 0) {
                            stack.push(a / b);
                        } else {
                            throw new Exception("Division by 0");
                        }
                        break;
                    case "log" :
                        double number = checkedPop();
                        if (number > 0) {
                            stack.push(Math.log(number)); }
                        else {
                            throw new Exception("Negative number for logarithm"); }
                        break;
                    case "pow" :
                        stack.push(Math.pow(checkedPop(), checkedPop()));
                        break;
                    case "sqrt" :
                        double num = checkedPop();
                        if (num >= 0) {
                            stack.push(Math.sqrt(num));
                        }
                        else {
                            throw new Exception("Negative number for root");
                        }
                        break;
                    case "sin" :
                        stack.push(Math.sin(checkedPop()));
                        break;
                    case "cos" :
                        stack.push(Math.cos(checkedPop()));
                        break;
                    default : throw new RuntimeException();
                }
            }
        }
        return checkedPop();
    }

    /**
     * checks if the stack is empty and if a number can be taken from it.
     *
     * @return number from the stack
     * @throws Exception if the stack is empty
     */
    public double checkedPop() throws Exception {
        if (stack.isEmpty()) {
            throw new Exception("Wrong expression");
        }
        else {
            return stack.pop();
        }
    }
}
