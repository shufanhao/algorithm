package offer

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestStack(t *testing.T) {
	assert.Equal(t, 5, evalRPN([]string{"2", "1", "3", "*", "+"}))
}
