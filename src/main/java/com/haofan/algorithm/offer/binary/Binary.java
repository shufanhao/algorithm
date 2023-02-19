package com.haofan.algorithm.offer.binary;

public class Binary {
    /**
     * 题目2：二进制加法
     * 输入两个表示二进制的字符串，请计算它们的和，并以二进制字符串的形式输出。例如，输入的二进制字符串分别是"11"和"10"，则输出"101"。
     * 思考：一般看到这个题目后，可能会把两个整数相加后得到和，再转成二进制，这种方法会导致溢出。
     * 解法：
     * 解法1：两个整数相加的可能性是0+0=0，-> 则字符为“0” 无进位; 0+1=1，-> 则字符为“1”，无进位; 1+1=2，-> 则字符为“0”，进位为1; 1+1+1=3，-> 则字符为“1”，进位为1
     *       先补齐小的数，小的数前面加0，然后一个for loop 判断上面4种case。
     * 解法2：如下
     **/
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int len = Math.max(a.length(), b.length());
        int carry = 0;

        // 要从后往前算
        for (int i=0; i<len; i++) {
            //随着i的增大，len-1-i会随之减小，也就实现了前移的操作，carry即为当前i下，a[i]与b[i]字符的和
            if (i < a.length()) {
                carry += getInt(a.charAt(a.length() - 1 - i));
            }
            if (i < b.length()) {
                carry += getInt(b.charAt(b.length() - 1 - i));
            }
            result.append(carry % 2);// 除k取余，将余数加入到s中
            carry /= 2; // 若carry =2则置为1，用于下次进位。若carry<2，则置为0，因为不需要进位
        }

        //这里之所以要加这个if是因为考虑到a，b首位都是1的情况
        //以a = "1010", b = "1011"为例:在第四次循环时，它们首位都是1，那么carry=2，然后s追加了0（s.append(carry % 2)）
        //之后carry置为了1，循环就结束了，但此时s中只有4位数，第四次的进位还没完成，所以我们这里给它补上
        if(carry > 0) result.append('1');

        return result.reverse().toString();
    }

    /**
     * 题目3：前n个数字二进制形式中1的个数
     * 输入一个非负数n，请计算0到n之间每个数字的二进制形式中1的个数，并输出一个数组。例如，输入的n为4，由于0、1、2、3、4的二进制形式中
     * 1的个数分别为0、1、1、2、1，因此输出数组[0，1，1，2，1]。
     * 解法：计算每个整数的二进制形式中1的个数可以通过i&(i-1) 计算i的二进制行驶中1的个数
     * i&(i-1)将i的二进制形式中最右边的1变成0，整数i的二进制中1的个数比i&(i-1)的二进制形式多1
     **/
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i=1; i<num; i++) {
            result[i] = result[i & (i- 1)] + 1;
        }
        return result;
    }

    /**
     * 题目4
     * 输入一个整数数组，数组中只有一个数字出现了一次，而其他数字都出现了3次。请找出那个只出现一次的数字。
     * 例如，如果输入的数组为[0，1，0，1，0，1，100]，则只出现一次的数字是100。
     * 解法：一个整数是由32个0或1组成的。我们可以将数组中所有数字的同一位置的数位相加。
     * 如果将出现3次的数字单独拿出来，那么这些出现了3次的数字的任意第i个数位之和都能被3整除。因此，如果数组中所有数字的第i个数位相加之和能被3整除，
     * 那么只出现一次的数字的第i个数位一定是0；如果数组中所有数字的第i个数位相加之和被3除余1，那么只出现一次的数字的第i个数位一定是1。
     * 这样只出现一次的任意第i个数位可以由数组中所有数字的第i个数位之和推算出来
     **/
    public int singleNumber(int[] nums) {
        int[] bitSums = new int[32];
        for (int num : nums) {
            for (int i=0; i<32; i++) {
                bitSums[i] += (num >> (31 -i)) &1;
            }
        }
        int result = 0;
        for (int i=0; i<32; i++) {
            result = (result << 1) + bitSums[i] % 3;
        }
        return result;
    }
    private static int getInt(char c) {
        return c == '1' ? 1 : 0;
    }

}
