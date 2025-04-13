package com.haofan.algorithm.offer.sort;

import com.haofan.algorithm.help.ListNode;
import com.haofan.algorithm.sort.BubbleSort;
import com.haofan.algorithm.sort.MergeSort;
import com.haofan.algorithm.sort.QuickSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortSolutionTest {
    private SortSolution sort = new SortSolution();

    @Test
    void bubbleSort() {
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4}, BubbleSort.sort(new int[]{2, 1, 4, 3}));
    }

    @Test
    void quickSort() {
        int[] array = new int[]{3, 4, 1, 5, 6};
        new QuickSort().quickSort(array, 0, 4);
        Assertions.assertArrayEquals(new int[]{1, 3, 4, 5, 6}, array);
    }

    @Test
    void mergeSort() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        MergeSort.sort(arr);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
    }

    @Test
    void sortArray() {
        System.out.println(Arrays.toString(sort.sortArray(new int[]{2, 3, 2, 3, 2, 1})));
    }

    @Test
    void relativeSortArray() {
        Assertions.assertArrayEquals(new int[]{2, 2, 1, 4, 3, 3, 6, 7, 9}, sort.relativeSortArray(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9}, new int[]{2, 1, 4}));
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