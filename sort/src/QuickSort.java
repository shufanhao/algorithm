import java.util.Arrays;

public class QuickSort {

    //http://wiki.jikexueyuan.com/project/easy-learn-algorithm/fast-sort.html
    void quickSort(int arr[], int left, int right) {
        if (left >= right) {
            return;
        }
        int i,j,temp;
        i = left;
        j = right;
        int privot = arr[i];
        while (i != j) {
            while (j >i && arr[j] >= privot) {
                j--;
            }
            while (j > i && arr[i] <= privot) {
                i++;
            }
            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = privot;
        quickSort(arr, left, i-1);
        quickSort(arr, i +1, right);
    }

    public static void main(String args[]) {
        QuickSort obj = new QuickSort();
        int[] nums = {3,4,1,5,6};
        obj.quickSort(nums, 0, nums.length -1);
        System.out.println(Arrays.toString(nums));
    }
}