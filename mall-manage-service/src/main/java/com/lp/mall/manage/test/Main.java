package com.lp.mall.manage.test;

public class Main {

    public static void main(String[] args) {
        People people=new People();
        Lockx lockx=new Lockx();
        Thread1 thread1=new Thread1(people,lockx);
        Thread2 thread2=new Thread2(people,lockx);
        thread1.start();
        thread2.start();
    }
}
