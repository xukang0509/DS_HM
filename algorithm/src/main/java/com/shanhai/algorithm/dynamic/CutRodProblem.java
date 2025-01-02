package com.shanhai.algorithm.dynamic;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CutRodProblem {
    public static void main(String[] args) {
        System.out.println(cut(new int[]{0, 1, 5, 8, 9}, 4));
    }

    private static int cut(int[] values, int len) {
        int[] dp = new int[len + 1];
        System.out.println(Arrays.toString(dp));
        for (int i = 1; i < values.length; i++) {
            int value = values[i];
            for (int j = i; j < len + 1; j++) {
                dp[j] = Integer.max(dp[j], value + dp[j - i]);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[len];
    }

    static void print(int[][] dp) {
        System.out.println("-".repeat(18));
        Object[] array = IntStream.range(0, dp[0].length + 1).boxed().toArray();
        System.out.printf(("%2d ".repeat(dp[0].length)) + "%n", array);
        for (int[] d : dp) {
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(("%2d ".repeat(d.length)) + "%n", array);
        }
    }
}
