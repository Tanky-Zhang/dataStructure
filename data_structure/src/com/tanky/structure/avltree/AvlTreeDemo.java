package com.tanky.structure.avltree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 平衡二叉树的实现细节
 * @Author: Tanky
 * @CreateDate: 2020/5/16
 */
public class AvlTreeDemo {

    public static void main(String[] args) {

        //{4,3,6,5,7,8};
        int[] arr = {3, 6, 5, 7, 8};

        AvlTree avlTree = new AvlTree(new Node(4));

        for (int i : arr) {
            //循环创建平衡二叉树
            avlTree.add(new Node(i));
        }

        System.out.println(avlTree.height());
        System.out.println(avlTree.leftHeight());
        System.out.println(avlTree.rightHeight());

    }

}


/**
 * 这里是平衡二叉树
 */
class AvlTree {

    Node root;

    public AvlTree(Node node) {
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

    public int rightHeight() {
        return root.rightHeight();
    }

    public int leftHeight() {
        return root.leftHeight();
    }

    public int height() {
        return root.getThreeHeight();
    }

    public List<Node> searchForParentNode(Node node) {
        List<Node> list = root.searchNode(node, null);
        return list;
    }


    /**
     * 找出左边最大的节点的值  向右寻找
     *
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


/**
 * 这是节点
 */
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

        //在添加了以后要判断是不是需要进行旋转 左旋 右旋 双旋 （能把人搞晕的旋转）


    }


    public int rightHeight() {

        if (this.right == null) {
            return 0;
        } else {
            return this.right.getThreeHeight();
        }

    }


    public int leftHeight() {

        if (this.left == null) {
            return 0;
        } else {
            return this.left.getThreeHeight();
        }

    }

    public int getThreeHeight() {
        return Math.max(this.left == null ? 1 : this.left.getThreeHeight() + 1, this.right == null ? 1 : this.right.getThreeHeight() + 1);
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

        //0号位置放置父节点，一号位置放置目标节点
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

    /**
     * 左旋转：
     * 1.以当前的根节点创建新的节点。
     * 2.把新的节点的左子树设置为当前节点的左子树
     * 3.把新节点的右子树设置为当前节点的右子树的左子树
     * 4.把当前节点的值替换成右子节点的值
     * 5.把当前节点的右子树设置成当前节点的右子树的右子树
     * 6.把当前节点的左子树（左子节点）设置成新的节点
     */
    public void leftRoate() {

        Node node = new Node(this.data);

        node.left=this.left;

        node.right=this.right.left;

        this.data=this.right.data;

        this.right=this.right.right;




    }
}