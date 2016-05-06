package com.buzz.atomic;

import com.buzz.concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicTest {
    private static Integer integer = new Integer(0);

    public static void main(String[] args) {
        test();
        testAndGet();
    }

    public static void test() {
        AtomicInteger atomicInt = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        IntStream.range(0, 1000).forEach(i -> {
                    executor.submit(atomicInt::incrementAndGet);
                    executor.submit(AtomicTest::increment);
                }
        );

        ConcurrentUtils.stop(executor);
        System.out.println(atomicInt.get());
        System.out.println(integer);
    }


    public static void testAndGet() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(9);
        IntStream.range(0, 1000).forEach(i -> {
            Runnable task = () -> atomicInteger.updateAndGet(n -> n + 2);
            executor.submit(task);
        });


        System.out.println(atomicInteger.get());

        atomicInteger.set(0);//init

        System.out.println("--accumulate test--");
        IntStream.range(0, 1000).forEach(i -> {
            Runnable task = () -> atomicInteger.accumulateAndGet(i, (n, m) -> n + m);
            executor.submit(task);
        });

        ConcurrentUtils.stop(executor);
        System.out.println(atomicInteger.get());
    }

    public static void increment() {
        integer = integer + 1;
    }
}
