package org.example;

public class HeapSort {
    public static void hsort(int arr[], int n) {
        for (int i = (n / 2 - 1); i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = (n - 1); i > 0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }
    public static void heapify(int arr[], int n, int i)
    {
        int maxid = i;

        if ((2*i+2) < n && arr[maxid] < arr[2 * i + 2])
            maxid = 2 * i + 2;
        if ((2*i+1) < n && arr[maxid] < arr[2 * i + 1])
            maxid = 2 * i + 1;

        if (maxid != i) {
            int temp = arr[maxid];
            arr[maxid] = arr[i];
            arr[i] = temp;
            heapify(arr, n, maxid);
        }

    }
}
