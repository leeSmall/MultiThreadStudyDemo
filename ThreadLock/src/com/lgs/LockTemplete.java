package com.lgs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lgs
 * 显示锁lock的标准写法
 */
public class LockTemplete {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        //获取锁
        lock.lock();
        try{
            // do my work.....
        }finally{
        	//释放锁
            lock.unlock();
        }
    }

}
