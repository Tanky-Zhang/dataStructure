package com.tanky.structure.concurrent;


import java.util.concurrent.CountDownLatch;

public class TestCownLatch {

    public static void main(String[] args) throws InterruptedException {

        Family family = new Family();

        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread() {
            @Override
            public void run() {
                family.fatherGet();
                countDownLatch.countDown();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                family.motherGet();
                countDownLatch.countDown();
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                family.meGet();
                countDownLatch.countDown();
            }
        }.start();

        countDownLatch.await();
        family.togetherEat();

    }

}
