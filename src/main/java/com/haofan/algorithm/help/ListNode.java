package com.haofan.algorithm.help;

import java.util.LinkedList;
import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }


    // create List
    public static ListNode arrayToList(int[] input) {
        ListNode dumpy = new ListNode(0);

        ListNode head = dumpy;
        for (int val : input) {
            head.next = new ListNode(val);
            head = head.next;
        }

        return dumpy.next;
    }

    public static int[] listToArray(ListNode head) {
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

}
