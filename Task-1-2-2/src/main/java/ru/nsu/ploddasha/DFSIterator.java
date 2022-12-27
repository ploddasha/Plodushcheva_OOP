package ru.nsu.ploddasha;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class DFSIterator<T> implements Iterator<Tree<T>> {
    private final int modCount;
    private final Stack<Tree<T>> stack;

    public DFSIterator(Tree<T> root) {
        stack = new Stack<>();
        //this.root = root
        stack.push(root);
        modCount = root.getModCount();
    }


    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Tree<T> next() {
        Tree<T> current = stack.pop();

        if (modCount != current.getModCount()) {
            throw new ConcurrentModificationException();
        }
        /*
        if (!hasNext()) {
            throw new NoSuchElementException();
        } */
        for (int i = 0; i < current.getChildren().size(); i++){
            stack.push(current.getChildren().get(i));
        }
        //stack.addAll(current.getChildren());
        //current.getChildren().forEach(stack::push);

        //return current.getValue();
        return current;
    }
}
