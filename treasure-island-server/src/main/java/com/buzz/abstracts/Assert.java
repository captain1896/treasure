package com.buzz.abstracts;

public abstract class Assert {

    public static void notNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("[Assertion failed] - object must not be null");
        }
    }

    public static void isTrue(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException("[Assertion failed] - this expression must be True");
        }
    }
}
