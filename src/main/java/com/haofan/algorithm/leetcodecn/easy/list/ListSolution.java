package com.haofan.algorithm.leetcodecn.easy.list;

import com.haofan.algorithm.help.ListNode;

/**
 * 链表问题，
 * 1. 注意双指针解法，双指针：快慢指针和首尾指针 （一般用于使用额外空间的情况）
 * 2."Dummy node" 节点技巧，所谓Dummy Node其实就是带头节点的指针。
 */
public class ListSolution {

    /**
     * 题目1：删除链表中的节点 <a href="https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/">...</a>
     *  要删除的节点一定在是链表中的节点。
     * 思路：其实是修改的要删除节点的值，两个指针，pre and cur, 要删除节点就是pre.next = cur.next;

     * -3,5,-99,
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = head, cur = head.next;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }

        if (cur != null) pre.next = cur.next;

        return head;
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
        ListNode root = new ListNode(0);
        // 用于指向合并后的链表的指针
        ListNode pointer = root;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                // 让指针指向head1，同时head1指针指向下一个元素
                pointer.next = head1;
                head1 = head1.next;
            } else {
                pointer.next = head2;
                head2 = head2.next;
            }
            // 合并后的链表的指针也要指向下一个
            pointer = pointer.next;
        }

        // 如果第一个链表的元素未处理完，则将其合并链表的最后一个节点之后
        if (head1 != null) {
            pointer.next = head1;
        }
        // 如果第二个链表的元素未处理完，则将其合并链表的最后一个节点之后
        if (head2 != null) {
            pointer.next = head2;
        }
        return root.next;
    }
}
