package com.tanky.algorithm.dynamicprogramming;

/**
 * 创建人： 19697
 * 创建时间： 2020/6/15
 * 作用：
 * 修改信息：有依赖的背包问题
 * <p>
 * 例题：
 * <p>
 * 金明今天很开心，家里购置的新房就要领钥匙了，新房里有一间金明自己专用的很宽敞的房间。
 * 更让他高兴的是，妈妈昨天对他说：“你的房间需要购买哪些物品，怎么布置，你说了算，只要不超过N元钱就行”。
 * 今天一早，金明就开始做预算了，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
 * <p>
 * 主件      附件
 * <p>
 * 电脑      打印机，扫描仪
 * 书柜      图书
 * 书桌      台灯，文具
 * 工作椅     无
 * <p>
 * 如果要买归类为附件的物品，必须先买该附件所属的主件。
 * 每个主件可以有0个、1个或2个附件。
 * 附件不再有从属于自己的附件。
 * <p>
 * 金明想买的东西很多，肯定会超过妈妈限定的N元。
 * 于是，他把每件物品规定了一个重要度，分为5等：用整数1~5表示，第5等最重要。
 * 他还从因特网上查到了每件物品的价格（都是10元的整数倍）。
 * 他希望在不超过N元（可以等于N元）的前提下，使每件物品的价格与重要度的乘积的总和最大。
 * 设第j件物品的价格为v[j]，重要度为w[j]，共选中了k件物品，编号依次为j1，j2，…，jk，则所求的总和为：
 * v[j1]∗w[j1]+v[j2]∗w[j2]+…+v[jk]∗w[jk]（其中*为乘号）
 * 请你帮助金明设计一个满足要求的购物单。
 *
 * 将有依赖的背包问题转化成分组背包问题
 *
 */

import java.util.Scanner;

public class PackageProblemRely {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // 总钱数
        int N = scanner.nextInt();
        // 购买物品个数
        int m = scanner.nextInt();

        int[] f = new int[N + 1];

        // 分组，goods[i][0]为主件，goods[i][1]为附件1，goods[i][2]为附件2

        Good[][] goods1 = new Good[60][4];

        for (int i = 1; i <= m; i++) {

            int v = scanner.nextInt();
            int p = scanner.nextInt();
            int q = scanner.nextInt();

            Good t = new Good(v, v * p);

            if (q == 0) {
                goods1[i][0] = t;
            } else {
                if (goods1[q][1] == null) {
                    goods1[q][1] = t;
                } else {
                    goods1[q][2] = t;
                }
            }
        }

        for (int i = 1; i <= m; i++) {

            for (int j = N; j >= 0 && goods1[i][0] != null; j--) {

                //以下代码从分组中选择价值最大的。共五种情况：不选主件，选主件，选附件1和主件，选附件2和主件，选附件1和附件2和主件
                Good master = goods1[i][0];

                int max = f[j];
                if (j >= master.v) {
                    max=Math.max(max,f[j - master.v] + master.vp);
                }

                //如果附件1不为null
                int vt;
                if (goods1[i][1] != null) {
                    if (j >= (vt = master.v + goods1[i][1].v) ){
                        max = Math.max(max,f[j - vt] + master.vp + goods1[i][1].vp);
                    }
                }

                //如果附件2不为null
                if (goods1[i][2] != null) {
                    if (j >= (vt = master.v + goods1[i][1].v + goods1[i][2].v)) {
                        max = Math.max(max,f[j - vt] + master.vp + goods1[i][1].vp + goods1[i][2].vp);
                    }
                }
                f[j] = max;
            }
        }

        System.out.println(f[N]);
    }
}

class Good {
    int v;
    int vp;

    public Good(int v, int vp) {
        this.v = v;
        this.vp = vp;
    }
}
