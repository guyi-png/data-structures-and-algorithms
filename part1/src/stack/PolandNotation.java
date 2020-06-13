package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰（后缀）表达式,仅模拟计算器的 + - * / 基本运算:
 *  （3+4）*5-6 的逆波兰(后缀)表达式为 34+5*6-  ，以 34+5*6- 为例针对后缀表达式求值思路：
 *  1.从左至右扫描，将3和4压入栈
 *  2.遇到+运算符，因此弹出4和3为栈顶元素，栈次元素，计算出3+4的值，得到7，再将7入栈
 *  3.将5入栈
 *  4.遇到*运算符，弹出5和7为栈顶和栈次，计算7*5的值，得到35，再将35放入栈中
 *  5.将6入栈
 *  6.最后遇到-运算符，计算35-6的值，得到最后的结果
 *
 *  中缀表达式 转 逆波兰表达式，见代码详情
 */
public class PolandNotation {
    public static void main(String[] args) {
        String expression1 = "1 + (( 2 + 3 ) * 4) - 5";   //result=16
        List<String> list = InfixExpressionList(expression1);
        System.out.println("中缀表达式对应的："+list);
        List<String> suffixExpressionList = infixToSuffix(list);
        System.out.println("后缀表达式对应的："+suffixExpressionList);
        int result = calc(suffixExpressionList);
        System.out.println(result);  //16
        System.out.println("**********************");


        String expression = "2 6 + 8 * 7 +";  // (2+6)*8+7=71
        List<String> list1 = getList(expression);
        System.out.println(list1);
        int result1 = calc(list1);
        System.out.println(result1); //71
    }
    //将字符串转为集合，便于取值
    public static List<String> getList(String expression){
        String[] split = expression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }
    //计算逆波兰表达式
    public static int calc(List<String> list){
        Stack<String> stack = new Stack<>();
        for (String item: list){
            if (item.matches("\\d+")){
                stack.push(item);
            }else{
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result= 0;
                if (item.equals("+")){
                    result = num1 + num2;
                }else if(item.equals("-")){
                    result = num2 -num1;
                }else if (item.equals("*")){
                    result = num1 *num2;
                }else if (item.equals("/")){
                    result = num2 / num1;
                }else{
                    throw  new RuntimeException("无效");
                }
                stack.push(result+"");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //将中缀表达式转为集合存储
    public static List<String> InfixExpressionList(String ex){
        List<String> list = new ArrayList<>();  //存放中缀表达式的内容
        int i = 0; //指针，用于遍历中缀表达式
        String str = "";  //用于多位数的拼接
        char val;  //用于保存每次遍历得到的字符

        //下面的一个循环遍历得到中缀表达式的内容
        do{
            val = ex.charAt(i);
            if (val < 48 || val > 57){  //是非数字的值. 字符编码值 '0'=48 '9'=57
                if (val == ' '){
                    i++;
                    continue;
                }
                str = "";
                list.add(""+val);
                i++;
            }else{   //是数字
                str += val;
                //到最后一个时，直接将str放入
                if (i+1 >= ex.length()){
                    list.add(str);
                    break;
                }
                //如果下一个是非数字，就可以将str放入了
                if (ex.charAt(i+1) < 48 || ex.charAt(i+1) > 57) {
                    list.add(str);
                }
                i++;
            }
        }while(i < ex.length());
        return list;
    }

    //将ArrayList(中缀表达式)：[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]  转化为
    //ArrayList(后缀表达式): [1, 2, 3, +, 4, *, +, 5, -]
    public static List<String> infixToSuffix(List<String> list){
        //定义符号栈
        Stack<String> opeStack = new Stack<>();
        //存储中间结果的集合,因为没有出栈操作，直接使用集合
        List<String> resList = new ArrayList<>();
        //从符号栈取出的符号
        String operator;

        //遍历list
        for (String item : list){
            //如果是一个数，就放入集合
            if (item.matches("\\d+")){
                resList.add(item);
            }else if (item.equals("(")){  //如果是"("则可以直接入符号栈
                opeStack.push(item);
            }else if (item.equals(")")){
                //如果是右括号，则让符号依次弹出符号栈，并放入到集合中，
                // 直到遇到"("，就停止操作，"("和")"废弃
                while (!opeStack.peek().equals("(")){
                    operator = opeStack.pop();
                    resList.add(operator);
                }
                opeStack.pop();   //弹出"("
            }else{  // 此时item是运算符号
                //当item的优先级小于等于栈顶运算符的优先级，
                //将栈顶的运算符放入集合中，再次用item与栈顶比较，重复操作

                //***********************************************************************
                //栈空，size() = 0   ->   opeStack.peek() ->  EmptyStackException
//                while (getPriority(opeStack.peek()) >= getPriority(item)){
//                    operator = opeStack.pop();
//                    resList.add(operator);
//                    if (opeStack.size() == 0){
//                       break;
//                    }
//                }

                while ( opeStack.size() != 0 && getPriority(opeStack.peek()) >= getPriority(item)){
                    operator = opeStack.pop();
                    resList.add(operator);
                }
                //***********************************************************************

                //操作完后，将item压入符号栈
                opeStack.push(item);
            }
        }
        //遍历list后，将opeStack中剩余的运算符依次放入集合
        while (opeStack.size() != 0){
            operator =  opeStack.pop();
            resList.add(operator);
        }
        //此结果为后缀表达式的内容，将结果集合返回
        return resList;
    }

    //用于比较运算符的优先级
    public static int getPriority(String operation){
        int result = 0;
        switch (operation){
            case "+":
            case "-":
                result =1;
                break;
            case "*":
            case "/":
                result = 2;
                break;
            default:
                break;
        }
        return result;
    }
}
