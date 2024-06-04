package com.shanhai.dataStructe._06stack;

import java.util.Iterator;

public class LinkedListStack<E> implements Stack<E>, Iterable<E> {
    private final int capacity;
    private final Node<E> head;
    private int size;

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
        head = new Node<>(null, null);
        this.size = 0;
    }

    @Override
    public boolean push(E value) {
        if (isFull()) return false;
        head.next = new Node<>(value, head.next);
        size++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        E value = head.next.value;
        head.next = head.next.next;
        size--;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = head.next;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
