package com.tanky.algorithm.greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 创建人： 19697
 * 创建时间： 2020/5/21
 * 作用：利用贪心算法解决集合覆盖问题,/思路就是每次找出覆盖率最大的电台，
 * 在这个过程中allAreas是不断变化的，他是不断的将已经找出来的集合移除掉，直到这个set为空为止
 * 修改信息：
 */
public class Greedy {


    public static void main(String[] args) {


        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();

        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");


        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);


        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");


        //思路就是每次找出覆盖率最大的电台，在这个过程中allAreas是不断变化的，他是不断的将已经找出来的集合移除掉，直到这个set为空为止
        String maxKey = null;

        Set selects = new HashSet();

        while (!allAreas.isEmpty()) {

            //通过循环找出了能够覆盖最大地区的广播的key值
            for (String key : broadcasts.keySet()) {

                HashSet<String> temSet = broadcasts.get(key);
                //与全部地区集合求取交集，求出的结果放入temSet中
                temSet.retainAll(allAreas);

                //接下来进行判断看看此时是不是最大值

                if (temSet.size() > 0 && (maxKey == null || temSet.size() > broadcasts.get(maxKey).size())) {

                    maxKey = key;

                }


            }

            if (maxKey != null) {
                allAreas.removeAll(broadcasts.get(maxKey));
                selects.add(maxKey);
            }

            maxKey = null;


        }

        System.out.println(selects);


    }


}
