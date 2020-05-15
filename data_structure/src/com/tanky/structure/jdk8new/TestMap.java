package com.tanky.structure.jdk8new;

import java.util.*;

/**
 * @Description: 测试map的相关操作
 * @Author: Tanky
 * @CreateDate: 2020/5/15
 */
public class TestMap {


    public static void main(String[] args) {

        Map map = new HashMap();
        map.put("aa", 111);

        List list = new ArrayList(3);

        list.add(1);

        list.add(2);

        list.add(3);

        List list1=new ArrayList();

        for (Object o : list) {

            String string = UUID.randomUUID().toString();
            map.put("UUID",string);
            list1.add(map);

        }

        System.out.println(list1);

    }


}
