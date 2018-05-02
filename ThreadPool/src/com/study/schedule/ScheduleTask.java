package com.study.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScheduledThreadPoolExecutor示例
 */
public class ScheduleTask implements Runnable {

    public static enum OperType{
        None,OnlyThrowException,CatheException
    }

    public static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private OperType operType;

    public ScheduleTask(OperType operType) {
        this.operType = operType;
    }

    @Override
    public void run() {

        switch (operType){
            case OnlyThrowException:
                System.out.println("Exception not catch:"+formater.format(new Date()));
                throw new RuntimeException("OnlyThrowException");
            case CatheException:
                try {
                    throw new RuntimeException("CatheException");
                } catch (RuntimeException e) {
                    System.out.println("Exception be catched:"+formater.format(new Date()));
                }
                break;
            case None:
                System.out.println("None :"+formater.format(new Date()));
        }
    }
}
