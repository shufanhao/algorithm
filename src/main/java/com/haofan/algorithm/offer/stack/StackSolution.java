package com.haofan.algorithm.offer.stack;

import java.util.Arrays;
import java.util.Stack;

public class StackSolution {
    public static void main(String[] args) {
        System.out.println("1");
    }

    /**
     * 找出数组中右边第一个比我小的元素，输出元素在数组中的小标, 输入[3, 4, 1, 5, 2]， 输出[2， 2，-1, 4, -1]
     * 没有则用-1表示。
     *
     * 一定要自己去写这个过程。
     *
     * stack: [3], 3在底部，发现4大于3，则变成:
     * stack: [3, 4], 1发现比peak元素4小，则4弹出，并且更新4的result的index, 1比3小，3弹出，更新3的result的index, 1入栈
     * stack: [1], 5发现比1要打，入栈，变成
     * stack: [1, 5], 2 比5小，则5弹出，并且更新5的result的index,
     */
    public int[] findRightSmall(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        int[] result = new int[array.length];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < array.length; i++) {
            while (!stack.empty() && array[stack.peek()] > array[i]) {
                // find out the peak element > array element
                int index = stack.pop();
                result[index] = i; // so here is to update
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * 面试题36：后缀表达式
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
     * 面试题36: 每日温度
     * 给定一个整数数组 temperatures，表示每天的温度，返回一个数组answer，其中answer[i]是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用0 来代替。
     * <p>
     * 就是单调递增栈，用单调栈存放已经遍历过的元素。
     * 输入temperatures = [35,31,33,36，34]，目的是找到右边第一个比它大的元素。
     *
     * stack: [{0, 35}， {1, 31}], 输入是31，比35小。
     * stack: [{0, 35}， {1, 31}, {2, 33}]，输入是33，比栈顶33大，则栈顶元素弹出{1, 31},
     * stack: [{0, 35}，{2, 33}, {3, 36}]，输入是36，比栈顶33大，则栈顶元素弹出{2, 33},然后{0, 35}
     * 输出: [3,1,1,0,0]
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
