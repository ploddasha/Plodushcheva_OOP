/**
 * Main.
 */

package org.example;

/**
 * HeapSort.
 */
public class HeapSort {

    /**
     * sorting.
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
     * build the max heap.
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
