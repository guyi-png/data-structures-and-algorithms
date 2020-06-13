package search;

import java.util.ArrayList;
import java.util.List;

/**
 *  二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr =  {34, 34, 34, 34, 34, 56, 777, 890};
        int index = binarySearch(arr, 0, arr.length - 1, 34);
        System.out.println("index: "+ index);
        List<Integer> indexList = binarySearchAll(arr, 0, arr.length - 1, 34);
        System.out.println("indexList: "+ indexList);
    }

    // 二分查找, 针对有序数组             //原数组最左边的索引  最右边的索引  要查的值
    public static int binarySearch(int[] arr, int left, int right, int val){
        if (left > right || val < arr[0] || val > arr[arr.length -1]){   //找不到
            return -1;
        }
        int middle = (left + right)/2; //中间的索引
        if (val > arr[middle]){ //向右递归
            return binarySearch(arr, middle+1, right, val);
        }
        if (val < arr[middle]){ ////向左递归
            return binarySearch(arr, left, middle-1, val);
        }
        return middle;
    }

    public static List<Integer> binarySearchAll(int[] arr, int left, int right, int val){
        if (left > right){   //找不到
            return new ArrayList<>();
        }
        int middle = (left + right)/2; //中间的索引
        if (val > arr[middle]){
            return binarySearchAll(arr, middle+1, right, val);
        }
        if (val < arr[middle]){
            return binarySearchAll(arr, left, middle-1, val);
        }

        List<Integer> indexList = new ArrayList<>();
        //向左扫描, 看看有没有多个和val相同的值
        int temp = middle -1;
        while (temp >= 0){
            if (arr[temp] != val){  //因为有序，只需判断前一位
                break;
            }else{
                indexList.add(temp);
                temp--;
            }
        }
        //放入找到的索引
        indexList.add(middle);
        //向右扫描
        temp = middle + 1;
        while (temp <= arr.length -1){
            if (arr[temp] != val){
                break;
            }else{
                indexList.add(temp);
                temp++;
            }
        }
        indexList.sort((Integer::compare));
        return indexList;
    }
}
