package sparsearray;

/**
 * 稀数组的处理方法：
 * 1.记录数组一共有几行几列，有多少个不同的值
 * 2.把具有不同值的元素的行列及值记录在一个规模的数组中，从而缩小程序的规模
 *
 *
 * 二维数组转化为稀疏数组的思路：
 *  1. 遍历原始的二维数组，得到有效数据的个数sum
 *  2. 根据sum就可以创建稀疏数组sparsearray int[sum + 1][3]  ?int[行数，列数]
 *  3. 将二维数组的有效数据存入稀疏数组
 *
 *  稀释数组转化为原始的二维数组的思路：
 *  1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，
 *  比如chessArr2 = int[11][11] 创建11x11的棋盘
 *  2.在读取稀疏数组后几行的数据，并赋给原始的二维数组
 */
public class Test {
    public static void main(String[] args) {
        //1.创建一个二维数组（棋盘）,11x11, 0表示没有下棋，1表示黑子，2表示白子
        int rowNum = 11,colNum = 11;
        int[][] chessArr1 = new int[rowNum][colNum];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("原始的二维数组:");
        for (int[] row : chessArr1){
            for (int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        //2.将二维数组转化为稀疏数组
        //2.1先遍历二维数组，得到非0数组的个数
        int sum =0;
        for (int i =0; i < rowNum; i++){
            for (int j =0; j < colNum; j++){
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }

        System.out.println("sum="+sum);  //2

        //3.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3]; //第一行存储row,column,value数据
        //4.给稀疏数组赋值
        sparseArr[0][0] = rowNum;
        sparseArr[0][1] = colNum;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存放到稀疏数组中
        int count = 0; //count 用于记录是第几个非0数据
        for (int i =0; i < rowNum; i++){
            for (int j =0; j < colNum; j++){
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("稀疏数组为:");
        System.out.println("row\tcolumn\tvalue");
        for (int i =0; i < sparseArr.length; i++){
            for (int j =0; j < 3; j++){
                System.out.print(sparseArr[i][j] + "\t\t");
            }
            System.out.println();
        }

        //将稀疏数组 --> 二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i=1; i < sparseArr.length; i++){
            int eleRowOffset = sparseArr[i][0];
            int eleColOffset = sparseArr[i][1];
            int eleValue = sparseArr[i][2];
            chessArr2[eleRowOffset][eleColOffset] = eleValue;
        }

        //输出现在的二维数组的形式
        System.out.println();
        for (int[] row : chessArr2){
            for (int data : row){
                System.out.print(data +"\t");
            }
            System.out.println();
        }
    }
}
