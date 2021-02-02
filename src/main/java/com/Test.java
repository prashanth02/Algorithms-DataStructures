package com;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        solve(10.25,17,5);

//        int arr[] = {3,2,1,0,-1};
////        System.out.println(maximumToys(arr,6));
//        String[] magazine = {"abc", "def", "abc", "def"};
//        String[] note = {"abc", "abc"};
//        rotLeft(arr, 2);

    }

    static void solve(double meal_cost, int tip_percent, int tax_percent) {
        float tip = (float) tip_percent/100;
        float tax = (float) tax_percent/100;
        int cost = (int)Math.round(meal_cost + (meal_cost*tip) + (meal_cost*tax));
        System.out.println(cost);
    }

    static int[] rotLeft(int[] a, int d) {
        ArrayDeque<Integer> result = new ArrayDeque<>();

        for(int al : a) {
            result.add(al);
        }

        while(d > 0) {
            d--;
            int first = result.removeFirst();
            result.addLast(first);
        }

        int[] test = new int[a.length];

        for(int i = 0; i< test.length; i++) {
            test[i] = result.removeFirst();
        }

        return test;
    }



    static void checkMagazine(String[] magazine, String[] note) {
        if(note.length > magazine.length) {
            System.out.println("No");
            return;
        }

        Map<String, Integer> magazineSet = new HashMap<>();

        for(String m : magazine) {
            if(magazineSet.containsKey(m)) {
                Integer value = magazineSet.get(m);
                magazineSet.put(m, (value + 1));
            } else {
                magazineSet.put(m, 1);
            }

        }

        for(String n : note) {
            if(!magazineSet.containsKey(n)) {
                System.out.println("No");
                return;
            } else {
                Integer value = magazineSet.get(n);
                if(value > 0) {
                    magazineSet.put(n, (value - 1));
                    continue;
                } else if(value == 0) {
                    System.out.println("No");
                    return;
                } else {
                    magazineSet.put(n, (value - 1));
                }
            }
        }

        System.out.println("Yes");
    }
}
