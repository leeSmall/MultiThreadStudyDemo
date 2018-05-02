package com.lgstudy.threadstate;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lgs
 * 创建日期：2017/11/15
 * 创建时间: 17:12
 * 查看线程的状态
 */
public class ThreadState {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(new SleepAlways(), "SleepAlwaysThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
        new Thread(new Sync(), "SyncThread-1").start();
        new Thread(new Sync(), "SyncThread-2").start();
    }

    /**
     * 该线程不断的进行睡眠
     */
    static class SleepAlways implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    /**
     * 该线程在Waiting.class实例上等待
     */
    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 该线程在Blocked.class实例上加锁后，不会释放该锁
     */
    static class Blocked implements Runnable {
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }

    /**
     * 该线程获得锁休眠后，又释放锁
     */
    static class Sync implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                SleepUtils.second(3000);
            } finally {
                lock.unlock();
            }

        }

    }
}
