package sort;

import java.util.Arrays;

/**
 *  基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = { 54, 3, 677, 835, 34, 250};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //基数排序, 用内存换时间，不处理负数
    public static void radixSort(int[] arr){
        //定义一个二维数组，里面的每个桶就是一个一维数组,防止桶溢出，就指定和arr数组大小一样
        int[][] bucket = new int[10][arr.length];
        //定义每个桶的 记录桶中元素数量的数组
        int[] elementCounts = new int[10];
/*        for (int value : arr) {
            int digit = value % 10;  //取到arr数组中每个数的  个位上的数
            //放到对应的桶中 的对应的位置
            bucket[digit][elementCounts[digit]] = value;
            elementCounts[digit]++;
        }
        //按照桶的顺序，将每个桶中的元素取出放入arr数组
        int index = 0;
        //遍历每个桶，将值按顺序放入arr中
        for (int j =0; j < bucket.length;j++){
            if (elementCounts[j] != 0) {   //如果当前的桶为空，就没有必要取了
                for (int k = 0; k < elementCounts[j]; k++){  //遍历桶中的元素
                    //取出元素放入arr中
                    arr[index++] = bucket[j][k];
                }
                elementCounts[j] = 0;  //注意重置桶中元素的数量
            }
        }
        System.out.println("第一轮后,"+ Arrays.toString(arr));
//        *********************************************************************
        for (int value : arr) {
            int digit = value /10 %10;  //(这的除以10是让小数点左移一位)取到arr数组中每个数的  十位上的数
            //放到对应的桶中 的对应的位置
            bucket[digit][elementCounts[digit]] = value;
            elementCounts[digit]++;
        }
        //按照桶的顺序，将每个桶中的元素取出放入arr数组
        index = 0;
        //遍历每个桶，将值按顺序放入arr中
        for (int j =0; j < bucket.length;j++){
            if (elementCounts[j] != 0) {   //如果当前的桶为空，就没有必要取了
                for (int k = 0; k < elementCounts[j]; k++){  //遍历桶中的元素
                    //取出元素放入arr中
                    arr[index++] = bucket[j][k];
                }
                elementCounts[j] = 0;
            }
        }
        System.out.println("第二轮后,"+ Arrays.toString(arr));
//        *********************************************************************
        for (int value : arr) {
            int digit = value /100 %10;  //(这的除以100是让小数点左移两位)取到arr数组中每个数的  百位上的数
            //放到对应的桶中 的对应的位置
            bucket[digit][elementCounts[digit]] = value;
            elementCounts[digit]++;
        }
        //按照桶的顺序，将每个桶中的元素取出放入arr数组
        index = 0;
        //遍历每个桶，将值按顺序放入arr中
        for (int j =0; j < bucket.length;j++){
            if (elementCounts[j] != 0) {   //如果当前的桶为空，就没有必要取了
                for (int k = 0; k < elementCounts[j]; k++){  //遍历桶中的元素
                    //取出元素放入arr中
                    arr[index++] = bucket[j][k];
                }
                elementCounts[j] = 0;
            }
        }
        System.out.println("第三轮后,"+ Arrays.toString(arr));                  */

//        整合后
        //得到最大数的索引
        int max = arr[0];
        for (int item : arr) {
            if (max < item) {
                max = item;
            }
        }
        //得到最大数的位数
        int maxLength = (max + "").length();

        for (int i =0; i < maxLength; i++){
            for (int value : arr) {
                int digit = value /(int)Math.pow(10,i) % 10;  //取到arr数组中每个数的  个位上的数
                //放到对应的桶中 的对应的位置
                bucket[digit][elementCounts[digit]] = value;
                elementCounts[digit]++;
            }
            //按照桶的顺序，将每个桶中的元素取出放入arr数组
            int index = 0;
            //遍历每个桶，将值按顺序放入arr中
            for (int j =0; j < bucket.length;j++){
                if (elementCounts[j] != 0) {   //如果当前的桶为空，就没有必要取了
                    for (int k = 0; k < elementCounts[j]; k++){  //遍历桶中的元素
                        //取出元素放入arr中
                        arr[index++] = bucket[j][k];
                    }
                    elementCounts[j] = 0;  //注意重置桶中元素的数量
                }
            }
        }
    }
}
