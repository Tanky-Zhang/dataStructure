package com.tanky.algorithm.kmp;

import java.util.Arrays;

/**
 * 创建人： zzg
 * 创建时间： 2020/5/21
 * 作用：利用kmp算法解决字符串匹配问题，部分匹配表
 * 修改信息：
 */
public class Kmp {


    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        //String str2 = "BBC";

        int[] next = kmpNext("ABCDABD");
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str2, str1, next);
        System.out.println("index=" + index);

    }

    /**
     * 执行kmp匹配的算法实现
     *
     * @param target 所要寻找的串串
     * @param str    要在哪个串串中寻找
     * @param next   部分匹配的数组
     */
    private static int kmpSearch(String target, String str, int[] next) {


        for (int i = 0, j = 0; i < str.length(); i++) {

            //同样是一直循环着找，直到找到有重复的第一个然后开始执行下边的，如果不相等意味着这里需要j置为0重新开始，让主串当前位置和模式串j位置比较
            while (j > 0 && str.charAt(i) != target.charAt(j)) {
                j = next[j - 1];
            }

            //如果找到了第一个开始
            if (str.charAt(i) == target.charAt(j)) {
                j++;
            }

            if (j == target.length()) {
                return i - j + 1;
            }

        }

        return -1;

    }

    /**
     * 求出部分匹配表
     *
     * @param str 在哪个串中寻找
     * @return 部分匹配表的值
     */
    private static int[] kmpNext(String str) {

        int next[] = new int[str.length()];

        //第一个是0 因为只有一个元素的时候就是0
        next[0]=0;

        for (int i = 1, j = 0; i < str.length(); i++) {

            //开始进行循环直到找到第一个相同的字符跳出循环
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }

            //处理相同的字符，如果到了j+1以后继续执行上边的while循环。
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        return next;

    }


}
