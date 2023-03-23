package com.haofan.algorithm.offer.List;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class ListNodeTest {
    private ListSolution list = new ListSolution();

    // create List
    private ListNode arrayToList(int[] input) {
        ListNode dumpy = new ListNode(0);

        ListNode head = dumpy;
        for (int val: input) {
            head.next = new ListNode(val);
            head = head.next;
        }

        return dumpy.next;
    }

    private int[] listToArray(ListNode head) {
        List<Integer> res = new LinkedList<>();

        ListNode node = head;
        res.add(node.val);
        while (node.next != null) {
            res.add(node.next.val);
            node = node.next;
        }

        int[] array = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            array[i] = res.get(i);
        }

        return array;
    }

    @Test
    void delete() {
        Assertions.assertArrayEquals(new int[]{4, 6, 8}, listToArray(list.delete(arrayToList(new int[]{4, 5, 6, 8}),5)));
    }

    @Test
    void removeNthFromEnd() {
        Assertions.assertArrayEquals(new int[]{4, 5, 6, 7}, listToArray(list.removeNthFromEnd(arrayToList(new int[]{4, 5, 6, 8, 7}), 2)));
    }

    @Test
    void detectCycle() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = head.next.next;

        Assertions.assertEquals(3, list.detectCycle(head).val);
    }

    @Test
    void getIntersectionNode() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(7);
        ListNode intersectionNode = new ListNode(4);

        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = intersectionNode;
        intersectionNode.next = new ListNode(5);

        head2.next = new ListNode(8);
        head2.next.next = intersectionNode;

        Assertions.assertEquals(intersectionNode, list.getIntersectionNode(head1, head2));
    }

    @Test
    void reverseList() {
        int[] array = new int[]{1, 2, 3, 4};
        ListNode raw = arrayToList(array);
        ListNode reverse = list.reverseList(raw);
        Assertions.assertArrayEquals(new int[]{4, 3, 2, 1}, listToArray(reverse));
    }

    @Test
    void addTwoNumbers() {
        ListNode head = list.addTwoNumbers(arrayToList(new int[]{9, 8, 4}), arrayToList(new int[]{1, 8}));
        Assertions.assertArrayEquals(new int[]{1, 0, 0, 2}, listToArray(head));
    }

    @Test
    void reorderList() {
        int[] res = listToArray(list.reorderList(arrayToList(new int[]{1, 2, 3, 4, 5, 6})));
        Assertions.assertArrayEquals(new int[] {1, 6, 2, 5, 3, 4}, res);
    }

    @Test
    void isPalindrome() {
        list.isPalindrome(arrayToList(new int[]{1, 2, 3, 3, 2, 1}));
    }
}