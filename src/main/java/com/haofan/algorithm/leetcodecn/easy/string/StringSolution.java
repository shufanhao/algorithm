package com.haofan.algorithm.leetcodecn.easy.string;

import java.util.Arrays;

/**
 * 字符串问题, 注意双指针
 */
public class StringSolution {

    public static void main(String args[]) {
        StringSolution ss = new StringSolution();
        System.out.println("题目1： " + ss.reverseString("haofan"));

        System.out.println("题目2： " + ss.reverse(-102));

        System.out.println("题目3： " + ss.firstUniqChar("loveleetcode"));

        System.out.println("题目4：" + ss.isAnagram("rat", "car"));

        System.out.println("题目5： " + ss.isPalindrome("race a car"));

        System.out.println("题目6： " + ss.myAtoi("  4193 with words"));

        System.out.println("题目7： " + ss.strStr("hello", "ll"));

        System.out.println("题目8： " + ss.countAndSay(4));

        String[] s = {"flower", "flow", "flight"};
        System.out.println("题目9： " + ss.longestCommonPrefix(s));
    }

    /**
     * 题目1：反转字符串
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
        for (int i = 0; i < s.length() / 2; i++) {
            char temp = sChar[i];
            sChar[i] = sChar[s.length() - i - 1];
            sChar[s.length() - i - 1] = temp;
        }
        return new String((sChar));
    }

    /**
     * 题目2： 颠倒整数
     * 给定一个32位有符号整数，将整数中的数字进行反转
     * 思路：根据 x % 10 = 个位数，x/10 = 除了个位数的前面几个数，还要考虑是100的这种情况
     */
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10; //取尾数
            int newResult = result * 10 + tail;
            //下面这行code 是为了cover case: 1000
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            result = newResult;
            x = x / 10;
        }
        return result;
    }

    /**
     * 题目3：字符串中的第一个唯一字符
     * 思路：26个英文字母放到数组中，然后遍历string, 让数组中的26个英文字母加1，最后循环
     * check 哪个是1，返回即可
     */
    public int firstUniqChar(String s) {
        if (s == null) {
            return -1;
        }
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            array[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (array[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 题目4：有效的字母异位词，两个单词如果包含相同的字母
     * 思路：同上一个题目，26个英文字母放到数组中，然分别遍历两个字符串，执行++，--
     * 然后遍历26个英文字母判断是否有不是0的case
     */
    public boolean isAnagram(String s, String t) {
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            array[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            array[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 题目5：验证回文字符串
     * 思路：两个指针分别指向字符串的左右，然后如果是字母或者数字，则指针++, 如果左右的字符相等，则指针++
     */
    public boolean isPalindrome(String s) {
        if (s == null) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (!isNumOrAlph(s.charAt(left))) {
                left++;
            } else if (!isNumOrAlph(s.charAt(right))) {
                right--;
            } else if (((s.charAt(left) + 32 - 'a') % 32) !=
                    ((s.charAt(right) + 32 - 'a') % 32)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    public boolean isNumOrAlph(char s) {
        if (s >= 'a' && s <= 'z') return true;
        else if (s >= 'A' && s <= 'Z') return true;
        else if (s >= '0' && s <= '9') return true;
        else return false;
    }

    /**
     * 题目6：字符串转整数
     * 思路：循环，char 转正int 可以通过 char - '0' 获得，然后可以通过base*10,获取最终的int 值
     */
    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int sign = 1; // + or -
        int i = 0;
        int base = 0;
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }
        if (i < str.length() && (str.charAt(i) == '-' || str.charAt(i) == '+')) {
            sign = str.charAt(i++) == '-' ? -1 : 1;
        }
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 &&
                    str.charAt(i) - '0' > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = base * 10 + (str.charAt(i++) - '0');
        }
        return sign * base;
    }

    /**
     * 题目7：实现strStr() 函数
     * 原理：subString
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null) {
            return 0;
        }
        if (needle == null) {
            return 0;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, needle.length() + i).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 题目8：数数并说
     * 并说：超过1个相同的数字连在一起时，并说，也就是”几个几“
     * 不并说：前后数字不同时，不并说，需要单说，也就是”一个几“
     */
    public String countAndSay(int n) {

        if (n <= 0) return "-1";

        String result = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder builder = new StringBuilder();
            int index = 0;
            while (index < result.length()) {
                // 记录值
                char val = result.charAt(index);
                // 记录连续位数
                int count = 0;

                // 查找连续位数，碰到不相同的数字时停止。
                while (index < result.length() && result.charAt(index) == val) {
                    index++;
                    count++;
                }

                // 追加连续位数到结果中
                builder.append(String.valueOf(count));
                // 追加连续位数的值到结果中
                builder.append(val);
            }
            result = builder.toString();
        }
        return result;
    }

    /**
     * 题目9：最长公共前缀
     * 思路：先排序，然后用字符串数组的第一个和最后一个比较
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs);
        StringBuilder sb = new StringBuilder();
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();
        for (int i = 0; i < first.length; i++) {
            if (last.length > i && first[i] == last[i]) {
                sb.append(first[i]);
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }
}
