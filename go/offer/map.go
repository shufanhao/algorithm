package offer

import "math/rand"

// RandomizedSet 面试题1：设计一个容器，插入，删除和随机访问都是O(1)的容器，
type RandomizedSet struct {
	numToLocation map[int]int
	nums          []int
}

func Constructor() RandomizedSet {
	return RandomizedSet{
		numToLocation: make(map[int]int),
		nums:          []int{},
	}
}

func (rs *RandomizedSet) Insert(val int) bool {
	if _, ok := rs.numToLocation[val]; ok {
		return false
	}

	rs.numToLocation[val] = len(rs.nums)
	rs.nums = append(rs.nums, val)
	return true
}

func (rs RandomizedSet) Remove(val int) bool {
	if _, exists := rs.numToLocation[val]; !exists {
		return false
	}

	location := rs.numToLocation[val]
	lastIndex := len(rs.nums) - 1
	lastVal := rs.nums[lastIndex]

	rs.nums[location] = lastVal
	rs.numToLocation[lastVal] = location
	//  delete last element
	rs.nums = rs.nums[:lastIndex]
	delete(rs.numToLocation, val)
	return true
}

func (rs *RandomizedSet) GetRandom() int {
	index := rand.Intn(len(rs.nums))
	return rs.nums[index]
}

type ListNode struct {
	// key 和 val 都要有
	key  int
	val  int
	next *ListNode
	prev *ListNode
}

type LRUCache struct {
	head  *ListNode
	tail  *ListNode
	cap   int
	cache map[int]*ListNode
}

func ConstructorLRUCache(capacity int) LRUCache {
	lru := LRUCache{
		head:  &ListNode{key: -1, val: -1},
		tail:  &ListNode{key: -1, val: -1},
		cap:   capacity,
		cache: make(map[int]*ListNode),
	}

	lru.head.next = lru.tail
	lru.tail.prev = lru.head
	return lru
}

func (lru *LRUCache) Get(key int) int {
	if node, ok := lru.cache[key]; ok {
		// move lru node to end of list.
		lru.moveToTail(node, node.val)
		return node.val
	}
	return -1
}

func (lru *LRUCache) Put(key int, value int) {
	if node, ok := lru.cache[key]; ok {
		lru.moveToTail(node, node.val)
	} else {
		if len(lru.cache) == lru.cap {
			// delete first node
			toBeDelete := lru.head.next
			lru.deleteNode(toBeDelete)
			delete(lru.cache, toBeDelete.key)
		}

		newNode := &ListNode{
			key: key,
			val: value,
		}
		lru.insertToTail(newNode)
		lru.cache[key] = newNode
	}
}

func (lru *LRUCache) moveToTail(node *ListNode, newValue int) {
	lru.deleteNode(node)
	node.val = newValue
	lru.insertToTail(node)
}

func (lru *LRUCache) deleteNode(node *ListNode) {
	node.prev.next = node.next
	node.next.prev = node.prev
}

func (lru *LRUCache) insertToTail(node *ListNode) {
	// swap node and tail
	lru.tail.prev.next = node
	node.prev = lru.tail.prev
	node.next = lru.tail
	lru.tail = node
}
