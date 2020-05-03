package com.itsherman.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _10TestABCAlternate {
    public static void main(String[] args) {
        AlternateDemo ad = new AlternateDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 21; i++) {
                    ad.loopA(i);
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 21; i++) {
                    ad.loopB(i);
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 21; i++) {
                    ad.loopC(i);
                    System.out.println("=====================================================");
                }
            }
        },"C").start();
    }
}

class AlternateDemo{

    private int number = 1;

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void loopA(int totalLoop){
        lock.lock();
        try{
            // 1. 判断
            if(number != 1){
                c1.await();
            }

            // 2. 打印
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+ i + "\t" + totalLoop);
            }

            // 3. 唤醒
            number = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(int totalLoop){
        lock.lock();
        try{
            // 1. 判断
            if(number != 2){
                c2.await();
            }

            // 2. 打印
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+i + "\t" + totalLoop);
            }

            // 3. 唤醒
            number = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(int totalLoop){
        lock.lock();
        try{
            // 1. 判断
            if(number != 3){
                c3.await();
            }

            // 2. 打印
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+i + "\t" + totalLoop);
            }

            // 3. 唤醒
            number = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
