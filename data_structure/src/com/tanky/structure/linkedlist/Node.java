package com.tanky.structure.linkedlist;

import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;

/**
 * @author zhangzhongguo
 * @date 2020/01/07
 */
public class Node {

    int value;

    Node nextNode;

    public Node(int value) {

        this.value = value;

    }

    /**
     * 添加节点
     */
    public Node append(Node node) {

        Node node1 = this;

        while (true) {

            //最后一个几点停掉然后赋值
            if (node1.nextNode == null) {
                node1.nextNode = node;
                break;
            } else {

                node1 = node1.nextNode;

            }
        }

        return this;

    }

    /**
     * 查看是不是最后一个节点
     */
    public boolean isLast(Node node) {

        return node.nextNode == null;
    }

    /**
     * 删除单链表的下一个节点
     */
    public void delNextNode() {

        //避免删除的是最后一个节点的下一个节点所以要做健壮性处理
        if (this.nextNode == null) {
            return;
        }
        Node node1 = this.nextNode.nextNode;
        this.nextNode = node1;

    }

    /**
     * 往单链表中插入节点(在当前节点的后边)
     */
    public void insertNode(Node node) {

        Node nextNode = this.nextNode;
        this.nextNode = node;
        node.nextNode = nextNode;

    }

}
