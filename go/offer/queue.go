package offer

type MovingAverage struct {
	nums     []int
	capacity int
	sum      int
}

func newMovingAverage(size int) MovingAverage {
	return MovingAverage{
		nums:     make([]int, 0, size),
		capacity: size,
		sum:      0,
	}
}

func (m *MovingAverage) Next(val int) float64 {
	m.nums = append(m.nums, val)
	m.sum += val
	if len(m.nums) > m.capacity {
		m.sum -= m.nums[0]
		m.nums = m.nums[1:]
	}

	return float64(m.sum) / float64(len(m.nums))
}
