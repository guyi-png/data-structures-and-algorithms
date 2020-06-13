package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  哈夫曼树：给定N个权值作为N个叶子结点，构造一棵二叉树，
 *  若该树的带权路径长度达到最小，称这样的二叉树为最优二叉树，
 *  也称为哈夫曼树(Huffman Tree)。哈夫曼树是带权路径长度最短的树，权值较大的结点离根较近。
 *
 *  路径长度： 路径中分支的数目
 *  带权路径长度: 路径中分支的数目 x 该节点的权
 */
public class HuffmanTreeTest {
    public static void main(String[] args) {
        int[] arr = {12,8,9,1,66,4,-12};
        Node2 huffmanTreeRoot = huffmanTree(arr);
        if (huffmanTreeRoot != null)
            huffmanTreeRoot.preOrder();
    }

    public static Node2 huffmanTree(int[] arr){
        List<Node2> nodes = new ArrayList<>();
        for (int value : arr){
            nodes.add(new Node2(value));  //将数组的元素转为Node中的权值，再将Node放入集合
        }

        while (nodes.size() > 1){
            Collections.sort(nodes);   //对集合排序
            //取出权值最小的两个节点(两个二叉树)
            Node2 left = nodes.get(0);
            Node2 right = nodes.get(1);
            //构建新的二叉树
            Node2 parent = new Node2(left.value+right.value);
            parent.left = left;
            parent.right = right;
            //从nodes中删除处理过的节点
            nodes.remove(left);
            nodes.remove(right);
            //将parent放到nodes中
            nodes.add(parent);
        }
        return nodes.get(0);  //nodes中的最后一个元素就是相应的huffman的根节点
    }
}

class Node2 implements Comparable<Node2>{
    int value; //权值+
    Node2 left;
    Node2 right;

    public Node2 (int value){
        this.value = value;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node2 o) {
        return this.value - o.value; //从小到大
    }

    @Override
    public String toString() {
        return "Node2{" +
                "value=" + value +
                '}';
    }
}
