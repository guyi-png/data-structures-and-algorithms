package tree;

/**
 *  线索化二叉树
 */
public class ThreadedBinaryTreeTest {
    public static void main(String[] args) {
        Node1 node1 = new Node1(1, "一");
        Node1 node2 = new Node1(2, "二");
        Node1 node3 = new Node1(3, "三");
        Node1 node4 = new Node1(4, "四");
        Node1 node5 = new Node1(5, "五");
        Node1 node6 = new Node1(6, "六");
        ThreadedBinaryTree tbt = new ThreadedBinaryTree();
        tbt.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node4);
        node4.setLeft(node3);
        node4.setRight(node5);
        node5.setLeft(node6);

        tbt.threadedNodes(node1);
        Node1 left = node6.getLeft();
        Node1 right = node6.getRight();
        System.out.println(left);
        System.out.println(right);
        System.out.println("*****************");

        tbt.threadedShowList();
    }
}

//线索化二叉树
class ThreadedBinaryTree{
    private Node1 root;
    private Node1 pre; //指向当前节点的前驱节点

    public void setRoot(Node1 root){
        this.root = root;
    }

    //中序线索化
    public void threadedNodes(Node1 node){  //node表示当前需要线索化的节点
        if (node == null){
            return;
        }
        //先线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前节点
        //如果当前节点的左节点不存在
        if (node.getLeft() == null){
            //将当前节点的左节点设为前驱节点
            node.setLeft(pre);
            node.setLeftType(1); //修改左节点类型为前驱节点
        }
        //当前节点为被后继的节点
        if (pre != null && pre.getRight() == null){
            //将它的前一个节点的右节点设为后继节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        // 将pre指向当前节点
        pre = node;
        //线索化右子树
        threadedNodes(node.getRight());
    }

    // 遍历线索化二叉树
    public void threadedShowList(){
        //存储当前遍历的节点
        Node1 node = root;
        while(node != null){
            // 当node的leftType ==1，则到叶子节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            // 打印叶子节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直打印
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            // 替换当前节点
            node = node.getRight();
        }
    }
}

class Node1{
    private final int no;
    private final String name;
    private Node1 left;  //左边的儿子
    private Node1 right; //右边的儿子
    //如果leftType ==0和rightType ==0表示指向的是左右子树
    //如果leftType ==1和rightType ==1表示指向的是前驱节点和后继节点
    private int leftType;
    private int rightType;

    public Node1(int no, String name){
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public Node1 getLeft() {
        return left;
    }

    public void setLeft(Node1 left) {
        this.left = left;
    }

    public Node1 getRight() {
        return right;
    }

    public void setRight(Node1 right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "Node1{" +
                "no=" + no +
                ", name='" + name+
                "'}";
    }
}
