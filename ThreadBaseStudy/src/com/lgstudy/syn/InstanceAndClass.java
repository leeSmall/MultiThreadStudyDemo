package com.lgstudy.syn;

import com.lgstudy.threadstate.SleepUtils;

/**
 * 类锁和实例锁
 */
public class InstanceAndClass {

    //测试类锁
    private static class TestClassSyn extends Thread{
        @Override
        public void run() {
            System.out.println("TestClass is going...");
            synClass();
        }
    }

    //测试对象锁
    private static class TestInstanceSyn extends Thread{
        private InstanceAndClass instanceAndClass;

        public TestInstanceSyn(InstanceAndClass instanceAndClass) {
            this.instanceAndClass = instanceAndClass;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is going..."+instanceAndClass);
            instanceAndClass.synInstance();
        }

    }

    //测试对象锁
    private static class TestInstance2Syn implements Runnable{
        private InstanceAndClass instanceAndClass;

        public TestInstance2Syn(InstanceAndClass instanceAndClass) {
            this.instanceAndClass = instanceAndClass;
        }
        @Override
        public void run() {
            System.out.println("TestInstance2 is going..."+instanceAndClass);
            instanceAndClass.synInstance2();
        }
    }

    //锁对象的方法
    private synchronized void synInstance(){
        SleepUtils.second(3);
        System.out.println("synInstance is going...");
        SleepUtils.second(3);
        System.out.println("synInstance ended");
    }

    //锁对象的方法
    private synchronized void synInstance2(){
        SleepUtils.second(3);
        System.out.println("synInstance2 going...");
        SleepUtils.second(3);
        System.out.println("synInstance2 ended");
    }

    //锁类的方法
    private static synchronized void synClass(){
        SleepUtils.second(1);
        System.out.println("synClass going...");
        SleepUtils.second(1);
    }

    public static void main(String[] args) {
        InstanceAndClass instanceAndClass = new InstanceAndClass();
        Thread t1 = new TestClassSyn();
        Thread t2 = new Thread(new TestInstanceSyn(instanceAndClass));
        Thread t3 = new Thread(new TestInstance2Syn(instanceAndClass));
        t2.start();
        t3.start();
        SleepUtils.second(1);
        t1.start();
    }

}
