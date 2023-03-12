package com.haofan.algorithm.offer.List;

public class ListSolution {
    /**
     * 理解哨兵节点
     * 通过创建哨兵节点，简化删除链表头结点操作
     */
    public ListNode delete(ListNode head, int value) {
        // 如果不创建dummy的话，则需要如下去判断
        /**
        if (head == null) {
            return null;
        }
        if (head.val == value) {
            return head.next;
        } */
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        while (node.next != null) {
            if (node.next.val == value) {
                node.next = node.next.next;
                break;
            }
            node = node.next;
        }
        return head;
    }

    /**
     * 面试题1： 删除链表的倒数第k个节点
     *
     * 解法：
     * 删除倒数第k个节点，主要要找到第k+1个节点即可。
     * left, right 指针，right先走，走k步，从k+1开始，left也走，当right到尾部时，left就是第k+1个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dump = new ListNode(0);
        dump.next = head;

        ListNode left = dump;
        ListNode right = head;

        for (int i=0; i < k; i++) {
            right = right.next;
        }

        while (right != null) {
            right = right.next;
            left = left.next;
        }

        left.next = left.next.next;
        return dump.next;
    }

    /**
     * 面试题2：链表中环的入口节点
     *
     * 如果链表中有环，那么应该如何找出环的入口节点。
     *           ----------------
     *           !    6再指向3   !
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6
     *
     * 解法：
     * 1. 首先要判断是否有环。快慢指针，快指针走2步，慢指针走一步，最终如果快慢指针相遇，则说明有环。
     * 2. 如果找到环的入口节点。两个指针，指向头结点，如果链表中环有n个节点，第一个指针先向前移动n步，两个指针相同的速度向前移动，
     *    当两个指针相遇时，正好是环的入口节点。
     * 3. 如何取到环的数目，找到任意一个环中的节点后，绕环一圈就是环的数目。
     */
    public ListNode detectCycle(ListNode head) {
        ListNode inLoop = getNodeInLoop(head);
        if (inLoop == null) {
            return null;
        }

        int loopCount = 1;
        for (ListNode n = inLoop; n.next != inLoop; n = n.next) {
            loopCount ++;
        }

        ListNode fast = head;
        for (int i = 0; i< loopCount; i++) {
            fast = fast.next;
        }
        ListNode slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    // 一块一慢指针，找到环中的一个节点。
    private ListNode getNodeInLoop(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = slow.next;
        while (slow != null && fast != null) {
            if (slow == fast) {
                return slow;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        return null;
    }
}
