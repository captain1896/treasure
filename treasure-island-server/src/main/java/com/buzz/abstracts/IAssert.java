package com.buzz.abstracts;


public interface IAssert {
    void isNull(Object object) throws IllegalArgumentException;

    void isNull(Object object, String message) throws IllegalArgumentException;

    void notNull(Object object) throws IllegalArgumentException;

    void notNull(Object object, String message) throws IllegalArgumentException;

    void isTrue(boolean expression) throws IllegalArgumentException;

    void isTrue(boolean expression, String message) throws IllegalArgumentException;

    void hasText(String object) throws IllegalArgumentException;

    void hasText(String object, String message) throws IllegalArgumentException;

    void hasLength(String object) throws IllegalArgumentException;

    void hasLength(String object, String message) throws IllegalArgumentException;

    void isInstanceOf(Class<?> clazz, Object obj) throws IllegalArgumentException;

    void isInstanceOf(Class<?> clazz, Object obj, String message) throws IllegalArgumentException;
}
