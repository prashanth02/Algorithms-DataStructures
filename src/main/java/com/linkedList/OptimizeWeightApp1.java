package com.linkedList;

import java.util.*;

public class OptimizeWeightApp1 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int [] abc = new int []{10, 4, 4, 4, 3};
        System.out.println("The answer is: "+Knapsack(abc));
    }

    private static List<Integer> Knapsack(int[] items) {
                /*print(optimizeBox([20, 15, 20, 50, 20]))
        # -> [50, 15]
        print(optimizeBox([10, 4, 4, 4, 3]))
        # -> [10, 3]
        print(optimizeBox([5, 3, 2, 4, 1, 2]))
        # -> [5,4]
        print(optimizeBox([20, 15, 20, 50, 20]))
        # ->[50,15]
        print(optimizeBox([10, 9, 9, 6, 5, 4, 3, 2, 1]))
        # ->[10,9,9]
        print(optimizeBox([1,2,5,8,4]))
        # -> [5,8]
        print(optimizeBox([2, 1, 1, 1]))
        # -> [1,1,1]*/
        Map<Integer, Integer> weightMap = new HashMap<>();
        // We have built the weight map with counts and total sum
        int totalSum = 0;
        for(int i : items) {
            weightMap.put(i, weightMap.getOrDefault(i,0)+1);
            totalSum += i;
        }
        // sort the array
        items = Arrays.stream(items).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        List<Integer> result = new ArrayList<>();
        int currSum = 0;
        for(int i : items) {
            // Keep adding to current sum with respect to frequency and if at any time current sum is greater than (reducing)
            // totalSum then add the elements to result
            if(currSum + i*weightMap.get(i) > totalSum - i*weightMap.get(i)){
                if(result.size()+weightMap.get(i) < items.length - result.size()-weightMap.get(i)){
                    for(int j=0; j<weightMap.get(i); j++) {
                        currSum += i * weightMap.get(i);
                        totalSum -= i * weightMap.get(i);
                        result.add(i);
                    }
                    break;
                }
            } else {
                currSum += i*weightMap.get(i);
                totalSum -= i*weightMap.get(i);
                result.add(i);
            }
        }

        // Edge case when if we have to consider the contraint of A's weight greater than B and count 'minimal' in A then
        // we might have to consider this where we would construct A such that weight is heavy but count is more than
        // B but still minimal
        if(currSum < totalSum) {
            // This means we will have to return the elements in items which are not in result
            List<Integer> resultB = new ArrayList<>();
            for(int i : items) {
                if(!result.contains(i)) {
                    resultB.add(i);
                }
            }
            result = resultB;
        }
        // Return sorted result
        Collections.sort(result);
        return result;
    }
}
