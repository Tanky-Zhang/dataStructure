package com.tanky.algorithm.kruskal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 创建人： ZhangZhongguo
 * 创建时间： 2020/5/21
 * 作用：克鲁斯卡尔算法用来解决最最小生成树的问题
 * 修改信息：
 */
public class Kruskal {


    private static final int INF = Integer.MAX_VALUE;


    static char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

    public static void main(String[] args) {

        int matrix[][] = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}};


        //用来存放有效的边
        int eages = 0;

        List<Edata> list = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {

            for (int j=i+1; j < matrix[0].length; j++) {

                if (matrix[i][j] != INF) {

                    list.add(new Edata(vertexs[i], vertexs[j], matrix[i][j]));

                    eages++;
                }

            }


        }

        Edata[] result = new Edata[eages];

        //根据权重进行排序
        list.sort(new Comparator<Edata>() {
            @Override
            public int compare(Edata o1, Edata o2) {
                return o1.weight - o2.weight;
            }
        });

        System.out.println(list);

        int index = 0;
        int[] ends = new int[vertexs.length];

        for (int m = 0; m < list.size(); m++) {

            //获取开始节点和结束节点的下标
            int p1 = getPosition(list.get(m).start);
            int p2 = getPosition(list.get(m).end);


            //获取两个节点的终点的下表把
            int e1 = getEnd(ends, p1);
            int end = getEnd(ends, p2);

            //不是相同的终点
            if (e1 != end) {

                ends[e1] = end;

                result[index++] = list.get(m);

            }

        }

        for(int i = 0; i < index; i++) {
            System.out.println(result[i]);
        }

    }

    private static int getEnd(int[] ends, int p1) {

        while (ends[p1] != 0) {

            p1 = ends[p1];

        }

        return p1;

    }

    private static int getPosition(char start) {

        int m = 0;
        for (int i = 0; i < vertexs.length; i++) {

            if (start == vertexs[i]) {
                m = i;
            }

        }

        return m;

    }

}


class Edata {

    char start;

    char end;

    int weight;

    public Edata(char start, char end, int weight) {

        this.start = start;

        this.end = end;

        this.weight = weight;

    }


    @Override
    public String toString() {
        return "<" + start + "------" + end + ">" + weight;
    }
}
