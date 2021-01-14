package com.arrays;

import java.util.Arrays;
import java.util.List;
/*
    Given an array of integers, rotate the array by 'N' elements.

    For positive values of NN, perform a right rotation.
    For negative values of NN, perform a left rotation.
    Make sure you make changes to the original array.
 */
class RotateArray{
    static void reverseArray(List<Integer> arr, int start, int end) {
        while (start < end) {
            int temp = arr.get(start);
            arr.set(start, arr.get(end));
            arr.set(end, temp);
            ++start;
            --end;
        }
    }

    static void rotateArray(List<Integer> arr, int n) {

        int len = arr.size();
        // Let's normalize rotations
        // if n > array size or n is negative.
        n = n % len;
        if (n < 0) {
            // calculate the positive rotations needed.
            n = n + len;
        }

        // Let's partition the array based on rotations 'n'.
        // For example: 1, 2, 3, 4, 5 where n = 2.
        // -> 5, 4, 3, 2, 1
        // -> 4, 5, 3, 2, 1
        // -> 4, 5, 1, 2, 3

        reverseArray(arr, 0, len-1);
        reverseArray(arr, 0, n-1);
        reverseArray(arr, n, len-1);
    }
    public static void main(String []args){
        List<Integer> arr = Arrays.asList(1, 10, 20, 0, 59, 86, 32, 11, 9, 40);
        System.out.println("Array Before Rotation\n"+arr);
        rotateArray(arr, 2);
        System.out.println("Array After Rotation\n"+arr);
    }
}
