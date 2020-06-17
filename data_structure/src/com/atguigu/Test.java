package com.atguigu;

import java.util.Arrays;
import java.util.HashSet;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		HashSet<String> hashSet1 = new HashSet<String>();
//		HashSet<String> hashSet2 = new HashSet<String>();
//		hashSet1.add("1");
//		hashSet1.add("2");
//		hashSet1.add("100");
//
//		hashSet2.add("1");
//		hashSet2.add("2");
//		hashSet2.add("200");
//		hashSet1.retainAll(hashSet2);//
//		System.out.println("hashSet1=" + hashSet1);//[1,2]

        int[] coins = new int[]{1, 2, 5};

        coinChange(coins, 11);


    }

    public static int coinChange(int[] coins, int amount) {

        int dp[][] = new int[coins.length+1][amount+1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = i + 1; j < dp[0].length; j++) {
                dp[i][j] = amount + 1;
            }
        }

        for (int i = 1; i < coins.length - 1; i++) {

            for (int j = 1 + 1; j < amount; j++) {

                //dp[i][j] = 0;

                //最内层循环控制重复的钱币的个数
                for (int k = 0; k * coins[i] <= j; k++) {

                    dp[i][j] = Math.min(dp[i][j], dp[i][j - k * coins[i]] + k);

                }

            }

        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[coins.length - 1][amount - 1] == 0 ? -1 : dp[coins.length - 1][amount - 1];
    }

}
