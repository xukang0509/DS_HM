package com.shanhai.dataStructe._16hashTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <h2>哈希表</h2>
 * <p>给每份数据分配一个编号，放入表格（数组）。</p>
 * <p>建立编号与表格索引的关系，将来就可以通过编号快速查找数据</p>
 * <ol>
 *  <li>理想情况编号当唯一，数组能容纳所有数据</li>
 *  <li>现实是不能说为了容纳所有数据造一个超大数组，编号也有可能重复</li>
 * </ol>
 * <p>
 * 解决
 * <ol>
 *     <li>有限长度的数组，以【拉链】方式存储数据</li>
 *     <li>允许编号适当重复，通过数据自身来进行区分</li>
 * </ol>
 */
@SuppressWarnings("all")
public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 1 << 4; // 16
    private static final float DEFAULT_LOAD_FACTOR = 0.75F; // 16
    private final float loadFactor;
    private Entry<K, V>[] table;
    private int size; // 元素个数
    private int thresHold;

    public HashTable() {
        table = new Entry[DEFAULT_CAPACITY];
        loadFactor = DEFAULT_LOAD_FACTOR;
        thresHold = (int) (loadFactor * table.length);
    }

    public static void main(String[] args) throws IOException {
        HashTable<Object, Object> table2 = new HashTable<>();
        for (int i = 0; i < 20_0000; i++) {
            Object o = new Object();
            table2.put(o, o);
        }
        table2.print();

        HashTable<String, String> table1 = new HashTable<>();
        List<String> strings = Files.readAllLines(Path.of("words"));
        for (String string : strings) {
            table1.put(string, string);
        }
        table1.print();
    }

    private int hash(K key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    // 根据 key 获取到 value
    public V get(K key) {
        return get(hash(key), key);
    }

    private V get(int hash, K key) {
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            return null;
        }
        Entry<K, V> p = table[idx];
        while (p != null && !Objects.equals(key, p.key)) {
            p = p.next;
        }
        return p == null ? null : p.value;
    }

    // 向 hash 表存入新 key value，如果 key 重复，则更新 value
    public void put(K key, V value) {
        put(hash(key), key, value);
    }

    private void put(int hash, K key, V value) {
        int idx = hash & (table.length - 1);
        // 1. idx 处有空位，直接新增
        if (table[idx] == null) {
            table[idx] = new Entry<>(hash, key, value);
            size++;
            return;
        }
        // 2. idx 处无空位，沿链表查找，有重复key更新，否则新增
        Entry<K, V> p = table[idx];
        while (true) {
            if (Objects.equals(key, p.key)) {
                p.value = value; // 更新
                return;
            }
            if (p.next == null) break;
            p = p.next;
        }
        p.next = new Entry<>(hash, key, value); // 新增
        size++;
        if (size > thresHold) {
            // 扩容
            resize();
        }
    }

    // 根据 key 删除对应的 Entry；并返回删除的 value
    public V remove(K key) {
        return remove(hash(key), key);
    }

    private V remove(int hash, K key) {
        int idx = hash & (table.length - 1);
        if (table[idx] == null) { // 没找到
            return null;
        }
        Entry<K, V> p = table[idx], pre = null;
        while (p != null && !Objects.equals(key, p.key)) {
            pre = p;
            p = p.next;
        }
        if (p == null) { // 没找到
            return null;
        } else if (pre != null) { // 找到了中间节点
            pre.next = p.next;
        } else { // 找到了首节点
            table[idx] = p.next;
        }
        size--;
        p.next = null; // help GC
        return p.value;
    }

    private void resize() {
        Entry<K, V>[] newTable = new Entry[table.length << 1];
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> p = table[i]; // 拿到每个链表头
            if (p != null) {
                // 拆分链表，移动到新数组
                /*
                    拆分规律
                    * 一个链表最多拆分成两个
                    * hash & table.length == 0 的一组
                    * hash & table.length != 0 的一组
                */
                Entry<K, V> a = null, aHead = null;
                Entry<K, V> b = null, bHead = null;
                while (p != null) {
                    if ((p.hash & table.length) == 0) {
                        // 分配到a
                        if (a != null) {
                            a.next = p;
                        } else {
                            aHead = p;
                        }
                        a = p;
                    } else {
                        // 分配到b
                        if (b != null) {
                            b.next = p;
                        } else {
                            bHead = p;
                        }
                        b = p;
                    }
                    p = p.next;
                }
                // 规律：a 链表保持索引位置不变；b 链表索引位置 + table.length
                if (a != null) {
                    a.next = null;
                    newTable[i] = aHead;
                }
                if (b != null) {
                    b.next = null;
                    newTable[i + table.length] = bHead;
                }
            }
        }
        table = newTable;
        thresHold = (int) (loadFactor * table.length);
    }

    public void print() {
        int[] sums = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> p = table[i];
            while (p != null) {
                sums[i]++;
                p = p.next;
            }
        }
        Map<Integer, Long> collect =
                Arrays.stream(sums).boxed().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(collect);
    }

    // 节点类
    public static class Entry<K, V> {
        final int hash;               // 哈希码
        K key;                  // 键
        V value;                // 值
        Entry<K, V> next;

        Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }
}

/*
    为什么计算索引位置用式子：
        【hash & (数组长度-1)】 等价于 【hash % 数组长度】
        10进制中去除以 10，100，1000时，余数就是被除数的后1，2，3 位
                    10^1 10^2 10^3
        2进制中去除以 10，100，1000时，余数也是被除数的后1，2，3 位
                    2^1 2^2 2^3 2^4
        因此求余数就是求二进制的后几位，而保留二进制后几位可以通过与
            1，3，7，11 ... 等数字按位与来实现，这些数字恰巧是数组长度-1

    为什么旧链表会拆分成两条，一条 hash & 旧数组长度==0 另一条!=0
        旧数组长度换算成二进制后，其中的 1 就是我们要检查的倒数第几位
            旧数组长度 8 二进制 => 1000 检查倒数第4位
            旧数组长度 16 二进制 => 10000 检查倒数第5位
        hash & 旧数组长度 就是用来检查扩容前后索引位置（余数）会不会变
    为什么拆分后的两条链表，一个原索引不变，另一个是原索引+旧数组长度

    它们都有个共同的前提：数组长度是 2 的 n 次方
 */