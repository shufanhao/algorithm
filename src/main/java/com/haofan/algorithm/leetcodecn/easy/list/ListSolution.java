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
}
