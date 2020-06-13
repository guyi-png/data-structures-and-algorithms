package search;

import java.util.Arrays;

/**
 *  插值查找
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i =0; i < arr.length; i++){
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));

        int index = interpolationSearch(arr, 0, arr.length - 1, 100);
        System.out.println(index);
    }

    //插值查找, 针对有序数组
    public static int interpolationSearch(int[] arr, int left, int right, int val){
        if (left > right || val < arr[0] || val > arr[arr.length -1]){
            return -1;
        }
        //二分查找中 middle = (left + right)/2 = left + (right - left)/2
        int middle = left + (right -left)* (val - arr[left])/(arr[right] - arr[left]);
        if (val > arr[middle]){  //向右递归
            return interpolationSearch(arr, middle+1, right, val);
        }
        if (val < arr[middle]){
            return interpolationSearch(arr, left, middle-1, val);
        }
        return middle;
    }
}
