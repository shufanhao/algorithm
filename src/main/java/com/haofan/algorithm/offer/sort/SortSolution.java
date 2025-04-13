package com.haofan.algorithm.offer.sort;

import com.haofan.algorithm.help.ListNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class SortSolution {

    /**
     * 面试题2：计数排序
     * <p>
     * 解法：先统计每个数组中每个整数在数组中出现的次数，然后按照从小到大顺序填入数组中。
     * 如: [2, 3, 2, 3, 2, 1]，1出现了1次，2出现3次，3出现2次，所以依次在数组中填入1个1，,3个2，,2个3。
     * 是不是 放到map中更加简单呢？
     */
    public int[] sortArray(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // find the min and max from array
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] counts = new int[max - min + 1];
        for (int num : nums) {
            counts[num - min]++;
        }

        int i = 0;
        // fill in num
        for (int num = min; num <= max; num++) {
            while (counts[num - min] > 0) {
                nums[i++] = num;
                counts[num - min]--;
            }
        }

        return nums;
    }

    /**
     * 面试题75：数组相对排序(<a href="https://leetcode.cn/problems/relative-sort-array/">link</a>)
     * <p>
     * 两个数组，arr1 和arr2，arr2中的元素各不相同，arr2 中的每个元素都出现在arr1中。
     * <p>
     * 对 arr1中的元素进行排序，使 arr1 中项的相对顺序和arr2中的相对顺序相同。未在arr2中出现过的元素需要按照升序放在arr1的末尾。
     * 假设数组中的元素都是0-1000范围内，这是关键，所以可以用计数排序.
     * <p>
     * 思路：计数排序。
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] counts = new int[1001];
        for (int num : arr1) {
            counts[num]++;
        }

        // 根据arr2的数组的内容，给arr1赋值，因为要根据的是arr1的顺序。
        // arr2中出现过的元素需要按照升序放在arr1的末尾。
        int i = 0;
        for (int num : arr2) {
            while (counts[num] > 0) {
                arr1[i++] = num;
                counts[num]--;
            }
        }

        // 这里是赋值arr1中剩下的元素，也就是counts里不等于0的数字。
        for (int num = 0; num < counts.length; num++) {
            while (counts[num] > 0) {
                arr1[i++] = num;
                counts[num]--;
            }
        }
        return arr1;
    }

    /**
     * 面试题77：排序链表
     * <p>
     * 如果是用快排的话，因为要随机取出一个值，但是随机从链表中取出一个值的复杂度是O(N)
     * 如果用归并排序的话，就不用随机取出。并且不像数组一样要创建一个新的数组，节省了空间复杂度
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode head1 = head;

        // split list and return the head of second part
        ListNode head2 = split(head);
        head1 = sortList(head1);
        head2 = sortList(head2);

        return merge(head1, head2);
    }

    // 快慢指针split
    private ListNode split(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            // slow走一步，fast走两步
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;
        slow.next = null;

        return second;
    }

    // merge两个排序子数组
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }

            cur = cur.next;
        }

        cur.next = head1 == null ? head2 : head1;
        return dummy.next;
    }

    /**
     * 面试题78: 合并排序链表
     *
     * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/description/">...</a>
     * <p>
     * 输入k个排序的链表，请将他们合并成一个排序的链表。
     * <p>
     * 1 -> 4 -> 7
     * 2 -> 5 -> 8
     * 3 -> 6 -> 9
     * 合并之后:
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
     * <p>
     * 思路：可以用最小堆来实现，因为目的是每次从三个排序链表中取出最小值，来构建新的排序后的链表。
     * <p>
     * 还有一个思路是把把所有的数字取出后，排序，然后再构建新的，但是空间复杂度比较高，另外也可以两两 排序两个有序链表。
     * Time: nlogk, 排序链表一共是n个节点，堆的大小是k。
     * Space: O(k)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((n1, n2) -> (n1.val - n2.val));

        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        while (!minHeap.isEmpty()) {
            ListNode least = minHeap.poll();
            cur.next = least;
            cur = least;

            if (least.next != null) {
                minHeap.offer(least.next);
            }
        }

        return dummy.next;
    }

    /**
     * 面试题406: 根据身高重建队列
     * <a href="https://leetcode.cn/problems/queue-reconstruction-by-height/description/">...</a>
     * <p>
     * 视频讲解：<a href="https://www.bilibili.com/video/BV1EA411675Y/?vd_source=a99571209c2d7a75b18bd709abc776ee">...</a>
     * <p>
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * <p>
     * 思路：先按照身高h从高到低排序，然后身高排序好后，再按照k(前面有几个人再插入)
     * 例子，按照身高排序后：
     * [7,0],[7,1],[6,1],[5,0],[5,2],[4,4]
     * 然后再插入顺序,
     * 遍历到[6,1]，因为需要时前面1个人比自己高，所以要插入到1的位置：
     * [7,0]，[6,1]，[7,1],[5,0],[5,2],[4,4]
     * 再遍历到[5,0]，因为前面要有0个人比自己高，所以要掺入到0的位置：
     * [5,0]，[7,0]，[6,1]，[7,1],[5,2],[4,4]
     * ....
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        List<int[]> que = new LinkedList<>();
        for (int[] p : people) {
            que.add(p[1], p);
        }

        return que.toArray(new int[people.length][]);
    }
}
