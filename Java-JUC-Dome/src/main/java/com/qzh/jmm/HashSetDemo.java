package com.qzh.jmm;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @version 1.0
 * @Auther: qzh
 * @Date: 2020/12/11 - 21:14
 * @Description: com.qzh.jmm
 */
public class HashSetDemo{
    public static void main(String[] args) {
        Set set = new HashSet();
//        Set set = new CopyOnWriteArraySet<>();

        for (int i = 0; i <=30 ; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
