package com.itsherman.juc;

/**
 * Volatile 关键字：保证多个线程访问volatile修饰的共像享变量在内存中的可见性。
 *          相较于 synchronized 是一种较为轻量级的同步策略
 * 特性：
 *  1. 不具备互斥性
 *  2. 不能保证原子性
 */
public class _01TestVolatile {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true){
            if(td.isFlag()){
                System.out.println("++++++++++++++++++++++++++++++++++");
                break;
            }
        }
    }
}


class ThreadDemo implements Runnable{

    private boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
    }

    public boolean isFlag() {
        return flag;
    }
}