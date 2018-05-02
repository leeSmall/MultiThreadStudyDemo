package com.lgs;

import java.util.concurrent.atomic.AtomicReference;

/**
 * lgs
 * 原子操作更新引用类型即可以同时更新多个值
 */
public class AtomicRef {

    static AtomicReference<User> userAtomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User("lgs",26);
        userAtomicReference.set(user);
        User updateUser = new User("ll",27);
        userAtomicReference.compareAndSet(user,updateUser);
        System.out.println(userAtomicReference.get().getName());
        System.out.println(userAtomicReference.get().getOld());
    }

    static class User{
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }

}
