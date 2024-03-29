## LeetCode  初级

https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/

#### 1. 数组

1,2,3（空间复杂度是1的没有想到）,6,8,9, 以后重点看这几个

- 如果要空间复杂度是o(1)的话，应该是肯定有两个指针，start,end,
  分别指向数组的第一个元素和最后一个元素，然后分别start ++, end--
- 考虑先排序
- 考虑异或，可以知道重复的元素
- 两个数组交集，考虑两个指针

1. 从排序数组中删除重复项。不考虑数组中超出新长度后面的元素分别用两个指针，i,j 去移动，不相等的话，指针i++;
   附加：颜色分类。http://www.cnblogs.com/grandyang/p/4341243.html
2. 买卖股票的最佳时机。其实就是就一个和：下一天的价格减去前一天的价格差，这个差求和; 附加：另外一种是只能最多交易一次，买一次，卖出一次，也就是最小
   买入，最大卖出，动态规划法；
3. 旋转数组。先反转前n-k个元素，再反转后K个元素，再整个数组反转。
4. 存在重复元素。（1）将数组放到HashMap中，然后检查是否有key，如果有说明，已经存放过。（2）先将数组排序，然后比较前后2个元素。
5. 只出现一次的数字。任何一个数字异或它自己都等于0。也就是说，如果我们从头到尾依次异或数组中的每一个数字，那么最终的结果刚好是那个只出现一次的数字，因为那些出现两次的数字全部在异或中抵消掉了。
6. 两个数组的交集：先将两个数组排序，然后两个指针分别指向数组1，数组2，根据两个数组的元素大小，决定是否移动指针。
7. 加一。比较容易
8. 移动0. 思路：先将不是0的数字筛选出来，放到数组的一侧，最后将剩下的填充0
9. 两数之和。将数组的元素和对赢的index，存到Map中用target减去 数组的每一个元素，然后将结果在map中检查是否有对应的key如果有，则返回。
10. 有效的数独
11. 旋转图像。
12. 输出数组中最大的连续子序列的长度
13. 长度最小的子数组，大于某个值得
14. 求最大平均值的连续子序列。其实对于最大平均值来讲，任何一个数加上比自己小的数，所得出的平均值是肯定比原来小，所以求最大平均值其实就是求
    哪个数最大。如果有限制条件说必须是至少是连续的子序列(大于等于2)，那就从含有2个元素的连续子序列开始找，因为3个数的平均值肯定没有2个数的平均值大

#### 2. 字符串

2，5

1. 反转字符串。思路：栈
2. 颠倒整数。思路：根据 x % 10 = 个位数，x/10 = 除了个位数的前面几个数，还要考虑是100的这种情况
3. 字符串中唯一字符。思路：26个英文字母放到数组中，然后遍历string, 让数组中的26个英文字母加1，最后循环check哪个是1，返回即
4. 有效的字母异位词。26个英文字母放进数组中，然分别遍历两个字符串，执行++，-- 然后遍历26个英文字母判断是否有不是0的case。
5. 验证回文字符串。思路：两个指针分别指向字符串的左右，然后如果是字母或者数字，则指针++, 如果左右的字符相等，则指针++
6. 字符串转整数。循环，char 转正int 可以通过 char - '0' 获得，然后可以通过base*10,获取最终的int 值
7. 实现strStr函数，思路：subString()
8. 报数 并说：超过1个相同的数字连在一起时，并说，也就是”几个几 不并说：前后数字不同时，不并说，需要单说,也就是一个几。
9. 最长公共前缀。先排序，然后用字符串数组的第一个和最后一个比较

#### 3. 链表

- 注意双指针解法，双指针：快慢指针和首尾指针 （一般用于使用额外空间的情况）
- "Dummy node" 节点技巧，所谓Dummy Node其实就是带头节点的指针。

1. 题目1：删除链表中的节点。思路：修改的要删除节点的值，将其改成和下一个节点一样的值。
2. 删除链表的倒数第N个几点，并返回链表的头结点，尝试用一次扫描。如果一次扫描：双指针让前指针先走N步，再让两个在指针同时后移，直到前指针到达尾部，此时，后指针的下一个节点就是要被删除的节点了。
3. 反转列表, p,q,r三个指针一起配合，使得两个节点间的指向反向，同时用r及记录剩下的链表
4. 合并两个有序链表。思路：两个指针指向两个链表，然后新建一个链表，更改新建链表的当前指针即可。
5. 回文链表。思路：(1)
   慢指针一次走一步，快指针一次走两步，当快指针走到底的时候，慢指针到了中间位置。（2）找到中点后，对中点后面的元素进行反转。(3)
   从头尾开始向中间移步，每次比较是否相同即可
6. 判断环形链表。思路：快慢指针，如果最后快慢指针指向相同则说明是环形链表

#### 4. 树

用递归和栈去解决问题

1. 二叉树的最大深度。递归求深度， return left > right ? left + 1 : right + 1;
2. 验证二叉搜索树。（左子树的节点值都小于node的值，右子树节点的值都大于node值）。理解的不是很好。
3. 对称二叉树。给定一个二叉树，检查它是否是镜像对称的。思路：递归
4. 二叉树的层次遍历。用队列实现。
5. 将有序数组转成二叉搜索树。理解的不是很好

#### 5. 排序和搜索

1. 合并两个有序数组。思路：两个指针分别指向两个数组，记录位置，用另一个指针指向合并后的数组（已知长度），然后比较大小，更改指针位置。循环的条件很关键。
2. 第一个错误的版本。思路：二分法求值，while(min <= max)

#### 6. 设计

1. Shuffle an Array。打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。就是用random随机取出一个数，然后这个数
   的对应的数组元素和遍历数组的最后一个元素交换。
2. 最小栈。就是构建一个list,对这个list 进行操作

#### 7. 数学

1. Fizz Buzz
2. 计数质数。思路：如果一个数是另一个数的倍数，那这个数肯定不是质数。从2开始，2所有的倍数，都不是质数，然后再从3开始。
3. 3的幂。思路：递归：如果余数是0，除数不是0，则继续递归看除数是否可以整除3。另一个思路：求对数,Log以3为底，求n的对数。
4. 罗马数字转整型。主要是规律（后面的字符比前面的大则减，反之，加）

#### 8. 其他

1. 位1的个数（汉明重量）。思路：某个数字移位，然后和1与，如果是1，则计数加1
2. 两个数字对应二进制不同位置的数目（汉明距离）。思路：先异或，然后求1的个数。
3. 颠倒二进制位。思路：向右移动一位，取到这一位然后将其移动到相反位置，再进行下一次循环
4. 帕斯卡三角形。思路：要理解逻辑，具体看code
5. 有效的括号。思路：充分利用栈，先进后出。如果是 (,{,[则入栈，如果是),}，]的话，则取出栈顶元素，判断是否是(,{,[
   。如果是，则取出栈顶元素，最后判断栈是否是空
6. 缺失数字
7. String 和 IP 地址转换，成分利用左移右移的位运算
