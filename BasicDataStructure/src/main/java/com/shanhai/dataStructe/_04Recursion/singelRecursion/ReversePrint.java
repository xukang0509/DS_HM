package com.shanhai.dataStructe._04Recursion.singelRecursion;

public class ReversePrint {
    public static void main(String[] args) {
        reversePrint1("abcdefg", 0);
    }

    public static void reversePrint(String str, int index) {
        if (index == str.length()) return;
        reversePrint(str, index + 1);
        System.out.print(str.charAt(index));
    }

    public static void reversePrint1(String str, int index) {
        if (index < str.length() - 1) reversePrint1(str, index + 1);
        System.out.print(str.charAt(index));
    }
}
