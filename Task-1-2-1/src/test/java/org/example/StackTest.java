package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StackTest {
    @Test
    public void push_pop_Test() {

        Stack<Integer> expected = new Stack<Integer>();
        Stack<Integer> actual = new Stack<Integer>();
        expected.push(4);
        actual.push(4);
        actual.push(5);
        actual.pop();

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void pushStack_popStack_Test() {

        Stack<Integer> expected = new Stack<Integer>();
        expected.push(4);

        Stack<Integer> forPush = new Stack<Integer>();
        forPush.push(-5);
        forPush.push(5);

        Stack<Integer> actual = new Stack<Integer>();
        actual.push(4);
        actual.push(-5);
        actual.push(5);

        expected.pushStack(forPush);

        Assertions.assertEquals(actual, expected);

        expected.pop();
        expected.pop();
        Stack<Integer> returning = actual.popStack(2);

        Assertions.assertEquals(actual, expected);
        Assertions.assertEquals(returning, forPush);
    }

    @Test
    public void pop_null_Test() {

        Stack<Integer> expected = new Stack<Integer>();
        Stack<Integer> actual = new Stack<Integer>();
        actual.pop();

        Assertions.assertEquals(actual, expected);

        expected.push(3);
        expected.pop();
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void popStack_null_Test() {

        Stack<Integer> expected = new Stack<Integer>();
        Stack<Integer> actual = new Stack<Integer>();
        actual.popStack(2);

        Assertions.assertEquals(actual, expected);
    }

}