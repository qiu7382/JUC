package com.qzh.jmm;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @version 1.0
 * @Auther: qzh
 * @Date: 2020/12/11 - 10:55
 * @Description: com.qzh.jmm
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        new ArrayList<Integer>().add(1);

        List<String> list = Arrays.asList("a","b","c");
        list.forEach(System.out::println);
        System.out.println("=======================");

        //并发修改异常
        //java.util.ConcurrentModificationException
//        List<String> list1 = new ArrayList<>();
//        List<String> list1 = Collections.synchronizedList(new ArrayList<>());
        List<String> list1 = new CopyOnWriteArrayList();

        /**
         * 并发原因
         * 一个线程正在写操作，另一个过来抢线程，导致数据不一致
         *
         * 解决方法
         * 1、Vector,太重是jdk1.0
         * 2、Collections.synchronizedList(new ArrayList<>());
         * 3、CopyOnWriteArrayList() 写时复制 JUC包的
         *    优点:对CopyOnWrite进行并发读操作，读的是原容器，写的copy的容器
         *    本质是读写分离的思想
         *    在add()方法里用到ReentrantLook锁住了add()方法
         */

        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                list1.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list1);
            },String.valueOf(i)).start();
        }
    }
}
