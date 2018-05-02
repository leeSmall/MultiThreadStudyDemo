package com.lgstudy;

/**
 * join的使用
 */
public class JoinTest {

    static class CutInLine implements Runnable{

        private Thread thread;

        public CutInLine(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                //在被插队的线程里，调用一下插队线程的join方法
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" will work");
        }
    }

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for(int i=0;i<10;i++){
            Thread thread =
                    new Thread(new CutInLine(previous),String.valueOf(i));
            System.out.println(previous.getId()+" cut in the thread:"+thread.getName());
            thread.start();
            previous = thread;
        }

    }

}
