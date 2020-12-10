package com.qzh.jmm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @version 1.0
 * @Auther: qzh
 * @Date: 2020/12/10 - 20:38
 * @Description: com.qzh.jmm
 */
@Getter
@ToString
@AllArgsConstructor
class User{
    String userName;
    String age;
}

public class AtomRes {
    public static void main(String[] args) {
        User z3 = new User("z3","18");
        User li4 = new User("li4","20");

        AtomicReference<User> atomicReference = new AtomicReference<>();

        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());
    }
}
