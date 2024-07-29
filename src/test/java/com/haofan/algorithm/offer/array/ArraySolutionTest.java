package com.haofan.algorithm.offer.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArraySolutionTest {

    private ArraySolution arraySolution = new ArraySolution();

    @Test
    void twoSum1() {
        int[] numbers = {1, 2, 4, 6, 10};
        int target = 8;
        int[] result1 = arraySolution.twoSum1(numbers, target);
        assertEquals(3, result1[0]);
        assertEquals(1, result1[1]);

        int[] result2 = arraySolution.twoSum2(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(Arrays.stream(result2).toArray()));
    }

    @Test
    void threeSum() {
        int[] nums = {-1, 0, 1, 2 , -1, -4};
        System.out.println("Three sum: " + Arrays.toString(arraySolution.threeSum(nums).toArray()));
    }

    @Test
    void minSunArrayLen() {
        int[] nums = {5, 1, 4, 3};
        assertEquals(2, arraySolution.minSunArrayLen(7, nums));
    }

    @Test
    void numSubarrayProductLessThanK() {
        int[] nums = {10, 5, 2, 6};
        assertEquals(8, arraySolution.numSubarrayProductLessThanK(nums, 100));
    }

    @Test
    void subArraySum() {
        int[] sums = {3, 4, 7, 2, -3, 1, 4, 2};
        assertEquals(4, arraySolution.subArraySum(sums, 7));
    }

    @Test
    void findMaxLength() {
        assertEquals(2, arraySolution.findMaxLength(new int[] {0, 1, 0}));
    }

    @Test
    void findDuplicate() {
        assertTrue(arraySolution.findDuplicate(new int[]{2, 3, 1, 0, 2, 5}));
    }

    @Test
    void productExceptSelf() {
        assertArrayEquals(new int[]{24,12,8,6}, arraySolution.productExceptSelf(new int[]{1, 2, 3, 4}));
    }

    @Test
    void searchMatrix() {
        int[][] matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        assertTrue(arraySolution.searchMatrix(matrix, 5));
        assertTrue(arraySolution.searchMatrix1(matrix, 5));
    }
}
