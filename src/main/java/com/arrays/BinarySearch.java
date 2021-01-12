package com.arrays;

/*
    Binary search is used to find the index of an element in a sorted array. If the element doesn’t exist, that can be determined efficiently as well.

    The algorithm divides the input array by half at every step. After every step, either we have found the index that we are looking for or half of the array can be discarded. Hence, the solution can be calculated in O(log \space n)O(log n) time.

    Here’s how the algorithm works:

    At every step, consider the array between low and high indices
    Calculate the mid index.
    If the element at the mid index is the key, return mid.
    If the element at mid is greater than the key, then change the index high to mid - 1.
    The index at low remains the same.
    If the element at mid is less than the key, then change low to mid + 1. The index at high remains the same.
    When low is greater than high, the key doesn’t exist and -1 is returned.
 */
public class BinarySearch {
    static int binarySearchRec(int[] a, int key, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + ((high - low) / 2);
        if (a[mid] == key) {
            return mid;
        } else if (key < a[mid]) {
            return binarySearchRec(a, key, low, mid - 1);
        } else {
            return binarySearchRec(a, key, mid + 1, high);
        }
    }

    static int binSearch(int[] a, int key) {
        return binarySearchRec(a, key, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 10, 20, 47, 59, 63, 75, 88, 99, 107, 120, 133, 155, 162, 176, 188, 199, 200, 210, 222};
        int[] inputs = {10, 49, 99, 110, 176};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("binSearch - input = " + inputs[i]+ ") = " +  binSearch(arr, inputs[i]));
        }
    }
}
