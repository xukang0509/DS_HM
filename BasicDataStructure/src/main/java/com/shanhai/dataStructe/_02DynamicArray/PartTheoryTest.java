package com.shanhai.dataStructe._02DynamicArray;

import com.shanhai.utils.Times;

/**
 * @description:
 * @author: xu
 * @date: 2024/5/13 21:01
 */
public class PartTheoryTest {
    public static void main(String[] args) {
        int rows = 10000000;
        int columns = 14;
        long[][] arr = new long[rows][columns];

        Times.test("测试ij", () -> ij(arr, rows, columns));
        System.out.println("==================================");
        Times.test("测试ji", () -> ji(arr, rows, columns));
    }


    private static void ij(long[][] arr, int rows, int columns) {
        long sum = 0L;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println("ij: sum = " + sum);
    }

    private static void ji(long[][] arr, int rows, int columns) {
        long sum = 0L;
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                sum += arr[i][j];
            }
        }
        System.out.println("ji: sum = " + sum);
    }
}
