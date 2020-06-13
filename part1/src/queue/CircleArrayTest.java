package queue;

import java.util.Scanner;

/**
 *   ArrayQueueTest 的改进，思路：
 *  1.front 指向队列的第一个元素，即arr[front]就是队列的第一个元素
 *  2.rear 指向队列的最后一个元素的后一位，即所有元素+rear项=maxSize,rear项做约定
 *  3.当满足条件 (rear + 1) % maxSize = front ,则队列为满
 *  4.当 rear = front，队列为空
 *  5.队列中有效数据的个数 (rear + maxSize - front) % maxSize
 *  6.通过取模操作实现队列的循环
 */
public class CircleArrayTest {
    public static void main(String[] args) {
        CircleArray ca = new CircleArray(4);
        char key; //接收用户的输入
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
                    ca.showQueue();
                    System.out.println();
                    break;
                case 'e':
                    scanner.close();
                    return;
                case 'a':
                    ca.addQueue((int)(Math.random() * 10+1));
                    System.out.println();
                    break;
                case 'g':
                    try{
                        int result = ca.getQueue();
                        System.out.println(result);
                    }catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }finally{
                        System.out.println();
                    }
                    break;
                case 'p':
                    try{
                        System.out.println(ca.peekHead());
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

class CircleArray{
    private int maxSize;  //表示数组（队列）的最大容量
    private int front;   //队列的第一个元素
    private int rear;    //队列的最后一个元素的后一位
    private int[] arr;   //该数组存放数据，模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
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
        arr[rear] = ele;  //添加元素到队列
        rear = (rear + 1) % maxSize;
        System.out.println("添加成功");
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列为空，无法取出数据");
        }
        /*
        1.front是第一个元素，先把front对应的值保存为临时变量
        2.将front后移，考虑取模
        3.将临时保存的变量返回
         */
        int value = arr[front];
        front = (front+1) % maxSize;
        return value;
    }

    //显示队列的全部数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("没有就不要玩");
            return;
        }
        //从front开始遍历
        for (int i =front; i < front+(rear+maxSize-front)%maxSize; i++){
            System.out.println("arr["+i%maxSize+"] = "+arr[i%maxSize]);
        }
    }

    //显示队列的头数据
    public int peekHead(){
        if (isEmpty()){
            throw new RuntimeException("没有就不要玩");
        }
        return arr[front];
    }
}
