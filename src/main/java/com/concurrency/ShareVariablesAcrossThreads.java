package com.concurrency;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShareVariablesAcrossThreads {
    private static final ThreadLocal<String> testThreadLocal = new ThreadLocal();

    public static void main(String[] args) throws Exception {
//        concurrentHashMapTest();

    }

    /*private static Map concurrentHashMapTest() throws Exception {
        List<String> stringList = new ArrayList<>();

        //10 million, 10 to the power 7 we can find the difference in the total count if just HashMap is used.
        for(int i=0; i<100; i++) {
            stringList.add(""+i);
        }

        int i = 0;
        Map<String, String> stringConcurrentHashMap = new ConcurrentHashMap<>();
        stringList.parallelStream().forEach(e -> {
            i++;
            if(i%2) {
                Thread.sleep(1000);
            } else {
                Thread.sleep(2000);
            }

            stringConcurrentHashMap.put(e, e);
        });

        System.out.println("Map size = " + stringConcurrentHashMap.size());

        return stringConcurrentHashMap;
    }*/
 }