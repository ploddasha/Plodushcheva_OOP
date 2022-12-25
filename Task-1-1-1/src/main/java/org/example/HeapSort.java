package org.example;

/**
 * HeapSort is a comparison-based sorting technique based on binary heap data structure.
 * The time required to perform Heap sort increases logarithmically.
 */
public class HeapSort {

    /**
     * Array converts into heap data structure with heapify,
     * then one by one it deletes the root node of the Max-heap and
     * replaces it with the last node in the heap and then heapify the root of the heap.
     * This process repeats until size of heap is greater than 1.
     *
     * @param arr is the array we will sort
     * @param n is the length of array
     */
    public static void hsort(int[] arr, int n) {
        for (int i = (n / 2 - 1); i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = (n - 1); i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    /**
     * Heapify is the process of creating a heap data structure
     * from a binary tree represented using an array.
     * It is used to create max-heap.
     *
     * @param arr is the array
     * @param n is the length of array
     * @param i is the index of root node of subtree
     */
    private static void heapify(int[] arr, int n, int i) {

        int maxid = i;
        if ((2 * i + 2) < n && arr[maxid] < arr[2 * i + 2]) {
            maxid = 2 * i + 2;
        }
        if ((2 * i + 1) < n && arr[maxid] < arr[2 * i + 1]) {
            maxid = 2 * i + 1;
        }
        if (maxid != i) {
            int temp = arr[maxid];
            arr[maxid] = arr[i];
            arr[i] = temp;
            heapify(arr, n, maxid);
        }
    }
}
