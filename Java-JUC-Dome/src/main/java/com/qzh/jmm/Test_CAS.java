package com.qzh.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @Auther: qzh
 * @Date: 2020/12/10 - 16:54
 * @Description: com.qzh.jmm
 */
public class Test_CAS {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5,2020)+", "+atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5,1024)+", "+atomicInteger.get());
    }
}
