package com.study.deadlock.bank;


import com.study.deadlock.bank.serivice.ITransfer;
import com.study.deadlock.bank.serivice.NormalTransfer;
import com.study.deadlock.bank.serivice.SafeTransfer;
import com.study.deadlock.bank.serivice.TryLockTransfer;


/**
 * 
 * @author THINKPAD
 *
 */
public class Bank {

    private static class TransferThread extends Thread{
        private String name;
        private Account from;
        private Account to;
        private int amount;
        private ITransfer transfer;

        public TransferThread(String name, Account from, Account to,
                              int amout,ITransfer transfer) {
            this.name = name;
            this.from = from;
            this.to = to;
            this.amount = amout;
            this.transfer = transfer;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                transfer.transfer(from,to,amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        Account zhangsan = new Account("zhangsan",20000);
        Account Lisi = new Account("lisi",20000);
        ITransfer transfer = new TryLockTransfer();
        TransferThread zsToLisi = new TransferThread("zsToLisi",zhangsan,Lisi,
                2000,transfer);
        TransferThread lisiTozs = new TransferThread("lisiTozs",Lisi,zhangsan,
                4000,transfer);
        zsToLisi.start();
        lisiTozs.start();

    }

}
