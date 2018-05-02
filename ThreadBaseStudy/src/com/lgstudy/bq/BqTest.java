package com.lgstudy.bq;

public class BqTest {
    public static void main(String[] args) {
        BlockingQueueWN bq = new BlockingQueueWN(10);
        Thread threadA = new ThreadPush(bq);
        threadA.setName("Push");
        Thread threadB = new ThreadPop(bq);
        threadB.setName("Pop");
        threadB.start();
        threadA.start();
    }

    //推数据入队列
    private static class ThreadPush extends Thread{
        BlockingQueueWN<Integer> bq;

        public ThreadPush(BlockingQueueWN<Integer> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            int i = 5;
            while(i>0){
                try {
                    Thread.sleep(1000);
                    System.out.println(" i="+i+" will push");
                    bq.enqueue(i--);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }

            }
        }
    }

    //取数据出队列
    private static class ThreadPop extends Thread{
        BlockingQueueWN<Integer> bq;

        public ThreadPop(BlockingQueueWN<Integer> bq) {
            this.bq = bq;
        }
        @Override
        public void run() {
            while(true){
                try {
                    System.out.println(Thread.currentThread().getName()
                            +" will pop.....");
                    Integer i = bq.dequeue();
                    System.out.println(" i="+i.intValue()+" alread pop");
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }

        }
    }
}
