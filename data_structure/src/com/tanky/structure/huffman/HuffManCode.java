package com.tanky.structure.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: $
 * @Author: Tanky
 * @CreateDate: 2020/5/13$ 11:00$
 */
public class HuffManCode {


    public static void main(String[] args) {
        int arr[] = new int[]{2, 5, 9, 6, 3, 2, 1};
        buildHuffManTree(arr);
    }


    /**
     * 构建哈夫曼树
     *
     * @param arr
     */
    public static void buildHuffManTree(int[] arr) {

        List<TreeNode> nodesList = new ArrayList<>();
        //构造node的list
        for (int i : arr) {
            nodesList.add(new TreeNode(i));
        }
        //然后对list进行排序
        Collections.sort(nodesList);

        while (nodesList.size() > 1) {
            TreeNode treeNode = new TreeNode(nodesList.get(0).value + nodesList.get(1).value);
            treeNode.setLeft(nodesList.get(0));
            treeNode.setRight(nodesList.get(1));
            nodesList = nodesList.subList(2, nodesList.size());
            nodesList.add(treeNode);
            Collections.sort(nodesList);
        }

        //到此正确
        frontPrint(nodesList.get(0));

    }

    /**
     * 写一个前序遍历的方法来验证数据
     *
     * @param treeNode
     */
    public static void frontPrint(TreeNode treeNode) {

        System.out.println(treeNode.value);

        if (treeNode.left != null) {
            frontPrint(treeNode.left);
        }

        if (treeNode.right != null) {
            frontPrint(treeNode.right);
        }

    }

}
