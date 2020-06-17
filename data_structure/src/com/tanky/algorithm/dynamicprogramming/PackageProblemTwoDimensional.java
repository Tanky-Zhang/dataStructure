package com.tanky.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 创建人： 19697
 * 创建时间： 2020/6/14
 * 作用：
 * 修改信息：二维费用的背包问题，这里是多了一个限制条件。
 */
public class PackageProblemTwoDimensional {


    public static void main(String[] args) {

        //定义三个背包的重量
        int[] weight = {1, 4, 3};
        //第二维的重量限制
        int[] weight2 = {2, 4, 6};

        //定义三个背包的价值
        int[] value = {1500, 3000, 2000};

        //定义背包所能够承载的最大重量
        int m = 4;
        //第二维的重量限制
        int m2 = 8;

        int[][] dp = new int[m + 1][m2 + 1];


        for (int i = 1; i <weight.length; i++) {

            for (int j = m; j >= weight[i - 1]; j--) {

                for (int k = m2; k >= weight2[i - 1]; k--) {

                    dp[j][k] = Math.max(dp[j][k], dp[j - weight[i - 1]][k - weight2[i - 1]] + value[i - 1]);

                }

            }


        }


        for (int i = 0; i < dp.length; i++) {

            System.out.println(Arrays.toString(dp[i]));

        }


    }

}
