package com.tanky.structure.graph;


import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 有关图结构的遍历以及最短路径的算法
 *
 * @author zhangzhg
 * @date 2020/01/05
 */
public class Graph {

    /**
     * 创建一个二维数组存放点与点之间的关系
     */
    private static boolean[][] isConnected;
    /**
     * 定义一个顶点的数组
     */
    private static String[] vertex;
    /**
     * 定义是否被访问的数组实现
     */
    private static boolean[] isVisited;
    /**
     * 深度优先的栈对象初始化
     */
    private static Stack<Integer> mystack = new Stack<>();
    /**
     * 广度优先搜索的队列对象的初始化
     */
    private static Queue<Integer> queue = new LinkedBlockingDeque<>();
    /**
     * 当前遍历的下标
     */
    private static int currentIndex = 0;

    public static void main(String[] args) {

        //创建图结构
        vertex = new String[]{"A", "B", "C", "D", "E", "F","G"};
        isConnected = new boolean[vertex.length][vertex.length];

        isVisited = new boolean[]{false, false, false, false, false, false,false};
        createGraph("A", "B");
        createGraph("A", "C");
        createGraph("A", "E");
        createGraph("C", "B");
        createGraph("D", "B");
        createGraph("E", "B");
        createGraph("A", "F");
        createGraph("D", "G");
        for (int m = 0; m < 7; m++) {
            for (int n = 0; n < 7; n++) {
                if (m == n) {
                    isConnected[m][n] = true;
                }
            }
        }
        //dfs();
        System.out.println("----------------------------------------------------");
        //bfs();

        System.out.println(getShortPath("A", "G"));


    }

    /**
     * 创建点与点之间的关系
     *
     * @param var1 一个顶点
     * @param var2 一个顶点
     */
    private static void createGraph(String var1, String var2) {

        int m = -1;
        int n = -1;
        //遍历顶点数组找出其对应的索引然后将连接信息初始化
        for (int i = 0; i < vertex.length; i++) {
            if (var1.equals(vertex[i])) {
                m = i;
            } else if (var2.equals(vertex[i])) {
                n = i;
            }
        }
        if (m != -1 && n != -1) {
            isConnected[m][n] = true;
            isConnected[n][m] = true;
        }

    }

    //*************************************************************深度优先搜索算法实现******************************************************

    /**
     * 深度优先搜索算法
     * 使用java栈结构实现
     */
    private static void dfs() {

        //第零个元素初始化为被访问的状态
        isVisited[0] = true;
        mystack.push(0);
        //打印栈顶元素
        System.out.println(vertex[0]);

        while (!mystack.isEmpty()) {


            for (int j = currentIndex; j < vertex.length; j++) {

                //意思是如果这两个点相连接且当前的节点没有被访问过
                if (isConnected[currentIndex][j] && !isVisited[j]) {
                    mystack.push(j);
                    isVisited[j] = true;
                    System.out.println(vertex[j]);
                    currentIndex = mystack.peek();

                }

            }

            mystack.pop();
            //循环完毕以后需要弹栈,且需要将当前的索引置为栈的栈顶索引
            if (!mystack.isEmpty()) {
                currentIndex = mystack.peek();
            }


        }

    }

    //***********************************************广度优先搜索算法实现******************************************************

    /**
     * 深度优先搜索算法的实现
     * 依靠队列实现
     */
    private static void bfs() {

        isVisited[0] = true;
        //输出顶点
        System.out.println(vertex[0]);
        //将第一个元素推入队列
        queue.offer(0);

        while (!queue.isEmpty()) {

            for (int j = currentIndex; j < vertex.length; j++) {

                if (isConnected[currentIndex][j] && !isVisited[j]) {

                    queue.offer(j);
                    System.out.println(vertex[j]);
                    isVisited[j] = true;

                }

            }
            queue.poll();
            if (!queue.isEmpty()) {
                currentIndex = queue.peek();

            }

        }

    }

    //***********************************************************求最短路径应当使用广度优先搜索要更为快速一点**********************************************************

    /**
     * 获取两点之间的最短路径
     */
    private static int getShortPath(String args1, String args2) {

        //用以存放到每个节点的距离
        int[] dist = new int[]{0, 0, 0, 0, 0, 0,0};

        int p = 0;
        for (int q = 0; q < vertex.length; q++) {

            if (vertex[q].equals(args1)) {

                currentIndex = q;
            }
            if (vertex[q].equals(args2)) {
                p = q;

            }

        }
        //将开始元素推入队列头
        queue.offer(currentIndex);

        isVisited[currentIndex] = true;

        while (!queue.isEmpty()) {

            for (int j = currentIndex; j < vertex.length; j++) {

                if (isConnected[currentIndex][j] && !isVisited[j]) {

                    dist[j] = dist[currentIndex] + 1;
                    queue.offer(j);
                    isVisited[j] = true;

                }

            }

            queue.poll();
            if (!queue.isEmpty()) {
                currentIndex = queue.peek();
            }

        }

        return dist[p];

    }


}
