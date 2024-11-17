package com.haofan.algorithm.leetcodecn.medium.list;

import com.haofan.algorithm.help.ListNode;
import com.haofan.algorithm.offer.List.ListSolution;

/**
 * 链表问题
 */
public class ListSolutionMedium {

    /**
     * @param args
     */
    public static void main(String args[]) {
        ListSolutionMedium list = new ListSolutionMedium();

        ListSolutionMedium c = new ListSolutionMedium();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);


        ListSolution common = new ListSolution();
        int[] input = {1, 4, 5, 9};
        // common.printList(list.oddEvenList(common.createList(input)), "题目2：");

        int[] input1 = {0, 8, 4, 5};
        int[] input2 = {1, 8, 4, 5};

        // common.printList(list.getIntersectionNode(common.createList(input1),
        //          common.createList(input2)), "题目3：");
    }

    /**
     * 题目2：奇偶链表https://blog.csdn.net/NoMasp/article/details/50535947
     * 思路：
     * 将节点分成奇数节点和偶数节点，然后分别更改指向即可
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return head;
        ListNode odd = head;
        ListNode even = odd.next;
        ListNode evenHead = even;
        while (odd.next != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    /**
     * 题目3：相交链表，编写一个程序，找到两个链表相交的起始节点
     * https://blog.csdn.net/hocsoul/article/details/80151330
     * 思路：先取到链表的长度，然后移动长度长的链表到另一个链表长度一致的位置，开始比较
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenHeadA = 0;
        int lenHeadB = 0;
        ListNode tempA = headA;
        ListNode tempB = headB;

        // 求链表A,B的长度
        while (tempA != null) {
            lenHeadA++;
            tempA = tempA.next;
        }
        while (tempB != null) {
            lenHeadB++;
            tempB = tempB.next;
        }
        // 将headA 和 headB 移到要进行节点比较的位置, 只需移动长度较大的链表
        while ((lenHeadA > lenHeadB) && headA != null) {
            headA = headA.next;
            lenHeadA--;
        }
        while ((lenHeadB > lenHeadA) && headB != null) {
            headB = headB.next;
            lenHeadB--;
        }
        while (headA != null) {
            // 在leetcode 运行环境下，是可以用headA == headB 来认为两个链表是相等的
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
}
