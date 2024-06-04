package com.shanhai.dataStructe._03LinkedList.tets;

import com.shanhai.dataStructe._03LinkedList.SingleLinkedListSentinel;
import org.junit.jupiter.api.Test;


/**
 * @description:
 * @author: xu
 * @date: 2024/5/15 22:33
 */
public class TestSLS {
    @Test
    public void test06() {
        SingleLinkedListSentinel<Integer> list = new SingleLinkedListSentinel<>();
        list.addLast(1, 2, 3, 4, 5, 6, 7, 8);

        list.add(3, 5);
        list.add(0, 10);
        for (Integer num : list) {
            System.out.println("num = " + num);
        }
    }


    @Test
    public void test05() {
        SingleLinkedListSentinel<Integer> list = new SingleLinkedListSentinel<>();
        list.addLast(1, 2, 3, 4, 5, 6, 7, 8);

        list.loopFor(System.out::print);
        System.out.println();
        System.out.println("=======================================");
        list.remove(7);
        list.loopFor(System.out::print);
        System.out.println();
        System.out.println("=======================================");
        list.remove(1);
        list.loopFor(System.out::print);
        System.out.println();
        System.out.println("=======================================");
        list.remove(0);
        list.loopFor(System.out::print);
        System.out.println();
        System.out.println("=======================================");
        list.remove(0);
        list.loopFor(System.out::print);
        System.out.println();
        System.out.println("=======================================");
        list.remove(0);
        list.loopFor(System.out::print);
        System.out.println();
        System.out.println("=======================================");
        list.remove(0);
        list.loopFor(System.out::print);
        System.out.println();
        System.out.println("=======================================");
        list.remove(0);
        list.loopFor(System.out::print);
        System.out.println();
        System.out.println("=======================================");
        list.remove(0);
        list.loopFor(System.out::print);
        System.out.println();
        list.remove(0);

    }

    @Test
    public void test04() {
        SingleLinkedListSentinel<Integer> list = new SingleLinkedListSentinel<>();
        list.addLast(1, 2, 3, 4, 5, 6, 7, 8);

        System.out.println(list.get(0));
        System.out.println(list.get(2));
        System.out.println(list.get(4));
        System.out.println(list.get(6));
        System.out.println(list.get(8));
    }

    @Test
    public void test03() {
        SingleLinkedListSentinel<Integer> list = new SingleLinkedListSentinel<>();

        list.addLast(1, 3, 2, 5, 6, 7);


        list.loopFor(System.out::println);
        System.out.println("======================================");
        list.loopWhile(System.out::println);
        System.out.println("=====================================");
        for (Integer integer : list) {
            System.out.println("integer = " + integer);
        }
    }


    @Test
    public void test02() {
        SingleLinkedListSentinel<Integer> list = new SingleLinkedListSentinel<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        list.loopFor(System.out::println);
        System.out.println("======================================");
        list.loopWhile(System.out::println);
        System.out.println("=====================================");
        for (Integer integer : list) {
            System.out.println("integer = " + integer);
        }
    }

    @Test
    public void test01() {
        SingleLinkedListSentinel<Integer> list = new SingleLinkedListSentinel<>();

        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        list.loopFor(System.out::println);
        System.out.println("======================================");
        list.loopWhile(System.out::println);
        System.out.println("=====================================");
        for (Integer integer : list) {
            System.out.println("integer = " + integer);
        }
    }
}
