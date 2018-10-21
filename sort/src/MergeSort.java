
import java.util.Arrays;

//https://www.cnblogs.com/chengxiao/p/6194356.html
public class MergeSort {
    /**
     * 归并排序：时间复杂度NlogN,
     * 采用 分治策略
     * 注意整个逻辑：
     * 分：就是递归去分。
     * 治：就是merge 两个有序元素
     * @param args
     */
    public static void main(String args[]) {
        int [] arr = {9,8,7,6,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        // 创建temp
        int [] temp = new int[arr.length];
        sort(arr, 0, arr.length -1, temp);
    }

    public static void sort(int[] arr, int left, int right, int[] temp) {
        // 错误1： 这个写成了left <= right
        if (left < right) {
            // 分，
            int mid = (left + right) / 2;
            // 左边归并排序，使得左子序列有序
            sort(arr, left, mid, temp);
            // 右边归并排序，使得右子序列有序
            sort(arr,mid +1, right, temp);
            // 治, 将两个有序子数组合并操作
            merge(arr, left, mid, right, temp);
        }
    }

    public static void merge(int[] arr, int left, int mid , int right, int[] temp) {
        int i= left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t ++] = arr[i ++];
            } else {
                temp[t ++] = arr[j++];
            }
        }
        // 将左边剩余元素填充进temp
        while (i <=mid) {
            temp[t++] = arr[i++];
        }
        // 将右边剩余元素填充进temp
        while (j<=right) {
            temp[t++] = arr[j++];
        }
        // 错误2： 忘记写t = 0;
        t = 0;
        // Copy temp 到 arr
        while (left <= right) {
            arr[left ++ ] = temp[t++];
        }
    }
}
