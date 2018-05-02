package com.study.dcl;

/**
 * 延迟类占位符单例，利用JVM的类加载的时候会自动给加载的类加上锁的机制
 * @author THINKPAD
 *
 */
public class SingleClassInit {
    private SingleClassInit(){}

    private static class InstanceHolder{
        public static SingleClassInit instance = new SingleClassInit();
    }

    public static SingleClassInit getInstance(){
        return InstanceHolder.instance;
    }
}


