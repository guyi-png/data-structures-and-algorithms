package tree;

/**
 *  平衡二叉树: AVL树         '_' 懵〇
 */
public class AVLTreeTest {
    public static void main(String[] args) {
        int[] arr = {4,3,6,5,7,8};//{10,12,8,9,7,6};
        AVLTree avlTree = new AVLTree();
        for (int value : arr){
            avlTree.add(new Node1(value));
        }
        avlTree.infixOrder();

        System.out.println(avlTree.root.height()); //树的高度
        System.out.println(avlTree.root.leftHeight()); //左子树的高度
        System.out.println(avlTree.root.rightHeight()); //右子树的高度
        System.out.println(avlTree.root);
    }

    private static class AVLTree{
        private Node1 root;

        //添加
        public void add(Node1 node){
            if (root == null){
                root = node;
            }else{
                root.add(node);
            }
        }

        //中序遍历(从小到大)
        public void infixOrder(){
            if (root != null){
                root.infixOrder();
            }else{
                System.out.println("没有就不要玩");
            }
        }

        //查找节点
        public Node1 search (int value){
            if (root != null){
                return root.search(value);
            }else{
                return null;
            }
        }

        //查找指定节点的父节点
        public Node1 searchParent(int value){
            if (root != null){
                return root.searchParent(value);
            }else{
                return null;
            }
        }

        //删除节点
        public void delNode(int value){
            if (root == null){
                return;
            }else{
                Node1 target = search(value);    //找到要删除的节点
                Node1 tarParent = searchParent(value); //找到要删除的节点的父节点
                if (target == null){
                    System.out.println("没有这个: "+ value);
                    return;          //没有找到要删除的节点
                }
                //如果只有一个节点
                if (root.left == null && root.right == null){
                    root = null;
                    return;
                }
                //如果要删除的节点是叶子节点
                if (target.left == null && target.right == null){
                    // 删除的叶子节点是其父节点的左子节点
                    if (tarParent.left != null && tarParent.left.value == value){
                        tarParent.left = null;
                    }else if (tarParent.right != null && tarParent.right.value == value){
                        // 删除的叶子节点是其父节点的右节点
                        tarParent.right = null;
                    }
                }else if(target.left != null && target.right != null) {        //如果删除的节点有两个子节点
                    //从右子树中找value最小的
                    Node1 minNode = target.right;
                    while (minNode.left != null){
                        minNode = minNode.left;
                    }
                    int minVal = minNode.value;
                    //找到就将该节点删除
                    delNode(minNode.value);
                    //将要删除节点的右子树中value最小的节点的值 赋给要删除的节点 （相当于删除了要删除的节点）
                    target.value = minVal;

                }else{      //如果删除的节点只有一个子节点
                    //当只剩最后两个时，要删除根节点，需要避免空指针
                    if (tarParent == null){
                        root = (target.left == null) ? target.right : target.left;
                        return;
                    }
                    //如果要删除的节点有左子节点
                    if (target.left != null){
                        // 删除的节点是其父节点的左子节点
                        if (tarParent.left != null && tarParent.left.value == value){
                            tarParent.left = target.left;
                        }
                        // 删除的节点是其父节点的右子节点
                        if (tarParent.right != null && tarParent.right.value == value){
                            tarParent.right = target.left;
                        }
                    } else{     //如果要删除的节点有右子节点
                        // 删除的节点是其父节点的左子节点
                        if (tarParent.left != null && tarParent.left.value == value){
                            tarParent.left = target.right;
                        }
                        // 删除的节点是其父节点的右子节点
                        if (tarParent.right != null && tarParent.right.value == value){
                            tarParent.right = target.right;
                        }
                    }
                }
            }
        }
    }



    private static class Node1{
        int value;
        Node1 left;
        Node1 right;

        public Node1(int value){
            this.value = value;
        }

        //返回以当前节点为根节点的树的高度
        public int height(){
            return Math.max(left == null ? 0:left.height() , right == null ? 0:right.height()) + 1;
        }

