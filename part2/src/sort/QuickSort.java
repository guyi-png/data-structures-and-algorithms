package sort;

import java.util.Arrays;

/**
 *  快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {85,1,6,65,6789,123,-15};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){ //left和right是索引
        int l = left;
        int r = right;
        //pivot 中轴
        int pivot = arr[(l+r)/2];
        int temp = 0;

        //让比pivot小的放左边，比pivot大的放右边， 即从小到大排序
        while ( l < r){
            //在pivot左边一直找，直到找到大于等于pivot的值
            while (arr[l] < pivot){
                l += 1;
            }
            //在pivot右边一直找，直到找到小于等于pivot的值
            while (arr[r] > pivot){
                r -= 1;
            }
            if (l >= r){
                //此时比pivot小的都放左边，比pivot大的都放右边
                break;
            }
            //交换满足上面的条件的两个值
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完成后， 发现arr[l] == pivot 则说明原来arr[r]取的值正是pivot
            //就可以缩小一下范围
            if (arr[l] == pivot){
                r--;
            }
            if (arr[r] == pivot){
                l++;  //一样缩小一下范围
            }
        }
        //判断l == r ，true则让l++ ，r--，避免递归时栈溢出
        if (l == r){
            l++;
            r--;
        }
        //向左递归
        if (left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if (l < right){
            quickSort(arr,l,right);
        }
    }
}
