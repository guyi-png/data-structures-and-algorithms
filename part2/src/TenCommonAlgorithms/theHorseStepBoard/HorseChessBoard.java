package TenCommonAlgorithms.theHorseStepBoard;

import java.awt.Point;
import java.util.ArrayList;

/**
 * 马踏棋盘算法
 *
 * 马踏棋盘算法也被称为骑士周游问题
 * 将马随机放在国际象棋的 8X8 棋盘Board[0~7] [0~7]的某个方格中，
 * 马按走棋规则(马走日字)进行移动。
 * 要求每个方格只进入一次，走遍棋盘上全部64个方格
 */
public class HorseChessBoard {
    private static int colNum; //棋盘的列数
    private static int rowNum; //棋盘的行数
    private static boolean[] isVisited; //表示该位置是否被访问过
    private static boolean finished; //是否踏了个遍

    public static void main(String[] args) {
        colNum = 8;
        rowNum = 8;
        int[][] chessboard = new int[rowNum][colNum];
        isVisited = new boolean[colNum * rowNum];

        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, 1, new Point());
        long end = System.currentTimeMillis();
        System.out.println("共："+(end - start)+"毫秒");

        //输出棋盘
        for (int[] rows : chessboard){
            for (int step : rows){
                System.out.print(step+"\t");
            }
            System.out.println();
        }
    }

    public static void traversalChessboard(int[][] chessboard, int step, Point startP){
        chessboard[startP.y][startP.x] = step;  //标记棋盘
        isVisited[startP.y * colNum + startP.x] = true;  //将第多少个点标记为访问过
        ArrayList<Point> points = next(startP);     //根据当前位置，得到马可以走哪些位置的集合
        sort(points);  //非递减排序(如 1, 2, 3, 3, 4, 4, 7...形式)，优化策略
        while (!points.isEmpty()){  //有位置可走
            Point p = points.remove(0); //尝试走一个位置
            if (!isVisited[p.y*colNum + p.x]){  //如果这个位置未访问
              //traversalChessboard(chessboard, ++step, p); //这样写结果就不正确了
                traversalChessboard(chessboard, step + 1, p); //走到位置上，step加1，递归重复操作
            }
        }
        // 马没有踏遍棋盘，又无路可走
        // 棋盘所有标记重置为0, 点重置为未访问
        // 未踏遍棋盘时， step < colNum*rowNum
        // 在回溯过程中， step < colNum*rowNum仍是成立的
        // 所以需要标识完成 finished = true
        if (step < colNum*rowNum && !finished){
            chessboard[startP.y][startP.x] = 0;
            isVisited[startP.y * colNum + startP.x] = false;
        }else{
            finished = true;
        }
    }


    //根据当前位置，计算马可以走哪些位置，并把这些位置放到集合中
    // point坐标（x,y） 左上位置为（0，0）向右下增
    public static ArrayList<Point> next(Point curP) {
        ArrayList<Point> points = new ArrayList<>();
        Point p = new Point();
        // 以下最多八种情况
        if ((p.x = curP.x - 2) >= 0 && (p.y = curP.y -1) >= 0){ //表示可以走最左 上的那个位置
            points.add(new Point(p));   //能走就将位置放到集合
        }
        if ((p.x = curP.x + 2) < colNum && (p.y = curP.y -1) >= 0){ //表示可以走最右 上的那个位置
            points.add(new Point(p));   //能走就将位置放到集合
        }
        if ((p.x = curP.x - 1) >= 0 && (p.y = curP.y -2) >= 0){ //表示可以走左 最上的那个位置
            points.add(new Point(p));   //能走就将位置放到集合
        }
        if ((p.x = curP.x + 1) < colNum && (p.y = curP.y -2) >= 0){ //表示可以走右 最上的那个位置
            points.add(new Point(p));   //能走就将位置放到集合
        }
        if ((p.x = curP.x + 2) < colNum && (p.y = curP.y + 1) < rowNum){ //表示可以走最右 下的那个位置
            points.add(new Point(p));   //能走就将位置放到集合
        }
        if ((p.x = curP.x - 2) >= 0 && (p.y = curP.y + 1) < rowNum){ //表示可以走最左 下的那个位置
            points.add(new Point(p));   //能走就将位置放到集合
        }
        if ((p.x = curP.x + 1) < colNum && (p.y = curP.y + 2) < rowNum){ //表示可以走右 最下的那个位置
            points.add(new Point(p));   //能走就将位置放到集合
        }
        if ((p.x = curP.x - 1) >= 0 && (p.y = curP.y + 2) < rowNum){ //表示可以走左 最下的那个位置
            points.add(new Point(p));   //能走就将位置放到集合
        }
        return points;
    }

    //优化策略，在得到可走的一些位置后， 比较每个位置下一步的可走位置数, 进行非递减排序
    public static void sort(ArrayList<Point> points){
        points.sort((Point o1, Point o2) ->{
            int counts1 = next(o1).size();
            int counts2 = next(o2).size();
            return Integer.compare(counts1, counts2);
        });
    }
}
