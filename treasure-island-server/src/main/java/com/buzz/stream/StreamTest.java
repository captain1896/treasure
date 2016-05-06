package com.buzz.stream;

import com.buzz.concurrent.ConcurrentUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {
    public static void main(String[] args) {
        test();
        testStream();
        barTest();
        reduceTest();
        mapAndReduce();
    }

    public static void test() {
        List<String> strings = Arrays.asList("Alice", "Lucas", "Delta", "Mary");
        strings
                .stream()
                .filter(s -> s.startsWith("A") || s.startsWith("L"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void testStream() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.submit(() -> {
            System.out.println("do something concurrently...");
            List<String> strings = Arrays.asList("AsList", "AsSet", "AsMap");
            strings.stream().collect(Collectors.groupingBy(String::length));
            strings.stream().map(String::toUpperCase).forEach(System.out::println);
            strings.stream().findFirst().ifPresent(System.out::println);
            strings.stream().forEach(System.out::println);
        });

        executor.submit(() -> {
            System.out.println("do something else again");
        });

        ConcurrentUtils.sleep(5);
        ConcurrentUtils.stop(executor);

    }

    public static void barTest() {
        List<Foo> foos = new ArrayList<>();
        IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));
        foos.stream().forEach(f -> IntStream.range(1, 4).forEach(i -> f.bars
                .add(new Bar("Bar" + i))));

        foos.stream().forEach(System.out::println);
        foos.stream().flatMap(foo -> foo.bars.stream()).forEach(System.out::println);
    }

    public static void reduceTest() {
        System.out.println("---reduce test----");
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alice", 25));
        persons.add(new Person("Lucas", 28));
        persons.add(new Person("Bob", 25));
        persons.add(new Person("Tin", 25));
        persons.add(new Person("Joey", 7));

        persons.stream().reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2).ifPresent(System.out::println);

        Integer intStream = persons.stream().reduce(0, (sum, p) -> sum = sum + p.getAge(), (sum1, sum2) -> sum1 + sum2);

        Integer ageSum = persons.parallelStream().reduce(0,
                (sum, p) -> {
                    System.out.format("accumulator: sum= %s; person=%s\n", sum, p);
                    return sum = sum + p.getAge();
                },
                (sum1, sum2) -> {
                    System.out.format("combiner:sum1= %s; sum2=%s\n", sum1, sum2);
                    return sum1 + sum2;
                });

        System.out.println("intStream=" + intStream + ",ageSum=" + ageSum);
    }

    public static void mapAndReduce() {
        Arrays.asList("a1", "a2", "b1", "c1", "c2").parallelStream().filter(s -> {
            System.out.format("filter: %s [%s]\n", s,
                    Thread.currentThread().getName());
            return true;
        }).map(s -> {
            System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
            return s.toUpperCase();
        }).sorted((s1, s2) -> {
            System.out.format("sort:%s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
            return s1.compareTo(s2);
        }).forEach(s -> System.out.format("ForEach: %s [%s] \n", s, Thread.currentThread().getName()));
    }
}
