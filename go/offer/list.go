package offer

import (
	_go "github.com/shufanhao/algorithm/go/go"
	"math"
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

/**
 * 面试题22：链表中环的入口节点
 * <p>
 * 如果链表中有环，那么应该如何找出环的入口节点。
 * ----------------
 * !    6再指向3   !
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6
 * <p>
 * 解法：
 * 1. 首先要判断是否有环。快慢指针，快指针走2步，慢指针走一步，最终如果快慢指针相遇，则说明有环。
 * 2. 如果找到环的入口节点。两个指针，指向头结点，如果链表中环有n个节点，第一个指针先向前移动n步，两个指针相同的速度向前移动，
 * 当两个指针相遇时，正好是环的入口节点。
 * 3. 如何取到环的数目，找到任意一个环中的节点后，绕环一圈就是环的数目。
 */
func detectCycle(head *_go.ListNode) *_go.ListNode {
	nodeInLoop := getNodeInLoop(head)
	if nodeInLoop == nil {
		return nil
	}

	// check the length of loop
	len := 1
	for n := nodeInLoop; n.Next != nodeInLoop; n = n.Next {
		len++
	}

	// fast start move len
	fast := head
	for i := 0; i < len; i++ {
		fast = fast.Next
	}
	// fast and slow start moving util meet
	slow := head
	for slow != fast {
		slow = slow.Next
		fast = fast.Next
	}
	return slow
}

func getNodeInLoop(head *_go.ListNode) *_go.ListNode {
	if head == nil || head.Next == nil {
		return nil
	}

	slow := head.Next
	fast := slow.Next

	//快慢指针去找到环中的某个node
	for slow != nil && fast != nil {
		if fast == slow {
			return slow
		}
		slow = slow.Next
		fast = fast.Next
		if fast != nil {
			fast = fast.Next
		}
	}
	return nil
}

func getIntersectionNode(headA, headB *_go.ListNode) *_go.ListNode {
	countA := _go.CountList(headA)
	countB := _go.CountList(headB)

	detla := math.Abs(float64(countA - countB))

	var longer, shorter *_go.ListNode
	if countA > countB {
		longer = headA
		shorter = headB
	} else {
		longer = headB
		shorter = headA
	}
	for i := 0; i < int(detla); i++ {
		longer = longer.Next
	}
	for longer != shorter {
		longer = longer.Next
		shorter = shorter.Next
	}
	return longer
}

/*
*
  - 1 -> 2 -> 3 reverse ...
  - 1 <- 2 <- 3
    prev cur next
*/
func reverseList(head *_go.ListNode) *_go.ListNode {
	var prev *_go.ListNode
	cur := head

	for cur != nil {
		next := cur.Next
		cur.Next = prev
		prev = cur
		cur = next
	}

	return prev
}

// addTwoNumbers, 先反转，再从头开始相加，最后再反转
// l1: 9 -> 8 -> 4,  反转后: 4 -> 8 -> 9
// l2: 1 -> 8        反转后：8 -> 1，  相加后 2 -> 0 -> 0 -> 1
// 反转后： 1 -> 0 -> 0 -> 2
func addTwoNumbers(l1 *_go.ListNode, l2 *_go.ListNode) *_go.ListNode {
	l1 = reverseList(l1)
	l2 = reverseList(l2)
	node := addReversed(l1, l2)
	return reverseList(node)
}

func addReversed(l1 *_go.ListNode, l2 *_go.ListNode) *_go.ListNode {
	dummy := &_go.ListNode{Val: 0}
	sumNode := dummy
	carry := 0

	for l1 != nil || l2 != nil {
		var val1, val2 int
		if l1 != nil {
			val1 = l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			val2 = l2.Val
			l2 = l2.Next
		}
		sum := val1 + val2 + carry
		carry = sum / 10

		newNode := &_go.ListNode{Val: sum % 10}
		sumNode.Next = newNode
		sumNode = sumNode.Next
	}

	if carry != 0 {
		sumNode.Next = &_go.ListNode{Val: carry}
	}

	return dummy.Next
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
func reorderList(head *_go.ListNode) *_go.ListNode {
	// 先通过快慢指针，找出链表的后半段
	dummy := &_go.ListNode{Val: 0}
	dummy.Next = head
	fast := dummy
	slow := dummy
	for fast != nil && fast.Next != nil {
		slow = slow.Next
		// fast go 2 step
		fast = fast.Next.Next
	}

	temp := slow.Next
	slow.Next = nil

	link(head, reverseList(temp), dummy)
	return head
}

// node1: 1 2 3 4 5 6， node2： 6 5 4
func link(node1 *_go.ListNode, node2 *_go.ListNode, start *_go.ListNode) {
	prev := start
	for node1 != nil && node2 != nil {
		// 保存node1的下一个节点
		temp := node1.Next

		// 将node1链接到prev后面
		prev.Next = node1
		// 将node2链接到node1后面
		node1.Next = node2

		// 更新prev为node2，以便下一次循环时链接node1的下一个节点
		prev = node2

		node1 = temp       // 移动node1到它的下一个节点
		node2 = node2.Next // 移动node2到它的下一个节点
	}

	if node1 != nil {
		prev.Next = node1
	}
}

/**
 * 判断是否是回文链表, 1, 2, 3, 3, 2, 1 假设是
 * [1,0,1]
 */
func isPalindrome(head *_go.ListNode) bool {
	if head == nil || head.Next == nil {
		return true
	}

	slow := head      // slow: 1
	fast := head.Next // fast 0
	for fast.Next != nil && fast.Next.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
	}

	secondHalf := slow.Next
	if fast.Next != nil {
		secondHalf = slow.Next.Next
	}

	slow.Next = nil

	return _go.ListEquals(secondHalf, reverseList(head))
}
