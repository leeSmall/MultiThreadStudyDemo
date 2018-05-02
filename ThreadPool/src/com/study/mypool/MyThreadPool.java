package com.study.mypool;

import java.util.LinkedList;
import java.util.List;

/**
 * 创建日期：2017/12/07
 * 创建时间: 21:27
 */
public class MyThreadPool {

    //默认的线程个数
    private int work_num = 5;

    //线程的容器
    private WorkThread[] workThreads;

    //任务队列
    private List<Runnable> taskQueue = new LinkedList<>();

    public MyThreadPool(int work_num) {
        this.work_num = work_num;
        workThreads = new WorkThread[work_num];
        for(int i=0;i<work_num;i++){
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
    }

    //提交任务的接口
    public void execute(Runnable task){
        synchronized (taskQueue){
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    //销毁线程池
    public void destroy(){
        System.out.println("ready stop pool....");
        for(int i=0;i<work_num;i++){
            workThreads[i].stopWorker();
            workThreads[i] = null;//加速垃圾回收
        }
        taskQueue.clear();
    }

    //工作线程
    private class WorkThread extends Thread{

        private volatile boolean on = true;

        public void run(){
            Runnable r = null;
            try{
                while(on&&!isInterrupted()){
                    synchronized (taskQueue){
                        //任务队列中无任务，工作线程等待
                        while(on&&!isInterrupted()&&taskQueue.isEmpty()){
                            taskQueue.wait(1000);
                        }
                        //任务队列中有任务，拿任务做事
                        if(on&&!isInterrupted()&&!taskQueue.isEmpty()){
                            r = taskQueue.remove(0);
                        }
                    }
                    if (r!=null){
                        System.out.println(getId()+" ready execute....");
                        r.run();
                    }
                    //加速垃圾回收
                    r = null;
                }

            }catch(InterruptedException e){
                System.out.println(Thread.currentThread().getId()+" is Interrupted");
            }
        }

        public void stopWorker(){
            on = false;
            interrupt();
        }

    }

}
