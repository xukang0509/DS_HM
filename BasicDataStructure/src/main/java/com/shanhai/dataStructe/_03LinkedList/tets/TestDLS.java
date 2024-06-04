package com.shanhai.dataStructe._03LinkedList.tets;

import com.shanhai.dataStructe._03LinkedList.DoubleLinkedListSentinel;
import org.junit.jupiter.api.Test;


public class TestDLS {
    @Test
    public void test01() {
        DoubleLinkedListSentinel<Integer> list = new DoubleLinkedListSentinel<>();
        list.addFirst(0);
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.forEach(System.out::println);


        System.out.println("=========================");

        list.removeFirst();
        list.removeFirst();

        list.forEach(System.out::println);
        System.out.println("=========================");

        list.addLast(11);
        list.addLast(12);
        list.addLast(13);

        list.forEach(System.out::println);
        System.out.println("=========================");


        list.removeLast();
        list.removeLast();
        list.forEach(System.out::println);
        System.out.println("=========================");

        list.remove(1);
        list.forEach(System.out::println);
        System.out.println("=========================");

        list.insert(1, 22);
        list.forEach(System.out::println);
        System.out.println("=========================");

    }
}