        // 返回左子树的高度
        public int leftHeight(){
            if (left == null){
                return 0;
            }
            return left.height();
        }

        // 返回右子树的高度
        public int rightHeight(){
            if (right == null){
                return 0;
            }
            return right.height();
        }

        //左旋转
        public void leftRotate(){
            //创建新节点,value为根节点的value
            Node1 newNode = new Node1(value);
            //把新节点的左节点设置为当前节点的左子节点
            newNode.left = left;
            //把新节点的右节点设置为当前节点的右子节点的左子节点
            newNode.right = right.left;
            //把当前节点的value替换成右子节点的value
            value = right.value;
            //把当前节点的右子节点的指向换位当前节点的右子节点的右子节点
            right = right.right;
            //把当前节点的左子节点设置为新的节点
            left = newNode;
        }

        //右旋转
        public void rightRotate(){
            //创建新节点,value为根节点的value
            Node1 newNode = new Node1(value);
            //把新节点的右节点设置为当前节点的右子节点
            newNode.right = right;
            //把新节点的左节点设置为当前节点的左子节点的右子节点
            newNode.left = left.right;
            //把当前节点的value替换成左子节点的value
            value = left.value;
            //把当前节点的左子节点的指向换位当前节点的左子节点的左子节点
            left = left.left;
            //把当前节点的右子节点设置为新的节点
            right = newNode;
        }

        //添加节点的方法
        public void add(Node1 node){
            if (node == null){
                return;
            }
            //判断传入的node的value和当前树的根节点的value的大小
            if (node.value < this.value){ //小于根节点，向左添加
                if (this.left == null){  //当前节点的左子节点为null
                    this.left = node;
                }else{
                    this.left.add(node); //递归向左添加
                }
            }else{   //大于或等于根节点，向右添加
                if (this.right == null){
                    this.right = node;
                }else{
                    this.right.add(node);
                }
            }
            //双旋转
            //当添加完一个节点后， 如果左子树的高度 - 右子树的高度差的大于1(右子树短了)， 则需要右旋转
            if (leftHeight() - rightHeight() > 1){
                // 如果添加的节点的父节点的右子树高度大于添加的节点的父节点的左子树高度
                if (left != null && left.rightHeight() > left.leftHeight()){
                    // 先对添加的节点的父节点进行左旋转
                    left.leftRotate();
                }
                // 再对添加的节点的父节点的父节点进行右旋转
                rightRotate();
                return; //避免混乱
            }

            //双旋转
            //当添加完一个节点后， 如果右子树的高度 - 左子树的高度差的大于1(左子树短了)， 则需要左旋转
            if (rightHeight() - leftHeight() > 1){
                if (right != null && right.leftHeight() > right.rightHeight()){
                    right.rightRotate();
                }
                leftRotate();
            }
        }

        //查找节点
        public Node1 search(int value){
            if (value == this.value){
                return this;        //找到了
            }else if (value < this.value){
                if (this.left == null){     //没有找到
                    return null;
                }
                return this.left.search(value);  //向左找
            }else {
                if (this.right == null){   //没有找到
                    return null;
                }
                return this.right.search(value);  //向右找
            }
        }

        //查找指定的节点的爸爸
        public Node1 searchParent(int value){
            if (  (this.left != null && this.left.value == value)       //当前节点的子节点是要找的节点
                    || (this.right != null && this.right.value == value)  ) {
                return this;
            }else{
                if (value < this.value && this.left != null){
                    return this.left.searchParent(value);       //向左子树递归查找
                }else if (value >= this.value && this.right != null){
                    return this.right.searchParent(value);      //向右子树递归查找
                }else{
                    return null;  //没有找到父节点
                }
            }
        }

        //中序遍历(从小到大)
        public void infixOrder(){
            if (this.left != null){
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null){
                this.right.infixOrder();
            }
        }

        @Override
        public String toString() {
            return "Node1{" +
                    "value=" + value +
                    '}';
        }
    }
}
