package com.study.dcl;

/**
 * 懒汉式单例-双重检查
 * @author THINKPAD
 *
 */
public class SingleDcl {

	//双重检查不能保证线程安全，原因是第一个线程可能还没有初始化完，
	//第二个线程就进来获取单例对象使用了,所以加一个volatile修饰保证可见性
    private volatile static SingleDcl single;
    private SingleDcl(){}

    public static SingleDcl getInstance(){
        if(null==single){
            synchronized (SingleDcl.class){
                if(single==null){
                    single = new SingleDcl();
                }
            }
        }
        return single;
    }


}
