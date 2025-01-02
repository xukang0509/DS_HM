package com.shanhai.algorithm.greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 霍夫曼树
 */
public class HuffmanTree {
    /*
        Huffman 树的构建过程

        1. 将统计了出现频率的字符，放入优先级队列
        2. 每次出队两个频次最低的元素，给它俩找个爹
        3. 把爹重新放入队列，重复 2~3
        4. 当队列只剩一个元素时，Huffman 树构建完成
     */
    private String str;
    private Node root;
    private Map<Character, Node> map = new HashMap<>();

    public HuffmanTree(String str) {
        this.str = str;
        // 功能1：统计频率
        processFreq(str);
        // 功能2：构造树
        buildHuffmanTree();
        // 功能3：计算每个字符的编码, 功能4：字符串编码后占用 bits
        executeCode();
    }

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree("abbccccccc");
        String encode = huffmanTree.encode();
        System.out.println(encode);
        System.out.println(huffmanTree.decode(encode));
    }

    // 统计频率
    private void processFreq(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            Node node = map.computeIfAbsent(c, Node::new);
            node.freq += 1;
        }
    }

    // 构造树
    private void buildHuffmanTree() {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFreq));
        queue.addAll(map.values());
        while (queue.size() >= 2) {
            Node x = queue.poll();
            Node y = queue.poll();
            int nFreq = x.freq + y.freq;
            queue.offer(new Node(nFreq, x, y));
        }
        root = queue.poll();
    }

    // 计算每个字符的编码, 字符串编码后占用 bits
    private void executeCode() {
        int sum = dfs(root, new StringBuilder());
        for (Node node : map.values()) {
            System.out.println(node.ch + "：" + node.code);
        }
        System.out.println("总共占据：" + sum + " bit");
    }

    private int dfs(Node node, StringBuilder code) {
        int sum = 0;
        if (node.isLeaf()) {
            node.code = code.toString();
            sum += node.freq * code.length();
        } else {
            sum += dfs(node.left, code.append("0"));
            sum += dfs(node.right, code.append("1"));
        }
        if (!code.isEmpty()) {
            code.deleteCharAt(code.length() - 1);
        }
        return sum;
    }

    // 编码
    public String encode() {
        char[] chars = str.toCharArray();
        StringBuilder encode = new StringBuilder();
        for (char c : chars) {
            encode.append(map.get(c).code);
        }
        return encode.toString();
    }

    // 解码
    public String decode(String code) {
        Node node = root;
        StringBuilder decode = new StringBuilder();
        for (char c : code.toCharArray()) {
            if (c == '0') {
                node = node.left;
            } else if (c == '1') {
                node = node.right;
            }
            if (node.isLeaf()) {
                decode.append(node.ch);
                node = root;
            }
        }
        return decode.toString();
    }

    private static class Node {
        Character ch; // 字符
        int freq; // 频次
        Node left;
        Node right;
        String code; // 编码

        Node(Character ch) {
            this.ch = ch;
        }

        Node(int freq, Node left, Node right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        int getFreq() {
            return this.freq;
        }

        boolean isLeaf() {
            return this.left == null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", freq=" + freq +
                    '}';
        }
    }
}
