package com.haofan.algorithm.others.queue;

import java.util.Stack;

/**
 * @author fanhao
 * 思路：
 * 入队时，将元素压入s1。
 * 出队时，判断s2是否为空，如不为空，则直接弹出顶元素；如为空，则将s1的元素逐个“倒入”s2，把最后一个元素弹出并出队。
 * 这个思路，避免了反复“倒”栈，仅在需要时才“倒”一次。但在实际面试中很少有人说出，可能是时间较少的缘故吧。
 * @date Apr, 21 2019
 */
public class Solution {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public static void main(String[] args) {
        Solution queue = new Solution();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop());
    }

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            System.out.println("Queue is empty");
        }

        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
