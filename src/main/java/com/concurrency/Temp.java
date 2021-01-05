package com.concurrency;

public class Temp {
    private static final ThreadLocal<String> testTL = new ThreadLocal();

    public Temp() {

    }

    public String getThreadLocal() {
        return testTL.get();
    }

    public void setThreadLocal(String test) {
        this.testTL.set(test);
    }
}