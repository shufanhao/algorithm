package com.haofan.algorithm.leetcodecn.easy.string;

import com.haofan.algorithm.leetcodecn.easy.string.StringSolution;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


class StringSolutionTest {

    StringSolution s = new StringSolution();
    @Test
    void reverseString() {
        char[] c = "haofan".toCharArray();
        s.reverseString(c);
        System.out.println(Arrays.toString(c));
    }

    @Test
    void reverse() {
        Assertions.assertEquals(-201, s.reverse(-102));
    }

    @Test
    void firstUniqChar() {
        Assertions.assertEquals(2, s.firstUniqChar("loveleetcode"));
    }

    @Test
    void isAnagram() {
        Assertions.assertFalse(s.isAnagram("rat", "car"));
    }

    @Test
    void isPalindrome() {
    }

    @Test
    void isNumOrAlph() {
    }

    @Test
    void myAtoi() {
        Assertions.assertEquals(4193, s.myAtoi("  4193 with words"));
    }

    @Test
    void strStr() {
        Assertions.assertEquals(2, s.strStr("hello", "ll"));
    }

    @Test
    void countAndSay() {
    }

    @Test
    void longestCommonPrefix() {
    }
}