package com.shanhai.algorithm.dynamic;

import java.util.Arrays;
import java.util.stream.IntStream;

public class KnapsackProblem {

    /*
     * 1. n个物品都是固体，有重量和价值
     * 2. 现在你要取走不超过 10克 的物品
     * 3. 每次可以拿一个或不拿，问最高价值是多少
     *  编号 重量(g)  价值(元)                         简称
     *  1    4       1600           黄金一块   400    A
     *  2    8       2400           红宝石一粒 300     R
     *  3    5       30             白银一块           S
     *  4    1       1_000_000      钻石一粒           D
     *
     *  100_1630 贪心解
     *  100_2400 正确解
     *
     *     0   1   2   3   4   5   6   7   8   9   10
     * 0   0   0   0   0   0   0   0   0   0   0   0
     * 1   0   0   0   0   A   A   A   A   A   A   A        黄金
     * 2   0   0   0   0   A   A   A   A   R   R   R        红宝石
     * 3   0   0   0   0   A   A   A   A   R   R   R        白银
     * 4   0   D   D   D   D   AD  AD  AD  AD  RD  RD       钻石
     *
     * if (j >= item.weight) { // 放得下
     *      dp[i][j] = max(dp[i-1][j], dp[i-1][j - item.weight] + item.value);
     * } else { // 放不下
     *      dp[i][j] = dp[i-1][j]
     * }
     * */

    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(1, "黄金", 4, 1600),
                new Item(2, "宝石", 8, 2400),
                new Item(3, "白银", 5, 30),
                new Item(4, "钻石", 1, 10_000),
        };
        System.out.println(select(items, 10));
    }

    private static int select(Item[] items, int total) {
        int[] dp = new int[total + 1];
        Arrays.fill(dp, 0);
        System.out.println(Arrays.toString(dp));
        for (int i = 1; i < items.length + 1; i++) {
            Item item = items[i - 1];
            for (int j = total; j > 0; j--) {
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
                // x: 上一次同容量背包的最大价值
                int x = dp[i - 1][j];
                if (j >= item.weight) {
                    // j-item.weight: 当前背包容量-这次物品重量=剩余背包空间
                    // y: 剩余背包空间能装下的最大价值 + 这次物品价值
                    int y = dp[i - 1][j - item.weight] + item.value;
                    dp[i][j] = Integer.max(x, y);
                } else {
                    dp[i][j] = x;
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
