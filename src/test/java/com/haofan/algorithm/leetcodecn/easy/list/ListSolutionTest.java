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
}