package TenCommonAlgorithms.dijkstraAlgorithm;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 * 解决一个顶点到另一个顶点的最小路程问题
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 9999; //N表示两顶点不能连通
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        Graph graph = new Graph(vertexes, matrix);
        graph.showGraph();
        System.out.println();
        graph.dijkstra(2);
    }
}

class Graph{
    private char[] vertexes; //顶点的数组
    private int[][] matrix; //邻接矩阵
    private VisitedVertex vv; //已访问顶点的集合

    public Graph(char[] vertexes, int[][] matrix){
        this.vertexes = vertexes;
        this.matrix = matrix;
    }

    public void showGraph(){
        for (int[] link : matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    //迪杰斯特拉算法
    public void dijkstra(int index){  //index为出发点的索引
        int start = index;  //记录出发点的索引
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        vv = new VisitedVertex(vertexes.length, index);
        update(index);

        StringBuilder path = new StringBuilder(vertexes[index] + "-> "); //描述路径
        for (int i =1; i < vertexes.length; i++){
            index = vv.updateArr(); //选择并返回新的访问顶点
            path.append(vertexes[index]).append("-> ");
            update(index);  //更新index顶点到其他顶点的距离以及更新前驱顶点
        }

        //输出结果
        System.out.println("*********************************");
        for (char value : vertexes){
            System.out.print(value+"\t");
        }
        System.out.println();
        System.out.println("各点访问情况：yes表示访问过，no表示未访问");
        for (int value : vv.alreadyArr){
            if (value == 1){
                System.out.print("yes\t");
            }else{
                System.out.print("no\t");
            }
        }
        System.out.println();
        System.out.println("各点的前驱顶点:");
        for (int value : vv.preVisited){
            System.out.print(vertexes[value]+"\t");
        }
        System.out.println();
        System.out.println("各点到出发点("+vertexes[start]+")的最小距离：");
        for (int value : vv.distance){
            System.out.print(value+"\t");
        }
        System.out.println();
        System.out.println("路径："+path);
        System.out.println("*********************************");
    }

    //更新index顶点到其他顶点的距离以及更新前驱顶点
    private void update(int index){
        int len;
        for (int j =0; j < matrix[index].length; j++){  //遍历当前index处顶点到其他顶点的情况
            //原来的index顶点与出发点的距离加上现在要更新的
            len = vv.getDistance(index) + matrix[index][j];
            // 如果j顶点没有被访问， 并且是连通的
            if (!vv.isVisited(j) && len < vv.getDistance(j)){
                vv.updatePre(index, j); //更新j的前驱顶点为index
                vv.updateDistance(j, len);//更新出发点到j的距离
            }
        }
    }
}

//已访问顶点的集合
class VisitedVertex{
    // 记录各个顶点是否访问过，0未访问，1访问过
    public int[] alreadyArr;
    // 被访问过的顶点前一个顶点的索引
    public int[] preVisited;
    // 记录从出发点到其他顶点的距离
    public int[] distance;

    public VisitedVertex(int length, int index){
        alreadyArr = new int[length];  //length表示顶点的个数, index以index为出发点
        preVisited = new int[length];
        distance = new int[length];
        // 初始化distance
        Arrays.fill(distance, 9999);
        distance[index] = 0;  //出发点离自己为0
        alreadyArr[index] = 1; //初始化时出发点就是被访问过的
    }

    // 判断顶点有没有被访问
    public boolean isVisited(int index){
        return alreadyArr[index] == 1;
    }

    // 更新出发点到index顶点的距离
    public void updateDistance(int index, int len){
        distance[index] = len;
    }

    // 更新前驱顶点
    public void updatePre(int pre, int index){
        preVisited[index] = pre;
    }

    // 获取index处顶点与出发点的距离
    public int getDistance(int index){
        return distance[index];
    }

    //继续选择并返回新的访问顶点
    public int updateArr(){
        int minDis = 9999;
        int index = 0;
        for (int i =0; i < alreadyArr.length; i++){
            if (alreadyArr[i] == 0 && distance[i] < minDis){
                minDis = distance[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }
}
