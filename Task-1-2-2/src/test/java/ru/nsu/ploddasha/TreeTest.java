package ru.nsu.ploddasha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreeTest {

    @Test
    void addGetValueTest() {

        Tree<String> tree = new Tree<>("Hello");
        Tree<String> tree2 = new Tree<>("World!");
        tree.add(tree2);
        String actual = tree.getValue() + " " + tree.getChildren().get(0).getValue();

        String expected = "Hello World!";

        Assertions.assertEquals(expected, actual);
    }

    //           Hello
    //          /
    //         World
    //        /
    //      !

    @Test
    void generalTest() {
        Tree<String> tree = new Tree<>("Hello");
        Tree<String> tree2 = tree.add("World");
        Tree<String> tree3 = tree2.add("!");

        Assertions.assertTrue(tree.isRoot());

        Assertions.assertTrue(tree3.isLeaf());

        tree2.setValue("NewWorld");
        Assertions.assertEquals("NewWorld", tree2.getValue());
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
    void removeTest() throws Exception {
        Tree<String> tree = new Tree<>("Hello");
        Tree<String> tree2 = tree.add("world");
        Tree<String> tree3 = tree2.add("!");
        tree.remove(tree2);

        Tree<String> treeAct = new Tree<>("Hello");
        Tree<String> treeAct2 = treeAct.add("!");

        Assertions.assertEquals(tree, treeAct);
    }

    @Test
    void removeAssertionTest() {
        Tree<String> tree = new Tree<>("Hello");
        Tree<String> tree2 = new Tree<>("World");
        Tree<String> tree3 = tree.add("!");

        Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> tree.remove(tree2));

        Assertions.assertEquals("Parent doesn't have this child", exception.getMessage());

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
    void getModCount() {
        Tree<String> tree = new Tree<>("Hello");
        Tree<String> tree1 = tree.add("World");
        Tree<String> tree2 = tree1.add("!");
        int actual = tree.getModCount();
        int actual2 = tree2.getModCount();
        int actual1 = tree1.getModCount();

        Assertions.assertEquals(2, actual);
        Assertions.assertEquals(2, actual1);
        Assertions.assertEquals(2, actual2);
    }

    //                       1
    //                     /   \
    //                    2     3
    //                  / | \   | \
    //                 4  5  6  7  8
    //                    |     | \
    //                    9     10 11
    @Test
    void getCountOfNodesTest() {
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

        int countOfNodes = tree.getCountOfNodesInTree();
        Assertions.assertEquals(11, countOfNodes);

        countOfNodes = child11.getCountOfNodesInTree();
        Assertions.assertEquals(1, countOfNodes);
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
        tree.setTypeOfIteration(Tree.IteratorTreeType.BfsIterator);
        Iterator<Tree<Integer>> iterator = tree.iterator();
        List<Integer> actual = new ArrayList<>();
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

        tree.setTypeOfIteration(Tree.IteratorTreeType.DfsIterator);
        Iterator<Tree<Integer>> iterator = tree.iterator();
        List<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add(iterator.next().getValue());
        }
        List<Integer> expected = Arrays.asList(1, 3, 8, 7, 11, 10, 2, 6, 5, 9, 4);

        Assertions.assertEquals(expected, actual);

    }

}