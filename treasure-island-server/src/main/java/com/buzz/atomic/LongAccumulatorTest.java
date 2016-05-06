package com.buzz.atomic;

import com.buzz.concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

public class LongAccumulatorTest {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        LongBinaryOperator operator = (x, y) -> 2 * x + y;
        LongAccumulator longAccumulator = new LongAccumulator(operator, 1L);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10).forEach(i -> {
            executor.submit(() -> longAccumulator.accumulate(i));
        });

        ConcurrentUtils.stop(executor);
        System.out.println(longAccumulator.get());
    }
}
