package com.shanhai.dataStructe._06stack;

import java.util.Deque;
import java.util.LinkedList;

public class InfixToSuffixTest {

    public static void main(String[] args) {
        System.out.println("infixToSuffix(\"a+b\") = " + infixToSuffix("a+b"));
        System.out.println("infixToSuffix(\"a+b-c\") = " + infixToSuffix("a+b-c"));
        System.out.println("infixToSuffix(\"a+b*c\") = " + infixToSuffix("a+b*c"));
        System.out.println("infixToSuffix(\"a*b-c\") = " + infixToSuffix("a*b-c"));
        System.out.println("infixToSuffix(\"(a+b)*c\") = " + infixToSuffix("(a+b)*c"));
        System.out.println("infixToSuffix(\"a+b*c+(d*e+f)*g\") = " + infixToSuffix("a+b*c+(d*e+f)*g"));
    }

    /*
    思路
        1. 遇到数字, 拼串
        2. 遇到 + - * /
            - 优先级高于栈顶运算符 入栈
            - 否则将栈中高级或平级运算符出栈拼串, 本运算符入栈
        3. 遍历完成, 栈中剩余运算符出栈拼串
            - 先出栈,意味着优先运算
        4. 带 ()
            - 左括号直接入栈
            - 右括号要将栈中直至左括号为止的运算符出栈拼串
    * */
    private static String infixToSuffix(String exp) {
        Deque<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : exp.toCharArray()) {
            switch (c) {
                case '+', '-', '*', '/' -> {
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        if (priority(c) > priority(stack.peek())) {
                            stack.push(c);
                        } else {
                            while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                                sb.append(stack.pop());
                            }
                            stack.push(c);
                        }
                    }
                }
                case '(' -> {
                    stack.push(c);
                }
                case ')' -> {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                }
                default -> sb.append(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    private static int priority(char c) {
        return switch (c) {
            case '(' -> 0;
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> throw new IllegalArgumentException("不合法字符:" + c);
        };
    }
}
