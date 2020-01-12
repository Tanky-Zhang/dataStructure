package com.tanky.structure.linkedlist;

/**
 * 循环链表
 * @author zhangzhg
 * @date 2020/01/07
 */
public class LoopNode {


    int value;

    /**
     * 任何一个单节点链表都是一个自循环的链表
     */
    LoopNode nextNode=this;

    public LoopNode(int value) {

        this.value = value;

    }

    /**
     * 插入方法同单链表是一样的不在赘述
     */




}
