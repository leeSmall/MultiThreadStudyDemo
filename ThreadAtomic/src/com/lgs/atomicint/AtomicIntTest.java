package com.lgs.atomicint;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * lgs
 * 原子操作更新整型
 */
public class AtomicIntTest {
    static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        ai.incrementAndGet();
        System.out.println(ai.get());
    }
}
