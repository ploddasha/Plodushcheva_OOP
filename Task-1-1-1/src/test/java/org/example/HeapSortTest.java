/**
 * Test.
 */

package org.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;


class HeapSortTest {

    private HeapSort heapSort = new HeapSort();

    @Test
    void testRegularArray() {
        final int[] testArray = {6, 3, 7, 1, 9, 5};
        final int[] expectedArray = {1, 3, 5, 6, 7, 9};
        heapSort.hsort(testArray, 6);

        assertArrayEquals(testArray, expectedArray);
    }

    @Test
    void testOneElementArray() {
        final int[] testArray = {3};
        final int[] expectedArray = {3};
        heapSort.hsort(testArray, 1);

        assertArrayEquals(testArray, expectedArray);
    }

    @Test
    void testEmptyArray() {
        final int[] testArray = {};
        final int[] expectedArray = {};
        heapSort.hsort(testArray, 0);

        assertArrayEquals(testArray, expectedArray);
    }

    @Test
    void testReversedArray() {
        final int[] testArray = {11, 9, 7, 7, 4};
        final int[] expectedArray = {4, 7, 7, 9, 11};
        heapSort.hsort(testArray, 5);

        assertArrayEquals(testArray, expectedArray);
    }

    @Test
    void testNegativeArray() {
        final int[] testArray = {6, 3, -7, 1, 9, 5};
        final int[] expectedArray = {-7, 1, 3, 5, 6, 9};
        heapSort.hsort(testArray, 6);

        assertArrayEquals(testArray, expectedArray);
    }

}