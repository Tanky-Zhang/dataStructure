package com.tanky.algorithm.horseridingchessboard;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 创建人： 19697
 * 创建时间： 2020/5/18
 * 作用：马踏棋盘算法的详细过程，首先是个简单未优化的版本
 * 修改信息：
 */
public class HorseRidingChessBoard {


    /**
     * 棋盘的宽度
     */
    private static int X;

    /**
     * 棋盘的高度
     */
    private static int Y;

    /**
     * 用来标记是否访问过了
     */
    private static boolean[] visizited;

    /**
     * 用来标记是否彻底访问完成
     */
    private static boolean isFinshed;


    public static void main(String[] args) {

        //先创建一个8*8的棋盘
        X = 8;
        Y = 8;

        visizited = new boolean[X * Y];

        int[][] chessBoard = new int[8][8];

        trvalCHessBoard(chessBoard, 1, 3, 1);

        for (int i = 0; i < chessBoard.length; i++) {

            System.out.println(Arrays.toString(chessBoard[i]));

        }

    }

    /**
     * 骑士周游问题递归操作方法
     *
     * @param chessBoard 传入的棋盘
     * @param row        传入的行
     * @param clonum     传入的点的列
     * @param step       走到某个点的步数
     */
    public static void trvalCHessBoard(int[][] chessBoard, int row, int clonum, int step) {

        //先把遍历的位置置为需要走的步数
        chessBoard[row][clonum] = step;

        //去把这个点的位置置为已经访问过
        int index = row * X + clonum;

        visizited[index] = true;

        //获取所有需要踩的下个点
        List<Point> next = next(new Point(clonum, row));

        //sortList(next);

        while (!next.isEmpty()) {

            Point remove = next.remove(0);

            //先查看这个点是否被访问过了
            if (!visizited[remove.y * X + remove.x]) {
                //开始递归
                trvalCHessBoard(chessBoard, remove.y, remove.x, step + 1);
            }

        }

        //需要进行回溯或者是还没有遍历完
        if (step < X * Y && !isFinshed) {
            chessBoard[row][clonum] = 0;
            visizited[index] = false;
        } else {
            isFinshed = true;
        }


    }

    /**
     * 该方法主要用来计算马儿能够走哪些点
     *
     * @param currentPoint
     * @return
     */
    public static List<Point> next(Point currentPoint) {

        List<Point> list = new ArrayList<>();

        //依次判断8个节点是否存在 可画图自己理解
        if (currentPoint.x - 2 >= 0 && currentPoint.y - 1 >= 0) {

            list.add(new Point(currentPoint.x - 2, currentPoint.y - 1));

        }

        if (currentPoint.y - 2 >= 0 && currentPoint.x - 1 >= 0) {

            list.add(new Point(currentPoint.x - 1, currentPoint.y - 2));

        }

        if (currentPoint.y - 2 >= 0 && currentPoint.x + 1 < X) {

            list.add(new Point(currentPoint.x + 1, currentPoint.y - 2));

        }

        if (currentPoint.y - 1 >= 0 && currentPoint.x + 2 < X) {

            list.add(new Point(currentPoint.x + 2, currentPoint.y - 1));

        }


        if (currentPoint.y + 1 < Y && currentPoint.x + 2 < X) {

            list.add(new Point(currentPoint.x + 2, currentPoint.y + 1));

        }

        if (currentPoint.y + 2 < Y && currentPoint.x + 1 < X) {

            list.add(new Point(currentPoint.x + 1, currentPoint.y + 2));

        }

        if (currentPoint.y + 2 < Y && currentPoint.x - 1 >= 0) {

            list.add(new Point(currentPoint.x - 1, currentPoint.y + 2));

        }

        if (currentPoint.y + 1 < Y && currentPoint.x - 2 >= 0) {

            list.add(new Point(currentPoint.x - 2, currentPoint.y + 1));

        }

        return list;

    }


    /* *
     * 利用贪心算法进行优化
     * 对获取的list的集合的所有元素进行非递减排序
     * 1，2，3，4，5 递增排序 5，4，3，2，1 递减排序  1，2，2，3，3，4，5 非递减
     *
     */

    public static void sortList(List<Point> points) {
        points.sort((Point o1, Point o2) -> {

            int size = next(o1).size();
            int size1 = next(o2).size();
            if (size < size1) {
                return -1;
            } else if (size > size1) {
                return 1;
            } else {
                return 0;
            }
        });

    }


}
