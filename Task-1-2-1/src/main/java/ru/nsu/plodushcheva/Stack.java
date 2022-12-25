package ru.nsu.plodushcheva;

import java.util.Arrays;

/**
 * Stack is an abstract data type that serves as a collection of elements, with rule LIFO
 * LIFO - last in, first out.
 */
public class Stack<T> {
    private int cnt;
    private int capacity;
    private T[] arr;

    /**
     * Stack constructor.
     */
    public Stack() {
        cnt = 0;
        capacity = 2;
        arr = (T[]) new Object[capacity];
    }

    /**
     * initial constructor  with specified capacity.
     *
     * @param capacity is a new capacity for stack
     */
    private Stack(int capacity) {
        cnt = 0;
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    /**
     * adds an element to the collection.
     *
     * @param temp is a new element
     */
    public void push(T temp) {
        if (cnt >= capacity) {
            capacity = capacity * 3 / 2;
            arr = Arrays.copyOf(arr, capacity);
        }
        arr[cnt++] = temp;
    }

    /**
     * adds a collection of elements (stack) to the collection (stack).
     *
     * @param temp is new collection
     */
    public void pushStack(Stack<T> temp) {
        int length = temp.count();
        for (int i = 0; i < length; i++) {
            push(temp.arr[i]);
        }
    }

    /**
     * removes the most recently added element.
     *
     * @return popped element
     */
    public T pop() {
        if (cnt == 0) {
            System.out.println("Stack is empty");
            return null;
        }
        T element = arr[--cnt];
        return element;

    }

    /**
     * deletes the specified number of elements.
     *
     * @param amount is a number of elements to delete
     * @return collection(stack) of deleted elements
     */
    public Stack<T> popStack(int amount) {
        if (amount >= cnt) {
            System.out.println("Stack will be empty");
            return null;
        }
        Stack<T> newarr = new Stack<T>(cnt);

        for (int i = cnt - amount; i < cnt; i++) {
            newarr.push(arr[i]);
        }
        cnt = cnt - amount;
        return newarr;
    }

    /**
     * count of stack elements.
     *
     * @return count if elements in stack
     */
    public int count() {
        return cnt;
    }

    /**
     * comparison method.
     *
     * @param o is a compared element
     * @return true or false depending on the eqality of elements
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stack<T> expected = (Stack<T>) o;

        if (count() != expected.count()) {
            return false;
        }
        for (int i = 0; i < cnt; i++) {
            if (arr[i] != expected.arr[i]) {
                return false;
            }
        }
        return true;
    }
}
