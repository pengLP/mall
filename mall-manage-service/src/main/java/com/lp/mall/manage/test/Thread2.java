package com.lp.mall.manage.test;

public class Thread2 extends Thread{
    private People people;
    private Lockx lockx;
    public Thread2(People people,Lockx lockx) {
        this.people=people;
        this.lockx=lockx;
    }

    int count =0;
    @Override
    public void run() {
        while (count<100){
            synchronized (lockx){
                System.out.println(people.getName()+people.getAge()+people.getSex());
            }
        }
    }
}
