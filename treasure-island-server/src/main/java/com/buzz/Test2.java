package com.buzz;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test2 {
    ScheduledExecutorService brushTaskService = Executors.newScheduledThreadPool(100);

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.run();

    }


    public void run() {
        for (int threadNo = 0; threadNo < 100; threadNo++) {
            final int i = threadNo;
            Runnable task = () -> doJob(i);
            brushTaskService.scheduleWithFixedDelay(task, 10000, 1000, TimeUnit.MILLISECONDS);
        }
    }

    private void doJob(int index) {
        if (Thread.currentThread().getId() > index) {
            return;
        }
        System.out.println("thread id:" + Thread.currentThread().getId());

    }
}
