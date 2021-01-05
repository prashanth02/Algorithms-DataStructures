package com.datastructure;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyDeque {

    /**
     * Constructs an empty array deque with an initial capacity
     * sufficient to hold 16 elements.
     */
    public MyDeque() {
        elements = new Object[16];
    }

    /**
     * The array in which the elements of the deque are stored.
     * All array cells not holding deque elements are always null.
     * The array always has at least one null slot (at tail).
     */
    transient Object[] elements;

    /**
     * The index of the element at the head of the deque (which is the
     * element that would be removed by remove() or pop()); or an
     * arbitrary number 0 <= head < elements.length equal to tail if
     * the deque is empty.
     */
    transient int head;

    /**
     * The index at which the next element would be added to the tail
     * of the deque (via addLast(E), add(E), or push(E));
     * elements[tail] is always null.
     */
    transient int tail;

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increases the capacity of this deque by at least the given amount.
     *
     */
    public void addFirst(Object e) {
        if (e == null)
            throw new NullPointerException();
        final Object[] es = elements;
        es[head = dec(head, es.length)] = e;
        if (head == tail)
            grow(1);
        System.out.println("Added to first " + e.toString());
    }

    /**
     * Circularly decrements i, mod modulus.
     * Precondition and postcondition: 0 <= i < modulus.
     */
    static final int dec(int i, int modulus) {
        if (--i < 0) i = modulus - 1;
        return i;
    }

    private void grow(int needed) {
        // overflow-conscious code
        final int oldCapacity = elements.length;
        int newCapacity;
        // Double capacity if small; else grow by 50%
        int jump = (oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1);
        if (jump < needed
                || (newCapacity = (oldCapacity + jump)) - MAX_ARRAY_SIZE > 0)
            newCapacity = newCapacity(needed, jump);
        final Object[] es = elements = Arrays.copyOf(elements, newCapacity);
        // Exceptionally, here tail == head needs to be disambiguated
        if (tail < head || (tail == head && es[head] != null)) {
            // wrap around; slide first leg forward to end of array
            int newSpace = newCapacity - oldCapacity;
            System.arraycopy(es, head,
                    es, head + newSpace,
                    oldCapacity - head);
            for (int i = head, to = (head += newSpace); i < to; i++)
                es[i] = null;
        }
    }

    /** Capacity calculation for edge conditions, especially overflow. */
    private int newCapacity(int needed, int jump) {
        final int oldCapacity = elements.length, minCapacity;
        if ((minCapacity = oldCapacity + needed) - MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0)
                throw new IllegalStateException("Sorry, deque too big");
            return Integer.MAX_VALUE;
        }
        if (needed > jump)
            return minCapacity;
        return (oldCapacity + jump - MAX_ARRAY_SIZE < 0)
                ? oldCapacity + jump
                : MAX_ARRAY_SIZE;
    }

    public Object removeFirst() {
        Object e = pollFirst();
        System.out.println("Removing first " + e.toString());
        if (e == null)
            throw new NoSuchElementException();
        return e;
    }

    public Object pollFirst() {
        final Object[] es;
        final int h;
        Object e = elementAt(es = elements, h = head);
        if (e != null) {
            es[h] = null;
            head = inc(h, es.length);
        }
        return e;
    }

    /**
     * Circularly increments i, mod modulus.
     * Precondition and postcondition: 0 <= i < modulus.
     */
    static final int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }

    /**
     * Returns element at array index i.
     * This is a slight abuse of generics, accepted by javac.
     */
    @SuppressWarnings("unchecked")
    static final <E> E elementAt(Object[] es, int i) {
        return (E) es[i];
    }

    /**
     * Inserts the specified element at the end of this deque.
     *
     * <p>This method is equivalent to {@link #}.
     *
     * @param e the element to add
     * @throws NullPointerException if the specified element is null
     */
    public void addLast(Object e) {
        if (e == null)
            throw new NullPointerException();
        final Object[] es = elements;
        es[tail] = e;
        if (head == (tail = inc(tail, es.length)))
            grow(1);
        System.out.println("Added to last " + e.toString());
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public Object removeLast() {
        Object e = pollLast();
        System.out.println("Removing last " + e.toString());
        if (e == null)
            throw new NoSuchElementException();
        return e;
    }

    public Object pollLast() {
        final Object[] es;
        final int t;
        Object e = elementAt(es = elements, t = dec(tail, es.length));
        if (e != null)
            es[tail = t] = null;
        return e;
    }

}
