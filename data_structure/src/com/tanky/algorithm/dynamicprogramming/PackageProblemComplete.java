package com.tanky.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 利用动态规划解决完全背包问题。
 */
public class PackageProblemComplete {

    public static void main(String[] args) {

        //定义三个背包的重量
        int[] weight = {1, 4, 3, 5};

        //定义三个背包的价值
        int[] value = {1500, 3000, 2000, 5000};

        //定义一共有多少个物品
        int n = value.length;

        //定义背包所能够承载的最大重量
        int m = 10;

        int[][] dp = new int[n + 1][m + 1];

        //定义一个二维数组用来存放放入物品的顺序
        int[][] path = new int[n + 1][m + 1];


        //完全背包问题无非就是商品可以重复选择，此时需要在原来的基础上进行多一次的循环，就是对于选择的商品数量进行循环
        for (int i = 1; i < dp.length; i++) {

            for (int j = 1; j < dp[0].length; j++) {

                for (int k = 0; k * weight[i-1] < j; k++) {

                    dp[i][j] = Math.max(dp[i - 1][j], value[i-1] + dp[i - 1][j - k * weight[i - 1]]);

                }

            }

        }

        for (int i = 0; i < dp.length; i++) {

            System.out.println(Arrays.toString(dp[i]));

        }


    }


}
