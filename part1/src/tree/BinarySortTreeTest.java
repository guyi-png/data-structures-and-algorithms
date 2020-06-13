package tree;

/**
 * 二叉排序树( binary sort tree ,BST)
 */
public class BinarySortTreeTest {
    public static void main(String[] args) {
        int[] arr = {45,76,2,75,8,11,128,1};
        BinarySortTree bst = new BinarySortTree();
        for (int value : arr) {
            bst.add(new Node(value));
        }
        System.out.println( "原来");
        bst.infixOrder();
        System.out.println("*******");
        bst.delNode(45);
        bst.delNode(76);
        bst.delNode(2);
        bst.delNode(75);
        bst.delNode(8);
        bst.delNode(11);
        bst.delNode(128);
        bst.delNode(1);

        bst.infixOrder();
    }

    //二叉排序树
    private static class BinarySortTree{
        private Node root;

        //添加
        public void add(Node node){
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
        public Node search (int value){
            if (root != null){
                return root.search(value);
            }else{
                return null;
            }
        }

        //查找指定节点的父节点
        public Node searchParent(int value){
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
                Node target = search(value);    //找到要删除的节点
                Node tarParent = searchParent(value); //找到要删除的节点的父节点
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
//                    //从左子树中找value最大的
//                    Node maxNode = target.left;
//                    while (maxNode.right != null){
//                        maxNode = maxNode.right;
//                    }
//                    int maxVal = maxNode.value;
//                    delNode(maxNode.value);

                    //从右子树中找value最小的
                    Node minNode = target.right;
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

    //树的节点
    private static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }

        //添加节点的方法
        public void add(Node node){
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
        }

        //查找节点
        public Node search(int value){
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
        public Node searchParent(int value){
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
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
