package TenCommonAlgorithms.KMP;

/**
 *  kmp算法 匹配字符串
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        int index = kmpSearch(str1, str2, next);
        System.out.println(index);
    }

    /**
     *
     * @param str1  源字符串
     * @param str2  子串
     * @param next  部分匹配值表
     * @return  在源字符串中匹配到子串的第一个索引, 未匹配到返回 -1
     */
    public static int kmpSearch(String str1, String str2, int[] next){
        // i 指向str1  j 指向str2
        for (int i=0,j=0; i < str1.length(); i++){
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j -1];     //很关键，记一下
            }
            if (str1.charAt(i) == str2.charAt(j)){ //匹配到了，再看下一位
                j++;
            }
            if (j == str2.length()){    //子串匹配完，就返回结果
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取子串的部分匹配值表
    public static int[] kmpNext(String str){
        int[] next = new int[str.length()]; //表示部分匹配值表
        next[0] = 0;  //如果字符串长度为1
        for (int i=1, j=0; i < str.length(); i++){
            while (j >0 && str.charAt(i) != str.charAt(j)){
                j = next[j-1];      //很关键，记一下
            }
            if (str.charAt(i) == str.charAt(j)){
                j++;    //匹配到了就入部分匹配表加1
            }
            next[i] = j;
        }
        return next;
    }
}
