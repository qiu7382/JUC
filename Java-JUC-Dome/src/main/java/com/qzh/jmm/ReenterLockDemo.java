package com.qzh.jmm;

/**
 * @version 1.0
 * @Auther: qzh
 * @Date: 2020/12/12 - 16:11
 * @Description: com.qzh.jmm
 */
class Phone{
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t"+"invoked sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t"+"==========sendEmail sendSMS");
    }
}

/**
 * 可重入锁
 *
 * t1	invoked sendSMS                 t1线程在外层获取锁的时候
 * t1	==========sendEmail sendSMS     t1在进入内层方法也会自动获取锁
 * t2	invoked sendSMS
 * t2	==========sendEmail sendSMS
 *
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
