package com.tanky.structure.algorithm.backtrack;

/**
 * @Description: 利用回溯解决八皇后问题
 * @author: Tanky
 * @CreateDate: 2020/5/8
 * <p>
 * 要在里边放八个皇后，横向纵向和斜向都不可以有两个
 * <p>
 * 0   0   0   0   0   0   0   0
 * 0   0   0   0   0   0   0   0
 * 0   0   0   0   0   0   0   0
 * 0   0   0   0   0   0   0   0
 * 0   0   0   0   0   0   0   0
 * 0   0   0   0   0   0   0   0
 * 0   0   0   0   0   0   0   0
 * 0   0   0   0   0   0   0   0
 *
 * 放置皇后以后：
 * 1   0   0   0   0   0   0   0
 * 0   0   0   0   0   0   1   0
 * 0   0   0   0   1   0   0   0
 * 0   0   0   0   0   0   0   1
 * 0   1   0   0   0   0   0   0
 * 0   0   0   1   0   0   0   0
 * 0   0   0   0   0   1   0   0
 * 0   0   1   0   0   0   0   0
 *
 */
public class EightQueue {

    static int[][] eightQueue = new int[8][8];

    public static void main(String[] args) {

        settleQueue(0);

        printArray();

    }

    /**
     * 我们来定义一个打印所有点位的方法
     */
    public static void printArray() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(eightQueue[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 开始写递归方法
     */
    public static boolean settleQueue(int y) {
        //这个是终止条件
        if (y == 8) {
            return true;
        }

        //接下来判断递归以及回溯
        for (int j = 0; j < 8; j++) {
            //清洗数据，避免回溯的时候造成脏数据
            for (int x = 0; x < 8; x++) {
                eightQueue[x][y] = 0;
            }
            //检查当前点位能不能放入数据
            if (checkQueue(j, y)) {
                //如果可以放入 那么就将当前点位 置为1 且递归下一个点位
                eightQueue[j][y] = 1;
                if (settleQueue(y + 1)) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * 定义一个判断横向纵想和斜向有没有数据的方法
     */
    public static boolean checkQueue(int x, int y) {
        for (int i = 0; i < y; i++) {
            //检查一下纵向
            if (eightQueue[x][i] == 1) {
                return false;
            }
            //x-i得出来的是行 且行随着i的增大而减小，但是必须判断x-i是 大于0的，因为很有可能出现i>x的情况
            if (x - i >= 1 && eightQueue[x - 1 - i][y - 1 - i] == 1) {
                return false;
            }
            //判断右上侧的倾斜
            if (x + 1 + i < 8 &&eightQueue[x + 1 + i][y - 1 - i] == 1){
                return false;
            }
        }
        return true;
    }

}
