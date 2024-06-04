package com.shanhai.dataStructe._03LinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @description:
 * @author: xu
 * @date: 2024/5/15 22:12
 */
public class SingleLinkedList<T> implements Iterable<T> {
    private Node<T> head; // 头部节点

    public SingleLinkedList() {
        this.head = null;
    }

    /**
     * 添加首部节点
     *
     * @param value
     */
    public void addFirst(T value) {
        this.head = new Node<>(value, this.head);
    }

    /**
     * 尾部添加节点
     *
     * @param value
     */
    public void addLast(T value) {
        Node<T> last = findLast();
        if (last == null) { // 链表中无节点
            addFirst(value);
            return;
        }
        last.next = new Node<>(value, null);
    }

    /**
     * 尾部添加多个节点
     *
     * @param first
     * @param rest
     */
    public void addLast(T first, T... rest) {
        Node<T> subList = new Node<>(first, null);
        Node<T> cur = subList;
        for (T value : rest) {
            cur.next = new Node<>(value, null);
            cur = cur.next;
        }
        Node<T> last = findLast();
        if (last == null) { // 链表中无节点
            head = subList;
            return;
        }
        last.next = subList;
    }

    /**
     * 删除首部元素
     *
     * @return
     */
    public T removeFirst() {
        if (this.head != null) {
            T v = this.head.value;
            this.head = this.head.next;
            return v;
        }
        throw illegalArguments(0);
    }

    /**
     * 根据索引删除元素
     *
     * @param index
     * @return
     */
    public T remove(int index) {
        if (index == 0) {
            return removeFirst();
        }
        Node<T> pre = findNode(index - 1); // 找到上一个节点
        Node<T> deleted; // 被删除节点
        if (pre != null && (deleted = pre.next) != null) {
            pre.next = deleted.next;
            return deleted.value;
        }
        throw illegalArguments(index);
    }

    public void add(T value) {
        addLast(value);
    }

    /**
     * 插入
     *
     * @param index
     * @param value
     */
    public void add(int index, T value) {
        if (index == 0) {
            addFirst(value);
            return;
        }
        Node<T> pre = findNode(index - 1); // 寻找上一个节点
        if (pre == null) throw illegalArguments(index); // 找不到，抛出异常
        pre.next = new Node<>(value, pre.next);
    }

    /**
     * 根据索引获取元素
     *
     * @param index
     * @return
     */
    public T get(int index) {
        Node<T> node = findNode(index);
        if (node == null) {
            throw illegalArguments(index);
        }
        return node.value;
    }

    /**
     * 根据索引获取节点
     *
     * @param index
     * @return
     */
    private Node<T> findNode(int index) {
        int idx = 0;
        for (Node<T> node = this.head; node != null; node = node.next, idx++) {
            if (idx == index) return node;
        }
        return null;
    }

    /**
     * 寻找最后一个节点
     *
     * @return
     */
    public Node<T> findLast() {
        Node<T> cur = this.head;
        while (cur != null && cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    private IllegalArgumentException illegalArguments(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法", index));
    }
    // =================遍历方法===========================

    /**
     * 方式一：while + Consumer
     *
     * @param consumer
     */
    public void loopWhile(Consumer<T> consumer) {
        Node<T> cur = head;
        while (cur != null) {
            consumer.accept(cur.value);
            cur = cur.next;
        }
    }

    /**
     * 方式二：for + Consumer
     *
     * @param consumer
     */
    public void loopFor(Consumer<T> consumer) {
        for (Node<T> cur = this.head; cur != null; cur = cur.next) {
            consumer.accept(cur.value);
        }
    }

    /**
     * 方式三：迭代器遍历
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> cur = head;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public T next() {
                T value = cur.value;
                cur = cur.next;
                return value;
            }
        };
    }

    // 方式四：递归遍历
    public void f() {
        f(head);
    }

    private void f(Node<T> node) {
        if (node == null) return;
        System.out.println("Before:" + node.value);
        f(node.next);
        System.out.println("After:" + node.value);
    }

    /*=================节点类=======================*/

    /**
     * 节点Node类
     *
     * @param <T>
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
