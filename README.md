# Algorithm
## Sort
## LeetCode
1. Easy: https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/
2. Medium: https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/

# 时间空间复杂度
参考：https://zhuanlan.zhihu.com/p/50479555
## 时间复杂度
1. 常数阶O(1)。无论代码执行了多少行，只要没有循环结构，这个代码的时间复杂度都是O(1)。

```
int i = 1;
int j = 2;
++i;
j++;
int m = i + j;
```
上述代码在执行的时候，它消耗的时候并不随着某个变量的增长而增长，那么无论这类代码有多长，即使有几万几十万行，都可以用O(1)来表示它的时间复杂度。

2. O(n)。一般是单层for循环

```
for(i=1; i<=n; ++i)
{
   j = i;
   j++;
}
```
3. O(logN)。

```
int i = 1;
while(i<n)
{
    i = i * 2;
}
```
while循环里面，每次都将 i 乘以 2，乘完之后，i 距离 n 就越来越近了。我们试着求解一下，假设循环x次之后，i 就大于 2 了，此时这个循环就退出了，也就是说 2 的 x 次方等于 n，那么 x = log2^n
也就是说当循环 log2^n 次以后，这个代码就结束了。因此这个代码的时间复杂度为：O(logn)

4. O(nlogN)。将时间复杂度为O(logn)的代码循环N遍的话，那么它的时间复杂度就是 n * O(logN)，也就是了O(nlogN)。

```
for(m=1; m<n; m++)
{
    i = 1;
    while(i<n)
    {
        i = i * 2;
    }
}
```
5. O(n²)。把 O(n) 的代码再嵌套循环一遍

```
for(x=1; i<=n; x++)
{
   for(i=1; i<=n; i++)
    {
       j = i;
       j++;
    }
}
```
## 空间复杂度
用来计算程序执行的所需要的实际空间大小。
1. O(1)。算法所需要的临时空间不随着某个变量n的大小而变化，则认为是O(1)。

```
int i = 1;
int j = 2;
++i;
j++;
int m = i + j;
```
2. O(n)。第一行new了一个数组出来，这个数据占用的大小为n，这段代码的2-6行，虽然有循环，但没有再分配新的空间

```
int[] m = new int[n]
for(i=1; i<=n; ++i)
{
   j = i;
   j++;
}

```
