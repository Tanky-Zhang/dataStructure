package com.tanky.algorithm.dijkstra;

import java.util.Arrays;

/**
 * 创建人： 19697
 * 创建时间： 2020/5/18
 * 作用：用迪杰斯特拉算法来解决最短路径的问题 非常的巧妙  迪杰斯特拉算法的思路就是先去找当前节点到每个节点的最小距离，
 * 然后找到最小的距离点位以后再次以这个节点位起点然后再次找出最短路径，以此类推最终找出开始点位到所有点位的最短路径
 * 修改信息：
 */
public class Dijkstra {

    public static void main(String[] args) {

        int N = 65535;

        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];

        int[] dis = new int[vertex.length];

        // N表示不可以连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};


        Graph graph = new Graph(matrix, vertex, dis);

        graph.dijkstra(6);

        System.out.println(Arrays.toString(dis));


    }


}


class Graph {

    /**
     * char数组存放顶点
     */
    private char[] vertex;

    /**
     * 存放图的邻接矩阵
     */
    private int[][] matrix;

    /**
     * 存放的是距离的数组
     */
    private int[] dis;

    /**
     * 标记是否访问过的的一个数组
     */
    private boolean[] isVisited;

    /**
     * 访问过某个节点的前驱节点的数组
     */
    private char[] preVisited;


    public Graph(int[][] matrix, char[] vertex, int[] dis) {
        this.vertex = vertex;
        this.matrix = matrix;
        isVisited = new boolean[vertex.length];
        preVisited = new char[vertex.length];
        this.dis = dis;
        Arrays.fill(this.dis, 65535);
    }


    public void dijkstra(int index) {

        isVisited[index] = true;

        dis[index] = 0;

        //首先处理 当前节点到每个节点的最短距离
        updateDis(index);

        //接下来遍历每个节点  因为已经处理过一个节点了  所以开始下标从1开始就可以了
        for (int i = 1; i < vertex.length; i++) {

            //返回之前的index的节点距离最短的节点
            int shortPath = getShortPath();

            updateDis(shortPath);

        }


    }

    private int getShortPath() {

        int min = 65535;

        int index = 0;

        for (int i = 0; i < dis.length; i++) {

            if (!isVisited[i] && dis[i] < min) {
                min = dis[i];
                index = i;
            }

        }

        //当前节点置为访问过就可以了
        isVisited[index] = true;

        return index;


    }


    private void updateDis(int index) {

        //先去更新起始点位到所有点位的距离
        for (int i = 0; i < matrix[index].length; i++) {

            //起始点位到当前点位的距离为起始点位到index的距离+index到当前点位的距离
            int len = dis[index] + matrix[index][i];

            //没有访问过且长度小于之前的长度
            if (len < dis[i] && !isVisited[i]) {
                //长度要变化过来
                dis[i] = len;
                //前驱节点要修改
                preVisited[i] = vertex[index];

            }
        }
    }

}
