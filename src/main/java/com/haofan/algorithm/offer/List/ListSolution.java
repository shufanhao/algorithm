package com.haofan.algorithm.offer.List;

import com.haofan.algorithm.help.ListNode;

import java.util.List;

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
     * 面试题21：删除链表的倒数第k个节点
     * <p>
     * 解法：
     * 删除倒数第k个节点，主要要找到第k+1个节点即可。
     * left, right 指针，right先走，走k步，从k+1开始，left也走，当right到尾部时，left就是第k+1个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dump = new ListNode(0);
        dump.next = head;

        ListNode left = dump;
        ListNode right = head;

        for (int i = 0; i < k; i++) {
            right = right.next;
        }

        while (right != null) {
            right = right.next;
            left = left.next;
        }

        assert left.next != null;
        left.next = left.next.next;
        return dump.next;
    }

    /**
     * 面试题22：链表中环的入口节点  <a href="https://leetcode.cn/problems/c32eOV/">...</a>
     * <p>
     * 如果链表中有环，那么应该如何找出环的入口节点。
     * ----------------
     * !    6再指向3   !
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * <p>
     * 解法：
     * 1. 首先要判断是否有环。快慢指针，快指针走2步，慢指针走一步，最终如果快慢指针相遇，则说明有环。
     * 2. 如果找到环的入口节点。两个指针，指向头结点，如果链表中环有n个节点，第一个指针先向前移动n步，两个指针相同的速度向前移动，
     * 当两个指针相遇时，正好是环的入口节点。
     * 3. 如何取到环的数目，找到任意一个环中的节点后，绕环一圈就是环的数目。
     */
    public ListNode detectCycle(ListNode head) {
        // 判断是否有环
        ListNode inLoop = getNodeInLoop(head);
        if (inLoop == null) {
            return null;
        }

        // 判断环的数目
        int loopCount = 1;
        for (ListNode n = inLoop; n.next != inLoop; n = n.next) {
            loopCount++;
        }

        // 快指针先走n步
        ListNode fast = head;
        for (int i = 0; i < loopCount; i++) {
            fast = fast.next;
        }

        // 当快慢指针再次相遇时，就是环的入口节点
        ListNode slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * 面试题23：两个链表的第一个重合点，如下 找出 第一个重合点 4 <a href="https://leetcode.cn/problems/3u1WK4/">...</a>
     * 1 -> 2 -> 3 ->
     * 4 -> 5 -> 6
     * 7 -> 8 ->
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
        for (int i = 0; i < delta; i++) {
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
     * 面试题24：翻转链表
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

    /**
     * 面试题25：链表中的数字相加 <a href="https://leetcode.cn/problems/lMSNwu/">...</a>
     * 给定两个表示非负整数的单向链表，如何实现这两个链表的相加，并且把他们的和仍然用单向链表表示？
     * 如：9 -> 8 -> 4        4 ->8 -> 9
     * 1 -> 8            8 -> 1
     * 结果应该是  2 -> 0 -> 0 -> 1，再翻转后 是1 -> 0 -> 0 -> 2
     * 解法：
     * 1. 如果单纯的把链表转成整数相加的话，如果链表很长有可能会溢出。
     * 2. 所以还是先把链表反转，然后最后从第一个元素开始相加，并且进位的方式，拿到结果后再反转。
     */
    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        ListNode sumNode = addReversed(head1, head2);
        return reverseList(sumNode);
    }

    private ListNode addReversed(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode sumNode = dummy;
        int carry = 0;
        while (head1 != null || head2 != null) {
            int sum = (head1 == null ? 0 : head1.val) + (head2 == null ? 0 : head2.val) + carry;
            carry = sum / 10;
            sumNode.next = new ListNode(sum % 10);
            sumNode = sumNode.next;

            head1 = head1 == null ? null : head1.next;
            head2 = head2 == null ? null : head2.next;
        }
        if (carry > 0) {
            sumNode.next = new ListNode(carry);
        }
        return dummy.next;
    }


    /**
     * 面试题26：重排链表 <a href="https://leetcode.cn/problems/LGjMqU/description/">...</a>
     * 重排链表: 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * 重排成：1 -> 6 -> 2 -> 5 -> 3 -> 4
     * <p>
     * 解法：
     * 1. 将链表后半段取出，然后翻转。
     * 2. 再和前半段链表连起来，即可。
     */
    public ListNode reorderList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // fast 走两步，slow 走一步，然后fast到终点后，slow后面的就是后半段
        }
        ListNode temp = slow.next;
        slow.next = null;

        link(head, reverseList(temp), dummy);
        return head;
    }

    private void link(ListNode node1, ListNode node2, ListNode head) {
        ListNode prev = head;
        while (node1 != null && node2 != null) {
            ListNode temp = node1.next; // 保存node1的下一个节点

            // 将node1链接到prev后面
            prev.next = node1;
            // 将node2链接到node1后面
            node1.next = node2;
            // 更新prev为node2，以便下一次循环时链接node1的下一个节点
            prev = node2;

            node1 = temp; // 移动node1到它的下一个节点
            node2 = node2.next; // 移动node2到它的下一个节点
        }

        if (node1 != null) {
            prev.next = node1;
        }
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


    /**
     * 面试题27：回文链表 <a href="https://leetcode.cn/problems/aMhZSa/">...</a>
     * 1 -> 2 -> 3 -> 3 -> 2 -> 1
     * <p>
     * 解法和上面的类似：
     * 1. 快慢指针拿出链表的后半部分，然后将后半部分反转
     * 2. 判断反转后的链表和前半部分链表是否相同
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHalf = slow.next;
        if (fast.next != null) {
            secondHalf = slow.next.next;
        }
        slow.next = null;
        return equals(head, reverseList(secondHalf));
    }

    /**
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        ListNode dummy = new ListNode(0);
        ListNode root = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                root.next = head1;
                head1 = head1.next;
            } else {
                root.next = head2;
                head2 = head2.next;
            }
            root = root.next;
        }

        if (head1 != null) {
            root.next = head1;
        }
        if (head2 != null) {
            root.next = head2;
        }

        return dummy.next;
    }

    private boolean equals(ListNode head1, ListNode head2) {
        while (head1 != null && head2 != null) {
            if (head1.val != head2.val) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1 == null && head2 == null;
    }

    /**
     * 141 环形链表
     *
     * <a href="https://leetcode.cn/problems/linked-list-cycle/description/">...</a>
     *
     * 快慢指针，快指针走两步
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

}
