package com.lp.mall.manage.test;

public class Thread1 extends Thread{
    private People people;
    private Lockx lockx;
    public Thread1(People people,Lockx lockx) {
        this.people=people;
        this.lockx=lockx;
    }
    int count =0;

    @Override
    public void run() {
        while (true){
            synchronized (lockx){
                if (count==0){
                    people.setName("小明");
                    people.setAge("1");
                    people.setSex("男");
                }else {
                    people.setName("小红");
                    people.setSex("女");
                    people.setAge("2");
                }
                count=(count+1)%2;
            }
        }
    }
}