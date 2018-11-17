import java.util.BitSet;

public class Solution {

    /**
     * 题目1： BitMap, 10亿个范围为1~2048的整数，将其去重并计算数字数目。
     */
    public static void bitMap(int arr[]) {
        BitSet bitSet = new BitSet(2);

        for (int i=0; i<arr.length; i++) {
            bitSet.set(arr[i], true);
        }

        // 去重，并计算数字数目
        for (int i=0; i<arr.length; i++) {
            System.out.println(bitSet.get(i));
        }
        System.out.println("size is: " + bitSet.size());
    }
    public static void main(String args[]) {
        int [] array = new int [] {1,2,3,22,0,3,63};
        bitMap(array);
    }
}
