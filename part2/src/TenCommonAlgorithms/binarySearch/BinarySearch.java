package TenCommonAlgorithms.binarySearch;

/**
 * 二分查找（非递归）
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {5,22,67,85,111,572,4982};  //二分查找只适用于有序数列
        int index = binarySearch(arr, 22);
        System.out.println(index);
    }

    public static int binarySearch(int[] arr, int val){
        int left = 0;
        int right = arr.length - 1;
        int middle = (left+right)/2;
        while (left <= right){
            if (arr[middle] == val){
                return middle;
            }else if (arr[middle] > val){
                right = middle-1;
                middle = (left+right)/2;
            }else{
                left = middle+1;
                middle = (left+right)/2;
            }
        }
        return -1;
    }
}
