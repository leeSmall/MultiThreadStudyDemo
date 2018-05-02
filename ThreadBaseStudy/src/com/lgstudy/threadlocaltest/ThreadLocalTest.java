package com.lgstudy.threadlocaltest;

/**
 * 
 * 创建日期：2017/11/28
 * 创建时间: 22:04
 */
public class ThreadLocalTest {

    static ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "init";
        }
    };

    public void test(){
        Thread[] runs = new Thread[3];
        for(int i =0;i<runs.length;i++){
            runs[i]=new Thread(new T1(i));
        }
        for(int i =0;i<runs.length;i++){
            runs[i].start();
        }
    }

    private static class T1 implements Runnable{

        private int id;

        public T1(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId()+" start");
            String s = threadLocal.get();
            s = s+"_"+id;
            threadLocal.set(s);
            System.out.println(Thread.currentThread().getId()+s);
        }
    }

    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
        test.test();
    }
}
