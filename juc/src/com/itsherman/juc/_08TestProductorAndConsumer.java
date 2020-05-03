package com.itsherman.juc;

public class _08TestProductorAndConsumer {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Consumer c = new Consumer(clerk);
        Productor p  = new Productor(clerk);
        new Thread(p,"生产者A").start();
        new Thread(p,"生产者C").start();
        new Thread(c,"消费者B").start();
        new Thread(c,"消费者D").start();
    }
}

class Clerk{

    private int procduct = 0;


    // 进货
    public synchronized void in(){
        // 为了避免虚假唤醒问题，应该总是使用在循环中
        while(procduct >= 1){
            System.out.println("进货已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 进货一件，当前货物："+ ++procduct);
        this.notifyAll();
    }

    //卖货
    public synchronized void sale(){
        while(procduct <= 0){
            System.out.println("缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 卖出一件，当前货物："+ --procduct);
        this.notifyAll();
    }
}

class Productor implements Runnable{

    private Clerk clerk;

    public Productor(Clerk clerk) {
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

class Consumer implements Runnable{

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
