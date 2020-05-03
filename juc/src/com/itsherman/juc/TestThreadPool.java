package com.itsherman.juc;

import java.util.concurrent.*;

public class TestThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 获取线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 提交任务
        ThreadPoolDemo tpd = new ThreadPoolDemo();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return tpd.sum();
            }
        });
        System.out.println(future.get());

        // 关闭线程池，释放资源
        executorService.shutdown();
    }
}

class ThreadPoolDemo{

    private int sum = 0;

    public int sum(){
        for (int i = 0; i < 101; i++) {
            sum += i;
        }
        return sum;
    }
}
