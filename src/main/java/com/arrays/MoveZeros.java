package com.arrays;

import java.util.Arrays;
/*
    Move all zeros to the left of an array while maintaining its order.

    Given an integer array, move all elements that are 0 to the left while maintaining the order of other elements in the array.
    The array has to be modified in-place.

    We will keep two markers: read_index and write_index and point them to the end of the array.
    Let’s take a look at an overview of the algorithm:

    While moving read_index towards the start of the array:

    If read_index points to 0, skip.
    If read_index points to a non-zero value,
    write the value at read_index to write_index and decrement write_index.

    Assign zeros to all the values before the write_index and to the current position of write_index as well.
 */

public class MoveZeros {
    public static void main(String[] args) {
        int[] v = new int[]{1, 10, 20, 0, 59, 63, 0, 88, 0};
        System.out.println("Original Array: " + Arrays.toString(v));

        moveZerosToLeft(v);

        System.out.println("After Moving Zeroes to Left: " + Arrays.toString(v));
    }
    static void moveZerosToLeft(int[] A) {
        if (A.length < 1) {
            return;
        }

        int writeIndex = A.length - 1;
        int readIndex = A.length - 1;

        while(readIndex >= 0) {
            if(A[readIndex] != 0) {
                A[writeIndex] = A[readIndex];
                writeIndex--;
            }
            readIndex--;
        }

        while(writeIndex >= 0) {
            A[writeIndex] = 0;
            writeIndex--;
        }
    }
}
