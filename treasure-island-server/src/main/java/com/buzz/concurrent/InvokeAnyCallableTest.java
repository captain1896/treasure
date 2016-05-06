package com.buzz.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAnyCallableTest {
    public static void main(String[] args) {
        test();
    }

    public static void test() {

        ExecutorService executorService = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 8),
                callable("task2", 9),
                callable("task3", 3)
        );

        try {
            String result = executorService.invokeAny(callables);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
