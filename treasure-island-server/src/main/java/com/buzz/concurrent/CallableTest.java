package com.buzz.concurrent;

import java.util.concurrent.*;

public class CallableTest {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Callable<Integer> task = () -> {
            TimeUnit.SECONDS.sleep(1);
            return 123;
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);
        System.out.println("future done?" + future.isDone());

        try {
            Integer result = future.get();
            System.out.println("future done?" + future.isDone());
            System.out.println("result:" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Integer integer = future.get(1, TimeUnit.SECONDS);
            executor.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
