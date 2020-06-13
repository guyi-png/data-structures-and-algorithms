package tree;

/**
 *  顺序存储二叉树
 */
public class ArrBinaryTreeTest {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.postOrder(0);
    }
}

//实现顺序存储二叉树
class ArrBinaryTree{
    private final int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //顺序存储二叉树的前序遍历
    public void preOrder(int index){  //index为数组的索引
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，没用");
            return;
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index*2 +1) < arr.length){
            preOrder(index*2+1);
        }
        //向右递归遍历
        if ((index*2 +2 < arr.length)){
            preOrder(index*2+2);
        }
    }

    //顺序存储二叉树的中序遍历
    public void infixOrder(int index){  //index为数组的索引
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，没用");
            return;
        }
        //向左递归遍历
        if ((index*2 +1) < arr.length){
            infixOrder(index*2+1);
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向右递归遍历
        if ((index*2 +2 < arr.length)){
            infixOrder(index*2+2);
        }
    }

    //顺序存储二叉树的后序遍历
    public void postOrder(int index){  //index为数组的索引
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，没用");
            return;
        }
        //向左递归遍历
        if ((index*2 +1) < arr.length){
            postOrder(index*2+1);
        }
        //向右递归遍历
        if ((index*2 +2 < arr.length)){
            postOrder(index*2+2);
        }
        //输出当前元素
        System.out.println(arr[index]);
    }
}
