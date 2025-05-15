package com.haofan.algorithm.offer.List;

import com.haofan.algorithm.help.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
     * <p> <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">...</a>
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
     * 2. 如何找到环的入口节点。两个指针，指向头结点，如果链表中环有n个节点，第一个指针先向前移动n步，两个指针相同的速度向前移动，
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
     * 面试题23：两个链表的第一个重合点，如下 找出 第一个重合点 <a href="https://leetcode.cn/problems/3u1WK4/">...</a>
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
     * 题目4：合并两个有序链表，新链表是通过拼接给定的两个链表的所有节点组成的
     * 输入的是递增排序的
     *
     * @return 合并后的链表
     */
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }
        // 创建一个临时节点，用于添加元素时方便
        ListNode dummy = new ListNode(0);
        // 用于指向合并后的链表的指针
        ListNode node = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                // 让指针指向head1，同时head1指针指向下一个元素
                node.next = head1;
                head1 = head1.next;
            } else {
                node.next = head2;
                head2 = head2.next;
            }
            // 合并后的链表的指针也要指向下一个
            node = node.next;
        }

        // 如果第一个链表的元素未处理完，则将其合并链表的最后一个节点之后
        if (head1 != null) {
            node.next = head1;
        }
        // 如果第二个链表的元素未处理完，则将其合并链表的最后一个节点之后
        if (head2 != null) {
            node.next = head2;
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
     * <p>
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

    static class NodeT {
        int val;
        NodeT next;
        NodeT random;

        public NodeT(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 138. <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/">...</a>
     *
     * Copy List with Random Pointer
     *
     * 思路：HashMap 建立新旧节点映射，key是旧节点，先遍历一遍初始化链表及val值，并创建hash表，再遍历一遍，根据
     * 哈希表处理random
     */
    public NodeT copyRandomList(NodeT head) {
        NodeT preHead = new NodeT(0);
        NodeT p = head, q = preHead;

        Map<NodeT, NodeT> map = new HashMap<>();
        // 第一次遍历链表，复制节点值并建立修旧节点映射
        while (p != null) {
            q.next = new NodeT(p.val);
            q = q.next;
            map.put(p, q);
            p = p.next;
        }

        // 第二次遍历链表，根据map处理random指针
        p = head;
        q = preHead.next;
        while (p != null) {
            if (p.random != null) {
                q.random = map.get(p.random);
            }
            p = p.next;
            q = q.next;
        }

        return preHead.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/reverse-linked-list-ii/solutions/634701/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/?envType=study-plan-v2&envId=top-interview-150">...</a>
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;

        // 1. 虚拟node 走left - 1
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 2. pre 再走right - left + 1
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 3. 切断一个子链表
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        // 切断链表
        pre.next = null;
        rightNode.next = null;

        // 4. revere 截断的链表
        reverseList(leftNode);

        // 5. 接回到原来的链表
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    /**
     * 面试题 82. Remove Duplicates from Sorted List II
     *
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii">...</a>
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode current = head;

        while (current != null) {
            // skip duplicates nodes
            while (current.next != null && current.val == current.next.val) {
                current = current.next;
            }

            // no duplicate, 这个地方比较有技巧
            if (pre.next == current) {
                pre = pre.next;
            } else {
                // skip duplicate
                pre.next = current.next;
            }
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * 面试题61: Rotate List <a href="https://leetcode.cn/problems/rotate-list/?envType=study-plan-v2&envId=top-interview-150">...</a>
     *
     * 自己写的稍微复杂一点儿。
     *
     * 其实是可以通过：
     * 找到链表的尾节点，并将其与头节点连接，形成一个环。
     * 计算需要旋转的步数 position = k % length。
     * 找到新的尾节点，它是从头节点开始的第 length - position - 1 个节点。
     * 将链表断开，返回新的头节点。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        // get the length of this list
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        int position = k % length;

        Map<Integer, Integer> map = new TreeMap<>();

        node = head;
        for (int i = 0; i < length && node != null; i++) {
            map.put((i + position) % length, node.val);
            node = node.next;
        }

        ListNode rotatedDummy = new ListNode(-1);
        ListNode rotatedNode = rotatedDummy;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            rotatedNode.next = new ListNode(entry.getValue());
            rotatedNode = rotatedNode.next;
        }

        return rotatedDummy.next;
    }

    /**
     * 86. Partition List <a href="https://leetcode.cn/problems/partition-list/?envType=study-plan-v2&envId=top-interview-150">...</a>
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }

        // < x
        ListNode dummyHead1 = new ListNode(-1);
        ListNode node1 = dummyHead1;
        // >x
        ListNode dummyHead2 = new ListNode(-1);
        ListNode node2 = dummyHead2;

        ListNode node = head;
        while (node != null) {
            if (node.val < x) {
                node1.next = new ListNode(node.val);
                node1 = node1.next;
            } else {
                node2.next = new ListNode(node.val);
                node2 = node2.next;
            }
            node = node.next;
        }
        node1.next = dummyHead2.next;
        return dummyHead1.next;
    }
}
