package com.tanky.algorithm.divideandconquer;

/**
 * 创建人： 19697
 * 创建时间： 2020/5/21
 * 作用：分治算法解决汉诺塔问题。
 * 修改信息：
 */
public class Divide {


    public static void main(String[] args) {

        hanoTower(1, 'a', 'b', 'c');

    }


    public static void hanoTower(int n, char a, char b, char c) {


        if (n == 1) {
            System.out.println("将第" + n + "个盘子从" + a + "移动到" + c);
        } else {
            hanoTower(n - 1, a, c, b);
            System.out.println("将第" + n + "个盘子从" + a + "移动到" + c);
            hanoTower(n - 1, c, a, b);
        }


    }


}
