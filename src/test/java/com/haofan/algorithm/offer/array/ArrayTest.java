package com.haofan.algorithm.offer.array;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest {

    private Array array = new Array();

    @Test
    void twoSum1() {
        int[] numbers = {1, 2, 4, 6, 10};
        int target = 8;
        int[] result1 = array.twoSum1(numbers, target);
        assertEquals(1, result1[0]);
        assertEquals(3, result1[1]);

        int[]  result2 = array.twoSum2(numbers, target);
        assertEquals(1, result2[0]);
        assertEquals(3, result2[1]);
    }

    @Test
    void threeSum() {
        int[] nums = {-1, 0, 1, 2 , -1, -4};
        System.out.println("Three sum: " + Arrays.toString(array.threeSum(nums).toArray()));
    }

    @Test
    void minSunArrayLen() {
        int[] nums = {5, 1, 4, 3};
        assertEquals(2, array.minSunArrayLen(7, nums));
    }

    @Test
    void numSubarrayProductLessThanK() {
        int[] nums = {10, 5, 2, 6};
        assertEquals(8, array.numSubarrayProductLessThanK(nums, 100));
    }
}