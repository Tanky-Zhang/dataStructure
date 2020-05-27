package com.tanky.structure.arraysort;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 创建人： 19697
 * 创建时间： 2020/1/8
 * 作用：十种排序算法实现
 * 修改信息：
 */
public class ArraySort {


    public static void main(String[] args) {


        int[] arr=new int[]{8,3,2,7,4,5,1};


        heapSort(arr);

        System.out.println(Arrays.toString(arr));

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
     *
     * 顺序存储的二叉树有这么几个特点(通常只考虑完全二叉树)：
     *
     * 第n个元素的左子节点为2*n+1
     * 第n个元素的右子节点为2*n+2
     * 第n个元素的父节点为(n-1)/2
     *
     */
    public static void heapSort(int[] arr) {

        //计算开始位置，从最后一层叶子节点的父节点开始遍历  因为是要索引所以-2
        int start = (arr.length - 2) / 2;

        //第一次首先将其调整为大顶堆
        for (int i=start;i<arr.length-1;i++){
            heapMax(arr, arr.length, i);
        }


        for (int i = arr.length - 1; i >= 0; i--) {

            int temp = arr[i];

            arr[i] = arr[0];

            arr[0] = temp;

            //因为第一次操作以后已经构成了大顶堆，所以每次可以从0向后进行调整
            heapMax(arr, i, 0);

        }

    }

    /**
     * 找出大顶堆
     *
     * 顺序存储的二叉树有这么几个特点(通常只考虑完全二叉树)：
     *
     * 第n个元素的左子节点为2*n+1
     * 第n个元素的右子节点为2*n+2
     * 第n个元素的父节点为(n-1)/2
     */
    public static void heapMax(int[] arr, int size, int index) {

        if (index < size) {

            //找出左子树和右子树的位置坐标
            int leftNode = 2 * index + 1;
            int rightNode = 2 * index + 2;

            //一开始默认当前节点为最大值
            int max = index;

            if (leftNode < size && arr[max] < arr[leftNode]) {
                max = leftNode;
            }
            if (rightNode < size && arr[max] < arr[rightNode]) {
                max = rightNode;
            }

            if (max != index) {

                int temp = arr[max];

                arr[max] = arr[index];

                arr[index] = temp;

                //这次交换可能导致了其子树的结构变化所以需要重新进行排序
                heapMax(arr, size, max);

            }

        }


    }

    //****************************************************基数排序（队列实现，数组实现）**********************************************************

    /**
     * 基数排序的队列实现
     * 基数排序适合于数据位数不一样的数据进行排序
     */

    public static void baseSortByQueue(int[] arr) {

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

    //****************************************************归并排序**************************************************


    /**
     * 归并排序
     *
     * @param start
     * @param end
     * @param old
     */
    public static void merge(int start, int end, int[] old, int[] temp) {

        if (start < end) {
            int midle = (end + start) / 2;
            merge(start, midle, old, temp);
            merge(midle + 1, end, old, temp);
            merageSort(start, end, midle, old, temp);
        }
    }


    public static void merageSort(int start, int end, int midle, int[] old, int[] temp) {

        int p1 = start;
        int p2 = end;
        int p3 = midle + 1;
        int index = 0;

        while (p1 < midle && p3 < p2) {

            if (old[p1] < old[p3]) {
                temp[index] = old[p1];
                p1++;
            } else {
                temp[index] = old[p3];
                p3++;
            }
            index++;
        }

        if (p1 < midle) {
            for (int i = p1; i < midle; i++) {
                temp[index] = old[p1];
                index++;
            }
        }

        if (p2 < p3) {
            for (int i = p2; i < p3; i++) {
                temp[index] = old[p2];
                index++;
            }
        }
        for (int j = 0; j < temp.length; j++) {
            old[start + j] = temp[j];
        }

    }

    //**********************************************冒泡排序*************************************************

    /**
     * 冒泡排序
     *
     * @param array
     */
    private static void bubbleSort(int[] array) {

        int temp;

        for (int i = 0; i < array.length; i++) {

            for (int j = i + 1; j < array.length; j++) {

                if (array[i] > array[j]) {

                    temp = array[j];

                    array[j] = array[i];

                    array[i] = temp;

                }

            }

        }

    }
    //*****************************************桶排序*************************************************

    /**
     * 桶排序的代码实现过程
     *
     * @param arr
     */
    public static void bucketSort(int[] arr) {

        //找出数组中的最大元素和最小元素
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        //计算桶的个数
        int length = (max - min) / arr.length + 1;

        //为了计算简单此处不再使用二维数组，直接采用list嵌套list的形式来进行存储

        List<ArrayList<Integer>> arrayLists = new ArrayList<>(length);

        //开始构建length个桶
        for (int j = 0; j < length; j++) {
            arrayLists.add(new ArrayList<>());
        }

        // 将每个元素放入桶
        for (int i = 0; i < arr.length; i++) {
            int num = (arr[i] - min) / (arr.length);
            arrayLists.get(num).add(arr[i]);
        }

        // 对每个桶进行排序
        for (int i = 0; i < arrayLists.size(); i++) {
            Collections.sort(arrayLists.get(i));
        }

        // 将桶中的元素赋值到原序列
        int index = 0;
        for (int i = 0; i < arrayLists.size(); i++) {
            for (int j = 0; j < arrayLists.get(i).size(); j++) {
                arr[index++] = arrayLists.get(i).get(j);
            }
        }

    }

    //******************************************计数排序**********************************************

    /**
     * 计数排序的实现过程
     *
     * @param arr
     */
    public static void countSort(int[] arr) {

        //找出数组中的最大元素和最小元素
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //得出对应的数组的长度
        int d = max - min;
        //创建统计数组  并计算统计对应元素个数
        int[] countArray = new int[d + 1];
        for (int j = 0; j < arr.length; j++) {
            countArray[arr[j] - min]++;
        }

        //对统计数组进行变形 后边的元素等于前边的元素之和
        int sum = 0;
        for (int m = 0; m < countArray.length; m++) {
            sum += countArray[m];
            countArray[m] = sum;
        }

        //对原始数组倒序
        int[] sortArray = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            sortArray[countArray[arr[i] - min] + 1] = arr[i];
            countArray[arr[i] - min]--;
        }

        System.arraycopy(sortArray, 0, arr, 0, sortArray.length);
    }

}
