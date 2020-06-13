package linkedlist;

import java.util.Stack;

/**
 * 单链表
 * 1.创建一个head头节点，作用是表示单链表的头
 * 2.然后添加后面的节点，直接加入到链表尾
 * 3.通过一个临时变量遍历单链表
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1,"亚索","快乐风男");
        HeroNode heroNode2 = new HeroNode(2,"泰达米尔", "蛮王");
        HeroNode heroNode3 = new HeroNode(3,"易", "剑圣");
        SingleLinkedList sll = new SingleLinkedList();
        //添加元素
//        sll.add(heroNode3);
//        sll.add(heroNode1);
//        sll.add(heroNode2);
        //按照node排序添加元素
        sll.addByOrder(heroNode3);
        sll.addByOrder(heroNode1);
        sll.addByOrder(heroNode2);
        sll.addByOrder(heroNode1);

        //反转链表
        System.out.println("反转后的样子");
        reverseLinkedList(sll.getHeadNode());

        System.out.println("现在链表中的元素");
        sll.listShow();            //查看链表

        // 逆序打印链表
        System.out.println("逆序打印该链表");
        reversePrint(sll.getHeadNode());

        //修改某节点的信息
        HeroNode newHeroNode = new HeroNode(3,"劫","影流之主");
        sll.update(newHeroNode);
        System.out.println("修改某节点后");
        sll.listShow();

        //删除链表中的某元素
        sll.delete(3);
        System.out.println("删除某节点后");
        sll.listShow();

        //查看某节点
        System.out.println("查看2号元素");
        sll.peekNode(2);

        //求单链表中节点个数，不包含头节点
        int length = getLength(sll.getHeadNode());
        System.out.println();
        System.out.println("节点数有 "+length);

        //找单链表中的倒数第2个节点
        HeroNode lastIndexNode = lastIndexNode(sll.getHeadNode(),2);
        System.out.println("第2个节点:");
        System.out.println(lastIndexNode);
    }

    //获取到单链表中节点个数，不包含头节点
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        //从头节点的下一个节点开始
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //找单链表中的倒数第n个节点
    //参数lastIndex 表示倒数第n个节点
    public static HeroNode lastIndexNode(HeroNode head, int lastIndex){
        if (head.next == null){  //链表为空
            return null;
        }
        //第一次遍历获得链表的长度
        int size = getLength(head);
        //第二次遍历到(size-lastIndex)处即 倒数第n个节点
        //先校验
        if (lastIndex <=0  ||  lastIndex >size){
            return null;
        }
        //定义临时变量cur
        HeroNode cur = head.next;
        for (int i =0; i < size-lastIndex; i++){
            cur = cur.next;                       //找到倒数第n个节点
        }
        return cur;
    }

    //将单链表反转
    public static void reverseLinkedList(HeroNode head){
        //当链表为空，或链表只有一个节点，此时无需反转
        if (head.next == null || head.next.next ==null){
            return;
        }
        //定义一个临时变量cur,用于遍历原来的链表
        HeroNode cur = head.next;
        //定义此临时变量cur的下一个节点
        HeroNode next = null;
        //定义反转后的链表的头
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表, 每次遍历取出一个节点，并放到头节点之后(所有节点之前)的位置
        while(cur != null){
            next = cur.next;//保留住cur的下一个节点以及后面的节点
            cur.next = reverseHead.next; //将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;  //让cur成为新链表的最前端
            cur = next;  // 让cur后移
        }
        //将head.next 指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }

    //逆序打印链表的节点，不可修改链表结构
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode cur = head.next;
        //创建栈,利用栈的先进后出的特点，达到逆序打印的目的
        Stack<HeroNode> stack = new Stack<>();
        while (cur != null){
            stack.push(cur);   //入栈
            cur = cur.next;
        }
        while(stack.size() > 0){
            System.out.println(stack.pop());  //出栈
        }
    }
}

//定义容器来保存链表，即定义链表
class SingleLinkedList{
    //先定义一个头节点
    private final HeroNode headNode = new HeroNode(0,"","");

    //获取头节点
    public HeroNode getHeadNode(){
        return headNode;
    }

    //添加节点到单向链表
    public void add(HeroNode heroNode){
        //1.此方法不考虑编号顺序，找到当前链表的最后节点
        //2.将最后的节点的next指向新的节点
        HeroNode temp = headNode;   //如果直接使用headNode，headNode指向就被修改了
        while(true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //while退出后temp就指向链表的最后的元素
        temp.next = heroNode;
    }

    //添加节点到单向链表,以node属性编号
    public void addByOrder(HeroNode heroNode){
        //先使用临时变量temp寻找添加位置，找到的temp是位于新添加的节点的上一位
        HeroNode temp = headNode;
        boolean flag = false;  //用于判断node编号是否存在
        while (true){
            if (temp.next == null){   //表示要添加的节点在最后
                break;
            }
            if (temp.next.node > heroNode.node){ //通过前一个节点的下一个节点找到需要添加的位置
                break;
            }
            if (temp.next.node == heroNode.node){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            //编号存在
            System.out.println("编号"+heroNode.node+"存在，添加无效");
        }else{
            //当编号不存在于前面的所有项,或最后一项正好是该编号
            heroNode.next = temp.next;
            temp.next = heroNode;       //两条语句不能交换位置
        }
    }

    //修改节点的信息,根据node编号修改，不修改node
    public void update(HeroNode newHeroNode){
        //判断链表是否为空
        if (headNode.next == null){
            System.out.println("链表为空");
            return;
        }
        //定义一个临时变量temp
        HeroNode temp = headNode.next;
        boolean flag = false;
        while(true){
            if (temp == null){
                break;   //到达链表尾的后面
            }
            if (temp.node == newHeroNode.node){  //找到需要修改的项
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //当flag为true修改需要修改的项
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.println("未找到相应的项: "+ newHeroNode.node);
        }
    }

    //删除节点
    public void delete(int node){
        if (headNode.next == null){
            System.out.println("链表为空");
            return;
        }
        /*
        定义临时变量temp找到需要删除节点的上一个节点
         */
        HeroNode temp = headNode;
        boolean flag = false;
        while(true){
            if (temp.next == null){  //到达链表的尾部
                break;
            }
            if (temp.next.node == node){  //找到需要删除节点的上一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //flag为true找到需要删除的项
        if (flag){
            temp.next = temp.next.next;  //删除相应的项
        }else{
            System.out.println("未找到相应的节点：" +node);
        }
    }

    //查找某节点
    public void peekNode(int node){
        if (headNode.next == null){   //判断链表是否为空
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = headNode;
        while(true){
            if (temp == null){    //到达链表尾的后面
                return;
            }
            if (temp.node == node){    //找到需要查看的节点
                System.out.println(temp);  //显示该节点
                return;
            }
            temp = temp.next;
        }
    }

    //显示链表
    public void listShow(){
        //判断链表是否为空，不考虑头节点
        if (headNode.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = headNode.next;
        while(true){
            //判断是否为链表的最后，也要打印最后一个元素
            if(temp == null) {
                return;
            }
            //打印链表元素
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//定义每个HeroNode对象(即链表中的单个元素)的类
class HeroNode{
    public int node;   //节点数
    public String name;
    public String nickname;
    public HeroNode next;  //存放下一个节点的地址

    public HeroNode(int node, String name, String nickname){
        this.node = node;
        this.name = name;
        this.nickname = nickname;
    }

    //重写 toString()易于表示
    @Override
    public String toString() {
        return "HeroNode{" +
                "node=" + node +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
