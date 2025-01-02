package com.shanhai.algorithm.dynamic;

import java.util.Arrays;
import java.util.stream.IntStream;

public class KnapsackProblemComplete {
    /*
     * 完全背包问题
     * 1. n个物品都是固体，有重量和价值
     * 2. 现在你要取走不超过 6克 的物品
     * 3. 每次可以拿多个或不拿，问最高价值是多少
     *  编号 重量(g)  价值(元)              简称
     *  1    2       3          青铜      c
     *  2    3       4          白银      s
     *  3    4       7          黄金      a
     *
     * i：下标i-1处的物品信息，i从1开始
     * j：当前背包的容量
     *
     *     0   1   2   3   4   5   6
     * 0   0   0   0   0   0   0   0
     * 1   0   0   c   c   cc  cc  ccc    青铜 重2
     * 2   0   0   c   s   cc  sc  ccc    白银 重3
     * 3   0   0   c   s   a   a   ac     黄金 重4
     *
     * if (j >= item.weight) { // 放得下
     *      dp[i][j] = max(dp[i-1][j], dp[i][j - item.weight] + item.value);
     * } else { // 放不下
     *      dp[i][j] = dp[i-1][j]
     * }
     * */

    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(1, "青铜", 2, 3),    // c
                new Item(2, "白银", 3, 4),    // s
                new Item(3, "黄金", 4, 7),    // a
        };
        System.out.println(select(items, 6));
    }

    private static int select(Item[] items, int total) {
        int[] dp = new int[total + 1];
        System.out.println(Arrays.toString(dp));
        for (Item item : items) {
            for (int j = 1; j < total + 1; j++) {
                if (j >= item.weight) {
                    dp[j] = Integer.max(dp[j], dp[j - item.weight] + item.value);
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[total];
    }

    private static void print(int[][] dp) {
        System.out.println("   " + "-".repeat(63));
        Object[] array = IntStream.range(0, dp[0].length + 1).boxed().toArray();
        System.out.printf(("%5d ".repeat(dp[0].length)) + "%n", array);
        for (int[] d : dp) {
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(("%5d ".repeat(d.length)) + "%n", array);
        }
    }

    private static int select1(Item[] items, int total) {
        int[][] dp = new int[items.length + 1][total + 1];
        print(dp);
        for (int i = 1; i < items.length + 1; i++) {
            Item item = items[i - 1];
            for (int j = 1; j < total + 1; j++) {
                if (j >= item.weight) {
                    dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - item.weight] + item.value);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            print(dp);
        }
        return dp[items.length][total];
    }

    private static class Item {
        int index;
        String name;
        int weight;
        int value;

        public Item(int index, String name, int weight, int value) {
            this.index = index;
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item(" + name + ")";
        }
    }
}
