package com.sort;

/* Java program for Merge Sort */
// https://www.geeksforgeeks.org/merge-sort/?ref=lbp
class MergeSort {
    // Merges two subarrays of arr[].
    // First subarray is arr[begin..pivot]
    // Second subarray is arr[pivot+1..end]
    void merge(int arr[], int begin, int pivot, int end) {
        // Find sizes of two subarrays to be merged
        int sizeTillPivot = pivot - begin + 1;
        int sizePivotTillEnd = end - pivot;

        /* Create temp arrays */
        int leftArr[] = new int[sizeTillPivot];
        int rightArr[] = new int[sizePivotTillEnd];

        /*Copy data to temp arrays*/
        for (int i = 0; i < sizeTillPivot; ++i)
            leftArr[i] = arr[begin + i];
        for (int j = 0; j < sizePivotTillEnd; ++j)
            rightArr[j] = arr[pivot + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = begin;
        while (i < sizeTillPivot && j < sizePivotTillEnd) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of leftArr[] if any */
        while (i < sizeTillPivot) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        /* Copy remaining elements of rightArr[] if any */
        while (j < sizePivotTillEnd) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[beginning..end] using merge()
    void sort(int arr[], int beginning, int end) {
        if (beginning < end) {
            // Find the middle point
            int m = (beginning + end) / 2;

            // Sort first and second halves
            sort(arr, beginning, m);
            sort(arr, m + 1, end);

            // Merge the sorted halves
            merge(arr, beginning, m, end);
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver code
    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6, 7};

        System.out.println("Given Array");
        printArray(arr);

        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array");
        printArray(arr);
    }
}
