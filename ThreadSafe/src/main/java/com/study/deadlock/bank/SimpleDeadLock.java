package com.study.deadlock.bank;

/**
 * 简单顺序死锁
 * 解决办法：保证拿锁的顺序一致
 * @author THINKPAD
 *
 */
public class SimpleDeadLock {

	//左锁
    private static Object left = new Object();
    //右锁
    private static Object right = new Object();

    private static void leftToRight() throws InterruptedException {
        synchronized (left){
            System.out.println(Thread.currentThread().getName()+" get left");
            Thread.sleep(100);
            synchronized (right){
                System.out.println(Thread.currentThread().getName()+" get right");
            }
        }
    }

    private static void rightToLeft() throws InterruptedException {
        synchronized (left){
            System.out.println(Thread.currentThread().getName()+" get right-left");
            Thread.sleep(100);
            synchronized (right){
                System.out.println(Thread.currentThread().getName()+" get left-right");
            }
        }
    }

    private static class TestThread extends Thread{
        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        @Override
        public void run(){
            try {
                rightToLeft();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("Main");
        TestThread  testThread = new TestThread("testThread");
        testThread.start();
        try {
            leftToRight();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
