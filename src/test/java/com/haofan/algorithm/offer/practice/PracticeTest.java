package com.haofan.algorithm.offer.practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PracticeTest {

    private Practice p = new Practice();

    @Test
    void minPathSum() {
        System.out.println(p.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }

    @Test
    void rob() {
        System.out.println(p.rob(new int[]{1,2,3,1}));
    }

    @Test
    void rob3() {
        assertEquals(16, p.maxProduct(new String[] {"abcw","baz","foo","bar","fxyz","abcdef"}));
    }

    @Test
    void intersect() {
        // 题目6：
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println("题目6： 两个数组的交集： " + Arrays.toString(p.intersect(nums1, nums2)));
    }

    @Test
    void plusOne() throws InterruptedException {
        int[] plusOne = {1, 2, 3};
        System.out.println("题目7： 加一: " + Arrays.toString(p.plusOne(plusOne)));

        int[] temp = {4, 9, 9};
        // System.out.println("题目7： 加一 " + Arrays.toString(p.plusOne(temp)));
    }

    @Test
    void moveZeroes() {
        int[] move = {0, 1, 0, 3, 12};
        System.out.println("题目8： 移动0 " + Arrays.toString(p.moveZeroes(move)));
    }

    @Test
    void testRotate() {
        int[][] rotateArr = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        p.rotate(rotateArr);
        for (int i = 0; i < rotateArr.length; i++) {
            System.out.println("题目11： 旋转图像 " + Arrays.toString(rotateArr[i]));
        }
    }
}