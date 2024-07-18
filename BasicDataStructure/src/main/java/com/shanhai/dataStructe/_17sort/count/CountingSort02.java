package com.shanhai.dataStructe._17sort.count;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class CountingSort02 extends BaseSort<Byte> {
    public CountingSort02(Byte[] array) {
        super(array);
    }

    public CountingSort02(Byte[] array, Comparator<Byte> comparator) {
        super(array, comparator);
    }

    public static void main(String[] args) {
        Random random = new Random();
        int length = 30;
        Byte[] bytes = new Byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) random.nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE);
        }
        System.out.println("Before Sort: " + Arrays.toString(bytes));
        new CountingSort02(bytes).sort();
        System.out.println("After Sort:  " + Arrays.toString(bytes));
    }

    @Override
    protected void sort() {
        byte[] counts = new byte[1 << 8];
        for (byte b : array) {
            counts[b & 0xFF]++;
        }
        int k = array.length - 1;
        for (int i = 128 + 256; k >= 0; ) {
            while (counts[--i & 0xFF] == 0) ;
            int v = i & 0xFF;
            int c = counts[i & 0xFF];
            for (int j = 0; j < c; j++) {
                array[k] = (byte) v;
                k--;
            }
        }
    }
}
