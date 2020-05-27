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

        int[] arr = new int[100];

        for (int i = 1; i <= 100; i++) {

            arr[i - 1] = i;

        }

        // int i = insertValueSearch(arr, 0, 99, 50);


        int i = commonBinarySearch(arr, 0, 99, 50);

        System.out.println(i + "====================");


    }

    //*********************************************折半二分查找*******************************************************

    static int commonBinarySearch(int[] arr, int left, int right, int findVal) {


        if (findVal < arr[0] || findVal > arr[arr.length - 1] || left > right) {

            return -1;
        }

        int mid = (left + right) / 2;

        if (arr[mid] > findVal) {

            commonBinarySearch(arr, 0, mid - 1, findVal);

        } else if (arr[mid] < findVal) {

            commonBinarySearch(arr, mid + 1, right, findVal);

        } else {

            return mid;

        }

        return -1;


    }

    //**********************************************差值二分查找算法***************************************************


    /**
     * @param arr     传入的数组
     * @param left    左边索引
     * @param right   右边的索引
     * @param findVal 要找的值
     */
    static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        if (findVal < arr[0] || findVal > arr[arr.length - 1] || left > right) {

            return -1;
        }


        //其实看这个差值查找的算法公式其实就是在寻找一个占比的问题，就是再看索引的占比
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);

        if (findVal < arr[mid]) {

            insertValueSearch(arr, left, mid - 1, findVal);

        } else if (findVal > arr[mid]) {

            insertValueSearch(arr, mid + 1, right, findVal);

        } else {

            return mid;

        }

        return -1;

    }


    //****************************************二分法查找的斐波那契查找***************************************************

    static int[] fib() {

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
    //*****************************************非递归方式的二分查找算法****************************************************

    public static int commonSearchNorecur(int[] arr, int fidVal) {


        int left = 0;

        int right = arr.length - 1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (arr[mid] == fidVal) {
                return mid;
            } else if (fidVal < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return -1;
    }


}
