package com.buzz.atomic;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;

public class ConcurrentMapTest {
    public static void main(String[] args) {
        test();
        testMap();
    }

    public static void test() {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("foo2", "bar2");
        map.put("foo3", "bar3");
        map.put("foo4", "bar4");
        map.put("c3", "p0");
        map.forEach((k, v) ->
                        System.out.printf(String.format("%s=%s \n", k, v))
        );

        String value = map.putIfAbsent("c3", "virus");
        System.out.println(value);

        String value2 = map.getOrDefault("killer", "virus");
        System.out.println(value2);

        map.replaceAll((key, iValue) -> "c3".equals(key) ? "d2" : iValue);
        System.out.println(map.get("c3"));
        System.out.println(map.get("foo3"));

        map.compute("foo", (key, mValue) -> mValue + mValue);
        System.out.println(map.get("foo"));

        map.merge("foo", "boo", (oldVal, newVal) -> newVal + " was " + newVal);
        System.out.println(map.get("foo"));

        //This value can be decreased or increased by setting the following JVM parameter:
        // -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        System.out.println(ForkJoinPool.getCommonPoolParallelism());
    }


    public static void testMap() {

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        map.forEach(1, (key, value) -> System.out.printf("key=%s , value=%s ;thread: %s \n", key, value, Thread.currentThread().getName()));

        String result = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if ("foo".equals(key)) {
                return value;
            }
            return null;
        });
        System.out.println("Result:" + result);

        result = map.reduce(1, (key, value) -> {
            System.out.println("Transform:" + Thread.currentThread().getName());
            return key + "=" + value;
        }, (s1, s2) -> {
            System.out.println("Reduce:" + Thread.currentThread().getName());
            return s1 + "," + s2;
        });

        System.out.println("Result:" + result);


    }
}
