package com.haofan.algorithm.offer.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArraySolutionTest {

    private ArraySolution array = new ArraySolution();

    @Test
    void twoSum1() {
        int[] numbers = {1, 2, 4, 6, 10};
        int target = 8;
        int[] result1 = array.twoSum1(numbers, target);
        assertEquals(3, result1[0]);
        assertEquals(1, result1[1]);

        int[] result2 = array.twoSum2(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(Arrays.stream(result2).toArray()));
    }

    @Test
    void threeSum() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println("Three sum: " + Arrays.toString(array.threeSum(nums).toArray()));
        System.out.println("Three sum: " + Arrays.toString(array.threeSumViaMap(nums).toArray()));

        final String metricName = String.format("%scert %s remaining days to expire", "haofan: ", "/app/test");
        System.out.println(metricName);
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

    @Test
    void subArraySum() {
        // int[] sums = {3, 4, 7, 2, -3, 1, 4, 2};
        int[] sums = {1, 2, 3};
        assertEquals(2, array.subArraySum(sums, 3));
    }

    @Test
    void findMaxLength() {
        assertEquals(2, array.findMaxLength(new int[]{0, 1, 0}));
    }

    @Test
    void findDuplicate() {
        assertEquals(2, array.findDuplicate(new int[]{2, 3, 1, 0, 2, 5}));
    }

    @Test
    void productExceptSelf() {
        assertArrayEquals(new int[]{24, 12, 8, 6}, array.productExceptSelf(new int[]{1, 2, 3, 4}));
    }

    @Test
    void searchMatrix() {
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        assertTrue(array.searchMatrix(matrix, 5));
        assertTrue(array.searchMatrix1(matrix, 5));
    }

    @Test
    void findDisappearedNumbers() {
        int[] input = new int[]{4, 3, 2, 7, 8, 2, 3, 1};

        // O(n) 空间复杂度
        List<Integer> result = array.findDisappearedNumbers(input);
        assertTrue(result.contains(5));
        assertTrue(result.contains(6));

        // O(1) 空间复杂度
        result = array.findDisappearedNumbers1(input);
        assertTrue(result.contains(5));
        assertTrue(result.contains(6));
    }

    @Test
    void subarraySum() {
        assertEquals(2, array.subarraySum(new int[]{1, 1, 1}, 2));
    }

    @Test
    void findUnsortedSubarray() {
        assertEquals(5, array.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }

    @Test
    void minAnagramLength() {
        assertEquals(2, array.minAnagramLength("abba"));
        // assertEquals(4, array.minAnagramLength("cdef"));
    }

    @Test
    void sortTheStudents() {
        int[][] students = new int[][]{{10, 6, 9, 1}, {7, 5, 11, 2}, {4, 8, 3, 15}};
        array.sortTheStudents(students, 2);
    }

    @Test
    void getKth() {
        assertEquals(148, array.getKth(144, 163, 5));
    }

    @Test
    void examRoom() {
        ArraySolution.ExamRoom room = new ArraySolution.ExamRoom(10);
        assertEquals(0, room.seat());
        assertEquals(9, room.seat());
        assertEquals(4, room.seat());
    }

    @Test
    void occurrencesOfElement() {
        int[] nums = new int[]{1,3,1,7};
        int[] queries = new int[]{1,3,2,4};
        assertArrayEquals(new int[]{0,-1,2,-1}, array.occurrencesOfElement(nums, queries, 1));
    }

    @Test
    void removeElement() {
        // nums = [0,1,2,2,3,0,4,2], val = 2
        // assertEquals(2, array.removeElement(new int[]{3,2,2,3}, 3));

        assertEquals(1, array.removeElement(new int[]{1}, 1));
    }

    @Test
    void isPossibleToSplit() {
        assertTrue(array.isPossibleToSplit(new int[]{1,1,2,2,3,4}));
    }

    @Test
    void hIndex() {
        assertEquals(3, array.hIndex(new int[]{3,0,6,1,5}));
    }

    @Test
    void minOperations() {
        assertEquals(0, array.minOperations(new int[]{1,1,2,4,9}, 1));
    }

    @Test
    void canCompleteCircuit() {
        assertEquals(3, array.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
    }

    @Test
    void candy() {
        assertEquals(4, array.candy(new int[]{1, 2, 2}));
    }

    @Test
    void spiralOrder() {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

        assertEquals(Arrays.asList(1,2,3,6,9,8,7,4,5), array.spiralOrder(matrix));
    }
}
