package com.haofan.algorithm.offer.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class StringSolution {

    /**
     * 面试题14：输入字符串s1和s2，如何判断字符串s2中是否包含字符串s1的某个变位词？
     * 如果字符串s2中包含字符串s1的某个变位词，则字符串s1至少有一个变位词是字符串s2的子字符串。假设两个字符串中只包含英文小写字母。
     * 例如，字符串s1为"ac"，字符串s2为"dgcaf"，由于字符串s2中包含字符串s1的变位词"ca"，因此输出为true。
     * 如果字符串s1为"ab"，字符串s2为"dgcaf"，则输出为false。
     * 变位词就是字母出现的次数一样。
     * <p>
     * 解法: 完全按照书上的方式。
     * 1. 因为字符串只可能包含小写字母，所以可以将每个字母的出现次数放在一个26长度的数组中，index是charAt()- 'a'，value是次数
     * 2. 先统计ac中字母出现的哈希表
     * 3. left, right 指针从遍历s2, left指向s2的第一个字符，right指向子字符串的最后一个字符，双指针指向dg,然后得到哈希表
     * 4. 双指针都向右移动，1位，定位到gc, 每次移动指针，也就是最右边添加一个字符，最左边删除一个字符，添加一个字符，则对应的位置-1，
     * 删除一个字符对应的位置+1
     * 5. 判断是否全0
     */
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        int[] counts = new int[26];
        for (int i = 0; i < s1.length(); ++i) {
            counts[s1.charAt(i) - 'a']++;
            // 这个地方有可能会忘记。
            counts[s2.charAt(i) - 'a']--;
        }

        if (areAllZero(counts)) {
            return true;
        }

        for (int i = s1.length(); i < s2.length(); ++i) {
            // i 相当于右指针， i- s1.length() 相当于左指针
            counts[s2.charAt(i) - 'a']--;
            counts[s2.charAt(i - s1.length()) - 'a']++;
            if (areAllZero(counts)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 面试题15：字符串中的所有变位词 <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/">...</a>
     * s1 = "cbaebabacd", s2 = "abc"，输出{0, 6}
     * 给定两个字符串 s1 和 s2，找到 s2 中所有变位词在字符串s1的起始下标，返回这些子串的起始索引。不考虑答案输出的顺序。
     * <p>
     * 解法：上面的面试14类似。
     */
    public List<Integer> findAnagrams(String s1, String s2) {
        List<Integer> indices = new LinkedList<>();
        if (s1.length() < s2.length()) {
            return indices;
        }

        int[] counts = new int[26];
        //
        for (int i = 0; i < s2.length(); i++) {
            counts[s2.charAt(i) - 'a']++;
            counts[s1.charAt(i) - 'a']--;
        }

        if (areAllZero(counts)) {
            indices.add(0);
        }

        for (int i = s2.length(); i < s1.length(); i++) {
            counts[s1.charAt(i) - 'a']--;
            counts[s1.charAt(i - s2.length()) - 'a']++;
            if (areAllZero(counts)) {
                indices.add(i - s2.length() + 1);
            }
        }

        return indices;
    }

    /**
     * 面试题16：不含重复字符的最长子字符串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 思路：
     * 从start开始遍历，但是第一步是while loop 来推进end, 直到推不动end, 然后start++
     * 巧妙：为end是外围variable，在start的loop上，end不会重置；[start~end]中间不需要重复计算
     * 最终可以O（n）
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        boolean[] chars = new boolean[256]; // 256 ASCII code
        int longest = 0;
        int left = 0;
        int right = 0;
        while (left < s.length()) {
            while (right < s.length() && !chars[s.charAt(right)]) {
                chars[s.charAt(right)] = true;
                longest = Math.max(longest, right - left + 1);
                right++;
            }
            chars[s.charAt(left)] = false; // 这个地方是关键
            left++;
        }
        return longest;
    }

    private boolean areAllZero(int[] counts) {
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 题目3：包含所有字符的最短字符串, 比较难理解。 <a href="https://leetcode.cn/problems/M1oyTv/description/">...</a>
     * 输入两个字符串s和t，请找出字符串s中包含字符串t的所有字符的最短子字符串。
     * 例如，输入的字符串s为"ADDBANCAD"，字符串t为"ABC"，则字符串s中包含字符'A'、'B'和'C'的最短子字符串是"BANC"。
     * 如果不存在符合条件的子字符串，则返回空字符串""。如果存在多个符合条件的子字符串，则返回任意一个。
     * <p>
     * 解法：
     * 1. 用哈希表统计字符串t的char出现的次数，+1
     * 2. left, right 指针遍历字符串s，如果char出现在t中，则哈希表对应位置-1
     * 3. 如果两个指针之间的子字符串还没有包含字符串t的所有字符，则在子字符串中添加新的字符，于是向右移动第2个指针。
     * 4. 如果某一时刻两个指针之间的子字符串已经包含字符串t的所有字符，由于目标是找出最短的符合条件的子字符串，因此向右移动第1个指针，
     * 以判断删除子字符串最左边的字符之后是否仍然包含字符串t的所有字符。
     */
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> chartToCount = new HashMap<>();

        for (char ch : t.toCharArray()) {
            chartToCount.put(ch, chartToCount.getOrDefault(ch, 0) + 1);
        }

        // count 是出现在出现在字符串t中，但是没有出现在字符串s中的子字符串的个数。
        int count = chartToCount.size();
        int start = 0, end = 0, minStart = 0, minEnd = 0;
        int minLength = Integer.MAX_VALUE;
        while (end < s.length() || (count == 0 && end == s.length())) {
            if (count > 0) {
                // step 3, 右移指针
                char endChar = s.charAt(end);
                if (chartToCount.containsKey(endChar)) {
                    chartToCount.put(endChar, chartToCount.get(endChar) - 1);
                    // = 0 说明
                    if (chartToCount.get(endChar) == 0) {
                        count--;
                    }
                }
                end++;
            } else {
                if (end - start < minLength) {
                    minLength = end - start;
                    minStart = start;
                    minEnd = end;
                }

                char starChar = s.charAt(start);
                if (chartToCount.containsKey(starChar)) {
                    chartToCount.put(starChar, chartToCount.get(starChar) + 1);
                    if (chartToCount.get(starChar) == 1) {
                        count++;
                    }
                }
                start++;
            }
        }
        return minLength < Integer.MAX_VALUE ? s.substring(minStart, minEnd) : "";
    }

    /**
     * 面试题18： 有效的回文
     * 判断一个字符串是否是一个回文字符串，假设只需要考虑字母和数字字符，并忽略大小写。回文是正着读和反着读都是一样的单词。
     * 解法：双指针，分别从前后遍历，判断是否相等。
     */
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            if (!Character.isLetterOrDigit(ch1)) {
                i++;
            } else if (!Character.isLetterOrDigit(ch2)) {
                j--;
            } else {
                ch1 = Character.toLowerCase(ch1);
                ch2 = Character.toLowerCase(ch2);
                if (ch1 != ch2) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }

    /**
     * 面试题19：最多删除一个字符得到回文
     * <p>
     * 给定一个字符串，判断如果最多从字符串中删除一个字符能不能得到一个回文字符串。
     * 例如 输入abca, 由于删除字符b或c，就能得到一个回文字符串，则输出为true
     * <p>
     * 解法：一样还是左右指针
     */
    public boolean validPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        for (; start < s.length() / 2; start++, end--) {
            if (s.charAt(start) != s.charAt(end)) {
                break;
            }
        }

        // start == s.length() / 2 , 是指字符串本身就是回文字符串。
        return start == s.length() / 2
                || isPalindrome(s, start, end - 1)
                || isPalindrome(s, start + 1, end);
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                break;
            }
            start++;
            end--;
        }
        return start >= end;
    }

    /**
     * 剑指offer 2：
     * 面试题5：
     * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为 "We Are Happy". 则经过替换之后的字符串为 "We%20Are%20Happy"。
     * 即在不使用额外数组空间的情况下（除了必要的输出缓冲区）原地替换字符串中的字符。
     * 快慢指针，从后往前
     */
    public String replaceSpace(String input) {
        int spaceCount = 0;
        // 计算空格数量
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                spaceCount++;
            }
        }
        int newLength = input.length() + 2 * spaceCount;
        // 创建新的字符数组存储新字符串，这是必要的输出缓冲区
        char[] newStr = new char[newLength];
        int p1 = input.length() - 1;
        int p2 = newLength - 1;
        while (p1 >= 0) {
            if (input.charAt(p1) == ' ') {
                newStr[p2--] = '0';
                newStr[p2--] = '2';
                newStr[p2--] = '%';
            } else {
                newStr[p2--] = input.charAt(p1);
            }
            p1--;
        }
        return new String(newStr);
    }

    /**
     * 面试题394: 字符串解码，有点儿难度。
     * <a href="https://leetcode.cn/problems/decode-string/description/">...</a>
     * <p>
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     * <p>
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     * <p>
     * 思路：
     * 双栈法
     */
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>(); // 存储数字
        Stack<String> stringStack = new Stack<>(); // 存储字符串
        String currentString = ""; // 当前解码字符串
        int k = 0; // 当前的倍数

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0'); //处理多位数，向 '20'
            } else if (ch == '[') {
                // 遇到[. push to stack
                countStack.push(k);
                stringStack.push(currentString);
                currentString = "";
                k = 0;
            } else if (ch == ']') {
                // 解码
                StringBuilder temp = new StringBuilder(stringStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(currentString);
                }
                currentString = temp.toString();
            } else {
                currentString += ch;
            }
        }
        return currentString;
    }

    // https://leetcode.cn/problems/existence-of-a-substring-in-a-string-and-its-reverse/description/
    public boolean isSubstringPresent(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        String reversedS = new StringBuilder(s).reverse().toString();
        for (int i = 0; i < s.length() - 1; i++) {
            String temp = s.substring(i, i + 2);
            if (reversedS.contains(temp)) {
                return true;
            }
        }
        return false;
    }
}
