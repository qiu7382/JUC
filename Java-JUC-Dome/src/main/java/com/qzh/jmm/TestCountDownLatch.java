package com.qzh.jmm;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    private CountDownLatch countDownLatch = new CountDownLatch(4); //构造方法表明记数数量

    //运动员类
    private class Runner implements Runnable{
        private int result;

        public Runner(int result) {
            this.result = result;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(result * 1000);   //模拟跑了多少秒
                countDownLatch.countDown();     //跑完计数减1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void begin(){
        System.out.println("----start---");
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 4; i++) {
            int result = random.nextInt(3) + 1; //随机设置每个运动员跑多少秒结束
            new Thread(new Runner(result)).start();
        }
        try {
            countDownLatch.await(); //现场等待为0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----end---");
    }

    public static void main(String[] args) {
        TestCountDownLatch testCountDownLatch = new TestCountDownLatch();
        testCountDownLatch.begin();
    }

}
