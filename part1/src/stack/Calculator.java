package stack;

/**
 *  仅模拟计算器的 + - * / 基本运算
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "1000/200+245*1";
        ArrayStack numStack = new ArrayStack(10);  //需要运算的数值存放的栈
        ArrayStack operStack = new ArrayStack(10); //运算符的栈
        int index = 0; //index用于扫描需要运算的表达式
        int num1 = 0, num2 = 0;  //表达式中的数值
        int oper = 0;  //表达式中的符号
        int result = 0;  //结果
        char val = ' ';  //将扫描得到的结果保存下来
        String strVal = "";  //用于拼接多位数

        //遍历expression
        while (index < expression.length()){
            val = expression.substring(index,++index).charAt(0); //取出一个数值或运算符
            if (val == ' '){
                continue;
            }
            if(isOperator(val)){ //如果是运算符
                /*
                以下逻辑： 如果符号栈为空，就直接入栈，如果符号栈有操作符，
                就进行比较，如果当前的操作符的优先级大于栈中的操作符，就直接入栈
                如果当前的操作符的优先级小于栈中的操作符，就需要从数值栈中取出（pop）
                两个数，再从符号栈中取出（pop）一个运算符,以这两个数作运算，
                返回的结果放入（压入）栈中（push），参与运算的操作符就不做处理（废弃），
                然后将当前的操作符（要入栈的操作符）压入符号栈
                 */
                if (operStack.isEmpty()){  //如果符号栈中没有操作符，可直接加入
                    operStack.push(val);
                }else{
                    if (priority(val) > priority(operStack.peekTop())){
                        operStack.push(val);
                    }else{
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = calc(num1, num2, oper);
                        numStack.push(result);
                        operStack.push(val);
                    }
                }
            }else{  //此时的val是数值的字符形式
                strVal += val;     //如果是多位数，就将多位数拼接在一起
                int intVal = Integer.parseInt(strVal); //解析字符串得到数值

                if (index == expression.length()){  //如果是最后一个数值，就直接加入
                    numStack.push(intVal);
                }else{
                    //查看下一位是不是数值，不是就直接加入
                    if (isOperator(expression.substring(index,index+1).charAt(0))){
                        numStack.push(intVal);
                        //重置字符串
                        strVal = "";
                    }
                }
            }
        }

        //表达式扫描完后，按顺序取出数值和运算符进行运算，
        // 数值栈中最后的元素就是最后的结果
        while (!operStack.isEmpty()){
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = calc(num1,num2,oper);
            numStack.push(result);
        }
        //最后的结果
        System.out.println("表达式（"+expression +"）的结果"+numStack.peekTop());
    }

    //获取运算符的优先级，优先级越高返回的数值越大
    public static int priority(int operator){
        if (operator == '*' || operator == '/'){
            return 1;
        }else if (operator == '+' || operator == '-'){
            return 0;
        }else if(operator == '(' || operator == ')' ){
            return 2;
        }else{
            throw new RuntimeException("不存在的运算符");  //不存在的运算符
        }
    }

    //判断是不是一个运算符
    public static boolean isOperator(char val){
        return val == '+'
                || val == '-'
                || val == '*'
                || val == '/';
    }

    //计算方法
    public static int calc(int num1, int num2, int operator){
        int result = 0;  //记录结果
        switch(operator){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;  //num2为第二次pop的值，即减号的左边的数
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;   //一样，num2为除号的左边的值
                break;
        }
        return result;
    }
}
