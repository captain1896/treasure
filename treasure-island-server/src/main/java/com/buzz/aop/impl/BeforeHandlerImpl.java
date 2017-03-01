package com.buzz.aop.impl;


import com.buzz.aop.BeforeHandler;

import java.lang.reflect.Method;

public class BeforeHandlerImpl extends BeforeHandler {

    @Override
    public void handlerBefore(Object proxy, Method method, Object[] args) {
        System.out.println("handling before before actual method execution...");
    }
}
