package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        System.out.println("未排序前：");
        System.out.println(Arrays.toString(arr));

        bubbleSort(arr);
        System.out.println("从小到大排序后：");
        System.out.println(Arrays.toString(arr));
    }

// 从小到大排序
    public static void bubbleSort(int[] arr) {
        boolean flag = false;  //用于标识，避免做更多的无用的循环
        int temp = 0;
/*        //第一次排序，将现在最大的数排最后
        for (int i = 0; i < arr.length-1; i++){  //i是比较次数
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第一次排序后");
        System.out.println(Arrays.toString(arr));

        //第二次排序，将现在最大的数排最后
        for (int i = 0; i < arr.length-2; i++){  //i是比较次数
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第二次排序后");
        System.out.println(Arrays.toString(arr));

        //第三次排序，将现在最大的数排最后
        for (int i = 0; i < arr.length-3; i++){  //i是比较次数
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第三次排序后");
        System.out.println(Arrays.toString(arr));

        //第四次排序，将现在最大的数排最后
        for (int i = 0; i < arr.length-4; i++){  //i是比较次数
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第四次排序后");
        System.out.println(Arrays.toString(arr));           */

        //嵌套循环 时间复杂度 O(n^2)
        for (int i=0; i< arr.length-1; i++){
            for (int j = 0; j < arr.length-1-i; j++){
                if (arr[j] > arr[j+1]){
                    flag = true;   //当进入if，表示进行过排序
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
//            System.out.println("第"+ (i+1) +"次排序后");
//            System.out.println(Arrays.toString(arr));

            if (!flag){  //此时表示没有当次没有进行排序，说明arr已经是有序的
                break;
            }
            flag = false;  //注意重置flag
        }
    }
}
