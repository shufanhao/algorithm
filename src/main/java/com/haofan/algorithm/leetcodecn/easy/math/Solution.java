package com.haofan.algorithm.leetcodecn.easy.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("题目1：" + solution.fizzBuzz(15));

        System.out.println("题目2: " + solution.countPrimes(10));

        System.out.println("题目3: " + solution.isPowerOfThree(9));

        System.out.println("题目4；" + solution.romanToInt("LVIII"));
    }

    /**
     * 题目1：Fizz Buzz， 比较简单
     * 写一个程序，输出从 1 到 n 数字的字符串表示。
     * 1. 如果 n 是3的倍数，输出“Fizz”；
     * 2. 如果 n 是5的倍数，输出“Buzz”；
     * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
     */
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>(n);
        for (int i = 1; i <= 15; i++) {
            if (i % 15 == 0) {
                list.add("FizzBuzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    /**
     * 题目2：计算质数的数量，http://www.cnblogs.com/lightwindy/p/8549471.html
     * 思路：如果一个数是另一个数的倍数，那这个数肯定不是质数。
     * 从2开始，2所有的倍数，都不是质数，然后再从3开始，
     */
    public int countPrimes(int n) {
        boolean[] prime = new boolean[n];
        Arrays.fill(prime, true);
        for (int i = 2; i < n; i++) {
            if (prime[i]) {
                // 如果是true
                for (int j = i * 2; j < n; j = j + i) {
                    prime[j] = false;
                }
            }
        }
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i < prime.length; i++) {
            if (prime[i]) {
                list.add(i);
                count++;
            }
        }
        System.out.println("质数：" + list);
        return count;
    }

    /**
     * 题目3：3的幂，给定一个正整数，写一个函数来判断它是否是3的幂次方
     */
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        int chu = n / 3;
        int yu = n % 3;
        if (yu == 0 && chu == 1) {
            return true;
        }
        if (yu == 0) {
            return isPowerOfThree(chu);
        } else {
            return false;
        }
    }

    /**
     * 题目3：罗马数字转整数
     */
    public int romanToInt(String s) {
        if (s == null) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = map.get(s.charAt(i));
            if (i == s.length() - 1 || map.get(s.charAt(i + 1)) <= map.get(s.charAt(i))) {
                res = res + val;
            } else {
                res = res - val;
            }
        }
        return res;
    }

}
