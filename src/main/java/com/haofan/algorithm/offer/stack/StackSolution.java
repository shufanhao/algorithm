package com.haofan.algorithm.offer.stack;

import java.util.Stack;

public class StackSolution {
    /**
     * 面试题1：后缀表达式
     * 输入一个后缀表达式，输出结果，假设输入[2, 1, 3, *, +]，计算应该是(1*3) + 2 = 5
     * <p>
     * 解法：只讲数字放入stack中，然后遇到操作符，出栈，计算结果。
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                case "-":
                case "*":
                case "/":
                    int num1 = stack.pop();
                    int num2 = stack.pop();
                    stack.push(calculate(num1, num2, token));
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    private int calculate(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }

    /**
     * 面试题2: 每日温度
     * 给定一个整数数组 temperatures，表示每天的温度，返回一个数组answer，其中answer[i]是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用0 来代替。
     * <p>
     * 输入temperatures = [30,40,50,60]
     * 输出: [1,1,1,0]
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        // 用stack保存每天的温度在数组中的下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            // 从数组中读取一个温度，与stack中保存的温度对比，如果高的话，则知道需要等待几天是最高温度
            while (!stack.empty() && temperatures[i] > temperatures[stack.peek()]) {
                int prev = stack.pop();
                result[prev] = i - prev;
            }
            // 把下标放在stack中
            stack.push(i);
        }
        return result;
    }
}
