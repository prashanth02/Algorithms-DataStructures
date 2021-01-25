package com.sort;

import java.util.Random;

public class BubbleSort {
    static int SIZE = 10000;
    static Random random = new Random(System.currentTimeMillis());
    static int[] input = new int[SIZE];

    public static void main(String args[]) {
        createTestData();
        long start = System.currentTimeMillis();
        bubbleSort(input);
        long end = System.currentTimeMillis();
        System.out.println("Time taken = " + (end - start));
        bubbleSortImproved(input);
    }

    static void bubbleSort(int[] input) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (input[j] > input[j + 1]) {
                    int tmp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = tmp;
                }
            }
        }
    }

    public static void bubbleSortImproved(int[] array) {
        for (int i = (array.length - 1); i > 0; i--) {
            boolean isSorted = true;
            for (int j = 0; j < i; j++) {
                //If elements in wrong order then swap them
                if (array[j] > array[j + 1]) {
                    isSorted = false;
                    int d = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = d;
                }
            }
            //If no swapping then array is already sorted
            if (isSorted)
                break;
        }
    }

    static void createTestData() {
        for (int i = 0; i < SIZE; i++) {
            input[i] = random.nextInt(10000);
        }
    }
}
