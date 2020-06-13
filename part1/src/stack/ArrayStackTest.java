package stack;

import java.util.Scanner;

/**
 * stack 特点：
 *  先入后出
 *  栈是限制线性表中元素的插入和删除只能在线性表的同一端进行的一种特殊线性表，
 *  也许插入和删除的一端称为栈顶，另一端称为栈底
 *  出栈（pop） 入栈（push）
 */
public class ArrayStackTest {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("(s): 查看栈");
            System.out.println("(e): 退出程序");
            System.out.println("(a): 入栈");
            System.out.println("(p): 出栈");
            System.out.println("请输入您的选择");
            key = scanner.next();
            if (key.length() >= 2){
                continue;
            }
            switch (key){
                case "s":
                    stack.showList();
                    break;
                case "e":
                    scanner.close();
                    System.out.println("程序退出");
                    System.exit(0);
                case "a":
                    System.out.println("请输入需要添加的数");
                    int ele = scanner.nextInt();
                    stack.push(ele);
                    break;
                case "p":
                    System.out.println("出栈的是："+stack.pop());
                    break;
            }
        }
    }
}

//用数组模拟栈
class ArrayStack{
    private final int maxSize;  //栈的最大容量
    private final int[] stack;  // 将数据放入该数组
    private int top = -1;      //表示栈顶,一开始栈为空，top初始化为-1

    //初始化栈
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈是否满
    public boolean isFull(){
        return top == maxSize -1;
    }

    //判断栈是否为空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int ele){
        if (isFull()){
            System.out.println("栈满，不可添加");
            return;
        }
        //top上移，添加元素
        stack[++top] = ele;
    }

    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，无数据可取");
        }
        //top下移，取出元素
        return stack[top--];
    }

    //查看栈顶
    public int peekTop(){
        if (isEmpty()){
            throw new RuntimeException("没有就不要看呐");
        }
        return stack[top];  //仅取出元素，不出栈
    }

    //遍历栈
    public void showList(){
        if (isEmpty()){
            System.out.println("没有数据");
            return;
        }
        //从栈顶开始遍历
        for (int i = top ; i >= 0; i--){
            System.out.println("stack["+i+"] = "+stack[i]);
        }
    }
}
