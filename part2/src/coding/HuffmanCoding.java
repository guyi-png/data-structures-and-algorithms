package coding;

import java.util.*;

/**
 * 哈夫曼编码, 有细微的瑕疵(bug)
 */
public class HuffmanCoding {
    //哈夫曼编码表
    static Map<Byte,String> huffmanCodes = new HashMap<>();
//    static Map<Byte,StringBuilder> huffmanCodes = new HashMap<>(); 在使用StringBuilder时后面会出现错误

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] huffmanCodeBytes = toHuffmanCodeZip(str);
        System.out.println("压缩后："+ Arrays.toString(huffmanCodeBytes));

        byte[] bytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("解压后：" + new String(bytes));
    }

    //将字符串解析为集合，集合元素保存了字符串中字符以及字符出现的次数
    public static List<Node> getNodes(byte[] bytes){
        List<Node> nodes = new ArrayList<>();
        Map<Byte,Integer> counts = new HashMap<>();
        for (byte b : bytes){
//            Integer count = counts.get(b); //得到counts中对应的字符的次数
//            if (count == null){   //如果counts为0，则该字符未出现过
//                counts.put(b,1);
//            }else{
//                counts.put(b,count+1);  //counts不为0,在原来的次数上加一
//            }
            counts.merge(b,1,Integer::sum);
        }
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()){  //遍历counts
            // 通过entry的键做Node中保存的字符data， 通过entry的值做Node中字符出现的次数wight
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }
    // 创建哈夫曼树

    public static Node createHuffmanTree(List<Node> nodes){
        while(nodes.size() > 1){
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null,left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);   //返回哈夫曼的根节点
    }

    //以哈夫曼树生成哈夫曼编码表，
    //二叉树的左子节点编码为 0 ， 二叉树的右子节点编码为 1
    // 哈夫曼编码表用Map存放，Map<Byte, StringBuilder> 键是字符，值是编码
    //  参数 node 当前节点  code 当前节点的路径的编码  sb 为当前节点的data对应的哈夫曼编码表
    public static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder sb = new StringBuilder(stringBuilder);
        sb.append(code);
        if (node != null){
            if (node.data == null){  //如果是非叶子节点
                //向左递归
                getCodes(node.left, "0", sb);
                //向右递归
                getCodes(node.right, "1", sb);
            }else{
                //如果是叶子节点
                huffmanCodes.put(node.data, sb.toString());
            }
        }
    }

    // 根据哈夫曼编码表生成相应的哈夫曼编码
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes){
            sb.append(huffmanCodes.get(b));
        }
//        System.out.println("哈夫曼编码: " + sb);
        //将编码后的字符串形式转为byte[]
        int len;
        //得到byte[]的长度
        if (sb.length() % 8 == 0){   //刚好八字节
            len = sb.length() / 8;
        }else{
            len =  sb.length() / 8 + 1;
        }
        byte[] bts = new byte[len];
        int index = 0;
        for (int i =0; i < sb.length(); i += 8){ //每8位编码数对应一个byte
            String strByte;
            if (i + 8 > sb.length()){
                strByte = sb.substring(i);   //最后几个编码数不够8个，直接把后面都加入
            }else{
                strByte = sb.substring(i, i + 8);
            }
            bts[index++] = (byte) Integer.parseInt(strByte,2); //以二进制的形式解析字符串
        }
        return bts;
    }

    public static byte[] toHuffmanCodeZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        getCodes(huffmanTreeRoot, "",new StringBuilder());
        return zip(bytes, huffmanCodes);
    }

    // 将字符串通过哈夫曼编码，得到编码的byte[]数组
    public static byte[] toHuffmanCodeZip(String str){
        byte[] bytes = str.getBytes();
//        System.out.println("原字符串的长度: "+bytes.length);//40

        List<Node> nodes = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
//        if (huffmanTreeRoot != null) {
//            System.out.println("前序遍历哈夫曼树");
//            huffmanTreeRoot.preOrder();
//        }

        getCodes(huffmanTreeRoot, "",new StringBuilder());
//        System.out.println("生成的哈夫曼编码表为"+ huffmanCodes);

        return zip(bytes, huffmanCodes);
    }

    //将一个byte转成一个二进制(补码形式)的字符串
    public static String byteToBitString(boolean flag, byte b){
        int temp = b;
        if (flag){  //正数的补码前面的0被省略,需要补高位, 最后的byte元素不够8位，不能补高位
            temp |= 256;   // 256( 1 0000 0000 ) | 0000 0001 = 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);  //返回temp的二进制的补码
        if (flag || temp < 0){
            return str.substring(str.length() -8);  //取后面的8位
        }else{
            return str;
        }
    }

    //对压缩的数据解码
    public static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanCodeBytes){
        //以huffmanCodeBytes得到对应的编码的字符串
        StringBuilder sb = new StringBuilder();
        for (int i =0; i < huffmanCodeBytes.length; i++){
            boolean flag = (i != huffmanCodeBytes.length-1);   //如果不是最后一个元素，都是要补高位的
            String string = byteToBitString(flag, huffmanCodeBytes[i]);
            sb.append(string);
        }
//        System.out.println("哈夫曼编码: " + sb);

        //调换哈夫曼编码表的键值
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }

        //配对哈夫曼表，解码
        List<Byte> list = new ArrayList<>();
        for (int i =0; i < sb.length();     ){
            int count = 1; //每次匹配时 记录扫描的次数
            boolean flag = true;
            Byte b = null;  //保存匹配的字符

            while (flag){
                //取出一个编码数 "0" or "1"
                String key = sb.substring(i, i + count);
                b = map.get(key);
                if (b == null){ //没找到，就继续向后扫描
                    count++;
                }else{
                    flag = false; //配对到了对应的哈夫曼编码表的一项（无多义性）
                }
            }
            list.add(b);    // 将找到的单个哈夫曼编码加入集合
            i += count;     // 让i移动到i+count位置, 继续匹配
        }
        byte[] b = new byte[list.size()];
        for (int i =0; i < b.length; i++){
            b[i] = list.get(i);
        }
        return b;
    }
}

class Node implements Comparable<Node>{
    Byte data;  //存放数据,如'a' => 97
    int weight;  //权重,字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight){
        this.data = data;
        this.weight = weight;
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;  //从小到大
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
