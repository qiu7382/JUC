package com.qzh.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @version 1.0
 * @Auther: qzh
 * @Date: 2020/12/10 - 20:59
 * @Description: com.qzh.jmm
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {

        System.out.println("=======ABA问题产生======");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(()->{
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("t2: "+atomicReference.compareAndSet(100,2020)+"\t"+atomicReference.get());
        },"t2").start();

        System.out.println("=======ABA问题解决======");

        int stamp = atomicStampedReference.getStamp();
        new Thread(()->{

            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("t3: "+atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1)+"\t"+atomicStampedReference.getReference()+"\t"+atomicStampedReference.getStamp());
            System.out.println("t3: "+atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1)+"\t"+atomicStampedReference.getReference()+"\t"+atomicStampedReference.getStamp());
        },"t3").start();

        new Thread(()->{
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean flag = atomicStampedReference.compareAndSet(100,2020,stamp,stamp+1);
            System.out.println("======t4版本号:"+stamp);
            System.out.println("======主内存版本号:"+atomicStampedReference.getStamp());
            System.out.println("版本号不一致,有效解决ABA问题");
            System.out.println("t4"+"\t"+flag+"\t"+atomicStampedReference.getReference()+"\t"+atomicStampedReference.getStamp());
        },"t4").start();
    }
}
