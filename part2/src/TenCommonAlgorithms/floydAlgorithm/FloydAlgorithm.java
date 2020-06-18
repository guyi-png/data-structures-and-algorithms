package TenCommonAlgorithms.floydAlgorithm;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 * 求各顶点到其他各顶点的最小路程
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 9999;
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        Graph graph = new Graph(vertexes, matrix);
        graph.floyd();
        graph.show();
    }
}

class Graph{
    private char[] vertexes;   //存放顶点的数组
    private int[][] dis;        //保存从各个顶点出发到其他顶点的距离
    private int[][] pre;        //保存到达目标顶点的前驱顶点

    public Graph(char[] vertexes, int[][] matrix){
        this.vertexes = vertexes;
        this.dis = matrix;
        pre = new int[vertexes.length][vertexes.length];
        // 对pre进行初始化，存放前驱顶点的索引
        for (int i =0; i < vertexes.length; i++){
            Arrays.fill(pre[i], i);
        }
    }

    public void show (){
        for (int i =0; i < pre.length; i++){
            for (int j =0; j < pre.length; j++){
                System.out.print(vertexes[pre[i][j]]+"\t");
            }
            System.out.println();
            for (int j =0; j < pre.length; j++){
                System.out.print(vertexes[i]+"到"+vertexes[j]+"的最小路程为"+dis[i][j]+"   ");
            }
            System.out.println();
        }
    }

    // 弗洛伊德算法
    public void floyd(){
        int len;
        for (int i =0; i < vertexes.length; i++){   // i为中间顶点的索引
            for (int j =0; j < vertexes.length; j++){  // j出发顶点的索引
                for (int k =0; k < vertexes.length; k++){ // k终点的索引
                    //判断从j顶点出发，经过i顶点到达k顶点的路程
                    //与从j顶点直接大k顶点的路程哪个小，就选小的
                    len = dis[j][i] + dis[i][k];
                    if (len < dis[j][k]){   //发现通过中间顶点更短
                        dis[j][k] = len;  //更新距离
                        pre[j][k] = pre[i][k];  //更新前驱顶点
                    }
                }
            }
        }
    }
}
