package easy

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func Test_countAndSay(t *testing.T) {
	ast := assert.New(t)
	ast.Equal("1211", countAndSay(4))
}

func Test_longestCommonPrefix(t *testing.T) {
	ast := assert.New(t)
	ast.Equal("fl", longestCommonPrefix([]string{"flower", "flow", "flight"}))
}
