
//https://www.cnblogs.com/chengxiao/p/6129630.html
public class HeapSort {

    public static void sort(int[] arr) {
        //1. 构建最大堆
        for (int i=arr.length/2 -1; i>=0; i--) {
            //从第一个非叶子节点从下至上，从右到左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //2. 交换堆顶元素，和末尾元素，并且将剩下的元素再次进行堆调整
        for (int j = arr.length -1; j>0; j--) {
            swap(arr, 0, j);
            adjustHeap(arr, 0, j); //重新对堆进行调整
        }
    }

    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; // 取出当前元素
        // 从i节点的左子节点开始，也就是2i+1开始, 后面的k=k*2 + 1 ，不太清楚为啥。
        for (int k=i*2 +1; k<length; k = k*2 +1) {
            //如果左子节点小于右子节点，k指向右子节点
            if (k +1 < length && arr[k] < arr[k+1]) {
                k++;
            }
            if (arr[k] > temp) {
                //如果子节点大于父节点，将子节点赋值给父节点
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp; //将 temp 放到最终位置
    }

    public static void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void main(String[] args) {
        int [] arr = {1,8,7,6,5,4,3,2,9};
        //int [] arr = {4,6,8,5,9};
        sort(arr);
        for (int one : arr) {
            System.out.println(one);
        }
    }
}
