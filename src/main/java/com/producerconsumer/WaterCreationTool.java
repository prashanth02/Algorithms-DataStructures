package com.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Write a Java program that simulates a Water manufacturing factory. Your factory has infinitely capable Hydrogen and Oxygen producer units (independent units) provided free of charge by the government. Your pipeline (or consumer) should consume 2 atoms of Hydrogen and one atom of Oxygen to generate one molecule of Water. There are 3 things to note
 * 1.	No wastage of Hydrogen or Oxygen
 * 2.	The size of the pipes that take the output of both the Hydrogen and Oxygen producers is limited in length and can hold only 500 atoms at a time.
 * 3.	The pipeline consumer that combines Hydrogen and Oxygen will take 5 seconds to generate 1 molecule of Water.
 * <p>
 * <p>
 * Write a multi-threaded program to simulate the producer and consumer.  Your factory’s output should be 10 molecules of Water per second.
 */
public class WaterCreationTool {
    public static void main(String[] args) {
        List<Integer> h2OBuilderList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            h2OBuilderList.add(i);
        }

        // Since water has to be generated every second, creating 5 water builders,
        // so that every second 1 water molecule is generated
        ForkJoinPool auditRulePool = new ForkJoinPool(5);
        try {
            auditRulePool.submit(() ->
                    h2OBuilderList.parallelStream().forEach(i -> {
                        new H2OBuilder();
                    })).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Water builder
 */
class H2OBuilder {

    // Pipeline capacity
    int hydrogen = 500;
    int oxygen = 500;

    // Time taken to create water molecule
    int totalTimeTake = 50; // TODO: set it 5000

    java.util.concurrent.Semaphore mutex;

    // Set the number of threads to wait
    java.util.concurrent.CyclicBarrier hydroxyBarrier;

    H2OBuilder() {

        mutex = new java.util.concurrent.Semaphore(0);
        /*
         *	1 for Oxygen Thread
         *	2 for Hydrogen Thread (2 molecules of Hydrogen)
         *  1 thread for building new water molecule
         */
        hydroxyBarrier = new java.util.concurrent.CyclicBarrier(4);
        System.out.println("Initial Oxygen Thread number :" + oxygen);
        System.out.println("Initial Hydrogen Thread number :" + hydrogen);

        //Condition for H2O to form
        while (oxygen != 0 && hydrogen > 1) {
            System.out.println("Oxygen Thread Left:" + oxygen + " Hydrogen Thread Left:" + hydrogen);

            // Creation Oxygen Thread
            Thread OxygenThread = new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                hydroxyBarrier.await();
                            } catch (InterruptedException | java.util.concurrent.BrokenBarrierException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            // Creation Hydrogen Thread
            Thread HydrogenOneThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        hydroxyBarrier.await();
                    } catch (InterruptedException | java.util.concurrent.BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            // Creation Hydrogen Thread
            Thread HydrogenTwoThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        hydroxyBarrier.await();
                    } catch (InterruptedException | java.util.concurrent.BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });

            OxygenThread.start();
            HydrogenOneThread.start();
            HydrogenTwoThread.start();
            try {
                //Reducing the number of Oxygen Thread and Hydrogen after creation of a H2O molecule
                oxygen--;
                hydrogen--;
                hydrogen--;
                hydroxyBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("Oxygen Thread number :" + oxygen);
            System.out.println("Hydrogen Thread number :" + hydrogen);

            try {
                // Creation of water molecule takes 5 seconds
                Thread.sleep(totalTimeTake);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Resetting the Barrier for creation of new molecule
            hydroxyBarrier.reset();
        }
    }

}
