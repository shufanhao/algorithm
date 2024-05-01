package list

import (
	_go "github.com/shufanhao/algorithm/go/go"
	"github.com/stretchr/testify/assert"
	"testing"
)

// -3, 5, -99, delete -99
func Test_deleteNode(t *testing.T) {
	head := deleteNode(_go.CreateLinkedList([]int{-3, 5, -99}), -99)

	assert.Equal(t, []int{-3, 5}, _go.ListToSlice(head), "test deleteNode failed")
}

func Test_mergeTwoLists(t *testing.T) {
	res := mergeTwoLists(_go.CreateLinkedList([]int{1, 2, 4}), _go.CreateLinkedList([]int{1, 3, 4}))

	assert.Equal(t, []int{1, 1, 2, 3, 4, 4}, _go.ListToSlice(res), "mergeTwoLists failed")

	res1 := mergeTwoLists(_go.CreateLinkedList([]int{}), _go.CreateLinkedList([]int{0}))

	assert.Equal(t, []int{0}, _go.ListToSlice(res1), "mergeTwoLists failed")
}
