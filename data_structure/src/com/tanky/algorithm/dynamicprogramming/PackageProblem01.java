package com.tanky.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 创建人： zzg
 * 创建时间： 2020/5/20
 * 作用：利用动态规划解决01背包问题
 * 修改信息：
 * <p>
 * dp二维数组：
 * <p>
 * 1磅   2磅   3磅    4磅
 * 物品A   1500, 1500, 1500, 1500
 * <p>
 * 物品B  1500, 1500, 1500, 3000
 * <p>
 * 物品C  1500, 1500, 2000, 3500
 */
public class PackageProblem01 {


    public static void main(String[] args) {

        //定义三个背包的重量
        int[] weight = {1, 4, 3};

        //定义三个背包的价值
        int[] value = {1500, 3000, 2000};

        //定义一共有多少个物品
        int n = value.length;

        //定义背包所能够承载的最大重量
        int m = 4;

        //定义一个二维数组用来存放放入物品的顺序
        int[][] path = new int[n + 1][m + 1];


        //定义一个二维数组，这个数组用来存放,dp[i][j]代表了当第i个物品放入容量为j的背包中以后的价值，
        //需要注意的是此时的背包的容量是变化的，因为随着放入背包的物品的增加背包的剩余容量是不断变小的，所以
        //通过逆向思维考虑，背包的容量随着物品的增加可以是不断增大的，直到到达了最大的容量
        int[][] dp = new int[n + 1][m + 1];

        //双层循环，最外层是物品的循环内层是容量的循环
        for (int i = 1; i < dp.length; i++) {

            for (int j = 1; j < dp[0].length; j++) {

                //此时分为了两种情况，当想要放入物品的重量已经超出了剩余的背包容量，那么此时就直接获取到放置上一个物品的时候的最大价值
                //此时上一个物品的最大价值就是在这个物品放入时的最大价值。j此时就代表了重量 i是从1开始的。所以用weight[i-1]
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //此时是第二种情况，那就是放入这个物品的重量小于剩余容量。   j-weight[i-1]在这个重量位置的数据早就已经算出来，
                    //所以要比较的是上一个物品放入时处在改背包容量下的最大值和上一个物品放入时处在 j-weight[i-1]在这个重量时的最大值与当前物品价值的和
                    //的大小比较。。。。。。。。。。。。。。。。状态转换方程有点不太好理解。。。。。。。。。。。。。。。。
                    //dp[i][j] = Math.max(dp[i - 1][j], value[i - 1] + dp[i - 1][j - weight[i - 1]]);
                    //想要存储放入背包的物品需要拆开上边的公式。

                    if (dp[i - 1][j] < value[i - 1] + dp[i - 1][j - weight[i - 1]]) {
                        //意味着此时需要将当前物品放入背包
                        //用等于1来标记放入背包
                        path[i][j] = 1;
                        dp[i][j] = value[i - 1] + dp[i - 1][j - weight[i - 1]];

                    } else {

                        dp[i][j] = dp[i - 1][j];
                    }

                }

            }


        }


        for (int i = 0; i < dp.length; i++) {

            System.out.println(Arrays.toString(dp[i]));

        }

        //最后来遍历一下放入背包的物品 此时需要倒序遍历
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                //意味着放入了背包
                System.out.println(i + "放入背包");
                j = j - weight[i - 1];
            }

            i--;

        }


    }


}
