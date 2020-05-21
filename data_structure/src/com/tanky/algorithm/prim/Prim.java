package com.tanky.algorithm.prim;

/**
 * 创建人： ZhangZhongguo
 * 创建时间： 2020/5/19
 * 作用：普利姆算法，利用普利姆算法求取最短路径的算法,思路就是每次找出最小的那个路径然后继续遍历下去。
 * 修改信息：
 */
public class Prim {

    public static void main(String[] args) {


        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};

        MinTree minTree = new MinTree(data, weight, 1);

        minTree.prim();


    }

}


class MinTree {

    char vertex[];
    int[][] weight;
    //开始的顶点
    int vex;

    public MinTree(char[] vertex, int[][] weight, int vex) {

        this.weight = weight;

        this.vertex = vertex;

        this.vex = vex;

    }


    public void prim() {

        boolean[] isvisited = new boolean[vertex.length];

        isvisited[vex] = true;

        int m = -1;
        int n = -1;
        int minWeight = 100000;

        //最外层循环代表了所需要的边的条数，如果有n个顶点那么最后的最小生成树一共有n-1条边
        for (int k = 1; k < vertex.length; k++) {

            for (int i = 0; i < vertex.length; i++) {

                for (int j = 0; j < vertex.length; j++) {

                    //前一个节点已经北方问过了，且这个节点还没有被访问过。
                    if (isvisited[i] && !isvisited[j] && weight[i][j] < minWeight) {

                        //把最小值存储起来
                        minWeight = weight[i][j];

                        m = i;

                        n = j;

                    }
                }

            }


            System.out.println("<" + vertex[m] + "----" + vertex[n] + ">" + minWeight);

            //同时将n点置为已经访问过了
            isvisited[n] = true;

            minWeight = 100000;


        }


    }


}

