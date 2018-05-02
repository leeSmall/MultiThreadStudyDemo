package com.lgstudy.bq;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 有界阻塞队列/有界缓存队列
 */
public class BlockingQueueWN<T> {

	//当前队列
    private List queue = new LinkedList<>();
    //队列支持的最大容量
    private final int limit;

    //外部修改队列的容量
    public BlockingQueueWN(int limit) {
        this.limit = limit;
    }

    //入队
    public synchronized void enqueue(T item) throws InterruptedException {
        //如果当前队列的容量已经满了的话就要等待
    	while(this.queue.size()==this.limit){
            wait();
        }
        //如果当前队列的容量为0的话，可以肯定有出队的线程正在等待，需要他可以准备出队了
        if (this.queue.size()==0){
            notifyAll();
        }
        //开始入队
        this.queue.add(item);
    }

    //出队
    public synchronized T dequeue() throws InterruptedException {
        //如果当前队列的容量为0的话就等待暂不出队
    	while(this.queue.size()==0){
            wait();
        }
    	//如果当前队列的容量已经满了的话，可以肯定有入队线程正在等待，需要唤醒他可以准备入队了
        if (this.queue.size()==this.limit){
            notifyAll();
        }
        //开始出队
        return (T)this.queue.remove(0);
    }
}
