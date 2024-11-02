package com.haofan.algorithm.offer.practice;

import com.haofan.algorithm.help.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.haofan.algorithm.help.ListNode.arrayToList;
import static com.haofan.algorithm.help.ListNode.listToArray;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PracticeTest {

    private Practice p = new Practice();

    @Test
    void minPathSum() {
        System.out.println(p.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }

    @Test
    void rob() {
        System.out.println(p.rob(new int[]{1,2,3,1}));
    }

    @Test
    void rob3() {
        assertEquals(16, p.maxProduct(new String[] {"abcw","baz","foo","bar","fxyz","abcdef"}));
    }

    @Test
    void intersect() {
        // 题目6：
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println("题目6： 两个数组的交集： " + Arrays.toString(p.intersect(nums1, nums2)));
    }

    @Test
    void plusOne() throws InterruptedException {
        int[] plusOne = {1, 2, 3};
        System.out.println("题目7： 加一: " + Arrays.toString(p.plusOne(plusOne)));

        int[] temp = {4, 9, 9};
        // System.out.println("题目7： 加一 " + Arrays.toString(p.plusOne(temp)));
    }

    @Test
    void moveZeroes() {
        int[] move = {0, 1, 0, 3, 12};
        System.out.println("题目8： 移动0 " + Arrays.toString(p.moveZeroes(move)));
    }

    @Test
    void testRotate() {
        int[][] rotateArr = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        p.rotate(rotateArr);
        for (int i = 0; i < rotateArr.length; i++) {
            System.out.println("题目11： 旋转图像 " + Arrays.toString(rotateArr[i]));
        }
    }

    @Test
    void minSubArrayLen() {
        int[] arr = {2, 3, 1, 2, 4, 3};
        assertEquals(2, p.minSubArrayLen(7, arr));
    }

    @Test
    void findMaxAverage() {
        int[] arr = {1,12,-5,-6,50,3};
        assertEquals(12.75, p.findMaxAverage(arr, 4));
    }

    @Test
    void groupAnagrams() {
        String[] strsIssue3 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> listsIssue3 = p.groupAnagrams(strsIssue3);
        System.out.println(Arrays.toString(listsIssue3.toArray()));
    }

    @Test
    public void longestValidParentheses() {
        Assertions.assertEquals(2, p.longestValidParentheses("()(()"));
    }

    @Test
    void numSubarrayProductLessThanK() {
        int[] nums = {10, 5, 2, 6};
        assertEquals(8, p.numSubarrayProductLessThanK(nums, 100));
    }

    @Test
    void strStr() {
        Assertions.assertEquals(2, p.strStr("hello", "ll"));
    }

    @Test
    void longestCommonPrefix() {
        Assertions.assertEquals("fl", p.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }

    @Test
    void delete() {
        Assertions.assertArrayEquals(new int[]{4, 5, 6}, listToArray(p.delete(arrayToList(new int[]{4, 5, 6, 8}),8)));
    }

    @Test
    void removeNthFromEnd() {
        Assertions.assertArrayEquals(new int[]{4, 5, 6, 7}, listToArray(p.removeNthFromEnd(arrayToList(new int[]{4, 5, 6, 8, 7}), 2)));
        // p.removeNthFromEnd(arrayToList(new int[]{1}), 1);
    }

    @Test
    void detectCycle() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = head.next.next;

        Assertions.assertEquals(3, p.detectCycle(head).val);
    }

    @Test
    void addTwoNumbers() {
        ListNode head = p.addTwoNumbers(arrayToList(new int[]{9, 8, 4}), arrayToList(new int[]{1, 8}));
        Assertions.assertArrayEquals(new int[]{1, 0, 0, 2}, listToArray(head));
    }

    @Test
    void reorderList() {
        int[] res = listToArray(p.reorderList(arrayToList(new int[]{1, 2, 3, 4, 5, 6})));
        Assertions.assertArrayEquals(new int[] {1, 6, 2, 5, 3, 4}, res);
    }

    @Test
    void mergeTwoLists() {
        ListNode res = p.mergeTwoLists(ListNode.arrayToList(new int[]{1, 2, 4}), ListNode.arrayToList(new int[]{1, 3, 4}));
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 4}, ListNode.listToArray(res));
    }

    @Test
    void deleteNode() {
        ListNode head = ListNode.arrayToList(new int[]{-3,5,-99});
        p.deleteNode(head, 5);
        assertArrayEquals(new int[]{-3, -99}, ListNode.listToArray(head));
    }

    @Test
    void oddEvenList() {
        ListNode head = ListNode.arrayToList(new int[]{1, 2, 3, 4, 5});
        ListNode res = p.oddEvenList(head);
    }
}