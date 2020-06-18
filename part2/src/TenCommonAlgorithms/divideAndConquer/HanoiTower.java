package TenCommonAlgorithms.divideAndConquer;

/**
 *  汉诺塔： 通过分治算法完成
 *
 *  分治（分而治之）： 就是把一个复杂的问题分成两个或更多的相同或相似的子问题，
 *  不断的分解问题，直到最后子问题可以简单的直接求解，原问题的解即子问题的解的合并
 */
public class HanoiTower {
    private static int count;

    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
        System.out.println("一共操作的次数: "+count);
    }

    //汉诺塔移动方法           num:有多少圆盘   a,b,c表示柱子（放盘用）
    public static void hanoiTower(int num, char a, char b, char c){
        count++;
        //如果只有一个盘
        if (num == 1){
            System.out.println("第1个盘从 "+ a + "->"+ c);
        }else{
            //如果盘是两个或多个，总是将最下面的盘看作一个盘，
            // 将它上面的所有盘看作另一个盘
            //最上面的盘为第一个盘
            hanoiTower(num-1, a, c, b);  //将上面的所有盘从a移动到b,移动过程会使用到c
            //将最下面的盘移动到c
            System.out.println("第"+num+"个盘从 "+ a + "->" + c);
            //把b上所有盘往c上放，移动过程会使用到a
            hanoiTower(num-1, b, a, c);
        }
    }
}
