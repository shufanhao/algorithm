package com.haofan.algorithm.offer.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BackTracking {

    /**
     * 面试题77: 组合问题
     *
     * https://leetcode.cn/problems/combinations/description/
     *
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 解法：经典的回溯法去解决问题
     */
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return result;
    }

    private void backtracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backtracking(n, k, i + 1);
            path.removeLast();
        }
    }
}
