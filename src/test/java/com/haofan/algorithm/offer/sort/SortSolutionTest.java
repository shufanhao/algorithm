package com.haofan.algorithm.offer.sort;

import com.haofan.algorithm.help.ListNode;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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
        System.out.println(Arrays.toString(sort.relativeSortArray(new int[]{2,3,1,3,2,4,6,7,9}, new int[]{2,1,4})));
    }

    @Test
    void sortArrayQuick() {
        System.out.println(Arrays.toString(sort.sortArrayQuick(new int[]{4, 3, 2, 8})));
    }

    @Test
    void sortList() {
        sort.sortList(ListNode.arrayToList(new int[]{4, 3, 2, 8}));
    }
}