package recursion;

/**
 *  了解递归: 调用方法本身
 */
public class SimpleRecursionTest {
    public static void main(String[] args) {
        test(10);
        System.out.println(test1(5));
    }
    //打印
    public static void test(int n){
        if (n > 1){
            test( n-1);
        }
        System.out.println("n="+n);
    }
    //阶乘
    public static int test1(int n){
        if (n <= 1){
            return 1;
        }
        return n * test1(n-1);
    }
}
