package com.haofan.algorithm.offer.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class HashSolution {
    /**
     * 面试题32：变位词组，互为变位词的是一组
     * 输入一组单词，[eat, tea, nat, tan, bat, ate] 这组单词可以分成3组，分别是eat, tea, ate, 和 tan, nat 和bat
     * <p>
     * 思路：
     * 1. 可以将输入的每个单词进行排序，然后放到hashmap中，key是排序后的单词，value是包含的变位词组。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] sortedArray = str.toCharArray();
            Arrays.sort(sortedArray);
            String sortedStr = new String(sortedArray);
            map.putIfAbsent(sortedStr, new LinkedList<>());
            map.get(sortedStr).add(str);
        }
        return new LinkedList<>(map.values());
    }

    /**
     * 面试题4：外星语言是否排序 <a href="https://leetcode.cn/problems/lwyVBB/description/">...</a>
     * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
     * 输出：true
     * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
     * <p>
     * 解法：
     * 1. 通过一个数组记录字母的order, 数组的值为顺序，下标为字母表的每个字母。
     * 2. 再比较words即可
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] orderArray = new int[order.length()];

        for (int i = 0; i < order.length(); i++) {
            orderArray[order.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (!isSorted(words[i], words[i + 1], orderArray)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSorted(String word1, String word2, int[] order) {
        int i = 0;
        for (; i < word1.length() && i < word2.length(); i++) {
            char ch1 = word1.charAt(i);
            char ch2 = word2.charAt(i);

            if (order[ch1 - 'a'] < order[ch2 - 'a']) {
                return true;
            }
            if (order[ch1 - 'a'] > order[ch2 - 'a']) {
                return false;
            }
        }

        return i == word1.length();
    }

    /**
     * 面试题5：最小时间差
     * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示
     * <p>
     * 输入：timePoints = ["00:00","23:59","23:55"]
     * 输出：4
     * <p>
     * 思路：
     * 1. 可以先将timePoints排序，然后再计算任意两个之间差值，取最小即可。那时间复杂度是O(nlogn)
     * 2. 如果想要更好的时间复杂度，因为之前是将时间花费在排序上，那么就优化排序。
     * 一天24小时，一共1440分钟，可以创建一个长度为1440分钟的数组，数组的index代表分钟数，值为true/false代表的是是否有这个time point
     * 最后遍历这个数组，然后看下相邻为true的值的差值是多少，求出最小差值即可。
     */
    public int findMinDifference1(List<String> timePoints) {
        int[] nums = timePoints.stream().mapToInt(s -> {
            String[] ss = s.split(":");
            return Integer.parseInt(ss[0]) * 60 + Integer.parseInt(ss[1]);
        }).sorted().toArray();

        // 这个非常有技巧， 60 * 24, 避免这种情况：00:00 23:59，然后判断出不是想差是1的这种情况。
        int res = nums[0] + 60 * 24 - nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            res = Math.min(res, nums[i] - nums[i - 1]);
        }

        return res;
    }

    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440) {
            return 0;
        }

        boolean[] minuteFlags = new boolean[1440];

        for (String time : timePoints) {
            String t[] = time.split(":");
            int min = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
            if (minuteFlags[min]) {
                return 0;
            }
            minuteFlags[min] = true;
        }

        return helper(minuteFlags);
    }

    private int helper(boolean[] minuteFlags) {
        int minDiff = minuteFlags.length - 1;
        int prev = -1;
        int first = minuteFlags.length - 1;
        int last = -1;

        for (int i = 0; i < minuteFlags.length; i++) {
            if (minuteFlags[i]) {
                if (prev > 0) {
                    minDiff = Math.min(i - prev, minDiff);
                }

                prev = i;
                first = Math.min(i, first);
                last = Math.max(i, last);
            }
        }
        return minDiff;
    }

    /**
     * 题目：128. 最长连续序列
     * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/description/">...</a>
     * 思路：
     * 1. 如果是先排序，然后再左右指针找的话，那么时间复杂度是O(nlogn) > O(n)
     * 2. 利用hash, 如果n-1 不在的话，那么while判断是否n+1 存在。
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : nums) {
            // 如果不包含num - 1
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    /**
     * 面试题30：<a href="https://leetcode.cn/problems/FortPu/">...</a> 设计一个容器，插入，删除和随机访问都是O(1)的容器，方法包括:
     * insert(), remove(), getRandom(等概率返回某一个元素)
     * <p>
     * 解法：
     * 1.插入和删除，如果是o(1)，则一定要用到hash表，但是如果直接用HashMap并且不能实现等概率的返回数组的每个元素，因为要实现等概率就一定是取到一个random(map size)的值。
     * 2. 但是如果把元素的内容放在数组中，则可以实现等概率返回数组的每个元素。
     * 3. 如果只考虑用数组，那么删除元素的时候需要O(N)才可以删除，所以还需要一个HashMap去存数组的元素和位置的对应关系。
     */
    public static class RandomizedSet {
        // key 是要插入的值，value 是在数组中的元素的位置。
        HashMap<Integer, Integer> numToLocation = new HashMap<>();
        ArrayList<Integer> nums = new ArrayList<>();

        public boolean insert(int val) {
            if (numToLocation.containsKey(val)) {
                return false;
            }
            numToLocation.put(val, nums.size());
            nums.add(val);
            return true;
        }

        public boolean remove(int val) {
            // Remove 要注意，如果删除数组中的某个元素，则其他元素需要向前移动以填补空缺，则O(N)
            // 所以删除元素的时候，让数组中最后一个元素和被删除的元素交换位置，然后再删除数组最后一个元素
            if (!numToLocation.containsKey(val)) {
                return false;
            }
            int location = numToLocation.get(val);
            // 拿到数组最后一个元素的值，并将其位置更换成val的位置
            numToLocation.put(nums.get(nums.size() - 1), location);
            // 删除val的位置
            numToLocation.remove(val);
            // 设置新的location的位置是数组最后一个元素
            nums.set(location, nums.get(nums.size() - 1));
            // 删除最后一个元素
            nums.remove(nums.size() - 1);
            return true;
        }

        public int getRandom() {
            Random random = new Random();
            int r = random.nextInt(nums.size());
            return nums.get(r);
        }
    }

    /**
     * 面试题31：LRU(最近最少使用)缓存的实现
     * get(key), 如果缓存中之前包含key，则返回值。
     * put(key, value), 如果缓存中包含键，则返回value，如果不包含，则添加，如果容量满了，则添加新的key之前，删除最近最少使用的key
     * <p>
     * 解法：
     * 1. 需要知道缓存表中最近最少使用的元素，可以把存入的元素放在链表中，每次访问一个元素，则元素移到链表的尾部，位于头部的元素则是最近最少使用的，如果已经达到capacity则需要删除的时候头结点
     * 2. 那如何把元素移动到尾部呢？先把节点从原来的位置删除，再添加到尾部。那如何把节点从原来的位置删除呢？要删除原来的位置，其实就是把它
     * 的前一个节点的next指针指向下一个节点就可以，但是在单向链表中找到前一个节点需要o(N)，所以需要双向链表。
     * 3. 数据结构可以是map 存key和链表节点，双向链表的tail节点存最近刚访问的节点。
     */
    public static class LRUCache {
        private ListNode head;
        private ListNode tail;
        private int capacity;
        private Map<Integer, ListNode> map;

        public LRUCache(int cap) {
            map = new HashMap<>();
            // 初始化head and tail 两个节点
            head = new ListNode(-1, -1);
            tail = new ListNode(-1, -1);
            head.next = tail;
            tail.prev = head;
            capacity = cap;
        }

        public int get(int key) {
            ListNode node = map.get(key);
            if (node != null) {
                // 将节点移动到最尾部
                moveToTail(node, node.value);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                moveToTail(map.get(key), value);
            } else {
                if (map.size() == capacity) {
                    // 删除节点， 删除的是头结点，表示没有被使用过
                    ListNode toBeDeleted = head.next;
                    deleteNode(toBeDeleted);

                    map.remove(toBeDeleted.key);
                }

                ListNode node = new ListNode(key, value);
                insertToTail(node);

                map.put(key, node);
            }
        }

        private void moveToTail(ListNode node, int newValue) {
            // Delete node
            deleteNode(node);
            node.value = newValue;
            insertToTail(node);
        }

        private void deleteNode(ListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void insertToTail(ListNode node) {
            // 有点儿不好理解
            // 因为tail是哨兵接节点，所以insert to tail 实际上是交换tail的前一个节点和node
            tail.prev.next = node;
            node.prev = tail.prev;
            node.next = tail;
            tail.prev = node;
        }
    }


    /**
     * leetcode 383: Ransom Note
     * <a href="https://leetcode.cn/problems/ransom-note">...</a>
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] chars = new int[26];

        for(char c : magazine.toCharArray()) {
            chars[c - 'a'] ++;
        }

        for(char c : ransomNote.toCharArray()) {
            chars[c - 'a'] --;
            // < 0，说明不能的。
            if ((chars[c - 'a']) < 0){
                return false;
            }
        }
       return true;
    }

    /**
     * leetcode 205: Isomorphic Strings
     * <a href="https://leetcode.cn/problems/isomorphic-strings/description">...</a>
     *
     * 一定要双向映射才可以，单向映射不可靠。
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // 检查 s -> t 的映射是否一致
            if (sToT.containsKey(c1) && sToT.get(c1) != c2) return false;
            // 检查 t -> s 的映射是否一致
            if (tToS.containsKey(c2) && tToS.get(c2) != c1) return false;

            // 建立映射关系
            sToT.put(c1, c2);
            tToS.put(c2, c1);
        }

        return true;
    }

    /**
     * Leetcode 290: https://leetcode.cn/problems/word-pattern
     *
     * 和上面的题目类似
     */
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }

        Map<Character, String> patternToWords = new HashMap<>();
        Map<String, Character> wordsToPattern = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (patternToWords.containsKey(c) && !patternToWords.get(c).equals(word)) {
                return false;
            }
            if (wordsToPattern.containsKey(word) && !wordsToPattern.get(word).equals(c)) {
                return false;
            }

            // 建立映射关系
            patternToWords.put(c, word);
            wordsToPattern.put(word, c);
        }

        return true;
    }

    /**
     * 202. Happy Number <a href="https://leetcode.cn/problems/happy-number">...</a>
     *
     * 注意避免循环
     */
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }

        return n == 1;

    }

    private int getNext(int n) {
        // split each element
        int sum = 0;
        while (n != 0) {
            // 取模运算，取出最后一位
            int digit = n % 10;
            n /= 10;
            sum = sum + digit * digit;
        }
        return sum;
    }
}
