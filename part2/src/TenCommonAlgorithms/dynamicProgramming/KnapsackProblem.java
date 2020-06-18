package TenCommonAlgorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * 背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {    1 ,   4 ,   3};  //物品占的容量
        int[] val = {1500, 3000, 2000}; //与上面物品占的容量对应的每个物品的价值
        int m = 4;  //背包的容量
        int n = w.length;  //n为物品的个数

        //创建二维数组表示表
        // v[i][j] 表示前i个物品中能装入容量为j的背包中的最大价值
        int[][]  v = new int[n+1][m+1];
        int[][] path = new int[n+1][m+1];//记录放入物品的情况
        //初始化第一行和第一列
        Arrays.fill(v[0], 0);
        for (int i =0; i < v.length; i++){
            v[i][0] = 0;
        }

        //开始动态规划
        for (int i =1; i < v.length; i++){  //i=0就是第一行，没有物品就不搞了
            for (int j =1; j <v[0].length; j++){ //j=0就是第一列，背包没有容量就不搞了
                //  注意 i，j都是从1开始的，而索引是从0开始
                if (w[i-1] > j){    //如果物品占的容量大于背包容量
                    v[i][j] = v[i-1][j];
                }else{
                    //如果物品占的容量小于背包容量，考虑放价值大的物品，
                    // 如果放了物品还有容量且容量够放其他物品，就接着放
                    // v[i][j] = Math.max( v[i-1][j], val[i-1]+ v[i-1][j - w[i-1]]);
                    if (v[i-1][j] > val[i-1]+ v[i-1][j - w[i-1]]){
                        v[i][j] = v[i-1][j];
                    }else{
                        v[i][j] = val[i-1]+ v[i-1][j - w[i-1]];
                        path[i][j] = 1;     //记录当前放入的物品
                    }
                }
            }
        }

        //打印规划表
        for (int[] arr : v){
            for (int value : arr){
                System.out.print(value+"\t");
            }
            System.out.println();
        }

        //找到放入策略
        int i = path.length-1;
        int j = path[0].length-1;
        while (i >0 && j >0){
            if (path[i][j] == 1){
                System.out.println("第"+i+"物品放入背包");
                j -= w[i-1];
            }
            i--;
        }
    }
}
