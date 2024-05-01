package offer

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"testing"
)

func Test_randomizedSet(t *testing.T) {
	set := Constructor()
	assert.True(t, set.Insert(1))
	assert.True(t, set.Insert(2))
	assert.True(t, set.Insert(3))
	assert.True(t, set.Remove(2))
	fmt.Print(set.GetRandom())
}

func Test_LRUCache(t *testing.T) {
	lru := ConstructorLRUCache(10)
	lru.Put(1, 1)
	lru.Put(2, 2)
	assert.Equal(t, 1, lru.Get(1))
}
