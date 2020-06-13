package sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {3346, 101, 38, 110, 8, -1, 1100};
        System.out.println("未排序前：");
        System.out.println(Arrays.toString(arr));

        insertSort(arr);
        System.out.println("从小到大排序后：");
        System.out.println(Arrays.toString(arr));
    }

// 从小到大排序
    public static void insertSort(int[] arr){
        int insertVal = 0;//定义一个待插入的数
        int insertIndex = 0;  //待插入的位置的索引
/*        //第一次轮回
        //先将原数组想象的分两个数组，第一项的数为一个有序(一开始只有一个元素)，剩下的装在一个无序里
        insertVal = arr[1];//定义一个待插入的数
        insertIndex = 0;  //待插入的位置的索引

        //insertIndex >= 0  保证在给insertVal找插入位置时，不越界
        //insertVal < arr[insertIndex]  表示没有找到要插入的位置
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];  //没有找到要插入的位置就让当前索引的值后移
            insertIndex--;
        }
        //此时就已经找到要插入的位置
        arr[insertIndex+1] = insertVal;
        System.out.println("第一轮插入后");
        System.out.println(Arrays.toString(arr));

        //第二次轮回
        insertVal = arr[2];//定义一个待插入的数
        insertIndex = 1;  //待插入的位置的索引
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];  //没有找到要插入的位置就让当前索引的值后移
            insertIndex--;
        }
        //此时就已经找到要插入的位置
        arr[insertIndex+1] = insertVal;
        System.out.println("第二轮插入后");
        System.out.println(Arrays.toString(arr));

        //第三次轮回
        insertVal = arr[3];//定义一个待插入的数
        insertIndex = 2;  //待插入的位置的索引
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];  //没有找到要插入的位置就让当前索引的值后移
            insertIndex--;
        }
        //此时就已经找到要插入的位置
        arr[insertIndex+1] = insertVal;
        System.out.println("第三轮插入后");
        System.out.println(Arrays.toString(arr));               */

        //for嵌套while  时间复杂度 O(n^2)
        for (int i = 1; i < arr.length; i++){
            insertVal = arr[i];//定义一个待插入的数
            insertIndex = i - 1;  //待插入的位置的索引

            //insertIndex >= 0  保证在给insertVal找插入位置时，不越界
            //insertVal < arr[insertIndex]  表示没有找到要插入的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];  //没有找到要插入的位置就让当前索引的值后移
                insertIndex--;
            }
            //此时就已经找到要插入的位置
            if (insertIndex + 1 != i) { //要插的位置就是自己的位置，就没有必要插了
                arr[insertIndex + 1] = insertVal;
            }
//            System.out.println("第"+i+"轮插入后");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
