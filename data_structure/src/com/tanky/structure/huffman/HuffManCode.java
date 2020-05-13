package com.tanky.structure.huffman;

import java.util.*;

/**
 * @Description: $
 * @Author: Tanky
 * @CreateDate: 2020/5/13$ 17:27$
 */
public class HuffManCode {

    Map<String,String> hufamanCodeMap=new HashMap<>(32);

    public static void main(String[] args) {

        //需要压缩的字符串
        String content = "i like like java java do you like java!!!";

        //获取其byte数组
        byte[] bytes = content.getBytes();

        Map<Byte, Integer> byteIntegerMap = new HashMap<>(32);


        //统计每个字符出现的次数即权值
        for (byte b : bytes) {
            if (byteIntegerMap.get(b) != null) {
                byteIntegerMap.put(b, byteIntegerMap.get(b) + 1);
            } else {
                byteIntegerMap.put(b, 1);
            }
        }

        //System.out.println(byteIntegerMap);

        //构建哈夫曼树
        List<TreeNode> treeNodes = new ArrayList<>();

        for (Map.Entry entry : byteIntegerMap.entrySet()) {
            treeNodes.add(new TreeNode((byte) entry.getKey(), (int) entry.getValue()));
        }

        Collections.sort(treeNodes);

        while (treeNodes.size() > 1) {
            TreeNode treeNode = new TreeNode(treeNodes.get(0).value + treeNodes.get(1).value);
            treeNode.setLeft(treeNodes.get(0));
            treeNode.setRight(treeNodes.get(1));
            treeNode.setData(null);
            treeNodes = treeNodes.subList(2, treeNodes.size());
            treeNodes.add(treeNode);
            Collections.sort(treeNodes);
        }




    }

    public void transCode(TreeNode treeNode){


        


    }


}
