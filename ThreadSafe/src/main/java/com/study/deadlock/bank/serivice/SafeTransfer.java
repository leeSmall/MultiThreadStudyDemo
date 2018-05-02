package com.study.deadlock.bank.serivice;

import com.study.deadlock.bank.Account;

/**
 * 动态顺序死锁
 * @author THINKPAD
 * 解决办法：通过特殊手段保证拿锁的顺序一致，如获取要锁定对象的hash值，然后比较大小，先锁小的再锁大的
 */
public class SafeTransfer implements ITransfer {

    private static Object tieLock = new Object();

    @Override
    public void transfer(Account from, Account to, int amount)
            throws InterruptedException {

    	//获取要锁定对象的hash值，然后比较大小，先锁小的再锁大的
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if(fromHash<toHash){
        	//先锁小的
            synchronized (from){
                System.out.println(Thread.currentThread().getName()+" get "+from.getName());
                Thread.sleep(100);
                //再锁大的
                synchronized (to){
                    System.out.println(Thread.currentThread().getName()
                            +" get "+to.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        }
        else if(toHash<fromHash){
        	//先锁小的
            synchronized (to){
                System.out.println(Thread.currentThread().getName()+" get "+to.getName());
                Thread.sleep(100);
              //再锁大的
                synchronized (from){
                    System.out.println(Thread.currentThread().getName()
                            +" get "+from.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        }
        else{
        	//hash值相等时在前面再加一把锁
            synchronized (tieLock){
                synchronized (to){
                    System.out.println(Thread.currentThread().getName()+" get "+from.getName());
                    Thread.sleep(100);
                    synchronized (from){
                        System.out.println(Thread.currentThread().getName()
                                +" get "+to.getName());
                        from.flyMoney(amount);
                        to.addMoney(amount);
                    }
                }
            }
        }
    }
}
