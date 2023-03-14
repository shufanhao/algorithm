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

    /**
     * 面试题3：两个链表的第一个重合点，如下 找出 第一个重合点 4
     * 1 -> 2 -> 3 ->
     *               4 -> 5 -> 6
     *      7 -> 8 ->
     * 解法：
     * 1. 构建环形链表，将第一个链表的尾节点指向第二个链表的头节点，那么就是上一个题目，找出第一个入口节点。
     * 2. 或者是先找出两个链表的长度差值delta，然后让长的链表先走delta step，然后再同时开始走，这样第一个重合的节点就是重合点。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int countA = countList(headA);
        int countB = countList(headB);
        int delta = Math.abs(countA - countB);
        ListNode longer = countA > countB ? headA : headB;
        ListNode shorter = countA > countB ? headB : headA;
        ListNode node1 = longer;
        for (int i=0; i< delta; i++) {
            node1 = node1.next;
        }
        ListNode node2 = shorter;
        while (node1 != node2) {
            node2 = node2.next;
            node1 = node1.next;
        }
        return node1;
    }

    /**
     * 面试题4：翻转链表
     * 1 -> 2 -> 3  翻转成 1 <- 2 <- 3
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        // current node
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // get the count of this list
    private int countList(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
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
