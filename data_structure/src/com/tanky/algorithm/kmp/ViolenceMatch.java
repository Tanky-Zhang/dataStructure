package com.tanky.algorithm.kmp;

/**
 * 创建人： 19697
 * 创建时间： 2020/5/22
 * 作用：使用暴力匹配解决字符串匹配问题
 * 修改信息：
 */
public class ViolenceMatch {


    public static void main(String[] args) {

        String target = "ababababsscadf";

        String match = "abs";


        int i = 0;
        int j = 0;


        while (i < target.length() && j < match.length()) {

            if (target.charAt(i) == match.charAt(j)) {

                i++;
                j++;

            } else {

                i = i - j + 1;

                j = 0;

            }

        }

        if (j == match.length()) {

            System.out.println(i - j);

        } else {
            System.out.println(-1);
        }

    }


}
