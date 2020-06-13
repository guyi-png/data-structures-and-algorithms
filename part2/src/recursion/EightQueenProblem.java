package recursion;

/**
 * 八皇后问题
 */
public class EightQueenProblem {
    //定义一个max表示最多8个皇后
    static int max = 8;
    //定义数组保存皇后放置的位置结果
    ////该 数组索引+1 为第几个皇后（或第几行），数组元素的值+1 为皇后放置的第几列
    static int[] arr = new int[max];
    //记录有多少种放置方式
    static int count = 0;
    //统计递归次数
    static int RecurTime;
    //统计冲突次数
    static int time;

    public static void main(String[] args) {
        check(0);
        System.out.println("递归次数为"+RecurTime);
        System.out.println("发生冲突的次数为"+time);
        System.out.println("一共有"+count+"种放置方式");
    }

    //放置皇后的方法, 递归回溯
    public static void check(int n){   //n表示第n+1个皇后
        RecurTime++; //次数
        if (n == max){  //当n=8时，就是第九个皇后
            print();
            count++;
            return;
        }
        //放置皇后，并判断是否冲突
        for (int i= 0; i < 8; i++){
            //把第n个皇后放到该行（第n行）的第一列
            arr[n] = i;
            //判断当放到第i列时，是否冲突
            if (judge(n)){
                //不冲突，接下来放下一个皇后，并递归
                check(n+1);
            }
            //如果上述条件不满足，继续循环放下一列
        }
    }

    //检测皇后之间是否会攻击（冲突）
    public static boolean judge(int n){    //n表示第n+1个皇后
        time++;
        for (int i= 0; i < n; i++){  //遍历检测前面皇后是否与第n+1个皇后冲突
            // 判断条件  判断是否在同一列  以及  是否在同一斜线上（ (行数2 -行数1) == (列数2 - 列数1)  以此条件判断）
            if (arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n]-arr[i])){
                return false;
            }
        }
        return true;
    }

    //打印皇后放置的位置
    public static void print(){
        for (int value : arr) {
            System.out.print(value + 1 + "\t");
        }
        System.out.println();
    }
}
