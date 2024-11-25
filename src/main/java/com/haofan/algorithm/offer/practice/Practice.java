package com.haofan.algorithm.offer.practice;

import com.haofan.algorithm.help.ListNode;
import com.haofan.algorithm.help.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import static com.haofan.algorithm.help.Common.swap;


public class Practice {

    int sum = 0;
    int pathNumber;
    int ans = 0;

    private static int getInt(char c) {
        return c == '1' ? 1 : 0;
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // 初始化：
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int column = matrix[0].length;
        int maxSide = 0;
        int[][] dp = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]);
                    }
                }
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }
        return maxSide * maxSide;
    }

    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();

        int max = Math.max(a.length(), b.length());
        int carry = 0;
        for (int i = 0; i < max; i++) {
            if (i < a.length()) {
                carry += getInt(a.charAt(a.length() - i - 1));
            }
            if (i < b.length()) {
                carry += getInt(b.charAt(b.length() - i - 1));
            }
            result.append(carry % 2);
            carry /= 2;
        }
        if (carry > 0) result.append('1');
        return result.reverse().toString();
    }

    public int maxProduct(String[] words) {
        boolean[][] flags = new boolean[words.length][26];

        for (int i = 0; i < words.length; i++) {
            for (Character c : words[i].toCharArray()) {
                flags[i][c - 'a'] = true;
            }
        }

        int max = 0;
        // 遍历找到这样的两个单词
        for (int i = 0; i < words.length; i++) {
            for (int j = 1; j < words.length; j++) {
                int k = 0;
                for (; k < 26; k++) {
                    if (flags[i][k] && flags[j][k]) {
                        break;
                    }
                }
                if (k == 26) {
                    int prod = words[i].length() * words[j].length();
                    max = Math.max(prod, max);
                }
            }
        }
        return max;
    }

    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, i, start++);
            } else if (nums[i] == 2) {
                swap(nums, i--, end--);
            }
        }
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] ans = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            ans[k] = list.get(k);
        }

        return ans;
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }

    public int[] moveZeroes(int[] nums) {
        int post = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[post++] = nums[i];
            }
        }

        for (int j = post; j < nums.length; j++) {
            nums[j] = 0;
        }

        return nums;
    }

    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // 对调列元素
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - j - 1];
                matrix[i][len - j - 1] = temp;
            }
        }
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int minLen = 0;
        int sum = 0;
        while (left < nums.length) {
            if (right < nums.length && sum < target) {
                sum += nums[right++];
            } else {
                sum -= nums[left++];
            }

            if (sum >= target) {
                minLen = Math.min(minLen, right - left);
            }
        }

        if (minLen == nums.length + 1) {
            return 0;
        }

        return minLen;
    }

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return 1.0 * maxSum / k;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int p = 0;
        int q = nums.length - 1;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            p = i + 1;
            while (p < q) {
                if (nums[p] + nums[q] + nums[i] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[p]);
                    list.add(nums[q]);
                    ans.add(list);
                    p++;
                    q--;
                    // avoid duplicate
                    while ((p < q) && (nums[p - 1] == nums[p])) {
                        p++;
                    }
                } else if (nums[p] + nums[q] + nums[i] < 0) {
                    p++;
                    while ((p < q) && (nums[p - 1] == nums[p])) {
                        p++;
                    }
                } else {
                    // > 0
                    q--;
                }
            }
            // 这个地方要注意，为了防止重复，一定要加
            while ((i + 1) < nums.length && (nums[i] == nums[i + 1])) {
                i++;
            }
        }
        return ans;
    }

    public void setZeroes(int[][] matrix) {
        int[] rowsZero = new int[matrix.length];
        int[] columnZero = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowsZero[0] = 1;
                    columnZero[j] = 1;
                }
            }
        }

        // 遍历每一行和每一个列
        for (int i = 0; i < rowsZero.length; i++) {
            if (rowsZero[i] == 1) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < columnZero.length; i++) {
            if (columnZero[i] == 1) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            String tempStr = String.valueOf(temp);
            if (map.containsKey(tempStr)) {
                map.get(tempStr).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(tempStr, list);
            }
        }

        return new ArrayList<>(map.values());
    }

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            int gap = Math.min(height[l], height[r]);
            max = Math.max(max, gap * (r - l));
            if (height[l] == gap) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 1) {
            return 0;
        }

        int[] temp = new int[s.length()];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    int j = stack.pop();
                    if (s.charAt(j) == '(') {
                        temp[i] = 1;
                        temp[j] = 1; // 匹配成功
                    }
                }
            }
        }

        int cur = 0;
        int maxLength = 0;
        for (int num : temp) {
            if (num == 1) {
                cur++;
            } else {
                maxLength = Math.max(cur, maxLength);
                // 关键点。
                cur = 0;
            }
        }

        return Math.max(cur, maxLength);
    }

    public int minSunArrayLen(int k, int[] nums) {
        int i = 0;
        int sum = 0;
        int min = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (i <= j && sum >= k) {
                min = Math.min(min, j - i + 1);
                sum -= nums[i++];
            }
        }
        return min;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int i = 0;
        int count = 0;
        int product = 1;
        for (int j = i; j < nums.length; j++) {
            product *= nums[j];
            while (i <= j && product >= k) {
                product /= nums[i++];
            }
            count += j - i + 1;
        }
        return count;
    }

    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        right[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        for (int i = 0; i < haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }

        return -1;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs);
        // 比较第一个和最后一个即可。
        int m = strs[0].length();
        int n = strs[strs.length - 1].length();
        int len = Math.min(m, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
                sb.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public ListNode delete(ListNode head, int value) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        while (node.next != null) {
            if (node.next.val == value) {
                node.next = node.next.next;
                break;
            }
            node = node.next;
        }
        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode nodeRight = dummy;
        ListNode nodeLeft = dummy;

        for (int i = 0; i < k + 1; i++) { // 加1是因为我们要让 nodeRight 提前一步，用于判断结束
            nodeRight = nodeRight.next;
        }

        while (nodeRight != null) {
            nodeRight = nodeRight.next;
            nodeLeft = nodeLeft.next;
        }

        // nodeLeft 就是要被删除的节点
        nodeLeft.next = nodeLeft.next.next;

        return dummy.next;
    }

    public ListNode detectCycle(ListNode head) {
        // 判断是否有环
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        ListNode inside = null;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                inside = fast;
                break;
            }
        }
        if (inside == null) {
            return null;
        }

        // 找到在环形链表中的节点后，然后再看一下长度是多少。
        int count = 1;
        for (ListNode temp = inside; temp.next != inside; temp = temp.next) {
            count++;
        }

        ListNode fastNode = dummy.next;
        ListNode slowNode = dummy.next;
        // 先走n步
        for (int i = 0; i < count; i++) {
            fastNode = fastNode.next;
        }

        while (fastNode != slowNode) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }

        return fastNode;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        // current node
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        head1 = reverseList(head1);
        head2 = reverseList(head2);

        ListNode sum = addReversed(head1, head2);
        return reverseList(sum);
    }

    private ListNode addReversed(ListNode head1, ListNode head2) {
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode sumNode = dummy;

        while (head1 != null || head2 != null) {
            int sum = (head1 == null ? 0 : head1.val) + (head2 == null ? 0 : head2.val) + carry;
            carry = sum / 10;
            sumNode.next = new ListNode(sum % 10);
            sumNode = sumNode.next;

            head1 = head1 == null ? null : head1.next;
            head2 = head2 == null ? null : head2.next;
        }

        if (carry > 0) {
            sumNode.next = new ListNode(carry);
        }

        return dummy.next;
    }

    public ListNode reorderList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp = slow;
        slow.next = null;

        link(head, reverseList(slow), dummy);
        return head;
    }

    private void link(ListNode node1, ListNode node2, ListNode head) {
        ListNode prev = head;
        while (node1 != null && node2 != null) {
            ListNode temp = node1.next;
            prev.next = node1;
            node1.next = node2;
            prev = node2;

            node2 = node2.next;
            node1 = temp;
        }

        if (node1 != null) {
            prev.next = node1;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        if (list1 != null) {
            node.next = list1;
        }
        if (list2 != null) {
            node.next = list2;
        }

        return dummy.next;
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null || head.val == val) {
            return null;
        }

        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null && cur.val != val) {
            prev = cur;
            cur = cur.next;
        }

        if (cur != null) {
            prev.next = cur.next;
        }
        return head;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode odd = head;
        ListNode even = odd.next;

        ListNode evenHead = even;
        while (odd.next != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;

        return head;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int headALen = 1;
        ListNode tempHeadA = headA;
        while (tempHeadA.next != null) {
            headALen++;
            tempHeadA = tempHeadA.next;
        }

        int headBLen = 1;
        ListNode tempHeadB = headB;
        while (tempHeadB.next != null) {
            headBLen++;
            tempHeadB = tempHeadB.next;
        }

        // 移动长的。
        while (headALen > headBLen && headA != null) {
            headA = headA.next;
            headALen--;
        }

        while (headBLen > headALen && headB != null) {
            headB = headB.next;
            headBLen--;
        }

        while (headA != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints, new Comparator<String>() {
            @Override
            public int compare(String t1, String t2) {
                int hour1 = Integer.parseInt(t1.substring(0, 2));
                int minute1 = Integer.parseInt(t1.substring(3, 5));
                int hour2 = Integer.parseInt(t2.substring(0, 2));
                int minute2 = Integer.parseInt(t2.substring(3, 5));

                // 将时间转换为总分钟数进行比较
                int totalMinutes1 = hour1 * 60 + minute1;
                int totalMinutes2 = hour2 * 60 + minute2;

                return Integer.compare(totalMinutes1, totalMinutes2);
            }
        });

        int minMinutes = Integer.MAX_VALUE;
        for (int i = 0; i < timePoints.size() - 1; i++) {
            int hour1 = Integer.parseInt(timePoints.get(i).substring(0, 2));
            int minute1 = Integer.parseInt(timePoints.get(i).substring(3, 5));
            int hour2 = Integer.parseInt(timePoints.get(i + 1).substring(0, 2));
            int minute2 = Integer.parseInt(timePoints.get(i + 1).substring(3, 5));

            // 将时间转换为总分钟数进行比较
            int delta = Math.abs((hour1 * 60 + minute1) - (hour2 * 60 + minute2));
            minMinutes = Math.min(minMinutes, delta);
        }

        return minMinutes;
    }

    public int[] findRightSmall(int[] array) {
        int[] res = new int[array.length];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty() && array[stack.peek()] > array[i]) {
                int index = stack.pop();
                res[index] = i;
            }
            stack.push(i);
        }

        return res;
    }

    public boolean isValid(String s) {
        if (s == null || s.length() % 2 == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (Set.of('(', '{', '[').contains(c)) {
                stack.push(c);
            } else {
                Character top = stack.pop();
                if (top == '(') {
                    if (c != ')') {
                        return false;
                    }
                } else if (top == '{') {
                    if (c != '}') {
                        return false;
                    }
                } else if (top == '[') {
                    if (c != ']') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public List<Integer> bfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return res;
    }

    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<Integer> res = new LinkedList<>();

        while (!queue.isEmpty()) {
            int len = queue.size();
            TreeNode maxNode = new TreeNode(Integer.MIN_VALUE);

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                maxNode = maxNode.val > node.val ? maxNode : node;

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(maxNode.val);
        }
        return res;
    }

    public List<Integer> recursionTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            dfs(root.left, res);
            dfs(root.right, res);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        // 中序遍历
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();

        stack.push(root);
//        while (cur != null || !stack.isEmpty()) {
//            while (cur != null) {
//                stack.push(cur);
//                cur = cur.left;
//            }
//            TreeNode temp = stack.pop();
//            res.add(temp.val);
//            cur = cur.right;
//        }
//        return null;
        // 前序遍历
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

    public int sumNumbers(TreeNode root) {
        return dfsSum(root, 0);
    }

    int dfsSum(TreeNode node, int path) {
        if (node == null) {
            return 0;
        }

        path = path * 10 + node.val;

        return dfsSum(node.left, path) + dfsSum(node.right, path);
    }

    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;

        int sum = 0;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }

            node = stack.pop();
            sum += node.val;
            node.val = sum;
            node = node.left;
        }

        return root;
    }

    public TreeNode convertBST1(TreeNode root) {
        if (root == null) {
            return null;
        }

        dfsBST(root);
        return root;
    }

    void dfsBST(TreeNode node) {
        if (node == null) {
            return;
        }
        dfsBST(node.right);
        sum += node.val;
        node.val = sum;
        dfsBST(node.left);
    }

    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                // 一直遍历左子树
                node = node.left;
            }

            node = stack.pop();
            if (set.contains(k - node.val)) {
                return true;
            }

            set.add(node.val);
            node = node.right;
        }

        return false;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        dfsinvertTree(root);
        return root;
    }

    void dfsinvertTree(TreeNode node) {
        if (node == null) {
            return;
        }

        // 单层递归逻辑
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        dfsinvertTree(node.left);
        dfsinvertTree(node.right);
    }

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        sum(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return pathNumber;
    }

    void sum(TreeNode node, int sum) {
        if (node == null) return;

        sum = sum - node.val;
        if (sum == 0) {
            pathNumber++;
        }

        sum(node.left, sum);
        sum(node.right, sum);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfsDepth(root);
        return ans - 1;

    }

    int dfsDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfsDepth(node.left);
        int right = dfsDepth(node.right);
        ans = Math.max(ans, left + right + 1);

        return Math.max(left, right) + 1;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        root1.val = root1.val + root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numToCount = new HashMap<>();
        Arrays.stream(nums).forEach(num -> numToCount.put(num, numToCount.getOrDefault(num, 0) + 1));

        Queue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
                (e1, e2) -> e1.getValue() - e2.getValue()
        );

        for (Map.Entry<Integer, Integer> entry : numToCount.entrySet()) {
            if (heap.size() < k) {
                heap.offer(entry);
            } else if (heap.peek().getValue() < entry.getValue()) {
                heap.poll();
                heap.offer(entry);
            }
        }

        int[] res = new int[k];
        int index = k - 1;
        while (!heap.isEmpty()) {
            Map.Entry<Integer, Integer> entry = heap.poll();
            res[index--] = entry.getKey();
        }
        return res;
    }


    public static class KthLargest {
        Queue<Integer> queue;
        int topK;

        public KthLargest(int k, int[] nums) {
            this.topK = k;
            queue = new PriorityQueue<>(topK);
            Arrays.stream(nums).forEach(num -> queue.add(num));
        }

        public int add(int val) {
            queue.offer(val);
            if (queue.size() > topK) {
                queue.poll();
            }

            return queue.peek();
        }
    }

    // 1, 2, 3, 4, 5, 6, target 2
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return 0;
    }

    public int peakIndexInMountainArray(int[] nums) {
        int left = 0;
        int right = nums.length - 2;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }

            if (nums[mid] > nums[mid - 1]) {
                left = mid + 1;
            } else {
                right = mid + 1;
            }
        }

        return nums.length;
    }


    public int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
        return nums;
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (e1, e2) -> (e1[0] - e2[0]));
        List<int[]> merged = new ArrayList<>();

        int i = 0;
        while (i < intervals.length) {
            int[] temp = new int[]{intervals[i][0], intervals[i][1]};
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] <= temp[1]) {
                temp[1] = Math.max(temp[1], intervals[j][1]);
                j++;
            }
            merged.add(temp);
            i = j;
        }

        int[][] res = new int[merged.size()][];
        return merged.toArray(res);
    }


    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] counts = new int[1001];
        for (int i = 0; i < arr1.length; i++) {
            counts[arr1[i]]++;
        }

        int i = 0;
        for (int num : arr2) {
            while (counts[num] > 0) {
                arr1[i++] = num;
                counts[num]--;
            }
        }

        // 赋值arr1剩下的元素，也就是counts不等于0的数字。
        for (int num = 0; num < counts.length; num++) {
            while (counts[num] > 0) {
                arr1[i++] = num;
                counts[num]--;
            }
        }

        return arr1;
    }


    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        // 这样的话，是头顶元素是最小的值。
        Queue<ListNode> minHeap = new PriorityQueue<>((n1, n2) -> (n1.val - n2.val));
        for (ListNode node : lists) {
            minHeap.offer(node);
        }

        while (!minHeap.isEmpty()) {
            // 最小的一个元素
            ListNode least = minHeap.poll();
            cur.next = least;
            cur = least;

            if (least.next != null) {
                minHeap.offer(least.next);
            }
        }

        return dummy.next;
    }

}