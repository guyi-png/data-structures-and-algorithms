package sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        System.out.println("未排序前：");
        System.out.println(Arrays.toString(arr));
        shellSort(arr);
        System.out.println("从小到大排序后：");
        System.out.println(Arrays.toString(arr));
    }

// 从小到大排序（交换法）
    public static void shellSort1(int[] arr){
 /*       //第一次轮回
        //第一次排序，排序之前先想象将10个数分为5(10/2)组,步数为5
        int temp = 0;
        //将步长设为5
        for (int i = 5; i < arr.length; i++){
            //此循环遍历各组中的所有的元素，目的就是让各组中的元素排序
            for (int j  = i - 5; j >= 0; j -= 5){
                if (arr[j] > arr[j + 5]){
                //如果当前元素大于加上步长后的元素，则需要交换
                    temp = arr[j];
                    arr[j] = arr[j +5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一次轮回后\n"+ Arrays.toString(arr));

        //第二次轮回
        //第二次排序，排序之前先想象将10个数分为2(5/2)组
        //将步长设为2
        for (int i = 2; i < arr.length; i++){
            for (int j  = i - 2; j >= 0; j -= 2){
                if (arr[j] > arr[j + 2]){
                    temp = arr[j];
                    arr[j] = arr[j +2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第二次轮回后\n"+ Arrays.toString(arr));

        //第三次轮回
        //第三次排序，排序之前先想象将10个数分为1(2/2)组
        //将步长设为1
        for (int i = 1; i < arr.length; i++){
            for (int j  = i - 1; j >= 0; j -= 1){
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j +1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第三次轮回后\n"+ Arrays.toString(arr));           */

        //整合
        int count = 0;
        int temp = 0;
        for (int gap = arr.length/2; gap > 0; gap/=2){
            for (int i = gap; i < arr.length; i++){
                for (int j  = i - gap; j >= 0; j -= gap){
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("第"+(++count)+"次轮回后\n"+ Arrays.toString(arr));
        }
    }

// 从小到大排序(移动法  优化)
    public static void shellSort(int[] arr){
        //增量gap，不断缩小gap
        for (int gap = arr.length/2; gap >0 ; gap /= 2){
            //从第gap个元素，逐个对其所在的数组进行直接插入排序, 内部原理与插入排序一样
            // 当shellSort中的gap=1时就是插入排序
            for (int i =gap; i < arr.length; i++){
                int j = i;
                int temp = arr[j];
                while (j -gap >= 0 && temp < arr[j - gap]){
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                // //此时就已经找到要插入的位置
                arr[j] = temp;
            }
        }
    }
}
