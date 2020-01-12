package com.tanky.structure.linkedlist;

/**
 * 创建人： 19697
 * 创建时间： 2020/1/7
 * 作用：
 * 修改信息：
 */
public class TestNode {

    public static void main(String[] args) {

        Node n1=new Node(1);
        Node n2=new Node(2);
        Node n3=new Node(3);

        n1.append(n2).append(n3);

        System.out.println(n1.nextNode.nextNode.value);



    }


}
