package com.tanky.structure.concurrent;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier2 {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        for (int i=0;i<5;i++){
            new Player(cyclicBarrier,"玩家"+i).start();
        }

    }

    static class Player extends Thread {

        String name;

        CyclicBarrier cyclicBarrier;

        public Player(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {

            System.out.println("玩家" + name + "已经进入游戏，等待其他玩家进入！！！");
            try {
                //堵塞等待直到到达栅栏的位置
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("玩家" + name + "已经开始游戏！！！！！！！！！");

        }
    }


}
