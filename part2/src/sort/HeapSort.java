package sort;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //堆排序,从小到大,使用大顶堆
    public static void heapSort(int[] arr){
//        adjustHeap(arr, 1, arr.length);
//        System.out.println(Arrays.toString(arr));
//        adjustHeap(arr,0, arr.length);
//        System.out.println(Arrays.toString(arr));
        for (int i = arr.length/2 -1; i >=0; i--){  //i=arr.length/2-1表示最后一个非叶子节点
            adjustHeap(arr,i,arr.length);
        }
        //将堆顶元素与末尾元素交换，将最大元素放到数组末端
        //重新调整堆, 重复操作
        int temp = 0;
        for (int j = arr.length-1; j >0; j--){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0 , j);
        }
    }

    //将一个二叉树，调整为一个大顶堆
    //arr待调整的数组  i表示非叶子节点在数组中的索引 length表示对多少个元素调整
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];
        for (int k= i*2+1; k < length; k = k *2+1){  //k = i*2+1 表示是i节点的左子节点
            if ( k+1 < length && arr[k] < arr[k+1]){    //当左子节点小于右子节点
                k++; //k指向右子节点
            }
            if (arr[k] > temp){  //如果子节点大于父节点
                arr[i] = arr[k];
                i = k;   //继续循环比较
            }else{
                break;
            }
        }
        arr[i] = temp;  //将temp值放到调整后的位置
    }
}
