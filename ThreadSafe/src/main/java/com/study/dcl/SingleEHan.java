package com.study.dcl;

/**
 * 饿汉式单例-线程安全
 * @author THINKPAD
 *
 */
public class SingleEHan {
    public static SingleEHan singleEHan = new SingleEHan();
    private SingleEHan(){};
}
