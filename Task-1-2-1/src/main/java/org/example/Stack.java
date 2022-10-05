package org.example;

import java.util.Arrays;
import java.util.Objects;

/**
 * Stack is an abstract data type that serves as a collection of elements, with rule LIFO
 * LIFO - last in, first out.
 */
public class Stack <T> {
    int cnt;
    int capacity;
    T[] arr;
    /**
     * Stack constructor.
     */
    public Stack (){
        cnt = 0;
        capacity = 5;
        arr = (T[]) new Object[capacity];
    }

    /**
     * adds an element to the collection.
     * @param temp is a new element
     */
    public void push(T temp){
        if (cnt >= capacity){
            capacity = capacity+10;
            arr = Arrays.copyOf(arr, capacity);
        }
        arr[cnt] = temp;
        cnt++;
    }

    /**
     * adds a collection of elements (stack) to the collection (stack).
     * @param temp is new collection
     */
    public void pushStack(Stack<T> temp){
        int length = temp.count();
        //T[] arr = (T[]) new Object[length];
        for (int i = 0; i < length; i++){
            push (temp.arr[i]);
        }
    }

    /**
     * removes the most recently added element.
     * @return popped element
     */
    public T pop(){
        if (cnt == 0) {
            System.out.println("Stack is empty");
            return null;
        }
        T element = arr[cnt - 1];
        arr[cnt - 1] = null;
        cnt--;
        return element;

    }

    /**
     * initial constructor  with specified capacity.
     * @param capacity is a new capacity for stack
     */
    private Stack(int capacity){
        cnt = 0;
        capacity = 5;
        arr = (T[]) new Object[capacity];
    }

    /**
     * deletes the specified number of elements.
     * @param amount is a number of elements to delete
     * @return collection(stack) of deleted elements
     */
    public Stack<T> popStack(int amount){
        if (amount >= cnt ) {
            System.out.println("Stack will be empty");
            return null; //return arr
        }
        Stack <T> newarr = new Stack<T>(cnt);

        for (int i = cnt - amount; i < cnt; i++) {
            newarr.push(arr[i]);
        }
        cnt = cnt - amount;
        return newarr;
    }

    /**
     * @return count if elements in stack
     */
    public int count(){
        return cnt;
    }

    /**
     * comparison method.
     * @param o is a compared element
     * @return true or false depending on the eqality of elements
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Stack<T> expected = (Stack<T>) o;

        if ( count() != expected.count() ){
            return false;
        } else {
            for (int i = 0; i < count(); i++) {
                if (arr[i] != expected.arr[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return hash
     */
    @Override
    public int hashCode() {
        int res = 31 * (Objects.hash(cnt)) + Arrays.hashCode(arr);
        return res;
    }
}
