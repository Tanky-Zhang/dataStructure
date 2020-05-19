package com.tanky.algorithm.floyd;

import java.util.Arrays;

/**
 * @Description: 佛洛依德算法的实现细节，利用佛洛依德算法求取最短路径的实现。该算法的核心思想比较容易理解，就是找一个中间节点
 * 去判断，利用这个中间节点来看看某两个节点能否利用这个节点进行连通，或者说利用这个中间节点获得的通路是否比这两个节点直接连通要近
 * 这样来找出两点间的最短路径，这种算法使用于计算所有点位互相中间的最短路径，而迪杰斯特拉算法适用于某个点位到所有点位的最短距离。
 * 然而这种算法的时间复杂度是比较高的，他的时间复杂度为n^3
 * @Author: Tanky
 * @CreateDate: 2020/5/19
 */
public class FloydAlgorithm {

    public static void main(String[] args) {

        // 测试看看图是否创建成功
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];

        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        Graph graph=new Graph(vertex,matrix);

        graph.floyd();

        for (int i=0;i<matrix.length;i++){
            System.out.println(Arrays.toString(matrix[i]));
        }




    }

}


class Graph {

    /**
     * 所有得顶点构成得顶点数组
     */
    char[] vertex;

    /**
     * 所有顶点构成得邻接矩阵
     */
    int[][] maxtrix;

    /**
     * 顶点的前置节点
     */
    char[] preVisited;


    public Graph(char[] vertex, int[][] maxtrix) {

        this.maxtrix = maxtrix;

        this.vertex = vertex;

        this.preVisited = new char[vertex.length];

    }

    /**
     * 佛洛依德算法开始
     */
    public void floyd() {

        int len;

        //最外层循环是中间节点
        for (int i = 0; i < vertex.length; i++) {

            //开始节点
            for (int j = 0; j < vertex.length; j++) {

                //结束节点
                for (int k = 0; k < vertex.length; k++) {

                    len = maxtrix[j][i] + maxtrix[i][k];

                    if (len < maxtrix[j][k]) {

                        maxtrix[j][k] = len;

                        preVisited[k] = vertex[i];

                    }


                }

            }

        }


    }


}
