package com.shanhai.dataStructe._05queue.test;

import com.shanhai.dataStructe._05queue.ArrayQueue;
import com.shanhai.dataStructe._05queue.ArrayQueue2;
import com.shanhai.dataStructe._05queue.ArrayQueue3;
import com.shanhai.dataStructe._05queue.LinkedListQueue;
import org.junit.jupiter.api.Test;


public class Test01 {
    @Test
    public void test01() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println("queue.size() = " + queue.size());

        queue.forEach(System.out::println);
        System.out.println("========================");


        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.size() = " + queue.size());
        queue.forEach(System.out::println);
        System.out.println("========================");

        System.out.println("queue.isEmpty() = " + queue.isEmpty());

        System.out.println("queue.peek() = " + queue.peek());

        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.isEmpty() = " + queue.isEmpty());
        System.out.println("queue.size() = " + queue.size());
        queue.forEach(System.out::println);
    }


    @Test
    public void test02() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(20);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println("queue.size() = " + queue.size());

        queue.forEach(System.out::println);
        System.out.println("========================");


        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.size() = " + queue.size());
        queue.forEach(System.out::println);
        System.out.println("========================");

        System.out.println("queue.isEmpty() = " + queue.isEmpty());

        System.out.println("queue.peek() = " + queue.peek());

        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.isEmpty() = " + queue.isEmpty());
        System.out.println("queue.size() = " + queue.size());
        queue.forEach(System.out::println);
    }


    @Test
    public void test03() {
        ArrayQueue2<Integer> queue = new ArrayQueue2<>(3);
        System.out.println("queue.offer(1) = " + queue.offer(1));
        System.out.println("queue.offer(2) = " + queue.offer(2));
        System.out.println("queue.offer(3) = " + queue.offer(3));
        System.out.println("queue.offer(4) = " + queue.offer(4));


        queue.forEach(System.out::println);
        System.out.println("queue.size() = " + queue.size());
        System.out.println("queue.isFull() = " + queue.isFull());
        System.out.println("========================");


        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.size() = " + queue.size());
        queue.forEach(System.out::println);
        System.out.println("========================");

        System.out.println("queue.isEmpty() = " + queue.isEmpty());

        System.out.println("queue.peek() = " + queue.peek());

        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.isEmpty() = " + queue.isEmpty());
        System.out.println("queue.size() = " + queue.size());
        queue.forEach(System.out::println);
    }

    @Test
    public void test04() {
        ArrayQueue3<Integer> queue = new ArrayQueue3<>(5);
        System.out.println("queue.offer(1) = " + queue.offer(1));
        System.out.println("queue.offer(2) = " + queue.offer(2));
        System.out.println("queue.offer(3) = " + queue.offer(3));
        System.out.println("queue.offer(4) = " + queue.offer(4));
        System.out.println("queue.offer(5) = " + queue.offer(5));

        queue.forEach(System.out::println);
        System.out.println("queue.size() = " + queue.size());
        System.out.println("queue.isFull() = " + queue.isFull());
        System.out.println("========================");


        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.size() = " + queue.size());
        queue.forEach(System.out::println);
        System.out.println("========================");

        System.out.println("queue.isEmpty() = " + queue.isEmpty());

        System.out.println("queue.peek() = " + queue.peek());

        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.isEmpty() = " + queue.isEmpty());
        System.out.println("queue.size() = " + queue.size());
        queue.forEach(System.out::println);
    }
}
