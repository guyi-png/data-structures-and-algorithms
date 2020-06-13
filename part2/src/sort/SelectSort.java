package sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {3346, 101, 38, 110, 8, -1, 1100};
        System.out.println("未排序前：");
        System.out.println(Arrays.toString(arr));

        selectSort(arr);
        System.out.println("从小到大排序后：");
        System.out.println(Arrays.toString(arr));
    }

// 从小到大排序
    public static void selectSort(int[] arr){
/*        //第一次轮回
        int minNumIndex = 0;  //最小值的索引
        int min = arr[0];   //假定第一个数是最小
        for (int i = 0 + 1;i < arr.length; i++){
            if (min > arr[i]){  //找到比当前的数还要小的数
                //重置最小值以及它的索引
                min = arr[i];
                minNumIndex = i;
            }
        }
        //循环过后，找到最小值以及其索引，下面交换最小值和第一个数的位置
        if (minNumIndex != 0){
            arr[minNumIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第一次轮回后");
        System.out.println(Arrays.toString(arr));

        //第二次轮回
        minNumIndex = 1;
        min = arr[1];
        for (int i = 0 + 2;i < arr.length; i++){
            if (min > arr[i]){  //找到比当前的数还要小的数
                //重置最小值以及它的索引
                min = arr[i];
                minNumIndex = i;
            }
        }
        //循环过后，找到最小值以及其索引，下面交换最小值和第二个数的位置
        if (minNumIndex != 1){
            arr[minNumIndex] = arr[1];
            arr[1] = min;
        }
        System.out.println("第二次轮回后");
        System.out.println(Arrays.toString(arr));

        //第三次轮回
        minNumIndex = 2;
        min = arr[2];
        for (int i = 0 + 3;i < arr.length; i++){
            if (min > arr[i]){  //找到比当前的数还要小的数
                //重置最小值以及它的索引
                min = arr[i];
                minNumIndex = i;
            }
        }
        //循环过后，找到最小值以及其索引，下面交换最小值和第三个数的位置
        if (minNumIndex != 2){
            arr[minNumIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第三次轮回后");
        System.out.println(Arrays.toString(arr));               */

        //嵌套循环 时间复杂度 O(n^2)
        for (int i =0 ; i< arr.length - 1; i++){
            int minNumIndex = i;  //最小值的索引
            int min = arr[i];   //假定第一个数是最小
            for (int j =1 + i; j < arr.length;j++){
                if (min > arr[j]){  //找到比当前的数还要小的数
                    //重置最小值以及它的索引
                    min = arr[j];
                    minNumIndex = j;
                }
            }
            //循环过后，找到最小值以及其索引，下面交换最小值和当前数的位置,
            // 如果当前数的位置和最小值的位置相同就没有必要交换
            if (minNumIndex != i){
                arr[minNumIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第"+(i+1)+"次轮回后");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
