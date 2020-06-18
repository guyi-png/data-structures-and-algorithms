package TenCommonAlgorithms.greedyAlgorithm;

import java.util.*;

/**
 * 贪心算法, 广播覆盖问题
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();  //存放广播的集合
        HashSet<String> hashSet1 = new HashSet<>(); //每个广播的覆盖地区的集合
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("武汉");

        broadcasts.put("K1",hashSet1);//加入key广播编号，value覆盖地区
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);

        HashSet<String> allAreas = new HashSet<>(); //包括全部的地区
        for (Map.Entry<String, HashSet<String>> entry : broadcasts.entrySet()){
            allAreas.addAll(entry.getValue());
        }

        ArrayList<String> selects = new ArrayList<>(); //存放选择的广播集合
        //临时集合，在遍历的过程中，存放遍历过程中的广播覆盖的地区与当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        //定义maxKey保存在一次遍历的过程中，能够覆盖的最大的地区对应的广播的key
        String maxKey = null;   //表示未覆盖的地区最多的广播的key
        HashSet<String> maxSet = null;  //表示未覆盖的地区最多
        while (allAreas.size() != 0){
            for (String key : broadcasts.keySet()){
                tempSet.clear(); //重置临时集合
                //当前key能覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);  //将地区都加到tempSet中
                tempSet.retainAll(allAreas); //与allAreas取交集
                if (maxKey != null){
                    maxSet = broadcasts.get(maxKey); //max用于下面的比较
                    maxSet.retainAll(allAreas);
                }
                if (tempSet.size() > 0 &&           //选最好的
                        (maxKey == null || tempSet.size() > maxSet.size()) ){
                        maxKey = key;
                }
            }
            // 当maxKey不为空，则将它放到select
            if (maxKey != null){
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));  //从allAreas中移除指定的几个地区
            }
            maxKey = null;  //将其设置为空
        }
        System.out.println("结果:" + selects);
    }
}
