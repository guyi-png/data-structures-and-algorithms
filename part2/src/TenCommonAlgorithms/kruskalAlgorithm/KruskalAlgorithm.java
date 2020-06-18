package TenCommonAlgorithms.kruskalAlgorithm;

import java.util.Arrays;

/**
 *  克鲁斯卡尔算法，求最小生成树（MST）解决公交车遍历所有地点问题
 *
 *  回路：加入的边的两个顶点不能都指向同一个终点，否则就构成了回路
 */
public class KruskalAlgorithm {
    private int edgeNum; //边数
    private char[] data; //存放所有顶点的数据
    private int[][] matrix; //图的邻接矩阵
    private static final int INF = Integer.MAX_VALUE;   //INF表示两顶点不能连通

    private class EData{  //它的对象表示一条边
        char start;  //边的一头
        char end; //边的另一头
        int weight; //权值

        public EData(char start, char end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EData{" +
                    "<" + start +
                    "," + end +
                    "> =" + weight +
                    '}';
        }
    }

    public KruskalAlgorithm(char[] data, int[][] matrix){
        this.data = data;
        this.matrix = matrix;
        for (int[] link : matrix){
            for (int value : link){
                if (value != INF && value != 0){
                    edgeNum++;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0,    12,   INF,  INF,  INF,  16,   14},
                {12,   0,    10,   INF,  INF,   7,   INF},
                {INF,  10,   0,    3,    5,     6,   INF},
                {INF,  INF,  3,    0,    4,     INF, INF},
                {INF,  INF,  5,    4,    0,     2,    8},
                {16,   7,    6,   INF,   2,     0,    9},
                {14,   INF,  INF,  INF,   8,    9,    0}   //自己跟自己就用0表示
        };

        KruskalAlgorithm ka = new KruskalAlgorithm(data, matrix);
        ka.print();
        EData[] edges = ka.getEdges();
        System.out.println("未排序:"+ Arrays.toString(edges));
        ka.sortEdges(edges,0,edges.length-1);
        System.out.println("排序后:"+ Arrays.toString(edges));
        System.out.println("*********************************************");
        ka.kruskal();
    }

    private void kruskal(){
        int index= 0;   //表示最后结果数组的索引
        int[] ends = new int[edgeNum];  //保存已有最小生成树中的每个顶点在最小生成树中的终点
        EData[] results = new EData[edgeNum];  //保存最后的最小生成树
        // 获得所有边
        EData[] edges = getEdges();
        // 将边进行排序
        sortEdges(edges,0,edges.length-1);
        // 遍历edges，将边添加到最小生成树中，并判断是否构成回路
        for (int i=0; i< edgeNum; i++){
            // 获取第i+1条边的一个顶点
            int p1 = getPosition(edges[i].start);
            // 获取第i+1条边的另一个顶点
            int p2 = getPosition(edges[i].end);
            //获取p1顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);   //ends[p1]为0则返回p1
            //获取p2顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            // m != n 就是没有构成回路
            if (m != n){
                ends[m] = n;  //设置m的终点为n
                results[index++] = edges[i]; //将边加入到生成树中
            }
        }
        //显示最小生成树
        System.out.println("最小生成树：");
        for (EData result : results) {
            if (result == null) {
                break;
            }
            System.out.println(result);
        }
    }

    private void print(){
        System.out.println("邻接矩阵：");
        for (int[] link : matrix){
            for (int value : link){
                System.out.printf("%13d", value);
            }
            System.out.println();
        }
    }

    //对边以权值排序
    private void sortEdges(EData[] edges, int left, int right){
        int l = left;
        int r = right;
        int pivot = edges[(l+r)/2].weight;
        EData temp = null;
        while (l < r){
            while (edges[l].weight < pivot){
                l += 1;
            }
            while (edges[r].weight > pivot){
                r -= 1;
            }
            if (l >= r){
                break;
            }
            temp = edges[l];
            edges[l] = edges[r];
            edges[r] = temp;
            if (edges[l].weight == pivot){
                r--;
            }
            if (edges[r].weight == pivot){
                l++;
            }
        }
        if (l == r){
            l++;
            r--;
        }
        if (left < r){
            sortEdges(edges, left, r);
        }
        if (l < right){
            sortEdges(edges, l , right);
        }
    }

    // 通过顶点数据返回顶点的索引
    private int getPosition(char ch){
        for (int i =0; i < data.length; i++){
            if (data[i] == ch){
                return i;
            }
        }
        return -1;
    }

    // 获取图中的边（包括无向的两种情况）
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i =0; i < data.length; i++){
            for (int j = 0; j < data.length; j++){
                if (i == j){    //自己对自己就没有必要
                    continue;
                }
                if (matrix[i][j] != INF){
                    edges[index++] = new EData(data[i],data[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    // 获取索引为i的顶点的终点，用于判断两个顶点的终点是否相同,即判断是否构成回路
    private int getEnd(int[] ends, int i){  //ends数组记录了各顶点对应的终点
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;   //返回索引为i的顶点对应的终点
    }
}
