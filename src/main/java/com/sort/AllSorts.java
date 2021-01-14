package com.sort;

import java.util.Arrays;
import java.util.Random;

/*
    Generally, time and space have an inversely proportional relationship with each other;
    if space increases then execution time decreases, and if execution time increases
    then space decreases.

    However, in the case of algorithms, there are too many variations that can affect an algorithm's performance:
    the hardware used for the test, the characteristics of the input fed to the algorithms,
    the operating system or other programs concurrently running on the system, etc.
    All these factors can affect an algorithm's performance.


 */
public class AllSorts {
    static int SIZE = 10000;
    static Random random = new Random(System.currentTimeMillis());
    static int[] input = new int[SIZE];

    public static void main(String args[]) {
        createTestData();
        long start = System.currentTimeMillis();
        bubbleSort(input);
        long end = System.currentTimeMillis();
        System.out.println("Time taken = " + (end - start));

        createTestData();
        start = System.currentTimeMillis();
        mergeSort(0, input.length - 1, input);
        end = System.currentTimeMillis();
        System.out.println("Time taken = " + (end - start));

        createTestData();
        start = System.currentTimeMillis();
        Arrays.sort(input);
        end = System.currentTimeMillis();
        System.out.println("Time taken = " + (end - start));
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

    static void createTestData() {
        for (int i = 0; i < SIZE; i++) {
            input[i] = random.nextInt(10000);
        }
    }

    static int[] scratch = new int[SIZE];

    static void mergeSort(int start, int end, int[] input) {

        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;

        // sort first half
        mergeSort(start, mid, input);

        // sort second half
        mergeSort(mid + 1, end, input);

        // merge the two sorted arrays
        int i = start;
        int j = mid + 1;
        int k;

        for (k = start; k <= end; k++) {
            scratch[k] = input[k];
        }

        k = start;
        while (k <= end) {

            if (i <= mid && j <= end) {
                input[k] = Math.min(scratch[i], scratch[j]);

                if (input[k] == scratch[i]) {
                    i++;
                } else {
                    j++;
                }
            } else if (i <= mid && j > end) {
                input[k] = scratch[i];
                i++;
            } else {
                input[k] = scratch[j];
                j++;
            }
            k++;
        }
    }
}
