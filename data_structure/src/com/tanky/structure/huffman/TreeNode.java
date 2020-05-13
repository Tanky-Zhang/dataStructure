package com.tanky.structure.huffman;

/**
 * @Description: $
 * @Author: Tanky
 * @CreateDate: 2020/5/13$ 11:01$
 */
public class TreeNode implements Comparable {

    int value;

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    Byte data;

    TreeNode left;

    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(byte data, int value) {
        this.data = data;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(Object o) {
        return this.value - ((TreeNode) o).value;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "value=" + value + "}";
    }
}
