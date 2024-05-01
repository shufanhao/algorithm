package com.haofan.algorithm.leetcodecn.easy.list;

import com.haofan.algorithm.help.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListSolutionTest {

    ListSolution list = new ListSolution();

    @Test
    void deleteNode() {
        ListNode head = ListNode.arrayToList(new int[]{-3,5,-99});
        list.deleteNode(head, 5);

        assertArrayEquals(new int[]{-3, -99}, ListNode.listToArray(head));

    }

    @Test
    void mergeTwoLists() {
        ListNode res = list.mergeTwoLists(ListNode.arrayToList(new int[]{1, 2, 4}), ListNode.arrayToList(new int[]{1, 3, 4}));
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 4}, ListNode.listToArray(res));
    }
}