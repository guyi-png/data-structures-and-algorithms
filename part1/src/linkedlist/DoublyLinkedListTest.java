package linkedlist;

/**
 * 双向链表
 */
public class DoublyLinkedListTest {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1,"亚索","快乐风男");
        HeroNode2 heroNode2 = new HeroNode2(2,"泰达米尔", "蛮王");
        HeroNode2 heroNode3 = new HeroNode2(3,"易", "剑圣");
        DoubleLinkedList dll = new DoubleLinkedList();
        //添加
//        dll.add(heroNode3);
//        dll.add(heroNode1);
//        dll.add(heroNode2);
        dll.addByOrder(heroNode3);
        dll.addByOrder(heroNode1);
        dll.addByOrder(heroNode2);
        dll.listShow();


        //修改
        HeroNode2 newHeroNode = new HeroNode2(3,"劫","影流之主");
        dll.update(newHeroNode);
        System.out.println("修改某节点后");
        dll.listShow();

        //删除
        dll.delete(3);
        System.out.println("删除某节点后");
        dll.listShow();

        //查看2号节点
        System.out.println("查看2号节点:");
        dll.peekNode(2);
    }
}

//定义容器来保存链表，即定义链表
class DoubleLinkedList{
    //先定义一个头节点
    private final HeroNode2 headNode = new HeroNode2(0,"","");

    //获取头节点
    public HeroNode2 getHeadNode(){
        return headNode;
    }

    //添加节点到单向链表
    public void add(HeroNode2 heroNode){
        //1.此方法不考虑编号顺序，找到当前链表的最后节点
        //2.将最后的节点的next指向新的节点
        HeroNode2 temp = headNode;   //如果直接使用headNode，headNode指向就被修改了
        while(true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //while退出后temp就指向链表的最后的元素
        //形成双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    ///有bug
    //添加节点到单向链表,以node属性编号
    public void addByOrder(HeroNode2 heroNode){
        //先使用临时变量temp寻找添加位置，找到的temp是新添加的节点
        HeroNode2 temp = headNode;
        boolean flag = false;  //用于判断node编号是否存在
        while (temp.next != null){
            if (temp.next.node > heroNode.node){ //找到需要添加的位置
                break;
            }
            if (temp.next.node == heroNode.node){  //需要添加的节点存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            //编号存在
            System.out.println("编号"+heroNode.node+"存在，添加无效");
        }else{
            heroNode.next = temp.next;
            if (temp.next != null)   //最后的节点后面没有节点了
                temp.next.pre = heroNode;
            temp.next = heroNode;
            heroNode.pre = temp;
        }
    }

    //修改节点的信息,根据node编号修改，不修改node
    public void update(HeroNode2 newHeroNode){
        //判断链表是否为空
        if (headNode.next == null){
            System.out.println("链表为空");
            return;
        }
        //定义一个临时变量temp
        HeroNode2 temp = headNode.next;
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
        if (headNode.next ==null){
            System.out.println("链表为空");
            return;
        }
        /*
        定义临时变量temp找到需要删除的节点
         */
        HeroNode2 temp = headNode;
        boolean flag = false;
        while(true){
            if (temp == null){  //到达链表的尾部的后面
                break;
            }
            if (temp.node == node){  //找到需要删除节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //flag为true找到需要删除的项
        if (flag){
            //删除相应的项
            temp.pre.next = temp.next;
            if (temp.next != null) {   //当temp是最后一个节点时，后面就没有节点了
                temp.next.pre = temp.pre;
            }
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
        HeroNode2 temp = headNode;
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
        HeroNode2 temp = headNode.next;
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

//定义每个HeroNode2对象(即链表中的单个元素)的类
class HeroNode2 {
    public int node;   //节点数
    public String name;
    public String nickname;
    public HeroNode2 next;  //存放下一个节点的地址
    public HeroNode2 pre;   //存放上一个节点的地址

    public HeroNode2(int node, String name, String nickname){
        this.node = node;
        this.name = name;
        this.nickname = nickname;
    }

    //重写 toString()易于表示
    @Override
    public String toString() {
        return "HeroNode2{" +
                "node=" + node +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
