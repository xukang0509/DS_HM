package com.shanhai.algorithm.dynamic;

import java.util.Arrays;

/**
 * @author xk
 * @since 2024-08-12 19:55
 */
public class Catalan {
    public static void main(String[] args) {
        System.out.println(catalan(6));
    }

    private static int catalan(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) { // 第 i 个卡特兰数的拆分
                System.out.print("(" + j + "," + (i - 1 - j) + ")\t");
                dp[i] += dp[j] * dp[i - 1 - j];
            }
            System.out.println();
            System.out.println(Arrays.toString(dp));
        }
        return dp[n];
    }
}
