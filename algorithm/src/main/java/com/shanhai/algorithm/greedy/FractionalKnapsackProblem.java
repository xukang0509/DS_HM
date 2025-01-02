package com.shanhai.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsackProblem {
    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(0, 4, 24),
                new Item(1, 8, 160),
                new Item(2, 2, 4000),
                new Item(3, 6, 108),
                new Item(4, 1, 4000)
        };
        select(items, 10);
    }

    private static void select(Item[] items, int total) {
        Arrays.sort(items, Comparator.comparingInt(Item::unitPrice).reversed());
        int remainder = total;
        int maxValue = 0;
        for (Item item : items) {
            if (remainder - item.weight > 0) {
                maxValue += item.value;
                remainder -= item.weight;
            } else {
                maxValue += remainder * item.unitPrice();
                break;
            }
        }
        System.out.println("最高价值为：" + maxValue);
    }

    private static class Item {
        int index;
        int weight;
        int value;

        public Item(int index, int weight, int value) {
            this.index = index;
            this.weight = weight;
            this.value = value;
        }

        public int unitPrice() {
            return value / weight;
        }

        @Override
        public String toString() {
            return "Item(" + index + ')';
        }
    }
}
