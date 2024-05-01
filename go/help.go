package _go

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

func CreateLinkedList(values []int) *ListNode {
	dummy := &ListNode{0, nil}
	current := dummy
	for _, value := range values {
		current.Next = &ListNode{value, nil}
		current = current.Next
	}
	return dummy.Next
}

func ListToSlice(head *ListNode) []int {
	var result []int
	for head != nil {
		result = append(result, head.Val)
		head = head.Next
	}
	return result
}

func PrintLinkedList(head *ListNode) {
	current := head
	for current != nil {
		fmt.Printf("%d ", current.Val)
		current = current.Next
	}
	fmt.Println()
}

func CountList(head *ListNode) int {
	count := 0
	for head != nil {
		head = head.Next
		count++
	}
	return count
}

func ListEquals(a, b *ListNode) bool {
	for a != nil && b != nil {
		if a.Val != b.Val {
			return false
		}
		a = a.Next
		b = b.Next
	}
	return a == nil && b == nil
}
