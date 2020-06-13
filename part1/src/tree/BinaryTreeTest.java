package tree;

/**
 *  二叉树
 */
public class BinaryTreeTest {
    //树能提高数据存储，读取的效率
    // 二叉排序树，既可以保证数据的检索的速度，同时也可以保证数据的插入，删除，修改的速度
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node node1 = new Node(1, "一");
        Node node2 = new Node(2, "二");
        Node node3 = new Node(3, "三");
        Node node4 = new Node(4, "四");
        Node node5 = new Node(5, "五");
        binaryTree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node4);
        node4.setLeft(node3);
        node4.setRight(node5);

        binaryTree.delNode(3);
        binaryTree.preOrder();
    }
}

//定义二叉树
class BinaryTree{
    private Node root;

    public void setRoot(Node root){
        this.root = root;
    }

    //删除节点(删除指定节点以及它儿子)
    public void delNode(int no){
        if (root != null){
            if (root.getNo() == no){
                root =null;
                return;
            }
            root.delNode(no);
        }else{
            System.out.println("树为空");
        }
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空");
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空");
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空");
        }
    }

    //前序遍历查找
    public Node preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }

    //中序遍历查找
    public Node infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }

    //后序遍历查找
    public Node postOrderSearch(int no){
        if (root != null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }
}

// 定义树节点
class Node{
    private final int no;
    private final String name;
    private Node left;  //左边的儿子
    private Node right; //右边的儿子

    public Node(int no, String name){
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name+
                "'}";
    }

    //递归删除节点
    public void delNode(int no){
        // 当前节点的左儿子不为空，并且左儿子是需要删除的节点
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        // 当前节点的右儿子不为空，并且右儿子是需要删除的节点
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        // 没有找到要删除的，就继续向左递归
        if (this.left != null){
            this.left.delNode(no);
        }
        //没有找到要删除的，就继续向右递归
        if (this.right != null){
            this.right.delNode(no);
        }
    }

    //前序遍历
    public void preOrder(){
        //输出父节点
        System.out.println(this);
        //递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if (this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        //递归向左子树后序遍历
        if (this.left != null){
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if (this.right != null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    //前序遍历查找
    public Node preOrderSearch(int no){
        if (this.no == no){  //找到了
            return this;
        }
        Node resNode = null;
        if (this.left != null){     //当左节点不为空，继续递归前序查找
             resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){    //当右节点不为空，继续递归前序查找
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public Node infixOrderSearch(int no){
        Node resNode = null;
        if (this.left != null){     //当左节点不为空，继续递归中序查找
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){  //找到了
            return this;
        }
        if (this.right != null){    //当右节点不为空，继续递归中序查找
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public Node postOrderSearch(int no){
        Node resNode = null;
        if (this.left != null){     //当左节点不为空，继续递归后序查找
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){    //当右节点不为空，继续递归后序查找
            resNode = this.right.postOrderSearch(no);
        }
        if (this.no == no){  //找到了
            return this;
        }
        return resNode;
    }
}
