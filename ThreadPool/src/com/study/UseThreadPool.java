package com.study;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 创建日期：2017/12/07
 * 创建时间: 21:59
 */
public class UseThreadPool {

    static class MyTask implements Runnable {

        private String name;


        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {// 执行任务
            try {
                Random r = new Random();
                Thread.sleep(r.nextInt(1000)+2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId()+" sleep InterruptedException:"
                        +Thread.currentThread().isInterrupted());
            }
            System.out.println("任务 " + name + " 完成");
        }
    }

    public static void main(String[] args) {
        //创建线程池
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2,4,60,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));
        ExecutorService threadPool2 = Executors.newFixedThreadPool(2);
        ExecutorService threadPool3 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool4 = Executors.newCachedThreadPool();
        ExecutorService threadPool6 = Executors.newWorkStealingPool();
        for(int i =0;i<=5;i++){
            MyTask task = new MyTask("Task_"+i);
            System.out.println("A new task will add:"+task.getName());
            threadPoolExecutor.execute(task);

        }
        threadPoolExecutor.shutdown();
    }


}
