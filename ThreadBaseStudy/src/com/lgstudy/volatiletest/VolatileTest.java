package com.lgstudy.volatiletest;

public class VolatileTest {
    public static void main(String[] args) {
        VolatileThread volatileThread = new VolatileThread();

        Thread t1 = new Thread(volatileThread);
        Thread t2 = new Thread(volatileThread);
        Thread t3 = new Thread(volatileThread);
        Thread t4 = new Thread(volatileThread);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
