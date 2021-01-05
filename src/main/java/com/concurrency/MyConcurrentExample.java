package com.concurrency;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/*

    TODO : ThreadLocal

    Consumer-Producer
    -----------
    BlockingDeque<E>
    A Deque that additionally supports blocking operations that wait for the deque to become non-empty when retrieving an element,
    and wait for space to become available in the deque when storing an element.

    BlockingQueue<E>
    A Queue that additionally supports operations that wait for the queue to become non-empty when
    retrieving an element, and wait for space to become available in the queue when storing an element.
    --------

    ExecutorService is a complete solution for asynchronous processing.
    It manages an in-memory queue and schedules submitted tasks based on thread availability.
    An Executor that provides methods to manage termination and methods that can produce
    a Future for tracking progress of one or more asynchronous tasks.


    Future.get() blocks the thread
    CompletableFuture gives the callback methods

    Futures were introduced in Java 5 (2004).
    They're basically placeholders for a result of an operation that hasn't finished yet.
    Once the operation finishes, the Future will contain that result. For example,
    an operation can be a Runnable or Callable instance that is submitted to an ExecutorService.
    The submitter of the operation can use the Future object to check whether the operation isDone(),
    or wait for it to finish using the blocking get() method.

    CompletableFutures were introduced in Java 8 (2014).
    They are in fact an evolution of regular Futures, inspired by Google's Listenable Futures,
    part of the Guava library. They are Futures that also allow you to string tasks together in a chain.
    You can use them to tell some worker thread to "go do some task X, and when you're done,
    go do this other thing using the result of X".
    Using CompletableFutures, you can do something with the result of the operation without actually blocking a thread to wait for the result.

    CountDownLatch - CountDownLatch (introduced in JDK 5) is a utility class which blocks a set of threads until some operation completes.
    A CountDownLatch is initialized with a counter(Integer type); this counter decrements as the dependent threads complete execution.
    But once the counter reaches zero, other threads get released.

    CyclicBarrier works almost the same as CountDownLatch except that we can reuse it

    The Semaphore is used for blocking thread level access to some part of the physical or logical resource.

    ThreadFactory acts as a thread (non-existing) pool which creates a new thread on demand.

    one of the most common integration patterns is the producer-consumer pattern.
    The java.util.concurrent package comes with a data-structure know as BlockingQueue –
    which can be very useful in these async scenarios.

    https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html
 */
public class MyConcurrentExample {
    public static void main(String[] args) {
        executorServiceExample();
//        testScheduledExecutorService();
        testFuture();

        testCompletableFuture();
    }

    private static void testCompletableFuture() {
        CompletableFuture<String> futureOperation = CompletableFuture.supplyAsync(
                () -> {
                    return "Hello I am here";
                });
        try {
            futureOperation.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void executorServiceExample() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        executor.shutdown();

        try {
            executor.awaitTermination(20l, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  Below are some of the important methods
        //  Initiates an orderly shutdown in which previously submitted
        //  tasks are executed, but no new tasks will be accepted.
//        executor.shutdown();
//        executor.invokeAll();
//        executor.invokeAny();
        //Blocks until all tasks have completed execution after a shutdown
//        executor.awaitTermination();
    }

    private static void testScheduledExecutorService() {
        ScheduledExecutorService executorService
                = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {
            System.out.println(LocalDateTime.now());
        }, 1, 1, TimeUnit.SECONDS);

        executorService.scheduleWithFixedDelay(() -> {
            System.out.println(LocalDateTime.now());
        }, 1, 1, TimeUnit.SECONDS);
    }

    private static void testFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<String> future = executorService.submit(() -> {
            return "Hello world";
        });

        try {
            String str = future.get();
            System.out.println(str);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
