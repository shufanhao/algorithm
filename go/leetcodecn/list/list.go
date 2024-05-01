package list

import _go "github.com/shufanhao/algorithm/go/go"

// -3, 5, -99, delete -99
func deleteNode(head *_go.ListNode, val int) *_go.ListNode {
	if head.Val == val {
		return head.Next
	}

	pre := head
	cur := head.Next
	for cur != nil && cur.Val != val {
		pre = cur
		cur = cur.Next
	}

	// find out this node
	if cur != nil {
		pre.Next = cur.Next
	}
	return head
}

func mergeTwoLists(list1 *_go.ListNode, list2 *_go.ListNode) *_go.ListNode {
	if list1 == nil {
		return list2
	}
	if list2 == nil {
		return list1
	}

	root := &_go.ListNode{Val: 0}
	p := root
	// 1 ->
	for list1 != nil && list2 != nil {
		if list1.Val <= list2.Val {
			p.Next = list1
			list1 = list1.Next
		} else {
			p.Next = list2
			list2 = list2.Next
		}
		p = p.Next
	}

	if list1 != nil {
		p.Next = list1
	}

	if list2 != nil {
		p.Next = list2
	}

	return root.Next
}
