package com.qzh.jmm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @version 1.0
 * @Auther: qzh
 * @Date: 2020/12/11 - 22:23
 * @Description: com.qzh.jmm
 */
@NoArgsConstructor
@Getter
@Setter
class Person{
    String age;
    String name;

    public Person(String name) {
        this.name = name;
    }
}
public class TestTransferValue {
    public void changeValue1(int age){
        age = 30;
    }
    public void changeValue2(Person person){
        person.setName("xxx");
    }
    public void changeValue3(String str){
        str = "xxx";
    }

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age-----"+age);

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("person-----"+person);

        String str = "abc";
        test.changeValue3(str);
        System.out.println("String-----"+str);
    }
}
