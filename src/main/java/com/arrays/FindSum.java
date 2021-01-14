package com.arrays;

/*
    Given an array of integers and a value,
    determine if there are any two integers in the array whose sum is equal to the given value.

    Need to find a better solution.
 */

public class FindSum {
    public static void main(String[] args) {
        int[] v1 = new int[]{5, 7, 1, 2, 8, 4, 3};
        int[] v2 = new int[]{3, 20, 1, 2, 7};

        int key = 28;
        for (int i = 0; i < v1.length; i++) {
            for (int j = 0; j < v2.length; j++) {
                if(key == v1[i] + v2[j]) {
                    System.out.println("findSumOfTwo " + v1[i] + " " + v2[j]);
                    break;
                }
            }
        }
    }
}
