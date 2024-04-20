package easy

import (
	"sort"
	"strconv"
)

/**
 * 题目8：数数并说 <a href="https://leetcode.cn/problems/count-and-say/description/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china">...</a>
 * 并说：超过1个相同的数字连在一起时，并说，也就是”几个几“
 * 不并说：前后数字不同时，不并说，需要单说，也就是”一个几“
 * 从1开始一直到n 循环
 */
func countAndSay(n int) string {
	if n < 1 {
		return "-1"
	}

	result := "1"
	for i := 1; i < n; i++ {
		index := 0
		tempResult := ""
		// 假设结果 result = 1211, 那么预期输出是1个1，,1个2，,2个1
		for index < len(result) {
			val := string(result[index])
			count := 0
			for index < len(result) && string(result[index]) == val {
				index++
				count++
			}

			tempResult += strconv.Itoa(count)
			tempResult += val
		}
		result = tempResult
	}
	return result
}

/***
 * 题目9：最长公共前缀 <a href="https://leetcode.cn/problems/longest-common-prefix/description/">...</a>
 * 思路：先排序，然后用字符串数组的第一个和最后一个比较
 */
func longestCommonPrefix(strs []string) string {
	sort.Strings(strs)

	prefix := ""
	first := strs[0]
	last := strs[len(strs)-1]

	for i := 0; i < len(first) && i < len(last); i++ {
		if first[i] == last[i] {
			prefix += string(first[i])
		} else {
			return prefix
		}
	}

	return prefix
}
