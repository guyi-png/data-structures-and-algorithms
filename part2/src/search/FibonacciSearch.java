package search;

import java.util.Arrays;

/**
 * 斐波那契查找
 */
public class FibonacciSearch {
    private final static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1, 7, 34, 89, 325, 1111};
        int index = fibonacciSearch(arr, 34);
        System.out.println(index);
    }

    //定义斐波那契数列
    public static int[] fibonacci(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i =2; i < f.length; i++){
            f[i] = f[i-1] + f[i -2];
        }
        return f;
    }

    // 斐波那契越到后面，最后的两个数之比越接近0.618
    // 斐波那契 F(k) = F(k - 1) + F(k -2) (k >=3)
    //             F(k-1)作为左边   F(k-2)作为右边
    //  middle = left + F(k-1) -1
    //  斐波那契查找  针对有序数组
    public static int fibonacciSearch(int[] arr, int val){
        int left = 0;
        int right = arr.length -1;
        int k = 0;  //斐波那契中的 k
        int middle = 0;   //middle指黄金分割点（近似）
        int[] f = fibonacci();
        //找到arr数组长度 对应的斐波那契中的刚好等于或大于它的数
        while (right > f[k] -1){
            k++;
        }
        // 当斐波那契中的那个数大于数组的长度
        // 使用Arrays类构造一个新的数组,多出的部分元素默认值为0
        int[] temp = Arrays.copyOf(arr,f[k]);
        //使用arr数组的最后的一个值填充新数组多出的部分元素的位置
        for (int i = right +1; i < temp.length; i++){
            temp[i] = arr[right];
        }

        while (left <= right){
            middle = left + f[k-1] -1;   //此时temp长度为 f[k],数组最后的索引为 f[k]-1
            if (val < temp[middle]){   //向左查找
                right = middle -1;
                k--;    //  F(k)= F(k-1)+F(k-2) , 左边F(k-1)= F(k-2)+F(k-3)
            }else if (val > temp[middle]){   //向右查找
                left = middle + 1;
                k -= 2;  // F(k)= F(k-1)+F(k-2) , 右边F(k-2)= F(k-3)+F(k-4)
            }else{
                // 可能查到arr的最后一个指的后面去，即temp中多出的那几个位置
                return Math.min(middle, right);
            }
        }
        return -1;    //找不到
    }
}
