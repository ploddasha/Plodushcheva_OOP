package ru.nsu.ploddasha;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class TreeTest {
    Tree<String> tree;

    @Test
    void addGetValueTest() {

        Tree<String> tree = new Tree<>("Hello");
        Tree<String> tree2 = new Tree<>("World!");
        tree.add(tree2);
        String actual = tree.getValue() + " " + tree.getChildren().get(0).getValue();

        String expected = "Hello World!";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getRoot() {
        Tree<String> tree = new Tree<>("Hello");
        Tree<String> tree2 = tree.add("World");
        tree2.add("!");
        Tree<String> actual = tree2.getRoot();

        Assertions.assertEquals(tree, actual);
    }

    @Test
    void getParent() {

        Tree<String> tree = new Tree<>("Hello");
        Tree<String> tree1 = tree.add("World");
        Tree<String> actual = tree1.getParent();

        Assertions.assertEquals(tree, actual);
    }

    @Test
    void getChildren() {

        Tree<String> tree = new Tree<>("Hello");
        Tree<String> tree1 = tree.add("World");
        List<Tree<String>> actual = tree.getChildren();

        List<Tree<String>> expected = new ArrayList<>();
        expected.add(tree1);

        Assertions.assertEquals(expected, actual);

        Tree<String> tree2 = tree.add("World2");
        Tree<String> tree3 = tree.add("World3");
        actual = tree.getChildren();
        expected.add(tree2);
        expected.add(tree3);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void getModCounter() {
        Tree<String> tree = new Tree<>("Hello");
        Tree<String> tree1 = tree.add("World");
        Tree<String> tree2 = tree1.add("!");

        int actual = tree.getModCount();
        Assertions.assertEquals(2, actual);

        actual = tree1.getModCount();
        Assertions.assertEquals(1, actual);

        actual = tree2.getModCount();
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void testBreathFirstSearch() {
        Tree<Integer> tree = new Tree<>(1);
        Tree<Integer> child1 = tree.add(2);
        Tree<Integer> child2 = tree.add(3);
        Tree<Integer> child11 = child1.add(4);
        Tree<Integer> child12 = child1.add(5);
        Tree<Integer> child13 = child1.add(6);
        Tree<Integer> child21 = child2.add(7);
        Tree<Integer> child22 = child2.add(8);
        Tree<Integer> child121 = child12.add(9);
        Tree<Integer> child211 = child21.add(10);
        Tree<Integer> child212 = child21.add(11);
        tree.setTypeOfIteration(2);
        Iterator<Tree<Integer>> iterator = tree.iterator();
        ArrayList<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add(iterator.next().getValue());
        }
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void depthFirstSearchIteratorTest() {
        Tree<Integer> tree = new Tree<>(1);
        Tree<Integer> child1 = tree.add(2);
        Tree<Integer> child2 = tree.add(3);
        Tree<Integer> child11 = child1.add(4);
        Tree<Integer> child12 = child1.add(5);
        Tree<Integer> child13 = child1.add(6);
        Tree<Integer> child21 = child2.add(7);
        Tree<Integer> child22 = child2.add(8);
        Tree<Integer> child121 = child12.add(9);
        Tree<Integer> child211 = child21.add(10);
        Tree<Integer> child212 = child21.add(11);

        tree.setTypeOfIteration(1);
        Iterator<Tree<Integer>> iterator = tree.iterator();
        List<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add(iterator.next().getValue());
        }
        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 9, 6, 3, 7, 10, 11, 8);

        Assertions.assertEquals(expected, actual);

    }

    //HUGE PROBLEM WITH MOD COUNT
    //WHAT IS IT FOR WHAT AND HOW YO USE

}