package com.lgs.bq;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lgs
 * 结合ReentrantLock和Condition实现线程安全的有界队列
 */
public class BlockingQueueLC<T> {
    private List queue = new LinkedList<>();
    private final int limit;
    Lock lock = new ReentrantLock();
    private Condition needNotEmpty = lock.newCondition();
    private Condition needNotFull = lock.newCondition();


    public BlockingQueueLC(int limit) {
        this.limit = limit;
    }

    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        try{
            while(this.queue.size()==this.limit){
                needNotFull.await();
            }
            this.queue.add(item);
            //通知出队线程有数据可以取了
            needNotEmpty.signal();
        }finally{
            lock.unlock();
        }
    }

    public  T dequeue() throws InterruptedException {
        lock.lock();
        try{
            while(this.queue.size()==0){
                needNotEmpty.await();
            }
            //通知入队线程有位置可以入队了
            needNotFull.signal();
            return (T) this.queue.remove(0);
        }finally{
            lock.unlock();
        }
    }
}
