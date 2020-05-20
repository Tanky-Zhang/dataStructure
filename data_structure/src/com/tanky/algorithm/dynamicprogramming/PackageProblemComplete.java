package com.tanky.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * ���ö�̬�滮�����ȫ�������⡣
 */
public class PackageProblemComplete {

    public static void main(String[] args) {

        //������������������
        int[] weight = {1, 4, 3, 5};

        //�������������ļ�ֵ
        int[] value = {1500, 3000, 2000, 5000};

        //����һ���ж��ٸ���Ʒ
        int n = value.length;

        //���屳�����ܹ����ص��������
        int m = 10;

        int[][] dp = new int[n + 1][m + 1];

        //����һ����ά����������ŷ�����Ʒ��˳��
        int[][] path = new int[n + 1][m + 1];


        //��ȫ���������޷Ǿ�����Ʒ�����ظ�ѡ�񣬴�ʱ��Ҫ��ԭ���Ļ����Ͻ��ж�һ�ε�ѭ�������Ƕ���ѡ�����Ʒ��������ѭ��
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
