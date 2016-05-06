package com.buzz.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorsTest {
    public static void main(String[] args) {
        test();
        testScheduledAtFixedRate();//without shutdown
        testScheduleWithFixedRate();
    }

    public static void test() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("test Scheduling:" + System.nanoTime());
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

        sleepInSecond(1);//任务没有执行,

        long remainingDelay = future.getDelay(TimeUnit.MICROSECONDS);
        System.out.println(String.format("Remaining Delay:%s ms", remainingDelay));
        executor.shutdownNow();
    }

    public static void testScheduledAtFixedRate() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("testScheduledAtFixedRate Scheduling:" + LocalDateTime.now().getSecond());
        int initialDelay = 5;
        int period = 1;
        System.out.println("schedule service starts:" + LocalDateTime.now().getSecond());
        executorService.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);

        sleepInSecond(20);
        executorService.shutdownNow();

    }

    /**
     * The difference is that the wait time period applies between the end of a task and the start of the next task
     * task1 finished,delay certain second ,then start to execute task2
     */
    public static void testScheduleWithFixedRate() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            sleepInSecond(2);
            System.out.println("testScheduleWithFixedRate Scheduling:" + System.nanoTime());
        };

        executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
        sleepInSecond(30);
        executor.shutdownNow();
    }

    public static void sleepInSecond(long sleepSeconds) {
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
