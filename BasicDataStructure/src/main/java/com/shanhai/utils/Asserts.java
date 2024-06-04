package com.shanhai.utils;

/**
 * @description:
 * @author: xu
 * @date: 2024/5/11 23:15
 */
public class Asserts {
    public static void test(boolean value) {
        try {
            if (!value) {
                throw new RuntimeException("测试未通过");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
