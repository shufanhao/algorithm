package com.haofan.algorithm.leetcodecn.easy.list;

/**
 * 链表问题，
 * 1. 注意双指针解法，双指针：快慢指针和首尾指针 （一般用于使用额外空间的情况）
 * 2."Dummy node" 节点技巧，所谓Dummy Node其实就是带头节点的指针。
 */
public class ListSolution {

    public static void main(String args[]) {
        ListSolution c = new ListSolution();
        int[] input = {4, 5, 1, 9};
        ListNode headQ1 = c.createList(input);
        c.deleteNode(headQ1.next);
        c.printList(headQ1, "题目1");

        ListNode headQ2 = c.createList(input);
        c.removeNthFromEnd(headQ2, 2);
        c.printList(headQ2, "题目2");

        //ListNode headQ3 = c.createList(input);
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        head = c.reverseList(head);
        c.printList(head, "题目3");
        int[] input_1 = {1, 2, 4, 5};
        int[] input_2 = {2, 3, 4, 6};
        ListNode headQ4 = c.mergeTwoLists(c.createList(input_1),
                c.createList(input_2));
        c.printList(headQ4, "题目4");

        int[] input_5 = {1, 2, 2, 1};
        ListNode headQ5 = c.createList(input_5);
        System.out.println("题目5：" + c.isPalindrome(headQ5));

        System.out.println("题目6：" + c.hasCycle(c.createList(input)));
    }

    /**
     * 题目1：删除链表中的节点
     * 思路：其实是修改的要删除节点的值，将其改成和下一个节点一样的值
     */
    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            return;
        }
        // 找到 下一个节点
        ListNode nexNode = node.next;
        // 然后将 下一个节点的val,next 赋值给node就可以了
        node.val = nexNode.val;
        node.next = nexNode.next;
    }

    /**
     * 题目2: 删除链表的倒数第N个几点，并返回链表的头结点，尝试用一次扫描
     * 思路：如果题目不要求一次扫描的话，比较容易处理
     * 如果一次扫描：双指针让前指针先走N步，再让两个在指针同时后移，
     * 直到前指针到达尾部，此时，后指针的下一个节点就是要被删除的节点了
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }
        ListNode curHead = head; //后指针
        ListNode preHead = head; // 前指针
        for (int i = 0; i < n; i++) {
            preHead = preHead.next;
        }
        while (preHead != null) {
            preHead = preHead.next;
            curHead = curHead.next;
        }
        // 此时curHead 的下一个节点就是被删除的节点
        curHead.next = curHead.next.next;
        return head;
    }

    /**
     * 题目3：反转列表 https://www.jianshu.com/p/5043be2fc875
     * 思路：p,q,r三个指针一起配合，使得两个节点间的指向反向，同时用r及记录剩下的链表
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode p = new ListNode(0);
        ListNode q = new ListNode(0);
        ListNode r = new ListNode(0);
        p = head;
        q = head.next;
        p.next = null;
        while (q != null) {
            r = q.next; //r 是 q的下一个节点
            q.next = p;
            p = q; //为了下一次循环
            q = r;
        }
        head = p;
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

    /**
     * 题目5：回文链表，判断是否是回文链表，要求O(n)时间复杂度 和 O(1)空间复杂度
     * 思路：因为是O(1)空间复杂度，加大了题目的难度，使用快慢指针法
     * 1. 慢指针一次走一步，快指针一次走两步，当快指针走到底的时候，慢指针到了中间位置
     * 2. 找到中点后，对中点后面的元素进行反转
     * 3. 从头尾开始向中间移步，每次比较是否相同即可
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        if (head.next == null) {
            return true;
        }
        ListNode slowP = head;
        ListNode fastP = head;
        while (fastP.next != null && fastP.next.next != null) {
            slowP = slowP.next;
            fastP = fastP.next.next;
        }
        //中间节点应该是slowP 右边一个节点
        fastP = slowP.next;

        // 反转fastP右边的链表
        ListNode p = fastP;
        ListNode q = fastP.next;
        p.next = null;
        ListNode r = new ListNode(0);
        while (q != null) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        // p 就是就是翻转后的后半部分的head 节点
        // 对比过程
        ListNode part1 = head;
        ListNode part2 = p;
        while (part2 != null) {
            if (part1.val != part2.val) {
                return false;
            }
            part1 = part1.next;
            part2 = part2.next;
        }
        return true;
    }

    /**
     * 题目6：环形链表，判断是否是环形链表
     * 思路：快慢指针，如果最后快慢指针指向相同则说明是环形链表
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode pSlow, pFast;
        pFast = pSlow = head;
        while (true) {
            pSlow = pSlow.next;
            pFast = pFast.next.next;
            if (pFast == null || pFast.next == null) {
                return false;
            }
            if (pFast == pSlow || pFast.next == pSlow) {
                return true;
            }
        }
    }

    public ListNode createList(int[] input) {
        // list is: 4 -> 5 -> 1 -> 9
        ListNode head = new ListNode(input[0]);
        head.next = new ListNode(input[1]);
        head.next.next = new ListNode(input[2]);
        head.next.next.next = new ListNode(input[3]);
        return head;
    }

    public void printList(ListNode head, String msg) {
        System.out.print(msg + ": ");
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
    }
}
