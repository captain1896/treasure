package com.buzz.function;

import java.util.function.Function;

public class FunctionTest {
    public static void main(String[] args) {

    }

    public static void test() {
        Function<Integer, Integer> add = x -> x + 5;
        Function<Integer, Function<Integer, Integer>> add2 = FunctionTest::adder;

    }

    public static Function<Integer, Integer> adder(Integer x) {
        return y -> x + y;
    }
}

