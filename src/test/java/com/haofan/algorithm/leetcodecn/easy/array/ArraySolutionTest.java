package com.haofan.algorithm.leetcodecn.easy.array;

import org.junit.jupiter.api.Test;

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
    }

    @Test
    void intersect() {
    }

    @Test
    void plusOne() {
    }

    @Test
    void moveZeroes() {
    }

    @Test
    void twoSum() {
    }

    @Test
    void isValidSudoku() {
    }

    @Test
    void testRotate() {
    }

    @Test
    void getOnsecutiveMaxLen() {
    }

    @Test
    void minSubArrayLen() {
    }

    @Test
    void maxSubArrayAverage() {
    }

    @Test
    void main() {
    }

    @Test
    void removeDuplicates() {
    }
}