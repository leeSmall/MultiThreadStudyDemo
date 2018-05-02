package com.study.deadlock.bank.serivice;


import com.study.deadlock.bank.Account;

/**
 * 
 * @author THINKPAD
 *
 */
public interface ITransfer {

    void transfer(Account from, Account to, int amount) throws InterruptedException;
}
