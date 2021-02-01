package com.sort;

import java.util.Arrays;

// Java program for implementation of QuickSort
// https://www.geeksforgeeks.org/quick-sort/?ref=lbp
class QuickSort {

    public static void main(String[] args) {
        // This is unsorted array
        Integer[] array = new Integer[]{12, 13, 24, 10, 3, 6, 90, 70};

        // Let's sort using quick sort
        quickSort(array, 0, array.length - 1);

        // Verify sorted array
        System.out.println(Arrays.toString(array));
    }

//    https://www.youtube.com/watch?v=7h1s2SojIRw&ab_channel=AbdulBari
//    Analysis - https://www.youtube.com/watch?v=-qOVVRIZzao&ab_channel=AbdulBari

//    Important: Select middle element as Pivot, to avoid O(n^2) of a sorted array.
//    There are some arrangements a quick sort can always perform O(n^2)
//    Stack size - best case is O(log n), worst case it is O(n)
    public static void quickSort(Integer[] arr, int low, int high) {
        //check for empty or null array
        if (arr == null || arr.length == 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        //Get the pivot element from the middle of the list
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            //Check until all values on left side array are lower than pivot
            while (arr[i] < pivot) {
                i++;
            }
            //Check until all values on left side array are greater than pivot
            while (arr[j] > pivot) {
                j--;
            }
            //Now compare values from both side of lists to see if they need swapping
            //After swapping move the iterator on both lists
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        //Do same operation as above recursively to sort two sub arrays
        if (low < j) {
            quickSort(arr, low, j);
        }
        if (high > i) {
            quickSort(arr, i, high);
        }
    }

    public static void swap(Integer array[], int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }


    // Didn't like the below, it is O(n^2) because the it selects last element as pivot.
    public static void mainDidntLike(String args[]) {
        int arr[] = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        QuickSort ob = new QuickSort();
        ob.sort(arr, 0, n - 1);

        System.out.println("sorted array");
        printArray(arr);
    }
    /* This function takes last element as pivot,
    places the pivot element at its correct
    position in sorted array, and places all
    smaller (smaller than pivot) to left of
    pivot and all greater elements to right
    of pivot */
    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                // swap arr[i] and arr[j]
                int temp = arr[i++];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


    /* The main function that implements QuickSort()
    arr[] --> Array to be sorted,
    low --> Starting index,
    high --> Ending index */
    void sort(int arr[], int low, int high) {
        if (low < high) {
			/* pi is partitioning index, arr[pi] is
			now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}

