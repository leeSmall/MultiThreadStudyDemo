package com.lgstudy;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * lgs
 * 
 * 
 * 一个java程序包含的线程
 */
public class ShowMainThread {

    public static void main(String[] args) {
        //java虚拟机的线程管理接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //获取线程信息的方法
        ThreadInfo[] threadInfos =
                threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo threadInfo:threadInfos){
            System.out.println(threadInfo.getThreadId()+":"+threadInfo.getThreadName());
        }
    }

}
