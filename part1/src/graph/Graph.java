package graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 图
 */
public class Graph {
    private final ArrayList<String> vertexList; //顶点的集合
    private final int[][] matrix;  //存储图的邻接矩阵
    private int edgeNum; //边数
    private boolean[] isVisited; //记录是否被访问过

    public static void main(String[] args) {
        int n = 5;
        String[] vertexes = {"A", "B", "C", "D", "E"};
        //创建图
        Graph graph = new Graph(5);
        //添加顶点
        for (String vertex : vertexes){
            graph.insertVertex(vertex);
        }
        //添加边
        graph.insertEdge(0, 1, 1);  // A-B
        graph.insertEdge(0, 2, 1);  // A-C
        graph.insertEdge(1, 2, 1);  // B-C
        graph.insertEdge(1, 3, 1);  // B-D
        graph.insertEdge(1, 4, 1);  // B-E

        graph.showGraph();

        System.out.println("深度遍历");
        graph.depthFirstSearch();

        System.out.println();
        System.out.println("广度遍历");
        graph.broadFirstSearch();
    }

    public Graph(int n){    //n 顶点数
        matrix = new int[n][n];
        vertexList = new ArrayList<>();
        edgeNum = 0;
    }

    //插入顶点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //添加边
    // v1 , v2 表示顶点的索引， weight表示是否直接连接, 当weight为1时就是直接连接
    public void insertEdge(int v1, int v2, int weight){
        matrix[v1][v2] = weight;
        matrix[v2][v1] = weight;
        edgeNum++;
    }

    //返回顶点个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    //返回边数
    public int getEdgeNum(){
        return edgeNum;
    }

    //返回顶点索引对应的顶点值
    public String getVertex(int index){
        return vertexList.get(index);
    }

    //获得两个顶点之间的关系， 是否直接连接
    public int getWeight(int v1, int v2){
        return matrix[v1][v2];
    }

    //显示矩阵
    public void showGraph(){
        for (int[] link : matrix){
            for (int weight : link){
                System.out.print(weight +"\t");
            }
            System.out.println();
        }
    }

    //获得第一个邻接顶点的索引
    public int getFirstNeighbor(int index){
        for (int j =0; j < vertexList.size(); j++){
            if (matrix[index][j] > 0 ){ // 发现第一个邻接顶点
                return j;
            }
        }
        return -1;  //没有邻接顶点
    }

    //根据前一个邻接顶点的索引来获取下一个邻接顶点的索引
    public int getNextNeighbor(int v1, int v2){
        for (int j =v2+1; j < vertexList.size(); j++){
            if (matrix[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //深度优先搜索遍历
    //重载depthFirstSearch，使其可达到全部顶点
    private void depthFirstSearch(){
        isVisited = new boolean[vertexList.size()];
        for (int i =0; i < getNumOfVertex(); i++){
            if (!isVisited[i]){
                depthFirstSearch(i,isVisited);
            }
        }
    }

    private void depthFirstSearch(int i, boolean[] isVisited){
        //输出当前顶点
        System.out.print(getVertex(i)+"->");
        //将该顶点设为访问过
        isVisited[i] = true;
        //查找顶点的第一个邻接顶点,返回第一个邻接顶点的索引
        int w = getFirstNeighbor(i);
        while (w != -1){  //有邻接顶点
            if (!isVisited[w]){
                // 如果这第一个邻接顶点没有被访问，以这第一个邻接顶点为当前顶点递归
                depthFirstSearch(w, isVisited);
            }else{
                // 如果这第一个邻接顶点被访问过，就跳过它
                w = getNextNeighbor(i,w);
            }
        }
    }

    //广度优先搜索遍历
    //重载broadFirstSearch，使其可达到全部顶点
    private void broadFirstSearch(){
        isVisited = new boolean[vertexList.size()];
        for (int i =0; i < getNumOfVertex(); i++){
            if (!isVisited[i]){
                broadFirstSearch(i, isVisited);
            }
        }
    }

    //对一个顶点进行广度优先遍历
    private void broadFirstSearch(int i, boolean[] isVisited){
        int u;  //表示队列的头的索引
        int w;  //第一个邻接顶点
        LinkedList<Integer> linkedList = new LinkedList<>();    //用于记录顶点访问的顺序
        //输出当前顶点
        System.out.print(getVertex(i)+ "->");
        //将该顶点设为访问过
        isVisited[i] = true;
        //将当前顶点的索引加入到集合的后面
        linkedList.addLast(i);

        while (!linkedList.isEmpty()){
            //取出队列的头的索引
            u = linkedList.removeFirst();
            // 得到第一个邻接顶点的索引
            w = getFirstNeighbor(u);
            while (w != -1){ //有邻接顶点
                if (!isVisited[w]){
                    System.out.print(getVertex(w)+"->");
                    //将该顶点设为访问过
                    isVisited[w] = true;
                    //将当前顶点的索引加入到集合的后面
                    linkedList.addLast(w);
                }
                //在同一层继续找下一个第一个邻接顶点
                w = getNextNeighbor(u, w);
            }
        }
    }
}
