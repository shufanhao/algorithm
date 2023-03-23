package com.haofan.algorithm.leetcodecn.easy.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArraySolutionSolutionTest {

    private ArraySolution array = new ArraySolution();
    @Test
    void removeDuplicates() {
        // 题目1：
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        assertEquals(5, array.removeDuplicates(nums), "题目1 从排序数组中删除重复项: ");
    }
}