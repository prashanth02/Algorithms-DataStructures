package com.arrays;
/*
    Given three integer arrays sorted in ascending order,
    return the smallest number that is common in all three arrays.

    We will use three iterators simultaneously to traverse each of the arrays.
    We can start by traversing the arrays starting from the 0th index,
    which will have the smallest value of each array.

    If the values at the array indices pointed to by the three iterators are equal,
    that’s the solution since that will be the smallest value (as arrays are sorted in ascending order)
    present in all of the arrays. Then, we’ll return the value.

    Otherwise, we’ll see which iterator amongst the three points to the smallest value and
    will increment that iterator so that it will point to the next index.

    If any of the three iterators reaches the end of the array while we haven’t found the common number,
    we’ll return -1.
 */
class LeastCommonNumber{
    static Integer findLeastCommonNumber(int[] arr1, int[] arr2, int[] arr3) {
        int i = 0, j = 0, k = 0;

        while(i < arr1.length && j < arr2.length && k < arr3.length) {

            // Finding the smallest common number
            if(arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                return arr1[i];
            }

            // Let's increment the iterator
            // for the smallest value.

            if(arr1[i] <= arr2[j] && arr1[i] <= arr3[k]) {
                i++;
            }

            else if(arr2[j] <= arr1[i] && arr2[j] <= arr3[k]) {
                j++;
            }

            else if(arr3[k] <= arr1[i] && arr3[k] <= arr2[j]) {
                k++;
            }
        }
        return -1;
    }
    public static void main(String []args){
        int[] v1 = new int[]{6, 7, 10, 25, 30, 63, 64};
        int[] v2 = new int[]{1, 4, 5, 6, 7, 8, 50};
        int[] v3 = new int[]{1, 6, 10, 14};

        Integer result = findLeastCommonNumber(v1, v2, v3);
        System.out.println("Least Common Number: " + result);
    }
}
