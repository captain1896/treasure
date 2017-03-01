package com.buzz.aop.impl;

import com.buzz.aop.ThrowHandler;

import java.lang.reflect.Method;

public class ThrowHandlerImpl extends ThrowHandler {
    @Override
    public void handlerThrow(Object proxy, Method method, Object[] args) {
        System.out.println("Handling throw actual method execution......");
    }
}
