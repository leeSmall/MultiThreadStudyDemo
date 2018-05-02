package com.lgs.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * lgs
 * 原子操作更新数组
 */
public class AtomicArray {
    static int[] value = new int[]{1,2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0,3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }

}
