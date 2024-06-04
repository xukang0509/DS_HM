package com.shanhai.dataStructe._06stack;


import org.junit.jupiter.api.Test;

public class TestStack {
    public static void main(String[] args) {
        System.out.println("main1");
        System.out.println("main2");
        method1();
        method2();
        System.out.println("main3");
    }

    public static void method1() {
        System.out.println("method1");
        method3();
    }

    public static void method2() {
        System.out.println("method2");
    }

    public static void method3() {
        System.out.println("method3");
    }

    @Test
    public void test01() {
        LinkedListStack<Integer> stack = new LinkedListStack<>(3);

        System.out.println("stack.push(1) = " + stack.push(1));
        System.out.println("stack.push(3) = " + stack.push(3));
        System.out.println("stack.push(5) = " + stack.push(5));
        System.out.println("stack.push(7) = " + stack.push(7));
        System.out.println("stack.peek() = " + stack.peek());

        stack.forEach(System.out::println);
        System.out.println("===================================");

        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.peek() = " + stack.peek());

        stack.forEach(System.out::println);
        System.out.println("===================================");

        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.peek() = " + stack.peek());

        stack.forEach(System.out::println);
        System.out.println("===================================");
    }

    @Test
    public void test02() {
        ArrayStack<Integer> stack = new ArrayStack<>(3);

        System.out.println("stack.push(1) = " + stack.push(1));
        System.out.println("stack.push(3) = " + stack.push(3));
        System.out.println("stack.push(5) = " + stack.push(5));
        System.out.println("stack.push(7) = " + stack.push(7));
        System.out.println("stack.peek() = " + stack.peek());

        stack.forEach(System.out::println);
        System.out.println("===================================");

        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.peek() = " + stack.peek());

        stack.forEach(System.out::println);
        System.out.println("===================================");

        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.peek() = " + stack.peek());

        stack.forEach(System.out::println);
        System.out.println("===================================");
    }
}
