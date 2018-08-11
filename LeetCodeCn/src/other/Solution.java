package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    /**
     * 题目1：位1的个数
     * 思路：和1 与操作，然后做右移操作
     */
    public int hammingWeight(int num) {
        long n = Integer.toUnsignedLong(num);
        int count = 0;
        while (n > 0) {
            // n 和 1 与，然后判断是否是1
            if ((n & 1) == 1) {
                count++;
            }
            n = n >> 1; //向右移动一位
        }
        return count;
    }

    /**
     * 题目2：汉明距离,两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
     * 思路：先异或，然后找1的个数
     */
    public int hammingDistance(int x, int y) {
        int res = x ^ y;
        int count = 0;
        while (res > 0) {
            if ((res & 1) == 1) {
                count++;
            }
            res = res >> 1;
        }
        return count;
    }

    /**
     * 题目3：颠倒二进制位，颠倒给定的 32 位无符号整数的二进制位
     * 思路：向右移动一位，取到这一位然后将其移动到相反位置，再进行下一次循环
     */
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = res | ((n >> i) & 1) << (31 - i);
        }
        return res;
    }

    /**
     * 题目4：帕斯卡三角形：给定一个非负整数n行，生成前n行
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
            result.add(new ArrayList<>(row));
        }
        return result;
    }

    /**
     * 题目5：有效的括号：
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 思路：
     * 充分利用栈，先进后出。如果是 (,{,[则入栈，其他则出栈，最后判断栈是否是空
     */
    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c.equals('(') || c.equals('{') || c.equals('[')) {
                stack.push(c);
            } else if (c.equals('}')) {
                if (!stack.isEmpty() && stack.peek().equals('{')) {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (c.equals(')')) {
                if (!stack.isEmpty() && stack.peek().equals('(')) {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (c.equals(']')) {
                if (!stack.isEmpty() && stack.peek().equals('[')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 题目6：缺失数字
     */
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += nums[i];
        }
        int miss = (nums.length * (nums.length + 1)) / 2 - res;
        return miss;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        System.out.println("题目1：" + s.hammingWeight(128));

        System.out.println("题目2：" + s.hammingDistance(1, 4));

        System.out.println("题目3：" + s.reverseBits(43261596));

        System.out.println("题目4： " + s.generate(4));

        System.out.println("题目5：" + s.isValid("()[]{}"));

        int[] nums = {3, 0, 1};
        System.out.println("题目6： " + s.missingNumber(nums));
    }
}
