package com.tanky.structure.huffman;

/**
 * @Description: 做一个类来 测试byte的相关内容
 * @Author: Tanky
 * @CreateDate: 2020/5/14
 */
public class TestByte {

    public static void main(String[] args) {

        //70   01000110     ‭01000110‬   1000110    101000110   -70  11111111111111111111111110111010

        //1000 0000 0000 0000 0000 0000 0000 0010
        //1111 1111 1111 1111 1111 1111 1111 1101
        //1111 1111 1111 1111 1111 1111 1111 1110
        System.out.println(Integer.toBinaryString(5));

        System.out.println((70|256));

        System.out.println(Integer.toBinaryString((70|256)));


    }
}
