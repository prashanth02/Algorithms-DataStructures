package com.concurrency;

public class Temp {
    private final ThreadLocal<String> testTL = new ThreadLocal();

    private Temp() {
    }

    public static Temp getInstance() {
        return new Temp();
    }

    public String getThreadLocal() {
//        System.out.println("Get = " + testTL.get() + " Thread name = " + Thread.currentThread().getName());
        return testTL.get();
    }

    public void setThreadLocal(String test) {
        if (test == null && testTL.get() == null)
            System.out.println("test = " + test + " testTL.get() = " + testTL.get() + " Thread name = " + Thread.currentThread().getName());
        if (test != null && testTL.get() != null)
            System.out.println("test = " + test + " testTL.get() = " + testTL.get() + " Thread name = " + Thread.currentThread().getName());
        this.testTL.set(test);
    }
}