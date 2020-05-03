package com.itsherman.juc;

/**
 * 模拟CAS算法
 */
public class _03TestCAS {

    public static void main(String[] args) {
        CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                int expect = cas.get();
                boolean flag = cas.compareAndSet(expect,(int)(Math.random()*101));
                System.out.println(flag);
            }).start();
        }
    }
}

class CompareAndSwap{

    private int value;

    public synchronized int get(){
        return value;
    }

    /**
     * 比较
     * @return
     */
    public synchronized  int compareAndSwap(int expect,int update){
        if(value == expect){
            this.value = update;
        }
        return value;
    }

    /**
     * 设置
     * @param expect
     * @param update
     * @return
     */
    public synchronized boolean compareAndSet(int expect,int update){
        return expect == compareAndSwap(expect,update);
    }
}
