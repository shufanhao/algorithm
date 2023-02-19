package main

import "fmt"

// Bucket sort
func topKFrequent(nums []int, k int) []int {
	var int2FreqMap = make(map[int]int)
	for _, v := range nums {
		int2FreqMap[v] = int2FreqMap[v] + 1
	}
	// use bucket sort, dynamic create a array
	bucket := make([][]int, len(nums) + 1)
	for num, freq := range int2FreqMap {
		if bucket[freq] == nil {
			bucket[freq] = [] int{num}
		} else {
			bucket[freq] = append(bucket[freq], num)
		}
	}
	var result = make([]int, 0)
	for i := len(nums); i>=0 && len(result) < k; i-- {
		if bucket[i] != nil {
			for _, v := range bucket[i] {
				result = append(result, v)
			}
		}
	}
	return result
}

func main() {
	input := [] int{1,1,1,2,2,3,4}
	//input := [] int{1}
	result := topKFrequent(input, 2)
	fmt.Println("result: ", result)
}