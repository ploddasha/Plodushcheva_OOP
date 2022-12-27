package ru.nsu.ploddasha;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Tree<T> implements Iterable<Tree<T>> {

    private T value;
    private Tree <T> parent;
    private final List<Tree<T>> children;
    private int modCount;

    private int typeIteration;


    public Tree( ) {
        //countOfNodes = 0;
        modCount = 0;
        children = new ArrayList<>();
    }
    public Tree(T value) {
        //countOfNodes = 0;
        this.modCount = 0;
        this.parent = null;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public Tree<T> getRoot() {
        Tree<T> root = this;
        while (root.parent != null){
            root = getParent();
        }
        return root;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void deleteChild(int i) {
        children.remove(i);
    }
    public Tree<T> getParent() {
        return parent;
    }
    public void setParent(Tree<T> parent){
        this.parent = parent;
    }
    public int getModCount(){
        return modCount;
    }
    // what's the difference
    public int getModCount(@NotNull Tree<T> root){
        return root.modCount;
    }

    public T getValue(){
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    //check check check
    public int getCountOfNodesInTree() {
        Iterator<T> treeIterator = (Iterator<T>) iterator();
        int number = 0;
        while (treeIterator.hasNext()) {
            treeIterator.next();
            number++;
        }
        return number;
    }

    public void addChild(Tree<T> child){ //setChild
        children.add(child);
    }

    public void addChild(T child){
        Tree<T> newChild = new Tree<>(child);
        children.add(newChild);
        //return newChild;
    }

    /*public void add(T child) {
        modCount++;
        countOfNodes++;
        if (countOfNodes == 1) {
            root = new Tree(child);
        } else {
            root.addChild(child);
        }
    } */
    public Tree<T> add(@NotNull Tree<T> node) {
        node.parent = this; // конструктор (без значения)?
        node.parent.addChild(node);

        //modCount++;
        //countOfNodes++;

        modCounterInc(node);
        return node;
    }

    public Tree<T> add(T value) {
        Tree<T> child = new Tree<T>(value);
        child.parent = this;
        child.getParent().children.add(child);

        modCounterInc(child);

        return child;
    }

    public void modCounterInc(Tree <T> child){
        Tree<T> buff = child;
        while (buff.getParent() != null) {
            buff = buff.parent;
            buff.modCount += 1;
        }
    }

    public boolean isRoot(){
        return parent == null;
    }

    public boolean isLeaf(){
        return children.isEmpty();
    }

    public void removeChild(Tree<T> child) {
        children.remove(child);
    }

    public void remove(Tree<T> node) throws Exception {
        /* boolean a = false;
        for(int i=0; i< children.size(); i++){
            if (children.get(i) == node){
                a = true;
            }
        }
        if (!a){
            throw new Exception("Parent doesn't have this child");
        }
        boolean c = children.stream().filter( x -> (x == node)).findAny().orElse(null) != null;
        if (!c) {
            throw new Exception("Parent doesn't have this child");
        } */

        if (!children.contains(node)) {
            throw new Exception("Parent doesn't have this child");
        }

        for (Tree<T> i : node.getChildren()) {
            this.addChild(i);
            i.setParent(this);
        }
        this.removeChild(node);

        modCounterInc(node);
    }


    public void setTypeOfIteration(int newType) {
        typeIteration = newType;
    }


    public enum IteratorTree {
        DFSIterator,
        BFSIterator
    }

    /**
     * Метод interface Iterable<T>:
     * Iterator<T>iterator()
     * @return объект-итератор
     */
    @Override
    public Iterator<Tree<T>> iterator() {

        return typeIteration == 1
                ? new DFSIterator<>(this)
                : new BFSIterator<>(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tree<?> node)) {
            return false;
        }
        return value.equals(node.value) && Objects.equals(children, node.children);
                //&& Objects.equals(parent, node.parent);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(value, children, parent);
        return Objects.hash(value, children);
    }

}