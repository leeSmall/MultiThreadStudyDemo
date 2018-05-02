package com.study.deadlock.bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 账户信息
 * @author THINKPAD
 *
 */
public class Account {
    private long number;
    private final String name;
    private int money;
    private final Lock lock = new ReentrantLock();

    public Lock getLock() {
        return lock;
    }

    public Account(String name, int amount) {
        this.name = name;
        this.money = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public void addMoney(int amount){
        money = money + amount;
    }

    public void flyMoney(int amount){
        money = money - amount;
    }
}
