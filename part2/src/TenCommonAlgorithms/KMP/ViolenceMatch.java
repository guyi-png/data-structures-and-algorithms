package TenCommonAlgorithms.KMP;

/**
 * 暴力匹配, 匹配字符串
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "欧勒格奥利给利给奥欧利给给给奥";
        String str2 = "给给给";
        int index = violenceMatch(str1, str2);
        System.out.println(index);
    }

    public static int violenceMatch(String str1, String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1l = str1.length();
        int s2l = str2.length();
        int i = 0;  //指向s1
        int j = 0;  //指向s2

        while (i < s1l && j < s2l){ //保证索引不越界
            if (s1[i] == s2[j]){
                i++;    //一个字符匹配到了，要匹配它的下一位
                j++;
            }else{
                // 如果匹配失败，让i在原来的基础上移一位，让j重置
                i = i - (j-1);
                j = 0;
            }
        }

        if (j == s2l) {  // 如果匹配成功
            return i - j;
        } else{
            return -1;
        }
    }
}
