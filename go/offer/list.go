package offer

import (
	_go "github.com/shufanhao/algorithm/go/go"
)

/**
 * 面试题21：删除链表的倒数第k个节点
 * <p>
 * 解法：
 * 删除倒数第k个节点，主要要找到第k+1个节点即可。
 * left, right 指针，right先走，走k步，从k+1开始，left也走，当right到尾部时，left就是第k+1个节点
 */
func removeNthFromEnd(head *_go.ListNode, n int) *_go.ListNode {
	dump := _go.ListNode{
		Val:  0,
		Next: head,
	}

	left := &dump
	right := &dump
	for i := 0; i < n; i++ {
		if right.Next == nil {
			return head
		}
		right = right.Next
	}

	for right.Next != nil {
		right = right.Next
		left = left.Next
	}

	left.Next = left.Next.Next

	return dump.Next
}
