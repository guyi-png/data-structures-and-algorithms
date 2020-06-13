package linkedlist;

/**
 * 约瑟夫问题
 */
public class JosephusTest {
    public static void main(String[] args) {
        CircleSingleLinkedList csll = new CircleSingleLinkedList();
        csll.addAllNode(10);
        csll.showAllNode();
        csll.countNode(1,2);
    }
}

class CircleSingleLinkedList{
    //创建一个first的节点
    private Node first = null;
    private int nums;
    //添加一个节点，用于构建一个环形的链表
    public void addAllNode(int nums){ //nums表示有多少节点
        this.nums = nums;  //记录节点总数
        if (nums <= 0){
            System.out.println("没有");
            return;
        }
        Node curNode = null; //辅助指针
        //使用for创建一个环形链表
        for (int i = 1; i <= nums; i++){
            //通过编号创建节点
            Node node = new Node(i);
            //如果是第一个节点
            if (i ==1){
                first = node;
                first.setNext(first);  //只有一个节点，自己指向自己，构成环状
                curNode = first;   //辅助指针指向第一个节点
            }else{
                curNode.setNext(node);  //让当前的节点的下一个指向新加入的节点
                node.setNext(first);    //让新加入的节点指向第一个节点，构成环状
                curNode = node;    //让当前的节点指向新的节点
            }
        }
    }

    //遍历当前的环形链表
    public void showAllNode(){
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        //使用辅助指针遍历
        Node curNode = first;
        while (curNode.getNext() != first){
            System.out.println("当前的编号："+curNode.getNo());
            curNode = curNode.getNext();  //向后移
        }
        System.out.println("最后的节点编号：" + curNode.getNo());
    }

    //通过用户的键入，得到从什么位置开始，计数为多少
    public void countNode(int startNo,int countNum){
        //对用户输入校验
        if (first ==null || startNo <1 || startNo >nums){
            System.out.println("没得玩");
            return;
        }
        System.out.println("开始取出节点");
        //创建辅助指针,用于拿出节点
        Node helper = first;
        while(helper.getNext() != first){   //循环使helper指向最后一个元素
            helper = helper.getNext();
        }
        //使辅助指针和first移动到开始的位置
        //(因为计数是从节点自己开始的，辅助指针在开始的位置的前面一个节点)
        for (int i =1; i < startNo; i++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //计数开始,找到需要取出的节点
        while (first.getNext() != first){
            for (int i =1; i < countNum; i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //取出节点
            System.out.println(first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        //最后一个元素
        System.out.println(first.getNo());
    }
}

class Node {
    private final int no;  //编号
    private Node next; //指向下一个节点

    public Node(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no;
    }
}
