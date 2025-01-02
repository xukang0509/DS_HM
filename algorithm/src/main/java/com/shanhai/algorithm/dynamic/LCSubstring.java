package com.shanhai.algorithm.dynamic;

import java.util.Arrays;

public class LCSubstring {
    public static void main(String[] args) {
        //System.out.println(lcs("itheima", "then"));
        System.out.println(lcs("ABCBA", "BABCA"));
    }

    private static int lcs(String a, String b) {
        if (a == null || a.isEmpty()) return 0;
        if (b == null || b.isEmpty()) return 0;
        char[] rowChars = a.toCharArray();
        char[] colChars = b.toCharArray();
        if (rowChars.length > colChars.length) { // 选择较短的字符串作为行
            char[] tmp = rowChars;
            rowChars = colChars;
            colChars = tmp;
        }
        int[] dp = new int[colChars.length + 1];
        System.out.println(Arrays.toString(dp));
        int maxLen = 0;
        for (int i = 1; i < rowChars.length + 1; i++) {
            for (int j = colChars.length; j > 0; j--) {
                if (rowChars[i - 1] == colChars[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    maxLen = Integer.max(maxLen, dp[j]);
                } else {
                    dp[j] = 0;
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return maxLen;
    }
}
