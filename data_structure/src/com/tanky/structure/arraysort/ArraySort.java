package com.tanky.structure.arraysort;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 创建人： 19697
 * 创建时间： 2020/1/8
 * 作用：八种排序算法实现
 * 修改信息：
 */
public class ArraySort {


    public static void main(String[] args) {


    }

    //************************************************选择排序（分为简单选择排序和堆排序）****************************************************

    /**
     * 简单选择排序
     */
    public static void selectSort(int[] arr) {

        int minINdex = 0;
        int temp;

        for (int i = 0; i < arr.length; i++) {

            for (int j = i + 1; j < arr.length; j++) {

                if (arr[i] > arr[j]) {
                    minINdex = j;
                }

            }

            //此时不是最小值进行交换
            if (minINdex != i) {
                temp = arr[minINdex];

                arr[minINdex] = arr[i];

                arr[i] = temp;

            }


        }


    }

    //******************************************************堆排序*****************************************************************************
    /**
     * 堆排序代码实现
     */
    public  static  void  heapSort(int[] arr){








    }

    //****************************************************基数排序（队列实现，数组实现）**********************************************************

    /**
     * 基数排序的队列实现
     * 基数排序适合于数据位数不一样的数据进行排序
     */

    public static void baseSortByQueu(int[] arr) {

        int maxData = arr[0];
        for (int i = 1; i < arr.length; i++) {

            if (maxData < arr[i]) {

                maxData = arr[i];

            }

        }
        Queue<Integer>[] myqueue = new LinkedBlockingDeque[9];

        int length = (maxData + "").length();

        for (int j = 0, n = 1; j <= length; j++, n = n * 10) {

            for (int l = 0; l < arr.length; l++) {

                int index = arr[l] / n % 10;

                myqueue[index].offer(arr[l]);
            }


        }

        int index = 0;

        for (int m = 0; m < myqueue.length; m++) {

            while (!myqueue[m].isEmpty()) {

                arr[index] = myqueue[m].poll();
                index++;
            }

        }

    }

    /**
     * 基数排序的数组实现
     * 采用二维数组进行
     */
    public static void baseSortByArray(int[] arr) {

        //用来存放最大值，以便得出位数进行循环处理
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //获得最大值的位数
        int length = (max + "").length();

        //定义一个二维数组，存放数据,最多有九位数字所以横向坐标是定住的，纵向坐标最多也就是数组的长度，即此时所有数据放入到一个桶中
        int[][] temp = new int[10][arr.length];

        //定义一个一维数组存放每个桶中放了多少个数据
        int[] counts = new int[10];

        for (int j = 1, n = 1; j <= length; j++, n *= 10) {
            //对每个数据进行遍历
            for (int m = 0; m < arr.length; m++) {
                //计算余数决定放入到哪个桶中
                int last = arr[m] / n % 10;
                temp[last][counts[last]] = arr[m];
                counts[last]++;

            }

            //把数据取出来放入原数组中
            int index = 0;

            for (int l = 0; l < counts.length; l++) {

                if (counts[l] != 0) {

                    for (int q = 0; q < counts[l]; q++) {

                        arr[index] = temp[l][q];

                        index++;

                    }

                    counts[l] = 0;

                }

            }


        }


    }

    //**************************************************插入排序分为了直接插入排序和希尔排序此处是直接插入排序*********************************************************************

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {

        int temp;

        for (int i = 0; i < arr.length; i++) {


            //如果当前数字比前一个小才会进行排序
            if (arr[i] < arr[i - 1]) {

                temp = arr[i];

                int j;

                for (j = i - 1; j >= 0; j--) {

                    if (arr[j] <= temp) {

                        break;

                    }


                }

                arr[j + 1] = temp;


            }

        }

    }

    //**************************************************希尔排序(属于插入排序)***************************************************

    /**
     * 希尔排序实现
     * 最重要的是有个步长可以来进行控制
     * 可以按照步长进行分组
     */
    public static void shellSort(int[] arr) {

        //开始的步长为数组长度除以2，每次循环都要除以2
        for (int d = arr.length / 2; d > 0; d /= 2) {
            //遍历所有的元素
            for (int i = d; i < arr.length; i++) {
                //遍历本组的元素
                for (int j = i - d; j >= 0; j -= d) {
                    //把本组中大的值交换到后边，小的值交换到前边
                    if (arr[j] > arr[j + d]) {
                        int temp = arr[j];
                        arr[j] = arr[j + d];
                        arr[j + d] = temp;
                    }
                }
            }

        }


    }

    //****************************************************快速排序********************************************************

    /**
     * 快速排序的实现
     * 运用递归的思想来实现，思想就是找个基准数每次排序将数组变成右边比基准数大左边比基准数小
     */
    public static void quickSort(int[] arr, int start, int end) {

        //以第零个元素作为基准数
        int stander = arr[0];

        int low = start;

        int high = end;

        while (low < high) {

            //如果右边的数组比标准数据大此时不需要交换，只需要将坐标往前移
            while (low < high && arr[low] <= arr[high]) {

                high--;
            }

            //如果没有比标准数据大此时就可以用右边的数据替换左边的数据
            arr[low] = high;

            //此时经过上边的操作坐标已经移到了数组的左边，所以可以从左边开始遍历了
            while (low < high && arr[low] <= arr[high]) {
                low++;
            }
            //但是如果左边的数据没有比右边的大，此时需要交换
            arr[high] = arr[low];

            //把标准数据赋值给低（高）所在的位置，需要注意的是此时高位和低位已经重合了

            arr[low] = stander;

            //左边的数据和右边的数据分别进行递归调用

            quickSort(arr, start, low);

            quickSort(arr, low + 1, high);

        }


    }



}
