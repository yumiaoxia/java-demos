package com.itsherman.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用等待唤醒机制实现线程通信
 */
public class _09TestProductorAndConsumerForLock {

    public static void main(String[] args) {
        Clerk2 clerk2 = new Clerk2();
        Consumer2 c = new Consumer2(clerk2);
        Productor2 p  = new Productor2(clerk2);
        new Thread(p,"生产者A").start();
        new Thread(p,"生产者C").start();
        new Thread(c,"消费者B").start();
        new Thread(c,"消费者D").start();
    }
}

class Clerk2{

    private int procduct = 0;

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();


    // 进货
    public  void in(){
        lock.lock();
        try{
            // 为了避免虚假唤醒问题，应该总是使用在循环中
            while(procduct >= 1){
                System.out.println("进货已满");
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 进货一件，当前货物："+ ++procduct);
            c1.signalAll();
        }finally {
            lock.unlock();
        }
    }

    //卖货
    public void sale(){
        lock.lock();
        try{
            while(procduct <= 0){
                System.out.println("缺货");
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 卖出一件，当前货物："+ --procduct);
            c1.signalAll();
        }finally {
            lock.unlock();
        }

    }
}

class Productor2 implements Runnable{

    private Clerk2 clerk;

    public Productor2(Clerk2 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 20; i++) {
            clerk.in();
        }
    }
}

class Consumer2 implements Runnable{

    private Clerk2 clerk;

    public Consumer2(Clerk2 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}



