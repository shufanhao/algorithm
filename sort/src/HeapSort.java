import java.util.Arrays;

//https://www.cnblogs.com/chengxiao/p/6129630.html
public class HeapSort {
    /**
     * 堆排序：利用堆这种数据结构而设计的一种排序算法。
     * 它的最坏，最好，平均时间复杂度为nlogn
     * 堆是具有以下性质的完全二叉树：每个节点的值都大于或等于其左右两个孩子
     * 节点的值，称为大顶堆，或小顶堆。
     * 思路：
     * 1. 将无序序列构建成一个堆，根据升序，大顶堆，降序，小顶堆。
     * 2. 将堆顶元素与末尾元素交换，将最大元素沉到数组末端。
     * 3. 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换，直到整个序列有序。
     */
    public static void sort(int[] arr) {
        // 构建大顶堆
        // 从第一个非叶子节点开始执行
        for (int j = arr.length / 2 - 1; j >= 0; j--) {
            adjustHeap(arr, j, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            // 将堆顶元素与末尾元素进行交换，将最大元素沉到数组末端
            swap(arr, 0, j);
            adjustHeap(arr, 0, j);
        }
    }

    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; // 取出当前元素
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 从左子节点小于右子节点，k指向右子节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;

    }

    public static void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 8, 7, 6, 5, 4, 3, 2, 9};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
