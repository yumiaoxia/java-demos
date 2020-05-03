package com.itsherman.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一、 用于解决多线程安全问题的方式
 *
 * synchronized： 隐式锁
 * 1. 同步代码块
 *
 * 2. 同步方法
 *
 * jdk 1.5 以后
 *
 * 3. 同步锁 Lock
 *  注意： 是一个显式锁，需要通过 lock 方法 上锁，必须通过 unlock() 方法进行释放锁(通常在finally代码块中释放锁)
 */
public class _07TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket,"1号窗口").start();
        new Thread(ticket,"2号窗口").start();
        new Thread(ticket,"3号窗口").start();
    }
}

class Ticket implements Runnable{

    private int ticket = 100;

    private Lock lock = new ReentrantLock();


    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                Thread.sleep(200);
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName() + "完成售票，余票为："+ --ticket);
                }else{
                    break;
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }


        }
    }
}
