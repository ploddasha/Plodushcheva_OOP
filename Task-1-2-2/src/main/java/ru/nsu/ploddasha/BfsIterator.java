package ru.nsu.ploddasha;

import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Queue;

/**
 * Breadth First Search Iterator.
 *
 * @param <T> value
 */
public class BfsIterator<T> implements Iterator<Tree<T>> {
    private final int modCount;
    private final Queue<Tree<T>> queue;

    /**
     * constructor of the BFS iterator.
     *
     * @param root is a current node
     */
    public BfsIterator(Tree<T> root) {
        modCount = root.getModCount();
        queue = new ArrayDeque<>();
        queue.add(root);
    }

    /**
     * Returns true if there is the next element.
     *
     * @return bool
     */
    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /**
     * Returns the next element.
     *
     * @return next element
     */
    @Override
    public Tree<T> next() {
        Tree<T> current = queue.remove();

        if (modCount != current.getModCount()) {
            throw new ConcurrentModificationException();
        }

        queue.addAll(current.getChildren());
        return current;
    }
}
