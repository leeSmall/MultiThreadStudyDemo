package com.lgstudy.interrupt;

/**
 * lgs
 * 
 * 安全的中断线程
 */
public class SafeInterrupt implements Runnable {

    private volatile boolean on = true;
    private long i =0;

    @Override
    public void run() {
    	//阻塞方法wait,sleep,blockingqueue(put,take)，on不起作用
    	//要加上线程的中断才能安全的终止线程
        while(on&&Thread.currentThread().isInterrupted()){
            i++;
        }
        System.out.println("TestRunable is runing :"+i);
    }

    public void cancel(){
        on = false;
        //在java线程很忙的时候可能不会理会中断，所以定义一个标志位on更好
        Thread.currentThread().interrupt();
    }
}
