package ru.nsu.ploddasha;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * A hierarchical data structure.
 * class Tree(Node).
 *
 * @param <T> any type (String, int, Integer)
 */
public class Tree<T> implements Iterable<Tree<T>> {

    private T value;
    private Tree<T> parent;
    private final List<Tree<T>> children;
    private int modCount;
    private IteratorTreeType typeOfIteration;

    /**
     * constructor for the Tree with specified value.
     *
     * @param value the value of the current node
     */
    public Tree(T value) {
        this.modCount = 0;
        this.parent = null;
        this.value = value;
        this.children = new ArrayList<>();
    }

    /**
     * root of the current node.
     *
     * @return Tree - the root of the structure
     */
    public Tree<T> getRoot() {
        Tree<T> root = this;
        while (root.parent != null) {
            root = root.getParent();
        }
        return root;
    }

    /**
     * All the child nodes of current node.
     *
     * @return list of children
     */
    public List<Tree<T>> getChildren() {
        return children;
    }

    /**
     * Parent tree of the current node.
     *
     * @return Tree - parent
     */
    public Tree<T> getParent() {
        return parent;
    }

    /**
     * setter of the parent of the node.
     *
     * @param parent of the current Tree
     */
    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    /**
     * Count of modifications
     * adding and removing new node.
     *
     * @return Modification count
     */
    public int getModCount() {
        Tree<T> root = getRoot();
        return root.modCount;
    }

    /**
     * getter of value of the current Tree.
     *
     * @return value of the node
     */
    public T getValue() {
        return value;
    }

    /**
     * setter of value of the current Tree.
     *
     * @param value of the node
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * count of all elements in the tree.
     *
     * @return count of the nodes in tree
     */
    public int getCountOfNodesInTree() {
        Iterator<T> treeIterator = (Iterator<T>) iterator();
        int number = 0;
        while (treeIterator.hasNext()) {
            treeIterator.next();
            number++;
        }
        return number;
    }

    /**
     * adding new child to the list of the current node.
     *
     * @param child new child
     */
    private void addChild(Tree<T> child) {
        children.add(child);
    }

    /**
     * adding new Node to the Tree
     * current Node is a parent.
     *
     * @param child new Tree
     */
    public void add(@NotNull Tree<T> child) {
        child.parent = this;
        child.parent.addChild(child);

        Tree<T> root = child.getRoot();
        root.modCount++;

    }

    /**
     * creating new node with specified value
     * and adding it to the current Tree as a child.
     *
     * @param value of the new node
     * @return new node
     */
    public Tree<T> add(T value) {
        Tree<T> child = new Tree<T>(value);
        child.parent = this;
        child.getParent().children.add(child);

        Tree<T> root = child.getRoot();
        root.modCount++;

        return child;
    }


    /**
     * checks if current node is a root.
     *
     * @return bool if current node is a root
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * checks if current node is a leaf.
     *
     * @return bool if current node is a leaf
     */
    public boolean isLeaf() {
        return children.isEmpty();
    }

    /**
     * remove specified child from the list of the children
     * of a current node.
     *
     * @param child wich deleted
     */
    private void removeChild(Tree<T> child) {
        children.remove(child);
    }

    /**
     * remove specified child from
     * of a current node.
     *
     * @param node that is deleted
     * @throws Exception if parent doesn't have this child
     */
    public void remove(Tree<T> node) throws Exception {

        if (!children.contains(node)) {
            throw new Exception("Parent doesn't have this child");
        }

        for (Tree<T> i : node.getChildren()) {
            this.addChild(i);
            i.setParent(this);
        }
        this.removeChild(node);

        Tree<T> root = node.getRoot();
        root.modCount++;
    }

    /**
     * setter of Type of Iteration.
     *
     * @param typeOfIteration in tree
     */
    public void setTypeOfIteration(IteratorTreeType typeOfIteration) {
        this.typeOfIteration = typeOfIteration;
    }

    /**
     * two variant of the Iterator
     * Depth first search iterator
     * Breadth First Search Iterator.
     */
    public enum IteratorTreeType {
        DfsIterator,
        BfsIterator
    }


    /**
     * Overriding the iterator.
     *
     * @return iterator object
     */
    @Override
    public Iterator<Tree<T>> iterator() {

        return typeOfIteration == IteratorTreeType.DfsIterator
                ? new DfsIterator<>(this)
                : new BfsIterator<>(this);
    }

    /**
     * Overriding the equals of nodes for correct comparison.
     *
     * @param o object we need to compare with
     * @return equal object or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tree<?> node)) {
            return false;
        }
        return value.equals(node.value) && Objects.equals(children, node.children);
    }

    /**
     * Overriding the hashCode for correct comparison.
     *
     * @return hash of node
     */
    @Override
    public int hashCode() {
        return Objects.hash(value, children);
    }

}