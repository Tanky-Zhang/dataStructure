package com.tanky.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 创建人： 19697
 * 创建时间： 2020/6/14
 * 作用：
 * 修改信息：升级版本的01背包问题，就是减小了空间复杂度，利用一维数据进行动态规划
 * 首先分析为
 */
public class PackageProblem01Upgrage {


    public static void main(String[] args) {

        //定义三个背包的重量
        int[] weight = {1, 4, 3};

        //定义三个背包的价值
        int[] value = {1500, 3000, 2000};

        //定义一共有多少个物品
        int n = value.length;

        //定义背包所能够承载的最大重量
        int m = 4;

        int[] dp = new int[m + 1];

        for (int i = 0; i < weight.length; i++) {

            for (int j = m; j >=weight[i]; j--) {

                if (j>=weight[i]) {

                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);

                }

                System.out.println(Arrays.toString(dp));

            }

        }




    }


}
