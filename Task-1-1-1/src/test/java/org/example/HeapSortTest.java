package org.example;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HeapSortTest {

    private HeapSort heapSort = new HeapSort();

    @Test
    void test1() {
        final int[] testArray = {6, 3, 7, 1, 9, 5};
        final int[] expectedArray = {1, 3, 5, 6, 7, 9};
        heapSort.hsort(testArray,6);

        assertArrayEquals(testArray, expectedArray);
    }

    @Test
    void test2() {
        final int[] testArray = {3};
        final int[] expectedArray = {3};
        heapSort.hsort(testArray,1);

        assertArrayEquals(testArray, expectedArray);
    }

    @Test
    void test3() {
        final int[] testArray = {};
        final int[] expectedArray = {};
        heapSort.hsort(testArray,0);

        assertArrayEquals(testArray, expectedArray);
    }

    @Test
    void test4() {
        final int[] testArray = {11, 9, 7, 7, 4};
        final int[] expectedArray = {4,7,7,9,11};
        heapSort.hsort(testArray,5);

        assertArrayEquals(testArray, expectedArray);
    }

    @Test
    void test5() {
        final int[] testArray = {6, 3, -7, 1, 9, 5};
        final int[] expectedArray = {-7, 1, 3, 5, 6, 9};
        heapSort.hsort(testArray,6);

        assertArrayEquals(testArray, expectedArray);
    }

}