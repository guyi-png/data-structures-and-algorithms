package recursion;

/**
 * 迷宫问题
 */
public class MazeProblem {
    public static void main(String[] args) {
        //创建二维数组模拟迷宫
        int rowNum = 8 ;
        int colNum = 7;
        int[][] mazeMap = new int[rowNum][colNum];

        //使用1表示有障碍物
        for (int i =0; i<colNum; i++){
            mazeMap[0][i] = 1;
            mazeMap[rowNum -1][i] = 1;
        }
        for (int i =0; i<rowNum; i++){
            mazeMap[i][0] =1;
            mazeMap[i][colNum -1] = 1;
        }
        mazeMap[3][1] = 1;
        mazeMap[3][2] = 1;

        //展示迷宫图
        System.out.println("展示迷宫图：");
        for (int i=0; i < rowNum; i++){
            for (int j =0; j < colNum; j++){
//                System.out.println(mazeMap[i][j]+'\t'); //'\t'被解析为数值
                //0000 1001	9	09	HT	␉	水平定位符号
                System.out.print(mazeMap[i][j] +"\t");
            }
            System.out.println();
        }
        System.out.println("*************************");

        //递归找路
//        way(mazeMap,1,1);
        //行进方式二：
        way2(mazeMap,1,1);

        //打印新的地图
        System.out.println("标记过的地图：");
        for (int i=0; i < rowNum; i++){
            for (int j =0; j < colNum; j++){
                System.out.print(mazeMap[i][j] +"\t");
            }
            System.out.println();
        }
    }

    //通过递归,避开障碍物,顺利到达目的地
    // mazeMap为迷宫地图
    //开始位置的坐标  (x , y)
    //  目的地 : (6 , 5)
    // 0表示未到达地域   1表示障碍物   2表示可以走的位置   3表示已经走过的地域,是死路
    // 设定某行进的方式： 下 -> 右 -> 上 -> 左   四面无路可走，则返回上一走
    public static boolean way(int[][] mazeMap,int x, int y){
        if (mazeMap[6][5] == 2){   //可到达终点
            return true;
        }else{
            if (mazeMap[x][y] == 0){ //如果当前的位置是未到达地域
                //以指定的行进方式 前进
                mazeMap[x][y] = 2;  //假设是可以走的位置
                if (way(mazeMap, x + 1, y)){  //向下
                    return  true;
                }else if (way(mazeMap, x, y + 1)){  //向右
                    return true;
                }else if (way(mazeMap, x - 1, y)){  //向上
                    return true;
                }else if(way(mazeMap, x, y - 1)){    //向左
                    return true;
                }else{
                    mazeMap[x][y] = 3; //表示该位置无路可走
                    return false;
                }
            }else{
                 return false;
            }
        }
    }
    //修改行进的方式: 上 -> 右 -> 下 -> 左
    public static boolean way2(int[][] mazeMap,int x, int y){
        if (mazeMap[6][5] == 2){   //可到达终点
            return true;
        }else{
            if (mazeMap[x][y] == 0){ //如果当前的位置是未到达地域
                //以指定的行进方式 前进
                mazeMap[x][y] = 2;  //假设是可以走的位置
                if (way2(mazeMap, x - 1, y)){  //向上
                    return  true;
                }else if (way2(mazeMap, x, y + 1)){  //向右
                    return true;
                }else if (way2(mazeMap, x + 1, y)){  //向下
                    return true;
                }else if(way2(mazeMap, x, y - 1)){    //向左
                    return true;
                }else{
                    mazeMap[x][y] = 3; //表示该位置无路可走
                    return false;
                }
            }else{
                return false;
            }
        }
    }

}
