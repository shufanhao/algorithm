package offer

func twoSum1(nums []int, target int) []int {
	// m 负责保存map[整数]整数的序列号
	m := make(map[int]int, len(nums))
	// 通过 for 循环，获取b的序列号
	for i, b := range nums {
		// 通过查询map，获取a = target - b的序列号
		if j, ok := m[target-b]; ok {
			// ok 为 true
			// 说明在i之前，存在nums[j] == a
			return []int{j, i}
			// 注意，顺序是j，i，因为j<i
		}
		// 把i和i的值，存入map
		m[nums[i]] = i
	}
	return nil
}

func twoSum2(nums []int, target int) []int {
	left := 0
	right := len(nums) - 1
	for (left < right) && ((nums[left] + nums[right]) != target) {
		if (nums[left] + nums[right]) < 10 {
			left++
		} else {
			right++
		}
	}
	if (left != right) && (nums[left]+nums[right] == target) {
		return []int{left, right}
	} else {
		return nil
	}
}
