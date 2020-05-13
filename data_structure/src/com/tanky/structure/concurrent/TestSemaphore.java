package com.tanky.structure.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class TestSemaphore {

    public static void main(String[] args) throws InterruptedException {


        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i <= 10; i++) {

            final int No = i;

            new Thread() {

                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("我是" + No);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //执行完业务以后释放
                    semaphore.release();
                }
            }.start();

        }

    }

}
