package com.buzz.aop;

import com.buzz.aop.AbstractHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ThrowHandler extends AbstractHandler {

    public abstract void handlerThrow(Object proxy, Method method, Object[] args);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Object result = method.invoke(getTargetObject(), args);
            return result;
        } catch (Exception e) {
            System.out.println("exception:" + e);
            handlerThrow(proxy, method, args);
            throw e.getCause();
        }
    }
}
