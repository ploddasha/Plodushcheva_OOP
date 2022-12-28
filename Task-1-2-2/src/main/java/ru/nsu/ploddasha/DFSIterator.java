package ru.nsu.ploddasha;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

/**
 * Depth first search iterator
 * walk through each descendant element of tree.
 *
 * @param <T> value
 */
public class DFSIterator<T> implements Iterator<Tree<T>> {
    private final int modCount;
    private final Stack<Tree<T>> stack;

    /**
     * constructor of the DFS iterator
     *
     * @param root is current node
     */
    public DFSIterator(Tree<T> root) {
        stack = new Stack<>();
        stack.push(root);
        modCount = root.getModCount();
    }


    /**
     * Returns true if there is the next element.
     *
     * @return bool
     */
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Returns the next element.
     *
     * @return next element
     */
    @Override
    public Tree<T> next() {
        Tree<T> current = stack.pop();

        if (modCount != current.getModCount()) {
            throw new ConcurrentModificationException();
        }
        stack.addAll(current.getChildren());
        return current;
    }
}
