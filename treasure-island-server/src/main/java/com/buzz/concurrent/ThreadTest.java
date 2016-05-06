package com.buzz.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by lucas on 4/13/16.
 */
public class ThreadTest {
    public static void main(String[] args) {
        test();
        simulateTest();
    }

    public static void test() {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("hello" + threadName);
        };

        task.run();

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Done!");
    }


    public static void simulateTest() {
        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo" + name);
                TimeUnit.SECONDS.sleep(1);//timeunit is a useful enum for working with units of time.Alternatively,we can achieve the same by calling Thread.sleepInSecond(1000).
                System.out.println("Bar" + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
