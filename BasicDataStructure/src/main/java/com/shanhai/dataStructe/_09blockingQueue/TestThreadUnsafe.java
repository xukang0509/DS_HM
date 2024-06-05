package com.shanhai.dataStructe._09blockingQueue;

import java.util.Arrays;

public class TestThreadUnsafe {
    private final String[] array = new String[10];
    private int size;

    public static void main(String[] args) throws InterruptedException {
        TestThreadUnsafe queue = new TestThreadUnsafe();
        new Thread(() -> queue.offer("e2"), "t2").start();
        new Thread(() -> queue.offer("e1"), "t1").start();

        Thread.sleep(100);
        System.out.println("queue = " + queue);
    }

    public void offer(String e) {
        array[size] = e;
        size++;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
