package com.lgstudy.waitnotify;

/**
 * lgs
 * 
 * wait/notify/notifyAll的演示
 */
public class User {

    public static final String CITY = "NewYork";
    private int age;
    private String city;

    public User(int age, String city) {
        this.age = age;
        this.city = city;
    }

    public User() {
    }

    //修改用户的城市后，发出通知
    public synchronized void changeCity(){
        this.city="London";
        notifyAll();
    }

    //修改用户的年龄后，发出通知
    public synchronized void changeAge(){
        this.age = 31;
        notifyAll();
    }

    //等待用户的年龄变化的方法，接收到通知，检查发现用户年龄大于30时，进行业务工作，否则一直等待
    //阻塞方法
    public synchronized void waitAge(){
        while(this.age<=30){
            try {
                wait();
                System.out.println("wait age ["+Thread.currentThread().getId()
                        +"] is notified!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the age is "+this.age);//业务工作
    }

    //等待用户的城市变化的方法，接收到通知，检查发现用户城市不是NewYork时，进行业务工作，否则一直等待
    //阻塞方法
    public synchronized void waitCity(){
        while(this.city.equals(CITY)){
            try {
                wait();
                System.out.println("wait city ["+Thread.currentThread().getId()
                        +"] is notified!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the city is "+this.city);//业务工作
    }
}
