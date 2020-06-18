package TenCommonAlgorithms.primAlgorithm;

import java.util.Arrays;

/**
 * 普利姆算法  求最小生成树（MST）解决修路问题
 *
 * 最小生成树： 给定一个带权的无向连通图，选取一棵生成树，
 * 使树上的所有边的权的总和最小
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexes = data.length;
        int[][] matrix = { //999表示两个顶点不能连通
                {999, 7, 3, 999, 999, 999, 1},
                {5, 999, 999, 9, 999, 999, 3},
                {7, 999, 999, 999, 8, 999, 999},
                {999, 9, 999, 999, 999, 4, 999},
                {999, 999, 8, 999, 999, 9, 4},
                {999, 999, 999, 3, 5, 999, 6},
                {2, 5, 999, 999, 4, 7, 999, 999}
        };

        MGraph mGraph = new MGraph(vertexes);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, vertexes, data, matrix);
        minTree.showGraph(mGraph);

        minTree.prim(mGraph,0);
    }
}

//创建最小生成树
class MinTree{
    public void createGraph(MGraph graph, int vertexes, char[] data, int[][] matrix){
        for (int i=0; i < vertexes; i++){
            graph.data[i] = data[i];
            System.arraycopy(matrix[i], 0, graph.matrix[i], 0, vertexes);
        }
    }

    //显示图
    public void showGraph(MGraph graph){
        for (int[] link : graph.matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    //用普利姆算法求最小生成树
    /**
     * @param graph 要操作的图
     * @param v 从图中的哪个顶点(索引)开始
     */
    public void prim(MGraph graph, int v){
        int[] visited = new int[graph.vertexes];  //表示有没有被访问过的，0未访问1访问了
//        for (int i =0; i < graph.vertexes; i++){ //初始化
//            visited[i] = 0;
//        }
        visited[v] = 1;  //将当前的顶点设为被访问过
        int h1 = -1;
        int h2 = -1;
        int minWeight = 999;   //表示边的权值最小
        for (int i =1; i <graph.vertexes; i++){     //有n个顶点，求最小生成树时，其边数n-1
            // 下面逻辑：每次的子图，找其中的顶点与哪个顶点最近
            for (int j =0; j < graph.vertexes; j++){    // j顶点表示被访问的顶点
                for (int k =0; k < graph.vertexes; k++){    // k顶点表示未访问的顶点
                    if (visited[j] == 1 && visited[k] == 0 && graph.matrix[j][k] < minWeight){
                        // 替换最小权重
                        minWeight = graph.matrix[j][k];
                        h1 = j;
                        h2 = k;  //记录边权重最小的两个顶点
                    }
                }
            }
            System.out.println("边<"+ graph.data[h1]+ "," + graph.data[h2]+"> 权值:"+minWeight);
            visited[h2] = 1; //把找到的（与访问过的顶点最近的）顶点设为被访问过
            minWeight = 999;   //重置最小权重
        }
    }
}

class MGraph{
    int vertexes;  //图的顶点数
    char[] data;    //存放所有顶点的数据
    int[][] matrix; //邻接矩阵

    public MGraph(int vertexes){
        this.vertexes = vertexes;
        data = new char[vertexes];
        matrix = new int[vertexes][vertexes];
    }
}
