package offer

import (
	"fmt"
	"strconv"
	"sync"
)

// Item go doesn't provide Stack API
type Item interface{}

type Stack struct {
	items []Item
	lock  sync.RWMutex
}

func NewStack() *Stack {
	s := &Stack{
		items: []Item{},
	}
	return s
}

func (s *Stack) Print() {
	fmt.Println(s.items)
}

func (s *Stack) Push(t Item) {
	s.lock.Lock()
	defer s.lock.Unlock()
	s.items = append(s.items, t)
}

func (s *Stack) Pop() Item {
	s.lock.Lock()
	defer s.lock.Unlock()
	if len(s.items) == 0 {
		return nil
	}

	item := s.items[len(s.items)-1]
	s.items = s.items[:len(s.items)-1]
	return item
}

// coding questions.
/**
 * 面试题36：后缀表达式
 * 输入一个后缀表达式，输出结果，假设输入[2, 1, 3, *, +]，计算应该是(1*3) + 2 = 5
 * <p>
 * 解法：只讲数字放入stack中，然后遇到操作符，出栈，计算结果。
 */
func evalRPN(tokens []string) int {
	stack := NewStack()
	for _, token := range tokens {
		switch token {
		case "+", "-", "*", "/":
			num1 := stack.Pop()
			num2 := stack.Pop()
			stack.Push(calculate(num2.(int), num1.(int), token))
		default:
			// switch to int
			num, _ := strconv.Atoi(token)
			stack.Push(num)
		}
	}
	return stack.Pop().(int)
}

func calculate(num1, num2 int, operator string) int {
	switch operator {
	case "+":
		return num1 + num2
	case "-":
		return num1 - num2
	case "*":
		return num1 * num2
	case "/":
		return num1 / num2
	}
	return 0
}
