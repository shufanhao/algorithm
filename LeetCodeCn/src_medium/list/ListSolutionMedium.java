package list;

import java.util.List;

/**
 * 链表问题
 */
public class ListSolutionMedium {

    /**
     * 题目1：两数相加 https://blog.csdn.net/biezhihua/article/details/79437
     * 思路：
     * 1. 链表对应节点相加时增加一个节点的进位，并保存下一个节点的进位；除法得进位，模得结果
     * 2. 两个链表长度不一致时，要处理长链表剩余的高位和进位进行计算的值
     * 3. 如果最高位计算时，还产生进位，需要增加一个节点
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp = null;
        ListNode result = null;

        int carry = 0 ; // 进位数
        while (l1 != null || l2 != null || carry != 0) {
            int sum = (l1 == null ? 0 : l1.val) +
                    (l2 == null ? 0 : l2.val) + carry;
            carry = sum / 10;

            ListNode node = new ListNode(sum%10);
            if (temp == null) {
                temp = node;
                result = temp;// 记录第一个元素
            } else {
                temp.next = node;
                temp = temp.next;
            }

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return result;
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
    public static void main(String args[]) {
        ListSolutionMedium list = new ListSolutionMedium();

        ListSolutionMedium c = new ListSolutionMedium();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = list.addTwoNumbers(l1, l2);
        System.out.print("题目1：");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }

        ListSolution common = new ListSolution();
        int[] input = {1,4,5,9};
        common.printList(list.oddEvenList(common.createList(input)), "题目2：");
    }
}
