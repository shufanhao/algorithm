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
