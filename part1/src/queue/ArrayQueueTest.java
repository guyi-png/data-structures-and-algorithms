package queue;

import java.util.Scanner;

/**
 *有 bug
 */
public class ArrayQueueTest {
    public static void main(String[] args) {
        ArrayQueue aq = new ArrayQueue(3);
        char key = ' '; //接收用户的输入
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("s: 显示队列");
            System.out.println("e: 退出");
            System.out.println("a: 添加数据到队列");
            System.out.println("g: 从队列获得数据");
            System.out.println("p: 查看队列的头数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    aq.showQueue();
                    System.out.println();
                    break;
                case 'e':
                    scanner.close();
                    return;
                case 'a':
                    aq.addQueue((int)(Math.random() * 10+1));
                    System.out.println();
                    break;
                case 'g':
                    try{
                        int result = aq.getQueue();
                        System.out.println(result);
                    }catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }finally{
                        System.out.println();
                    }
                    break;
                case 'p':
                    try{
                        System.out.println(aq.peekHead());
                    }catch(RuntimeException e){
                        System.out.println( e.getMessage());
                    }finally{
                        System.out.println();
                    }
                    break;
            }
        }
    }
}

class ArrayQueue{
    private int maxSize;  //表示数组（队列）的最大容量
    private int front;   //队列的头
    private int rear;    //队列的尾
    private int[] arr;   //该数组存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[arrMaxSize];
        front = -1; //队列为空，指向队列头的前一个位置
        rear = -1;  //队列为空，指向队列尾且包含尾
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize -1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int ele){
        //先判断队列是否为满
            if (isFull()){
                System.out.println("队列为满，不能添加数据");
                return;
            }
        arr[++rear] = ele;  //让rear后移,并添加元素到队列
        System.out.println("添加成功");
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否为空
            if (isEmpty()){
                throw new RuntimeException("队列为空，无法取出数据");
            }
        return arr[++front];   //让front后移,并从队首移出元素
    }

    //显示队列的全部数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("没有就不要玩");
            return;
        }
        //遍历展示
        for (int i =0; i < arr.length; i++){
            System.out.println("arr[" + i + "] = " + arr[i]);
        }
    }

    //显示队列的头数据
    public int peekHead(){
           if (isEmpty()){
                throw new RuntimeException("没有就不要玩");
           }
       return arr[front+1];
    }
}
