package com.tanky.structure.linkedlist;

/**
 * 双链表实现
 * @author zhangzhg
 * @date 2020/01/07
 */
public class DoubleNode {


    DoubleNode nextNode=this;


    DoubleNode preNode=this;


    int value;

    public DoubleNode(int value){


        this.value=value;



    }


}
