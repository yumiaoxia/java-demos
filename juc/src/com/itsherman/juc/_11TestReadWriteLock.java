package com.itsherman.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class _11TestReadWriteLock {

    public static void main(String[] args) {
        ReadWriteDemo rd = new ReadWriteDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    rd.set((int)(Math.random() * 101));
                }
            }
        },"Write:").start();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rd.get();
                }
            },"READ").start();
        }
    }
}

class ReadWriteDemo{

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private int number = 0;


    // 读
    public void get(){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":" + number);
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
    // 写
    public void set(int number){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + number);
            this.number = number;
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

}
