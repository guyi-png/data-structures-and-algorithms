package sort;

import java.util.Arrays;

/**
 *  归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5 ,7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length -1, temp);
        System.out.println(Arrays.toString(arr));
    }

    //分, 然后治
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left < right){
            int middle = (left + right)/2;//中间索引
            //向左递归
            mergeSort(arr, left, middle, temp);
            //向右递归
            mergeSort(arr,middle+1,right,temp);
            //归并
            merge(arr,left, middle, right, temp);
        }
    }

    //治                    //排序的原始数组   左边索引的起始位置 中间索引  右边索引  中转数组
    public static void merge(int[] arr, int left, int middle, int right, int[] temp){
        int l1 = left;  //左边有序序列的初始索引
        int l2 = middle + 1;    //右边有序序列的初始索引
        int t = 0;  //中转数组中的当前索引
        //先把左右两边的数据按照规则填充到temp数组，
        // 直到左右两边的有序序列，有一边处理完为止
        while (l1 <= middle && l2 <= right){
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //就将左边的当前元素放入到temp数组中，任何索引后移
            if (arr[l1] <= arr[l2]){  //从小到大排序
                temp[t] = arr[l1];
                t++;
                l1++;
            } else{
                temp[t] = arr[l2];
                t++;
                l2++;
            }
        }
        //把一边数组中剩余的元素依次放入到temp
        while (l1 <= middle){
            temp[t] = arr[l1];
            t++;
            l1++;
        }
        while (l2 <= right){
            temp[t] = arr[l2];
            t++;
            l2++;
        }
        //将完整的temp数组的元素拷贝到arr中
        t = 0;
        while (left <= right){
            arr[left] = temp[t];
            t++;
            left++;
        }
    }
}
