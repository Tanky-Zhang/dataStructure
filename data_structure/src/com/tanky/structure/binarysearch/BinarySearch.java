package com.tanky.structure.binarysearch;

import java.util.Arrays;

/**
 * 创建人： 19697
 * 创建时间： 2020/5/25
 * 作用：
 * 修改信息：
 */
public class BinarySearch {


    public static void main(String[] args) {


    }

    //*********************************************等分二分查找*******************************************************

    //**********************************************差值二分查找算法***************************************************

    //****************************************二分法查找的斐波那契查找***************************************************

    public static int[] fib() {

        //申请一个而是大小的数组
        int[] f = new int[20];

        f[0] = 1;
        f[1] = 1;

        for (int j = 2; j < f.length; j++) {
            f[j] = f[j - 1] + f[j - 2];
        }

        return f;

    }

    /**
     * @param a   斐波那契的数组
     * @param key 需要寻找的目标数据
     */
    public static int fibSearch(int[] a, int key) {

        int low = 0;

        int mid = 0;

        int height = a.length - 1;

        //分割的下表
        int k = 0;

        //获得斐波那契数组
        int f[] = fib();

        while (height > f[k] - 1) {
            k++;
        }

        //意思就是将a拷贝到f[k]长度的temp数组中去
        int[] temp = Arrays.copyOf(a, f[k]);

        //然后处理一下后几位
        for (int i = height + 1; i < temp.length; i++) {

            temp[i] = a[height];
        }

        while (low <= height) {

            //核心推导的公式
            mid = low + f[k - 1] - 1;

            if (key < a[mid]) {

                height = mid - 1;

                k--;

            } else if (key > a[mid]) {

                low = mid + 1;

                k -= 2;

            } else {

                if (mid < height) {

                    return mid;
                } else {
                    return height;

                }

            }


        }

        return -1;


    }


}
