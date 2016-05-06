package com.buzz.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SynchronizedTest {
    private static int count = 0;

    private static void increment() {
        count = count + 1;
    }

    private static synchronized void incrementSync() {
        count = count + 1;
    }

    private void incrementSyncBlock() {
        synchronized (this) {
            count = count + 1;
        }
    }

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000).forEach(i -> executor.submit(() -> {
            incrementSync();
        }));

        ConcurrentUtils.stop(executor);
        System.out.println(count);
    }


}
