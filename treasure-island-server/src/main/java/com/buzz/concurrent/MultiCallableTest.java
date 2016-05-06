package com.buzz.concurrent;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class MultiCallableTest {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3",
                () -> "task4"
        );

        ExecutorService executor = Executors.newWorkStealingPool();

        try {

            // 调用该方法的线程会阻塞,直到tasks全部执行完成(正常完成/异常退出)
            List<Future<String>> results = executor.invokeAll(callables);//batch submitting of multiple callables at once

            results.stream().map(future -> {
                String result = StringUtils.EMPTY;
                try {
                    result = future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return result;
            }).forEach(System.out::println);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();

    }
}
