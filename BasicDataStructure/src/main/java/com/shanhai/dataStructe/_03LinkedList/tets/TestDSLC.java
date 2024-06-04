package com.shanhai.dataStructe._03LinkedList.tets;

import com.shanhai.dataStructe._03LinkedList.DoublyLinkedListSentinel;
import org.junit.jupiter.api.Test;


public class TestDSLC {
    @Test
    public void test01() {
        DoublyLinkedListSentinel<Integer> list = new DoublyLinkedListSentinel<>();
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


        list.removeByValue(0);

        list.forEach(System.out::println);
        System.out.println("=========================");
    }
}
