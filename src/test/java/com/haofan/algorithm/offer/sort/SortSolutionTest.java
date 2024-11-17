package com.haofan.algorithm.offer.sort;

import com.haofan.algorithm.help.ListNode;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortSolutionTest {
    private SortSolution sort = new SortSolution();

    @Test
    void merge() {
        // intervals = [[1,3],[8,10],[2,6]，[15,18]]
        int[][] result = sort.merge(new int[][]{{1, 3}, {8, 10}, {2, 6}, {15, 18}});
        Assert.assertEquals(3, result.length);
    }

    @Test
    void sortArray() {
        System.out.println(Arrays.toString(sort.sortArray(new int[]{2, 3, 2, 3, 2, 1})));
    }

    @Test
    void relativeSortArray() {
        System.out.println(Arrays.toString(sort.relativeSortArray(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9}, new int[]{2, 1, 4})));
    }

    @Test
    void quickSort() {
        int[] raw = new int[]{3, 5, 4, 7, 6};
        sort.quickSort(raw, 0, 4);
        System.out.println(Arrays.toString(raw));
    }

    @Test
    void sortList() {
        sort.sortList(ListNode.arrayToList(new int[]{4, 3, 2, 8}));
    }

    @Test
    void mergeKLists() {
        ListNode[] lists = new ListNode[]{ListNode.arrayToList(new int[]{1, 4, 7}), ListNode.arrayToList(new int[]{2, 5, 8})};
        ListNode result = sort.mergeKLists(lists);
        assertArrayEquals(new int[]{1, 2, 4, 5, 7, 8}, ListNode.listToArray(result));
    }

    @Test
    void reconstructQueue() {
        int[][] arrays = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        sort.reconstructQueue(arrays);
    }
}