package string;

import java.util.Stack;

/**
 * 字符串问题
 */
public class StringSolution {

    /**
     * 反转字符串：
     * 1. 解法：放到栈中，利用栈的先进后出，实现倒叙
     * 2. 解法：将前后两个字符颠倒
     */
    public String reverseString(String s) {

        if (s == null || s.isEmpty()) {
            return s;
        }
        /* //解法1
        Stack<Character> stack = new Stack<>();
        char[] sChar = s.toCharArray();
        for(int i=0; i<sChar.length;i++) {
            stack.push(sChar[i]);
        }
        for (int i=0; i<s.length(); i++) {
            sChar[i] = stack.pop();
        }
        return new String(sChar);*/
        // 解法2：
        char[] sChar = s.toCharArray();
        for (int i=0; i<s.length()/2; i++) {
            char temp = sChar[i];
            sChar[i] = sChar[s.length() - i -1];
            sChar[s.length() - i -1] = temp;
        }
        return new String((sChar));
    }

    /**
     * 给定一个32位有符号整数，将整数中的数字进行反转
     * 思路：根据 x % 10 = 个位数，x/10 = 除了个位数的前面几个数，还要考虑是100的这种情况
     * @param x
     * @return
     */
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10; //取尾数
            int newResult = result * 10 + tail;
            //下面这行code 是为了cover case: 1000
            if ((newResult - tail) / 10 != result)
            { return 0; }
            result = newResult;
            x = x /10;
        }
        return result;

    }
    public static void main(String args[]) {
        StringSolution ss = new StringSolution();
        System.out.println("题目1： " + ss.reverseString("haofan"));

        System.out.println("题目2： " + ss.reverse(100));
    }
}
