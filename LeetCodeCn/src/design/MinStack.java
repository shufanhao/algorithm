package design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 设计一个支持push,pop,top操作，并能常数时间内检索到最小元素的栈
 */
public class MinStack {
    private List<Integer> list = new ArrayList<>();

    public void push(int num) {
        list.add(num);
    }

    public void pop() {
        if (list != null && !list.isEmpty()) {
            list.remove(list.size() - 1);
        }
    }

    public int top() {
        if (list != null && !list.isEmpty()) {
            return list.get(list.size() - 1);
        } else {
            return Integer.MIN_VALUE;
        }

    }

    public int getMin() {
        Object array[] = list.toArray();
        Arrays.sort(array);
        return (int)array[0];
    }

    public static void main(String args[]) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
