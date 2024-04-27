package offer

import (
	_go "github.com/shufanhao/algorithm/go/go"
	"github.com/stretchr/testify/assert"
	"testing"
)

// 测试removeNthFromEnd函数
func Test_removeNthFromEnd(t *testing.T) {
	// 创建测试链表: 1 -> 2 -> 3 -> 4 -> 5
	list := _go.CreateLinkedList([]int{1, 2, 3, 4, 5})

	// 测试删除倒数第1个节点
	expectedList := _go.CreateLinkedList([]int{1, 2, 3, 4})
	newList := removeNthFromEnd(list, 1)
	assert.Equal(t, expectedList, newList, "removeNthFromEnd(list, 1) failed")
}

func Test_detectCycle(t *testing.T) {
	head := _go.ListNode{Val: 1}
	head.Next = &_go.ListNode{Val: 2}
	head.Next.Next = &_go.ListNode{Val: 3}
	head.Next.Next.Next = &_go.ListNode{Val: 4}
	head.Next.Next.Next.Next = &_go.ListNode{Val: 5}
	head.Next.Next.Next.Next.Next = head.Next.Next

	cycle := detectCycle(&head)
	assert.Equal(t, 3, cycle.Val, "detectCycle(&head) failed")
}

func Test_getIntersectionNode(t *testing.T) {
	head1 := &_go.ListNode{Val: 1}
	head1.Next = &_go.ListNode{Val: 2}
	head1.Next.Next = &_go.ListNode{Val: 3}

	// 创建intersectionNode节点
	intersectionNode := &_go.ListNode{Val: 4}

	// 将intersectionNode作为head1链表的最后一个节点
	head1.Next.Next.Next = intersectionNode
	intersectionNode.Next = &_go.ListNode{Val: 5}

	// 创建head2链表
	head2 := &_go.ListNode{Val: 7}
	head2.Next = &_go.ListNode{Val: 8}

	// 将intersectionNode作为head2链表的最后一个节点
	head2.Next.Next = intersectionNode

	interSection := getIntersectionNode(head1, head2)
	assert.Equal(t, 4, interSection.Val)
}

func Test_reverseList(t *testing.T) {
	node1 := _go.CreateLinkedList([]int{1, 2, 3})
	node2 := reverseList(node1)

	assert.Equal(t, _go.ListToSlice(node2), []int{3, 2, 1}, "reverseList failed")
}

func Test_addReversed(t *testing.T) {
	l1 := _go.CreateLinkedList([]int{9, 8, 4})
	l2 := _go.CreateLinkedList([]int{1, 8})

	res := addTwoNumbers(l1, l2)
	assert.Equal(t, _go.ListToSlice(res), []int{1, 0, 0, 2})
}

func Test_reorderList(t *testing.T) {
	resList := reorderList(_go.CreateLinkedList([]int{1, 2, 3, 4, 5, 6}))
	assert.Equal(t, []int{1, 6, 2, 5, 3, 4}, _go.ListToSlice(resList))
}
