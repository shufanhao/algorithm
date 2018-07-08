import java.util.Arrays;

//https://www.cnblogs.com/chengxiao/p/6129630.html
public class HeapSort {

    public static void sort(int[] arr) {
        for (int i=arr.length/2 -1; i>=0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int j= arr.length -1; j>0; j--) {
            swap(arr, 0, j);
            adjustHeap(arr, 0, j);
        }
    }

    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k=2*i+1; k<length; k=2*k +1) {
            if ((k+1) < length && arr[k] < arr[k+1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
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
        int [] arr = {1,8,7,6,5,4,3,2,9};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
