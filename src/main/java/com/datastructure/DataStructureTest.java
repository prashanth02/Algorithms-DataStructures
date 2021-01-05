package com.datastructure;

public class DataStructureTest {

    public static void main(String args[]) {
        System.out.println("Test stack");
        MyStack myStack = new MyStack();
        myStack.push("1");
        myStack.push("2");
        myStack.push("3");
        myStack.pop();
        myStack.pop();
        myStack.pop();

        System.out.println("Test Dequeue");

        MyDeque deque = new MyDeque();
        deque.addFirst("10");
        deque.addFirst("20");
        deque.addLast("90");

        deque.removeFirst();
        deque.removeLast();

    }
}
