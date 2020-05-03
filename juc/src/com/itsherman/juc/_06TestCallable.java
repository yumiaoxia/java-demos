package com.itsherman.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建执行线程的方式三： 实现Callable接口。相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常。
 */
public class _06TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo cd = new CallableDemo();
        FutureTask<String> task = new FutureTask<>(cd);
        new Thread(task).start();
        // Futuretask 可用于闭锁，get方法会阻塞主线程，直到子线程执行完
        System.out.println(task.get());
    }

}

class CallableDemo implements Callable<String>{

    @Override
    public String call() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        return "OK";
    }
}
