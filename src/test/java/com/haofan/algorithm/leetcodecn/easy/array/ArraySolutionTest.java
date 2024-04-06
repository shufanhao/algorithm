package com.haofan.algorithm.leetcodecn.easy.array;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArraySolutionTest {
    private ArraySolution array = new ArraySolution();

    @Test
    void sortColors() {
        int[] nums = {2, 0, 1};
        array.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    void maxProfitI() {
        int[] nums = {7,1,5,3,6,4};
        assertEquals(5, array.maxProfitI(nums));
        assertEquals(5, array.maxProfitIDP(nums));
    }

    @Test
    void maxProfitII() {
        int[] nums = {7,1,5,3,6,4};
        assertEquals(7, array.maxProfitII(nums));
    }

    @Test
    void maxProfitIII() {
    }

    @Test
    void rotate() {
        int[] rotate = {1, 2, 3, 4, 5, 6, 7};
        array.rotate(rotate, 3);
        System.out.println("旋转数组： " + Arrays.toString(rotate));

        array.rotate(new int[]{-1}, 2);
    }

    @Test
    void reverse() {
    }

    @Test
    void containsDuplicate() {
        assertTrue(array.containsDuplicate(new int[] {0, 1, 0}));
    }

    @Test
    void singleNumber() {
        // 题目5：
        int[] single = {4, 1, 2, 1, 2};
        System.out.println("题目5：只出现一次的数字： " + array.singleNumber(single));
    }

    @Test
    void intersect() {
        // 题目6：
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println("题目6： 两个数组的交集： " + Arrays.toString(array.intersect(nums1, nums2)));
    }

    @Test
    void plusOne() throws InterruptedException {
        int[] plusOne = {9, 9, 9};
        System.out.println("题目7： 加一： 范宇增 " + Arrays.toString(array.plusOne(plusOne)));
    }

    @Test
    void moveZeroes() {
        int[] move = {0, 1, 0, 3, 12};
        System.out.println("题目8： 移动0 " + Arrays.toString(array.moveZeroes(move)));
    }

    @Test
    void twoSum() {
        int[] twosSumArr = {2, 7, 11, 15};
        System.out.println("题目9： 两数之和 " + Arrays.toString(array.twoSum(twosSumArr, 9)));
    }

    @Test
    void isValidSudoku() {
        char[][] a = {
                {'.', '8', '7', '6', '5', '4', '3', '2', '1'},
                {'2', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'3', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'4', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'6', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'9', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        assertTrue(array.isValidSudoku(a));
    }

    @Test
    void testRotate() {
        int[][] rotateArr = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        array.rotate(rotateArr);
        for (int i = 0; i < rotateArr.length; i++) {
            System.out.println("题目11： 旋转图像 " + Arrays.toString(rotateArr[i]));
        }
    }

    @Test
    void getOnsecutiveMaxLen() {
        int[] arr = {1, 1, 2, 2, 3, 3, 1, 1, 1, 1};
        System.out.println("题目12：输出数组中最大的连续子序列的长度 " + array.getOnsecutiveMaxLen(arr));
    }

    @Test
    void minSubArrayLen() {
        int[] arr = {2, 3, 1, 2, 4, 3};
        assertEquals(2, array.minSubArrayLen(7, arr));
    }

    @Test
    void findMaxAverage() {
        int[] arr = {1,12,-5,-6,50,3};
        assertEquals(12.75, array.findMaxAverage(arr, 4));
    }

    @Test
    void removeDuplicates() {
        int[] arr = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(array.removeDuplicates(arr));
    }
}