package com.haofan.algorithm.offer.string;

import java.util.Arrays;

public class StringOffer {

    /**
     * 题目1：输入字符串s1和s2，如何判断字符串s2中是否包含字符串s1的某个变位词？
     * 如果字符串s2中包含字符串s1的某个变位词，则字符串s1至少有一个变位词是字符串s2的子字符串。假设两个字符串中只包含英文小写字母。
     * 例如，字符串s1为"ac"，字符串s2为"dgcaf"，由于字符串s2中包含字符串s1的变位词"ca"，因此输出为true。
     * 如果字符串s1为"ab"，字符串s2为"dgcaf"，则输出为false。
     * 变位词就是字母出现的次数一样。
     *
     * 解法: 完全按照书上的方式。
     * 1. 因为字符串只可能包含小写字母，所以可以将每个字母的出现次数放在一个26长度的数组中，index是charAt()- 'a'，value是次数
     * 2. 先统计ac中字母出现的哈希表
     * 3. left, right 指针从遍历s2, left指向s2的第一个字符，right指向子字符串的最后一个字符，双指针指向dg,然后得到哈希表
     * 4. 双指针都向右移动，1位，定位到gc, 每次移动指针，也就是最右边添加一个字符，最左边删除一个字符，添加一个字符，则对应的位置-1，
     *    删除一个字符对应的位置+1
     * 5. 判断是否全0
     */
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        int[] counts = new int[26];
        for (int i=0; i< s1.length(); ++i) {
            counts[s1.charAt(i) - 'a'] ++;
            counts[s2.charAt(i) - 'a'] --;
        }

        if (areAllZero(counts)) {
            return true;
        }

        for (int i=s1.length(); i<s2.length(); ++i) {
            // i 相当于右指针， i- s1.length() 相当于左指针
            counts[s2.charAt(i) - 'a'] --;
            counts[s2.charAt(i - s1.length()) - 'a'] ++;
            if (areAllZero(counts)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 题目2：不含重复字符的最长子字符串
     *  输入一个字符串，求该字符串中不含重复字符的最长子字符串的长度。例如，输入字符串"babcca"，其最长的不含重复字符的子字符串是"abc"，长度为3。
     *
     *  解法：左右指针，按照书中的解法
     *  假设到ba, 左：b, 右 a. 没有重复的话，右指针+1, 如果有重复左指针+1
     *  因为没有说可能只有限定的字符，假设字符串只包含ASCII码的话，则有256种可能。还是像上面题目一样，讲char出现的次数放在hash表中。
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int[] counts = new int[256];
        int right = 0;
        int left = -1;
        int longest = 1;
        for (; right < s.length(); right++) {
            counts[s.charAt(right)] ++;
            while (hasGreaterThan1(counts)) {
                // 有重复的那么左指针+1，并且将左指针对应的char清零
                left ++;
                counts[s.charAt(left)] --;
            }
            longest = Math.max(right - left, longest);
        }
        return longest;
    }

    private boolean hasGreaterThan1(int[] counts) {
        for (int count: counts) {
            if (count > 1) {
                return true;
            }
        }
        return false;
    }
    private boolean areAllZero(int[] counts) {
        for (int count: counts) {
            if (count != 0 ) {
                return false;
            }
        }
        return true;
    }
}
