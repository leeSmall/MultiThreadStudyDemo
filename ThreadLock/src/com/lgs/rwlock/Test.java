package com.lgs.rwlock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * lgs
 * 读写锁和内置锁的性能对比
 */
public class Test {

    static final int threadRatio = 10;
    static final int threadBaseCount = 3;
    static CountDownLatch countDownLatch= new CountDownLatch(1);

    //模拟实际的数据库读操作
    private static class ReadThread implements Runnable{

        private IGoodsNum goodsNum;

        public ReadThread(IGoodsNum goodsNum) {
            this.goodsNum = goodsNum;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long start = System.currentTimeMillis();
            for(int i=0;i<100;i++){
                goodsNum.getGoodsNumber();
            }
            long duration = System.currentTimeMillis()-start;
            System.out.println(Thread.currentThread().getName()+"读取库存数据耗时："+duration+"ms");

        }
    }


    //模拟实际的数据库写操作
    private static class WriteThread implements Runnable{

        private IGoodsNum goodsNum;

        public WriteThread(IGoodsNum goodsNum) {
            this.goodsNum = goodsNum;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long start = System.currentTimeMillis();
            Random r = new Random();
            for(int i=0;i<10;i++){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                goodsNum.setGoodsNumber(r.nextInt(10));
            }
            long duration = System.currentTimeMillis()-start;
            System.out.println(Thread.currentThread().getName()+"写库存数据耗时："+duration+"ms");

        }
    }

    public static void main(String[] args) throws InterruptedException {
        GoodsVo goodsVo =
                new GoodsVo("goods001",100000,10000);
        //读写锁和内置锁的性能对比 RwNumImpl/NumSyn
        IGoodsNum goodsNum = new RwNumImpl(goodsVo);
        for(int i = 0;i<threadBaseCount*threadRatio;i++){
            Thread readT = new Thread(new ReadThread(goodsNum));
            readT.start();
        }
        for(int i = 0;i<threadBaseCount;i++){
            Thread writeT = new Thread(new WriteThread(goodsNum));
            writeT.start();
        }
        countDownLatch.countDown();

    }

}
