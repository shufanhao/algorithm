package com.haofan.algorithm.offer.List;

import com.haofan.algorithm.help.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.haofan.algorithm.help.ListNode.arrayToList;
import static com.haofan.algorithm.help.ListNode.listToArray;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ListNodeTest {
    private ListSolution list = new ListSolution();

    @Test
    void delete() {
        assertArrayEquals(new int[]{4, 5, 6}, listToArray(list.delete(arrayToList(new int[]{4, 5, 6, 8}), 8)));
    }

    @Test
    void removeNthFromEnd() {
        // Assertions.assertArrayEquals(new int[]{4, 5, 6, 7}, listToArray(list.removeNthFromEnd(arrayToList(new int[]{4, 5, 6, 8, 7}), 2)));
        list.removeNthFromEnd(arrayToList(new int[]{1}), 1);
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
        assertArrayEquals(new int[]{4, 3, 2, 1}, listToArray(reverse));
    }

    @Test
    void addTwoNumbers() {
        ListNode head = list.addTwoNumbers(arrayToList(new int[]{9, 8, 4}), arrayToList(new int[]{1, 8}));
        assertArrayEquals(new int[]{1, 0, 0, 2}, listToArray(head));
    }

    @Test
    void reorderList() {
        int[] res = listToArray(list.reorderList(arrayToList(new int[]{1, 2, 3, 4, 5, 6})));
        assertArrayEquals(new int[]{1, 6, 2, 5, 3, 4}, res);
    }

    @Test
    void isPalindrome() {
        list.isPalindrome(arrayToList(new int[]{1, 2, 3, 3, 2, 1}));
    }

    @Test
    void mergeTwoLists() {
        ListNode res = list.mergeTwoLists(ListNode.arrayToList(new int[]{1, 2, 4}), ListNode.arrayToList(new int[]{1, 3, 4}));
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 4}, ListNode.listToArray(res));
    }

    @Test
    void hasCycle() {
        ListNode node = new ListNode(3);
        node.next = new ListNode(2);
        node.next.next = new ListNode(1);
        node.next.next = node;
        Assertions.assertTrue(list.hasCycle(node));
    }

    @Test
    void deleteDuplicates() {
        ListNode res = list.deleteDuplicates(ListNode.arrayToList(new int[]{1, 2, 3, 3, 4, 4, 5}));
        assertArrayEquals(new int[]{1, 2, 5}, ListNode.listToArray(res));
    }

    @Test
    void rotateRight() {
        List<Long> listT = Arrays.asList(0L, 30L, TimeUnit.HOURS.toMinutes(1), TimeUnit.HOURS.toMinutes(2), TimeUnit.HOURS.toMinutes(4), TimeUnit.HOURS.toMinutes(8), TimeUnit.HOURS.toMinutes(12), TimeUnit.DAYS.toMinutes(1), TimeUnit.DAYS.toMinutes(2), TimeUnit.DAYS.toMinutes(3));

        ListNode res = list.rotateRight(ListNode.arrayToList(new int[]{1, 2, 3, 4, 5}), 2);
        assertArrayEquals(new int[]{4,5,1,2,3}, ListNode.listToArray(res));

        ListNode res2 = list.rotateRight(ListNode.arrayToList(new int[]{0, 1, 2}), 4);
        assertArrayEquals(new int[]{2, 0, 1}, ListNode.listToArray(res2));
    }

    @Test
    void partition() {
        ListNode res = list.partition(ListNode.arrayToList(new int[]{1, 4, 3, 2, 5, 2}), 3);
        assertArrayEquals(new int[]{1, 2, 2, 4, 3, 5}, ListNode.listToArray(res));
    }
}