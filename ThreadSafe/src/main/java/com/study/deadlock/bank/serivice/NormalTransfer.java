package com.study.deadlock.bank.serivice;

import com.study.deadlock.bank.Account;

/**
 * 
 * @author THINKPAD
 *
 */
public class NormalTransfer implements ITransfer{
    @Override
    public void transfer(Account from, Account to, int amount)
            throws InterruptedException {
        synchronized (from){
            System.out.println(Thread.currentThread().getName()+" get "+from.getName());
            Thread.sleep(100);
            synchronized (to){
                System.out.println(Thread.currentThread().getName()
                        +" get "+to.getName());
                from.flyMoney(amount);
                to.addMoney(amount);
            }
        }
    }
}
