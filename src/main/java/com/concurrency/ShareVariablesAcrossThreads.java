package com.concurrency;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicReference;

public class ShareVariablesAcrossThreads {

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    public static void main(String[] args) throws Exception {
        threadShareTest();
    }

    private static Map concurrentHashMapTest() {
        List<String> stringList = new ArrayList<>();

        //10 million, 10 to the power 7 we can find the difference in the total count in a HashMap vs ConcurrentHasMap is used.
        for(int i=0; i<10; i++) {
            stringList.add(""+i);
        }

        Map<String, String> stringConcurrentHashMap = new ConcurrentHashMap<>();
        stringList.parallelStream().forEach(e -> {
            stringConcurrentHashMap.put(e, e);
        });

        System.out.println("Map size = " + stringConcurrentHashMap.size());

        return stringConcurrentHashMap;
    }

    //The below code proves that each thread has a separate set of stack of variables.
    private static Map threadShareTest() throws Exception {
        List<Integer> integerArrayList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            integerArrayList.add(i);
        }

        Map<Integer, Integer> integerConcurrentHashMap = new ConcurrentHashMap<>();

//        integerArrayList.parallelStream().forEach(e -> {
//            integerConcurrentHashMap.put(integerArrayList.get(e, e));
//        });

        Temp temp = Temp.getInstance();
        AtomicReference<Integer> i = new AtomicReference<>(new Integer(1));

        System.out.println("******** = " + Runtime.getRuntime().availableProcessors());

        //ForkJoinPool doesn't use the main thread
        ForkJoinPool myPool = new ForkJoinPool();
        myPool.submit(() ->
        integerArrayList.parallelStream().forEach(e -> {
            try {
                System.out.println("Thread name = " + Thread.currentThread().getName() + " Arraylist value = " + e);
                i.getAndSet(i.get() + 1);
                temp.setThreadLocal(i.get() + "");
//                if (i.get() % 2 == 0) {
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException interruptedException) {
//                        interruptedException.printStackTrace();
//                    }
//                } else {
//                    try {
//                        Thread.sleep(20);
//                    } catch (InterruptedException interruptedException) {
//                        interruptedException.printStackTrace();
//                    }
//                }
            } finally {
                temp.setThreadLocal(null);
            }
        })
                //Get will wait till all tasks are completed
    ).get();

        System.out.println("Map size = " + integerConcurrentHashMap.size());

        return integerConcurrentHashMap;
    }
}