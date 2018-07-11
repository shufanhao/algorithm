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
    public static void main(String args[]) {
        StringSolution ss = new StringSolution();
        System.out.println("题目1： " + ss.reverseString("haofan"));
    }
}
