package ru.nsu.plodushcheva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StackTest {
    @Test
    public void pushPopTest() {

        Stack<Integer> expected = new Stack<Integer>();
        Stack<Integer> actual = new Stack<Integer>();
        expected.push(4);
        actual.push(4);
        actual.push(5);
        actual.pop();

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void pushPopStackTest() {

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
    public void popNullTest() {

        Stack<Integer> expected = new Stack<Integer>();
        Stack<Integer> actual = new Stack<Integer>();
        actual.pop();

        Assertions.assertEquals(actual, expected);

        expected.push(3);
        expected.pop();
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void popStackNullTest() {

        Stack<Integer> expected = new Stack<Integer>();
        Stack<Integer> actual = new Stack<Integer>();
        actual.popStack(2);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void stringTest() {
        Stack<String> expected = new Stack<>();
        expected.push("apple");
        expected.push("banana");
        expected.push("orange");

        Stack<String> actual = new Stack<>();
        actual.push("apple");
        actual.push("banana");
        actual.push("orange");
        actual.push("cucumber");
        actual.pop();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void wrapperCharacterTest() {
        Stack<Character> expected = new Stack<>();
        expected.push('c');

        Stack<Character> actual = new Stack<>();
        actual.push('c');

        Assertions.assertEquals(expected, actual);

        Stack<Character> temp = new Stack<>();
        temp.push('a');
        temp.push('t');
        expected.pushStack(temp);
        actual.push('a');
        actual.push('t');

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void wrapperBooleanTest() {
        Stack<Boolean> expected = new Stack<>();
        expected.push(true);
        expected.push(false);
        expected.push(true);

        Stack<Boolean> actual = new Stack<>();
        actual.pushStack(expected);
        actual.push(false);
        actual.push(false);
        actual.popStack(2);

        Assertions.assertEquals(expected, actual);
    }

}