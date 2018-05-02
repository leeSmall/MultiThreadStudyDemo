package com.lgs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lgs
 * Condition的使用方式
 */
public class ConditionTemplete {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void waitc() throws InterruptedException {
        lock.lock();
        try{
            condition.await();
        }finally{
            lock.unlock();
        }
    }

    public void waitnotify() throws InterruptedException {
        lock.lock();
        try{
            condition.signal();
            //condition.signalAll();尽量少使用
        }finally{
            lock.unlock();
        }
    }


}
