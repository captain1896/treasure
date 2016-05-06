package com.buzz.sugar;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.Supplier;

public class NullPointerExceptionTest {
    public static void main(String[] args) {
        nestTest();
        testWithHelper();

    }

    private static void nestTest() {
        Outer outer = new Outer();
        if (outer != null && outer.getNested() != null && outer.nested.getInner() != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.getFoo());
        }
    }


    public static void test() {

        Outer obj = new Outer();
        Nested nested = new Nested();
        Inner inner = new Inner();

        obj.nested = nested;
        nested.inner = inner;
        inner.foo = "it is a test";

        Optional.of(obj)
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getFoo)
                .ifPresent(System.out::println);
    }

    public static void testWithHelper() {
        Outer outer = new Outer();
        resolve(() -> outer.getNested().getInner().getFoo()).ifPresent(System.out::println);
    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }

    }
}
