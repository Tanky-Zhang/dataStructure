package com.tanky.structure.binarysorttree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 二叉排序树的实现细节   一看就会一做就废系列
 * @Author: Tanky
 * @CreateDate: 2020/5/15
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {

        Node node = new Node(3);
        Node node1 = new Node(7);
        Node node2 = new Node(9);
        Node node3 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(1);

        //**********************************新增节点***************************************
        BinarySortTree binarySortTree = new BinarySortTree(node);
        binarySortTree.add(node1);
        binarySortTree.add(node2);
        binarySortTree.add(node3);
        binarySortTree.add(node4);
        binarySortTree.add(node5);

        //binarySortTree.midPrint();

        //***********************************删除节点************************************************

        binarySortTree.delete(node4);
        binarySortTree.midPrint();
        //System.out.println(node3.left);
//        List<Node> list = binarySortTree.searchForParentNode(node5);
//
//        //父节点
//        System.out.println(list.get(0).data);
//        //目标节点
//        System.out.println(list.get(1).data);


    }


}


class BinarySortTree {

    Node root;

    public BinarySortTree(Node node) {

        this.root = node;

    }

    public void add(Node node) {
        if (root == null) {
            return;
        }
        root.add(node);
    }

    public void midPrint() {
        root.midPrint();
    }


    public void delete(Node node) {

        //如果删除的是叶子节点然后直接找到父节点删除掉父节点的这个子节点即可
        List<Node> list = searchForParentNode(node);
        //父节点获取到
        Node node1 = list.get(0);
        Node node2 = list.get(1);

        //意味者没有找到这个节点
        if (node2 == null) {
            return;
        }

        //父节点为null意味者父节点是根节点 且这个棵树只有一个节点
        if (node1 == null) {
            this.root = null;
        }

        //删除的时候首先考虑第一种情况  删除的是叶子节点
        if (node2.left == null && node2.right == null) {

            //不是左节点就是右节点 两个分支即可
            if (node1.left.data == node2.data) {
                node1.left = null;
            } else {
                node2.right = null;
            }

        } else if (node2.left != null && node2.right != null) {

            //此时有两种解决方案，第一种是找左边最大的另外一种是找右边最小的  咱们来找下左边最大的数据把
            Node node3 = searchLeftBigNode(node2);
            //先把找出来的 最小节点删掉把
            delete(node3);
            //将当前节点的值改为最大节点的值
            node2.data = node3.data;

        } else {
            //目标节点的左节点不为null
            if (node2.left != null) {
                //其中又分了两种情况 1.当前节点为父节点的左节点
                if (node1.left.data == node2.data) {
                    node1.left = node2.left;
                } else {
                    node1.right = node2.left;
                }
            } else {
                //其中又分了两种情况 1.当前节点为父节点的左节点
                if (node1.left.data == node2.data) {
                    node1.left = node2.right;
                } else {
                    node1.right = node2.right;
                }
            }

        }

    }


    public List<Node> searchForParentNode(Node node) {

        List<Node> list = root.searchNode(node, null);

        return list;
    }


    /**
     * 找出左边最大的节点的值  向右寻找
     * @param node
     * @return
     */
    public Node searchLeftBigNode(Node node) {

        //向右找才是最大节点
        if (node.right == null) {
            return node;
        } else {
            return searchLeftBigNode(node.right);
        }

    }


}


class Node {

    int data;

    Node left;

    Node right;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


    /**
     * ------------添加一个节点-------
     *
     * @param node
     */
    public void add(Node node) {

        if (node.data < this.data) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }


    public void midPrint() {

        if (this.left != null) {
            this.left.midPrint();
        }

        System.out.println(this.data);

        if (this.right != null) {
            this.right.midPrint();
        }

    }

    public List<Node> searchNode(Node node, Node parent) {

        //0号位置防止父节点，一号位置防止目标节点
        List<Node> list = new ArrayList<>();

        if (node == null) {
            return null;
        }
        //Node parent=null;
        if (this.data == node.data) {
            list.add(parent);
            list.add(this);
            return list;
        } else if (node.data < this.data) {
            if (this.left == null) {
                return new ArrayList<>();
            }
            //如果小往左找
            return this.left.searchNode(node, this);

        } else {
            if (this.right == null) {
                return new ArrayList<>();
            }
            return this.right.searchNode(node, this);

        }

    }

}
