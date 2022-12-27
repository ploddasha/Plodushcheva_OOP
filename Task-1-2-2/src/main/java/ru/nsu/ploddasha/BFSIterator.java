package ru.nsu.ploddasha;

import java.util.*;

public class BFSIterator<T> implements Iterator<Tree<T>> {
    private final int modCount;
    //private final List<Tree<T>> queue;
    private final Queue<Tree<T>> queue;

    public BFSIterator(Tree<T> root) {
        modCount = root.getModCount();
        queue = new ArrayDeque<>();
        queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Tree<T> next() {
        Tree<T> current = queue.remove();
        /*
        if (modCount != current.getModCount()) {
            throw new ConcurrentModificationException();
        } */

        queue.addAll(current.getChildren());
        //return current.getValue();
        return current;
    }
}
