package com.shanhai.algorithm.skipList;

import com.shanhai.utils.Asserts;
import com.shanhai.utils.Times;

/**
 * @author xk
 * @since 2024-08-29 20:37
 */
public class Main {
    public static void main(String[] args) {
        SkipList<Integer, Integer> list = new SkipList<>();
        int count = 30;
        int delta = 10;
        Times.test("SkipList", () -> testSkipList(list, count, delta));
    }

    private static void testSkipList(SkipList<Integer, Integer> list, int count, int delta) {
        for (int i = 0; i < count; i++) {
            list.put(i, i + delta);
        }
        System.out.println(list);
        for (int i = 0; i < count; i++) {
            Asserts.test(list.get(i) == i + delta);
        }
        Asserts.test(list.size() == count);
        for (int i = 0; i < count; i++) {
            Asserts.test(list.remove(i) == i + delta);
        }
        Asserts.test(list.size() == 0);
    }

}
