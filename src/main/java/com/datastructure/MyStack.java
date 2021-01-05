package com.datastructure;

import java.util.Deque;

/**
 * From java documentation
 *
 * The class represents a last-in-first-out
 * (LIFO) stack of objects.
 * <p>
 * When a stack is first created, it contains no items.
 *
 * <p>A more complete and consistent set of LIFO stack operations is
 * provided by the {@link Deque} interface and its implementations, which
 * should be used in preference to this class.  For example:
 * <pre>   {@code
 *   Deque<Integer> stack = new ArrayDeque<Integer>();}</pre>
 */
public class MyStack extends MyDeque {

    public void push(Object e) {
        System.out.println("Push object = " + e);
        addFirst(e);
    }

    public Object pop() {
        Object e = removeFirst();
        System.out.println("Pop object = " + e);
        return e;
    }
}
