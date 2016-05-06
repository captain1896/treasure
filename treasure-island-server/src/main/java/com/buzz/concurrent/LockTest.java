package com.buzz.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

public class LockTest {
    private static ReentrantLock lock = new ReentrantLock();
    private static int count = 0;

    public static void main(String[] args) {
        //test();
        //testWriteRockTest();
        //testStampLock();
        //testTryOptimisticRead();
        testSemaphore();
    }

    private static void increment() {
        lock.lock();
    }

    public static void test() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ReentrantLock lock = new ReentrantLock();
        executor.submit(() -> {
            lock.lock();
            ConcurrentUtils.sleep(1);
            lock.unlock();
        });

        executor.submit(() -> {
            System.out.println("Locked:" + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired:" + locked);

        });

        ConcurrentUtils.stop(executor);

    }

    public static void testWriteRockTest() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        executor.submit(() -> {
            lock.writeLock().lock();
            ConcurrentUtils.sleep(1);
            map.put("foo", "bar");
            lock.writeLock().unlock();
        });

        Runnable readTask = () -> {
            lock.readLock().lock();
            System.out.println(map.get("foo"));
            ConcurrentUtils.sleep(1);
            lock.readLock().unlock();
        };

        executor.submit(readTask);
        executor.submit(readTask);

        ConcurrentUtils.stop(executor);

    }

    public static void testStampLock() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        StampedLock lock = new StampedLock();
        executor.submit(() -> {
            long stamp = lock.writeLock();
            ConcurrentUtils.sleep(1);
            map.put("foo", "bar");
            lock.unlockWrite(stamp);
        });

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            System.out.println(map.get("foo"));
            ConcurrentUtils.sleep(1);
            lock.unlockRead(stamp);
        };

        executor.submit(readTask);
        executor.submit(readTask);

        ConcurrentUtils.stop(executor);
    }

    public static void testTryOptimisticRead() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.tryOptimisticRead();

            System.out.println("Optimistic lock Valid:" + lock.validate(stamp));
            ConcurrentUtils.sleep(1);
            System.out.println("Optimistic lock Valid:" + lock.validate(stamp));
            ConcurrentUtils.sleep(2);
            System.out.println("Optimistic lock Valid:" + lock.validate(stamp));

            lock.unlock(stamp);

        });

        executor.submit(() -> {
            long stamp = lock.writeLock();
            System.out.println("Write lock acquired");
            ConcurrentUtils.sleep(2);
            lock.unlock(stamp);
            System.out.println("Write done");
        });

        executor.submit(() -> {
            long stamp = lock.readLock();

            if (count == 0) {
                stamp = lock.tryConvertToOptimisticRead(stamp);

                if (stamp == 0L) {
                    System.out.println("Could not convert to write lock");
                    stamp = lock.writeLock();
                }

                count = 23;
            }

            System.out.println(count);
            lock.unlock(stamp);
        });

        ConcurrentUtils.stop(executor);
    }


    public static void testSemaphore() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(5);
        Runnable longRunningTask = () -> {
            boolean permit = false;

            try {
                permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);

                if (permit) {
                    System.out.println("Semaphore acquired");
                    ConcurrentUtils.sleep(5);
                } else {
                    System.out.println("Could not acquire semaphore");
                }

            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                if (permit) {
                    semaphore.release();
                }
            }
        };

        IntStream.range(0, 10).forEach(i -> executor.submit(longRunningTask));
        ConcurrentUtils.stop(executor);

    }

}
