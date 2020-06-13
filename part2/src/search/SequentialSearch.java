package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺序查找
 */
public class SequentialSearch {
    public static void main(String[] args) {
        int[] arr = {32,34,-14,7,78,32};

        int index = seqSearch(arr, 32);
        System.out.println(index);

        List<Integer> indexList = seqSearchAll(arr, 32);
        System.out.println(indexList);
    }

    public static int seqSearch(int[] arr, int val){
        //线性查找是逐一进行比对，找到相同的值就返回索引，没有就返回 -1
        for (int i =0; i < arr.length; i++){
            if (val == arr[i]){
                return i;
            }
        }
        return -1;
    }

    public static List<Integer> seqSearchAll(int[] arr, int val){
        List<Integer> list = new ArrayList<>();
        for (int i =0; i < arr.length; i++){
            if (val == arr[i]){
                list.add(i);
            }
        }
        if (list.size() == 0){
            return null;
        }else{
            return list;
        }
    }
}
