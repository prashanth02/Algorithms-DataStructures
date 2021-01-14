package com.arrays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
    Given a large array of integers and a window of size ww,
    find the current maximum value in the window as the window slides through the entire array.

    Let’s try to find all maximums for a window size equal to 33 in the array given below:
        -4
        2
        -5
        3
        6
        Step 1: For the first 3 elements in the window, max is 2.
            max = 2
        Step 2: Slide window one position to the right and max for window becomes 3.
            max = 3
        Step 3: In the last window, max is 6.
        max = 6

        The memory complexity of this solution is linear, O(w)O(w), where ww is the window size in this case.

    The algorithm uses the deque data structure to find the maximum in a window.
    A deque is a double-ended queue in which push and pop operations work in O(1)O(1) at both ends.
    It will act as our window.

    At the start of the algorithm, we search for the maximum value in the first window.
    The first element’s index is pushed to the front of the deque.

    If an element is smaller than the one at the back of the queue, then the index of this element is pushed in and becomes the new back.
    If the current element is larger, the back of the queue is popped repeatedly until we can find a higher value,
    and then we’ll push the index of the current element in as the new back.

    As we can see, the deque stores elements in decreasing order.
    The front of the deque contains the index for the maximum value in that particular window.

    We will repeat the following steps each time our window moves to the right:

    Remove the indices of all elements from the back of the deque,
    which are smaller than or equal to the current element.

    If the element no longer falls in the current window, remove the index of the element from the front.

    Push the current element index at the back of the window.

    The index of the current maximum element is at the front.

    Let’s run an example with window size = 3=3 on the array below:
 */
public class MaxSlidingWindow {
    public static ArrayDeque<Integer> findMaxSlidingWindow(int[] arr, int windowSize) {

        ArrayDeque<Integer> result = new ArrayDeque<>(); // ArrayDeque for storing values
        Deque<Integer> list = new LinkedList<>(); // creating a linked list to store the index

        if (arr.length > 0) {

            if (arr.length < windowSize) // Invalid State
                return result;

            for (int i = 0; i < windowSize; ++i) {
                // Removing last smallest element index
                while (!list.isEmpty() && arr[i] >= arr[list.peekLast()]) {
                    list.removeLast();
                }

                // Adding newly picked element index
                list.addLast(i);
            }

            for (int i = windowSize; i < arr.length; ++i) {
                result.add(arr[list.peek()]);

                // Removing all the elements indexes which are not in the current window
                while ((!list.isEmpty()) && list.peek() <= i - windowSize)
                    list.removeFirst();

                // Removing the smaller elements indexes which are not required
                while ((!list.isEmpty()) && arr[i] >= arr[list.peekLast()])
                    list.removeLast();

                // Adding newly picked element index
                list.addLast(i);
            }

            // Adding the max number of the current window in the result
            result.add(arr[list.peek()]);
            return result; // returning result
        } else
            return result;
    }

    public static void main(String[] args) {
//        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        System.out.println("Array = " + Arrays.toString(array));
//        System.out.println("Max = " + findMaxSlidingWindow(array, 3));

        int[] array2 = {10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67};
        System.out.println("Array = " + Arrays.toString(array2));
        System.out.println("Max = " + findMaxSlidingWindow(array2, 3));
    }
}
