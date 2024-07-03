package offer

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestMovingAverage_Next(t *testing.T) {
	ma := newMovingAverage(3)
	assert.Equal(t, float64(1), ma.Next(1))
	assert.Equal(t, 5.5, ma.Next(10))
}
